package lk.rupavahini.PPUManagement.asset.commonAsset.controller;

import lk.rupavahini.PPUManagement.asset.employee.entity.Employee;
import lk.rupavahini.PPUManagement.asset.userManagement.entity.PasswordChange;
import lk.rupavahini.PPUManagement.asset.userManagement.entity.User;
import lk.rupavahini.PPUManagement.asset.userManagement.service.UserService;
import lk.rupavahini.PPUManagement.auth.entity.UserMgt;
import lk.rupavahini.PPUManagement.auth.service.UserMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class ProfileController {
    @Autowired
    private UserMgtService userMgtService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ProfileController(PasswordEncoder passwordEncoder) {

        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/profile")
    public String userProfile(Model model, Principal principal) {
        model.addAttribute("addStatus", true);
        Employee byUserName = userMgtService.findByUserName(principal.getName());
        if (byUserName == null) {
            model.addAttribute("employeeDetail", null);
        } else {
            model.addAttribute("employeeDetail", byUserName);
        }
        return "employee/employee-detail";
    }

    @GetMapping(value = "/passwordChange")
    public String passwordChangeForm(Model model) {
        model.addAttribute("pswChange", new PasswordChange());
        return "login/passwordChange";
    }

    @PostMapping(value = "/passwordChange")
    public String passwordChange(@Valid @ModelAttribute PasswordChange passwordChange,
                                 BindingResult result, RedirectAttributes redirectAttributes) {
//        UserMgt user =
//                userService.findById(userService.findByUserIdByUserName(SecurityContextHolder.getContext().getAuthentication().getName()));

        if (passwordEncoder.matches(passwordChange.getOldPassword(),
//                user.getPassword()
                ""
        ) && !result.hasErrors() && passwordChange.getNewPassword().equals(passwordChange.getNewPasswordConform())) {

//            user.setPassword(passwordChange.getNewPassword());
//            userService.persist(user);

            redirectAttributes.addFlashAttribute("message", "Congratulations .!! Success password is changed");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/home";

        }
        redirectAttributes.addFlashAttribute("message", "Failed");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        return "redirect:/passwordChange";

    }
}
