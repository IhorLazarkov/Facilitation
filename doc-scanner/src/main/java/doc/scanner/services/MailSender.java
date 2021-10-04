package doc.scanner.services;

import doc.scanner.utils.PropertyFileReader;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

public class MailSender {

    private String sentTo;
    private String title;
    private String msg;

    public MailSender(String sentTo, String title, String msg) {
        this.sentTo = sentTo;
        this.title = title;
        this.msg = msg;
    }

    public void send() {
        new MailConfigurer().withMailSender(mimeMessage -> {
            try {
                mimeMessage.setText(msg);
                mimeMessage.setSubject(title);
                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(sentTo));
                mimeMessage.setFrom(new InternetAddress(PropertyFileReader.getInstance().getProperties().getProperty("auth.user")));

                Transport.send(mimeMessage);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
    }
}
