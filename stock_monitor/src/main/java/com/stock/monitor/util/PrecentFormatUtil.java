package com.stock.monitor.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @Author Andrew He
 * @Date: Created in 17:40 2018/4/26
 * @Description:
 * @Modified by:
 */
public class PrecentFormatUtil {

    /**
     * 将数字转换为百分数至小数点后两位
     * @param number
     * @return
     */
    public static String convertNumToPrecent(float number) {

        DecimalFormat df = new DecimalFormat("0.00%");
        BigDecimal d=new BigDecimal(number);

        return df.format(d);
    }
}
