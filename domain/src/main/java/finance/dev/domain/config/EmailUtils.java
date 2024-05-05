package finance.dev.domain.config;

import finance.dev.common.annotation.MethodInfo;
import finance.dev.common.annotation.TypeInfo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.UnsupportedEncodingException;

@TypeInfo(name = "EmailUtils", description = "이메일 유틸 클래스")
public class EmailUtils {
    private final JavaMailSender javaMailSender;
    private final MimeMessage mimeMessage;
    private final MimeMessageHelper mimeMessageHelper;

    @MethodInfo(name = "setSubject", description = "이메일 제목을 설정합니다.")
    public void setSubject(String subject) throws Exception {
        try {
            mimeMessageHelper.setSubject(subject);
        } catch (MessagingException e) {
            throw new Exception("이메일 제목 설정에 실패했습니다.");
        }
    }

    @MethodInfo(name = "setText", description = "이메일 내용을 설정합니다.")
    public void setText(String htmlContent) throws Exception {
        try {
            mimeMessageHelper.setText(htmlContent, true);
        } catch (MessagingException e) {
            throw new Exception("이메일 내용 설정에 실패했습니다.");
        }
    }

    @MethodInfo(name = "setFrom", description = "이메일 발신자를 설정합니다.")
    public void setFrom() throws Exception {
        try {
            mimeMessageHelper.setFrom(new InternetAddress(
                    "pocj8ur4in@naver.com", "Finance Dev"));
        } catch (MessagingException e) {
            throw new Exception("이메일 발신자 설정에 실패했습니다.");
        }
    }

    @MethodInfo(name = "setTo", description = "이메일 수신자를 설정합니다.")
    public void setTo(String email) throws Exception {
        try {
            mimeMessageHelper.setTo(email);
        } catch (MessagingException e) {
            throw new Exception("이메일 수신자 설정에 실패했습니다.");
        }
    }

    @MethodInfo(name = "send", description = "이메일을 발송합니다.")
    public void send() throws Exception {
        try {
            javaMailSender.send(mimeMessage);
        } catch (MailSendException e) {
            throw new Exception("이메일 발송에 실패했습니다.");
        }
    }

    public EmailUtils(JavaMailSender javaMailSender) throws Exception {
        try {
            this.javaMailSender = javaMailSender;
            mimeMessage = this.javaMailSender.createMimeMessage();
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
        } catch (MessagingException e) {
            throw new Exception("이메일 발송 객체 생성에 실패했습니다.");
        }
    }
}
