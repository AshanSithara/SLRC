/*
 *Time   :- 5:52 PM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.email.service;

import lk.rupavahini.PPUManagement.asset.employee.entity.Employee;
import lk.rupavahini.PPUManagement.email.entity.EmailMsg;
import lk.rupavahini.PPUManagement.email.model.EmailModel;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface EmailService {

    void sendEmployeeCreateEmail(EmailModel emailModel);

    void sendSponserCreateEmail(EmailModel emailModel);

    EmailMsg contactemployeeemail(EmailModel emailModel);

    EmailMsg contactsponsoremail(EmailModel emailModel);

    EmailMsg customEmailSend(EmailModel emailModel);

    @Cacheable
    public List<EmailMsg> findAll();

    public String sendSMS(String to,String msg);
}
