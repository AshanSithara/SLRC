/*
 *Time   :- 10:53 PM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.auth.controller;

import lk.rupavahini.PPUManagement.auth.entity.UserMgt;
import lk.rupavahini.PPUManagement.auth.service.UserMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RestController
@RequestMapping(value = "user")
public class UserManagementController {

    @Autowired
    private UserMgtService userMgtService;

    @GetMapping(value = "sign-up")
    public ModelAndView loadsignupview(){
        return new ModelAndView("login/sign-up");
    }

    @PostMapping(value = "create")
    public ResponseEntity createUser(@RequestBody UserMgt userMgt, BindingResult bindingResult, ModelMap modelMap) {
        if (userMgtService.isUserAlreadyPresent(userMgt)) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(userMgtService.addUser(userMgt), HttpStatus.OK);
    }
}
