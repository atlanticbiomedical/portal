package com.biomed.server;

import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EMailSender {

  private final String username;
  private final String password;

  @Inject
  public EMailSender(
      @Named("email.username") String username,
      @Named("email.password") String password) {

    this.username = username;
    this.password = password;
  }

  public void sendMessage(String destination, String messageSubject, String messageBody) {

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username + "@atlanticbiomedical.com", password);
      }
    });
    try {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(username + "@atlanticbiomedical.com"));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destination));
      message.setSubject(messageSubject);
      message.setText(messageBody);

      Transport.send(message);
    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }

  }
}
