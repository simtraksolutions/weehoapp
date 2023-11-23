//package com.example.myapplication;
//import android.os.Message;
////import android.se.omapi.Session;
//
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import javax.mail.Session;
//import java.net.Authenticator;
//import java.net.PasswordAuthentication;
//import java.util.Properties;
//public class EmailSender {
//    public static void sendEmail(String recipient, String subject, String messageBody) {
//        final String senderEmail = "your_email@example.com"; // Your email address
//        final String senderPassword = "your_email_password"; // Your email password
//
//        // Mail properties configuration
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.example.com"); // Your SMTP host
//        props.put("mail.smtp.port", "587"); // Your SMTP port
//
//        // Create a session with the sender's email and password
//        javax.mail.Session session = javax.mail.Session.getInstance(props,
//                new Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(senderEmail, senderPassword);
//                    }
//                });
//
//        try {
//            // Create a MimeMessage object
//            MimeMessage mimeMessage = new MimeMessage(session); // Assuming 'session' is a valid Session object
//
//// Pass MimeMessage as a Message object if required
//            Message message = (Message) mimeMessage;
//
//            // Set From: header field
//            message.setFrom(new InternetAddress(senderEmail));
//
//            // Set To: header field
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
//
//            // Set Subject: header field
//            message.setSubject(subject);
//
//            // Set the actual message
//            message.setText(messageBody);
//
//            // Send message
//            Transport.send(message);
//
//            System.out.println("Email sent successfully!");
//
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
