package com.stock.monitor.util;

import io.github.biezhi.ome.OhMyEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

import static io.github.biezhi.ome.OhMyEmail.SMTP_163;

/**
 * @Author Andrew He
 * @Date: Created in 16:39 2018/4/26
 * @Description:
 * @Modified by:
 */
@Component
public class EmailSender {

    @Value("${email.sender.address}")
    private String senderAddress;

    @Value("${email.sender.password}")
    private String senderPassword;

    public void emailInit() {
        OhMyEmail.config(SMTP_163(false), senderAddress, senderPassword);
    }

    public void sendEmail(String mailMessage, String aimMailAddress) throws MessagingException {
        emailInit();
        OhMyEmail.subject("股票信息")
                .from("股票信息提示系统")
                .to(aimMailAddress)
                .text(mailMessage)
                .send();
    }
}
