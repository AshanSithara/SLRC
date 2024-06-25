/*
 *Time   :- 6:42 PM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.email.controller;

<<<<<<< HEAD
import lk.rupavahini.PPUManagement.auth.service.UserMgtService;
=======
>>>>>>> 4609734 (Initial commit)
import lk.rupavahini.PPUManagement.email.model.EmailModel;
import lk.rupavahini.PPUManagement.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/contact")
@RestController
=======
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/contact")
>>>>>>> 4609734 (Initial commit)
@Controller
public class ContactController {

    @Autowired
    private EmailService emailService;
<<<<<<< HEAD

    @Autowired
    private UserMgtService userMgtService;
    @GetMapping
    public ModelAndView viewContact(){
        ModelAndView modelAndView=new ModelAndView("contact/contact");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        modelAndView.addObject("username", username);
        modelAndView.addObject("role", userMgtService.usernamebyrole(username));
=======
    @GetMapping
    public ModelAndView viewContact(){
        ModelAndView modelAndView=new ModelAndView("contact/contact");
>>>>>>> 4609734 (Initial commit)
        modelAndView.addObject("emails", emailService.findAll());
        return  modelAndView;
    }

<<<<<<< HEAD

=======
>>>>>>> 4609734 (Initial commit)
    @GetMapping(value = "/add")
    public ModelAndView viewContactAdd(){
        return new ModelAndView("contact/addcontact");

    }

    @PostMapping(value = "/employee/email")
    public ResponseEntity contactemployeeemail(@RequestBody EmailModel emailModel){
        return new ResponseEntity(emailService.contactemployeeemail(emailModel), HttpStatus.OK);

    }

    @PostMapping(value = "/sponsor/email")
    public ResponseEntity contactsponsoremail(@RequestBody EmailModel emailModel){
        return new ResponseEntity(emailService.contactsponsoremail(emailModel), HttpStatus.OK);
    }

    @GetMapping(value = "send-sms")
    public ResponseEntity sendsms(){
        return new ResponseEntity(emailService.sendSMS("94762102467","test"), HttpStatus.OK);
    }
}
