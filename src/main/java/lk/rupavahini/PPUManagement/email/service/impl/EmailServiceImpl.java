/*
 *Time   :- 5:58 PM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.email.service.impl;

import lk.rupavahini.PPUManagement.asset.employee.dao.EmployeeDao;
import lk.rupavahini.PPUManagement.asset.employee.entity.Employee;
import lk.rupavahini.PPUManagement.asset.sponsor.dao.SponsorDao;
import lk.rupavahini.PPUManagement.asset.sponsor.entity.Sponsor;
import lk.rupavahini.PPUManagement.email.entity.EmailMsg;
import lk.rupavahini.PPUManagement.email.model.EmailModel;
import lk.rupavahini.PPUManagement.email.repo.EmailRepository;
import lk.rupavahini.PPUManagement.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private SponsorDao sponsorDao;

    @Autowired
    private JavaMailSender emailSender;
    static final String CONFIGSET = "ConfigSet";
    @Override
    public void sendEmployeeCreateEmail(EmailModel emailModel) {

        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);

        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress("slrcppum@gmail.com", "Sri Lanka Rupavahini (PVT) LTD"));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(emailModel.getEmail()));
            msg.setSubject("Here , Successfully Complete Your Employee Registration  ");
            msg.setContent("Thank you join with us "+emailModel.getName()+" . We inform you new update late .connect with us . stay safe !<br><br> Sri Lanka Rupavahini (PVT) LTD ","text/html");
            msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Transport transport=null;
        try {
            transport = session.getTransport();
            transport.connect("smtp.gmail.com", "slrcppum@gmail.com", "slrcppum123");
            transport.sendMessage(msg, msg.getAllRecipients());
            sendSMS("94"+emailModel.getMobilenumber(),"Thank you join with us "+emailModel.getName()+" . We inform you new update late .connect with us . stay safe ! Sri Lanka Rupavahini (PVT) LTD  ");
            emailRepository.save(new EmailMsg(emailModel.getEmail(),emailModel.getName(),"Here , Successfully Complete Your Employee Registration  ","Thank you join with us "+emailModel.getName()+" . We inform you new update late .connect with us . stay safe !<br><br> Sri Lanka Rupavahini (PVT) LTD ",emailModel.getMobilenumber(),1));

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }finally{
            try {
                transport.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void sendSponserCreateEmail(EmailModel emailModel) {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);

        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress("slrcppum@gmail.com", "Sri Lanka Rupavahini (PVT) LTD"));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(emailModel.getEmail()));
            msg.setSubject("Here , Successfully Complete Your Sponsor Registration  ");
            msg.setContent("Thank you join with us "+emailModel.getName()+" . We inform you new update late .connect with us . stay safe !<br><br> Sri Lanka Rupavahini (PVT) LTD ","text/html");
            msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Transport transport=null;
        try {
            transport = session.getTransport();
            transport.connect("smtp.gmail.com", "slrcppum@gmail.com", "slrcppum123");
            transport.sendMessage(msg, msg.getAllRecipients());
            sendSMS("94"+emailModel.getMobilenumber(),"Thank you join with us "+emailModel.getName()+" . We inform you new update late .connect with us . stay safe ! Sri Lanka Rupavahini (PVT) LTD  ");
            emailRepository.save(new EmailMsg(emailModel.getEmail(),emailModel.getName(),"Here , Successfully Complete Your Sponsor Registration  ","Thank you join with us "+emailModel.getName()+" . We inform you new update late .connect with us . stay safe !<br><br> Sri Lanka Rupavahini (PVT) LTD ",emailModel.getMobilenumber(),1));


        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }finally{
            try {
                transport.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public EmailMsg contactemployeeemail(EmailModel emailModel) {
        Employee employee=null;
        EmailMsg save=null;
        try {
            employee= employeeDao.findById(emailModel.getId()).orElseThrow(() -> new Exception("Data not Found"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (employee!=null){
            emailModel.setEmail(employee.getEmail());
            emailModel.setId(0);
            emailModel.setMobilenumber(employee.getMobileOne());
            emailModel.setName(employee.getName());
            save=customEmailSend(emailModel);
        }
        return save;
    }

    @Override
    public EmailMsg contactsponsoremail(EmailModel emailModel) {
        Sponsor sponsor=null;
        EmailMsg save=null;
        try {
            sponsor= sponsorDao.findById(emailModel.getId()).orElseThrow(() -> new Exception("Data not Found"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (sponsor!=null){
            emailModel.setEmail(sponsor.getEmail());
            emailModel.setId(0);
            emailModel.setMobilenumber(sponsor.getContactOne());
            emailModel.setName(sponsor.getName());
            save=customEmailSend(emailModel);
        }
        return save;
    }

    @Override
    public EmailMsg customEmailSend(EmailModel emailModel) {

        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);

        EmailMsg save=null;
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress("slrcppum@gmail.com", "Sri Lanka Rupavahini (PVT) LTD"));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(emailModel.getEmail()));
            msg.setSubject(emailModel.getSubject());
            msg.setContent(emailModel.getText(),"text/html");
            msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Transport transport=null;
        try {
            transport = session.getTransport();
            transport.connect("smtp.gmail.com", "slrcppum@gmail.com", "slrcppum123");
            transport.sendMessage(msg, msg.getAllRecipients());
            sendSMS("94"+emailModel.getMobilenumber(),emailModel.getText());
            save= emailRepository.save(new EmailMsg(emailModel.getEmail(), emailModel.getName(), emailModel.getSubject(), emailModel.getText(), emailModel.getMobilenumber(), 0));


        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }finally{
            try {
                transport.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        return save;

    }


    @Override
    public List<EmailMsg> findAll() {
        return emailRepository.findAll();
    }

    @Override
    public String sendSMS(String to, String msg) {
        final String uri = "https://app.notify.lk/api/v1/send?user_id=13305&api_key=6N8KIWl4jCGOkHxuKiTH&sender_id=NotifyDEMO&to="+to+"&message="+msg;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
    }
}
