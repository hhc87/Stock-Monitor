package com.stock.monitor.monitor;

import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.stock.monitor.email.dao.EmailRepository;
import com.stock.monitor.email.model.Email;
import com.stock.monitor.record.dao.RecordRepository;
import com.stock.monitor.record.model.Record;
import com.stock.monitor.stock.model.Stock;
import com.stock.monitor.util.EmailSender;
import com.stock.monitor.util.PrecentFormatUtil;
import com.stock.monitor.util.SplitUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author Andrew He
 * @Date: Created in 15:37 2018/4/26
 * @Description:
 * @Modified by:
 */
@Component
@EnableScheduling
public class StockMonitor {

    @Value("${source.url}")
    private String sourceUrl;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private EmailRepository emailRepository;

    /**
     * 定时请求新浪股票接口，获取股票信息并计算
     *
     * @throws HttpProcessException
     * @throws MessagingException
     * @throws ParseException
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void monitorStock() throws HttpProcessException, MessagingException, ParseException {

        String resp = com.arronlong.httpclientutil.HttpClientUtil.get(HttpConfig.custom().url(sourceUrl));

        List<String> firstList = SplitUtil.splitString(resp, "\"");
        List<String> secondList = SplitUtil.splitString(firstList.get(1), ",");

        float startPrice = Float.parseFloat(secondList.get(1));
        float recentPrice = Float.parseFloat(secondList.get(3));

        if (recentPrice > startPrice) {
            float up = (recentPrice - startPrice) / startPrice;
            if (up > 0.01) {
                String msg = "当前" + secondList.get(0) + "上涨 : " + PrecentFormatUtil.convertNumToPrecent(up);
                sendMsg(msg);
            }
        } else {
            float down = (startPrice - recentPrice) / startPrice;
            if (down > 0.01) {
                String msg = "当前" + secondList.get(0) + "下跌 : " + PrecentFormatUtil.convertNumToPrecent(down);
                sendMsg(msg);
            }
        }
    }

    /**
     * 时间判断，并向指定邮箱发送信息
     *
     * @param msg
     * @throws MessagingException
     * @throws ParseException
     */
    public void sendMsg(String msg) throws MessagingException, ParseException {
        // 时间区间判断
        if (dateAndTimeCheck()) {
            Record recentRecord = null;
            Sort sort = new Sort(Sort.Direction.DESC, "id");
            List<Record> recordList = recordRepository.findAll(sort);
            if (recordList.size() > 0) {
                recentRecord = recordList.get(0);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String nowDate = dateFormat.format(new Date());

                if (dateFormat.parse(recentRecord.getSendTime()).getTime() + 60 * 60 * 1000 < dateFormat.parse(nowDate).getTime()) {
                    // 向所有邮箱发送信息
                    List<Email> emailList = emailRepository.findAll();
                    if (emailList.size() > 0) {
                        for (Email email : emailList) {
                            Record record = new Record();
                            record.setSendTime(nowDate);
                            record.setAimAddress(email.getEmailAddress());
                            recordRepository.save(record);
                            emailSender.sendEmail(msg, email.getEmailAddress());
                        }
                    }

                } else {
                    System.out.println("桥豆麻袋");
                }

            } else {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String nowDate = dateFormat.format(new Date());
                // 向所有邮箱发送信息
                List<Email> emailList = emailRepository.findAll();
                if (emailList.size() > 0) {
                    for (Email email : emailList) {
                        Record record = new Record();
                        record.setSendTime(nowDate);
                        record.setAimAddress(email.getEmailAddress());
                        recordRepository.save(record);
                        emailSender.sendEmail(msg, email.getEmailAddress());
                    }
                }
            }
        }
    }

    /**
     * 根据日期查询对应的星期
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDaysCode = {"0", "1", "2", "3", "4", "5", "6"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysCode[intWeek];
    }

    /**
     * 设置邮件发送区间，仅为周一至周五 9：00 - 15：00
     *
     * @return
     */
    public static Boolean dateAndTimeCheck() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        LocalTime checkTimeAM = LocalTime.parse("09:59", DateTimeFormatter.ISO_LOCAL_TIME);
        LocalTime checkTimePM = LocalTime.parse("15:01", DateTimeFormatter.ISO_LOCAL_TIME);

        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        if (!getWeekOfDate(date).equals("6") && !getWeekOfDate(date).equals("0")) {
            if (localTime.isAfter(checkTimeAM) && localTime.isBefore(checkTimePM)) {
                return true;
            }
        }
        return false;
    }

}
