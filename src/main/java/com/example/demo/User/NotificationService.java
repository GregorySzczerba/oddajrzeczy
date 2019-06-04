package com.example.demo.User;

import com.example.demo.ConfirmationToken.ConfirmationToken;
import com.example.demo.ConfirmationToken.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationService {

    private JavaMailSender javaMailSender;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendNotification(User user) throws MailException {

        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("javaverifyspring@gmail.com");
        mail.setSubject("Dziękujemy za rejestrację");
        mail.setText("Właśnie zarejestrowałeś się na stronie OddajRzeczy.pl" + " Twoje dane to: " + "Imię: " + user.getName()
                + " nazwisko: " + user.getLastName() +
                "Aby potwierdzić rejestrację kliknij : " +
                "http://localhost:8080/confirm-account/" + confirmationToken.getConfirmationToken());

        javaMailSender.send(mail);
    }
}
