package finance.dev.domain.config;

import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@TypeInfo(name = "EmailConfig", description = "이메일 설정 클래스")
@Configuration
public class EmailConfig {
    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.protocol}")
    private String protocol;

    @Value("${spring.mail.default-encoding}")
    private String encoding;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean auth;

    @Value("${spring.mail.properties.mail.smtp.ssl.enable}")
    private boolean ssl_enable;

    @Value("${spring.mail.properties.mail.smtp.ssl.trust}")
    private String ssl_trust;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private boolean starttls_enable;

    @Value("${spring.mail.properties.mail.smtp.starttls.required}")
    private boolean starttls_required;

    @Value("${spring.mail.properties.mail.smtp.timeout}")
    private int timeout;

    @Value("${spring.mail.properties.mail.smtp.socketFactory.class}")
    private String socketFactory_class;

    @Value("${spring.mail.properties.mail.smtp.socketFactory.fallback}")
    private boolean socketFactory_fallback;

    @Value("${spring.mail.properties.mail.smtp.socketFactory.port}")
    private int socketFactory_port;

    @Value("${spring.mail.properties.mail.debug}")
    private boolean debug;

    @MethodInfo(name = "getJavaMailSender", description = "JavaMailSender 객체를 반환합니다.")
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        javaMailSender.setDefaultEncoding(encoding);
        javaMailSender.setProtocol(protocol);
        javaMailSender.setJavaMailProperties(getMailProperties());

        return javaMailSender;
    }

    @MethodInfo(name = "getMailProperties", description = "JavaMailSender 객체의 Properties를 반환합니다.")
    private Properties getMailProperties() {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", auth);
        properties.put("mail.smtp.ssl.enable", ssl_enable);
        properties.put("mail.smtp.ssl.trust", ssl_trust);
        properties.put("mail.smtp.starttls.enable", starttls_enable);
        properties.put("mail.smtp.starttls.required", starttls_required);
        properties.put("mail.smtp.timeout", timeout);
        properties.put("mail.smtp.socketFactory.class", socketFactory_class);
        properties.put("mail.smtp.socketFactory.fallback", socketFactory_fallback);
        properties.put("mail.smtp.socketFactory.port", socketFactory_port);
        properties.put("mail.debug", debug);

        return properties;
    }
}
