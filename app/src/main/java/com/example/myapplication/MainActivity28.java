package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MainActivity28 extends AppCompatActivity {
    Button button;
    private static final String CHANNEL_ID = "MY CHANNEL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main28);
        button = findViewById(R.id.button8);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.weeho, null);
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap largeIcon = bitmapDrawable.getBitmap();

                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification.Builder notification = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    notification = new Notification.Builder(MainActivity28.this)
                            .setLargeIcon(largeIcon)
                            .setSmallIcon(R.drawable.weeho)
                            .setContentText("you are succefully Book the party")
                            .setSubText("New msg jadeja")
                            .setChannelId(CHANNEL_ID);
                    nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "New Channel", NotificationManager.IMPORTANCE_HIGH));
//            .build();
                }else {
                    notification = new Notification.Builder(MainActivity28.this)
                            .setLargeIcon(largeIcon)
                            .setSmallIcon(R.drawable.weeho)
                            .setContentText("you are succefully Book the party")
                            .setSubText("New msg jadeja");
                }

                Notification notif = notification.build();
                nm.notify(1, notif);
                Toast.makeText(MainActivity28.this, "notification sended", Toast.LENGTH_SHORT).show();

//                String stringsendermail = "urvrajsinh.jadeja113724@marwadiuniversity.ac.in";
//                String stringreceivermail = "jadejaurvrajsinh@gmail.com";
//                String stringpasswordsendermail = "Bndc@761";
//                String stringhost = "smtp.gmail.com";
//                Properties properties = System.getProperties();
//                properties.put("mail.smtp.host", stringhost);
//                properties.put("mail.smtp.port", "465");
//                properties.put("mail.smtp.ssl.enable", "true");
//                properties.put("mail.smtp.auth", "true");
//
//                javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
//                    @Override
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(stringsendermail, stringpasswordsendermail);
//
//                    }
//                });
//
//                try {
//                    MimeMessage mimeMessage = new MimeMessage(session);
//                    mimeMessage.setFrom(new InternetAddress(stringsendermail));
//                    mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringreceivermail));
//                    mimeMessage.setSubject("Subject: Android App email");
//                    mimeMessage.setText("Programmer send you this mail");
//                    Transport.send(mimeMessage);
//                    Toast.makeText(MainActivity28.this, "email sended", Toast.LENGTH_SHORT).show();
//                } catch (MessagingException e) {
//                    throw new RuntimeException(e);
//                }
            }

        });
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

    }
}
