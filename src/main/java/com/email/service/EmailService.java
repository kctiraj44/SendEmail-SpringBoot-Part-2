package com.email.service;


import org.springframework.stereotype.Service;
import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Service
public class EmailService {


    public boolean sendEmail(String subject , String message, String to){

        boolean f =false;



        String from ="kcteelak@gmail.com";

        //Mail host variable
        String host ="smtp.gmail.com";

        //get the system properties
        Properties pro =System.getProperties();

        System.out.println("Properties: -"+pro);


        //setting imp info to properties object
        pro.put("mail.smtp.host", host);
        pro.put("mail.smtp.port", "465");
        pro.put("mail.smtp.ssl.enable", "true");
        pro.put("mail.smtp.auth", "true");


        //step1 : get the session objects
        Session session =  Session.getInstance(pro, new Authenticator() {


            protected PasswordAuthentication getPasswordAuthentication() {

                return new  PasswordAuthentication("kcteelak@gmail.com","myheart150");
            }

        });

        session.setDebug(true);



        // step 2 : compose the  message

        MimeMessage   m=new  MimeMessage(session);


        //from  email
        try {
            m.setFrom(from);

            //adding recipient
            m.addRecipient(Message.RecipientType.TO,  new InternetAddress(to));


            // adding subject to message
            m.setSubject(subject);

            //adding text to message
            m.setText(message);


            //Step 3 : send the messsgase using transport class
            Transport.send(m);
            System.out.println("Send Success.....");
            f=true;

        }
        catch(Exception e) {
            e.printStackTrace();
        }

return  f;


    }


}
