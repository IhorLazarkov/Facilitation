package doc.scanner.services;

import doc.scanner.utils.PropertyFileReader;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

class MailConfigurer {

    public void withMailSender(MailFunction<MimeMessage> f) {
        withSession(session -> {
            MimeMessage mimeMessage = new MimeMessage(session);
            f.apply(mimeMessage);
        });
    }

    private Properties getSystemProperties() {
        Properties properties = System.getProperties();

        Properties configProperties = PropertyFileReader.getInstance().getProperties();
        properties.put("mail.smtp.host", configProperties.getProperty("smtp.host"));
        properties.put("mail.smtp.port", configProperties.getProperty("smtp.port"));
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        return properties;
    }

    private void withSession(MailFunction<Session> f) {
        f.apply(Session.getInstance(getSystemProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        PropertyFileReader.getInstance().getProperties().getProperty("auth.user"),
                        PropertyFileReader.getInstance().getProperties().getProperty("auth.pwd"));
            }
        }));
    }

    @FunctionalInterface
    public interface MailFunction<A> {
        public void apply(A a);
    }
}
