package finance.dev.domain.handler;

import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@TypeInfo(name = "EmailHandler", description = "이메일 핸들러 클래스")
@Component
public class EmailHandler {
    private final JavaMailSender javaMailSender;
    private final MimeMessage mimeMessage;
    private final MimeMessageHelper mimeMessageHelper;

    @MethodInfo(name = "setSubject", description = "이메일 제목을 설정합니다.")
    public void setSubject(String subject) throws MessagingException {
        mimeMessageHelper.setSubject(subject);
    }

    @MethodInfo(name = "setText", description = "이메일 내용을 설정합니다.")
    public void setText(String htmlContent) throws MessagingException {
        mimeMessageHelper.setText(htmlContent, true);
    }

    @MethodInfo(name = "setFrom", description = "이메일 발신자를 설정합니다.")
    public void setFrom(InternetAddress internetAddress) throws MessagingException {
        mimeMessageHelper.setFrom(internetAddress);
    }

    @MethodInfo(name = "setTo", description = "이메일 수신자를 설정합니다.")
    public void setTo(String email) throws MessagingException {
        mimeMessageHelper.setTo(email);
    }

    @MethodInfo(name = "send", description = "이메일을 발송합니다.")
    public void send() throws MailSendException {
        javaMailSender.send(mimeMessage);
    }

    public EmailHandler(JavaMailSender javaMailSender) throws MessagingException {
        this.javaMailSender = javaMailSender;
        mimeMessage = this.javaMailSender.createMimeMessage();
        mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
    }
}
