package CSCI5308.GroupFormationTool.AccessControl;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class UserNotification implements IUserNotifications{

    private static final String EMAIL = "advsdcgrp17@gmail.com";
    private static final String PASSWORD = "grp17@2020";
    private static final String SUBJECT = "Credentials Details";
    private static final String AUTHPROP = "mail.smtp.auth";
    private static final String STARTTLSPROP = "mail.smtp.starttls.enable";
    private static final String HOSTPROP = "mail.smtp.host";
    private static final String PORTPROP = "mail.smtp.port";
    private static final String EMAILPROP = "smtp.gmail.com";
    private static final String PORT = "587";

    @Override
    public void sendUserLoginCredentials(IUser user, String rawPassword)
    {
        try
        {
            Properties props = new Properties();
            props.put(AUTHPROP, "true");
            props.put(STARTTLSPROP, "true");
            props.put(HOSTPROP, EMAILPROP);
            props.put(PORTPROP, PORT);

            Session session = Session.getInstance(props, new Authenticator()
            {
                protected PasswordAuthentication getPasswordAuthentication()
                {
                    return new PasswordAuthentication(EMAIL,PASSWORD);
                }
            });

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(EMAIL, false));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            msg.setSubject(SUBJECT);

            msg.setContent(
                    "Your Banner ID is: "+ user.getBanner() + "\n" +
                    "Your password is: " + rawPassword , "text/html");

            Transport.send(msg);
        }
        catch (MessagingException ex)
        {
            ex.printStackTrace();
        }
    }
}
