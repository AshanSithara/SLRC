/*
 *Time   :- 10:06 PM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.asset.broadcast.controller;

import lk.rupavahini.PPUManagement.asset.broadcast.entity.BroadcastDetails;
import lk.rupavahini.PPUManagement.asset.broadcast.model.BroadcastDetailModel;
import lk.rupavahini.PPUManagement.asset.broadcast.service.BroadcastDetailsService;
import lk.rupavahini.PPUManagement.asset.employee.service.EmployeeService;
import lk.rupavahini.PPUManagement.auth.service.UserMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@RestController
@Controller
@RequestMapping(value = "broadcast-detail")
public class BroadcastDetailsController {

    @Autowired
    private BroadcastDetailsService broadcastDetailsService;

    @Autowired
    private UserMgtService userMgtService;

    @Autowired
    private final EmployeeService employeeService;

    public BroadcastDetailsController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity addBroadcast(@RequestBody BroadcastDetailModel broadcastDetailModel) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        broadcastDetailModel.setCreatedBy(username);
        BroadcastDetails save = broadcastDetailsService.save(broadcastDetailModel);
        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        String datestring = df.format(save.getProgrammebegintime());
        String datestring2 = df.format(save.getProgrammeendtime());
        BroadcastDetailModel broadcastDetailModel1 = new BroadcastDetailModel(datestring,datestring2,save.getBroadcast().getId(),save.getClibrary().getId(),save.getId()+"",save.getStatus(),save.getClibrary().getCode());
        return new ResponseEntity(broadcastDetailModel1, HttpStatus.OK);
    }

    @GetMapping(value = "/date-check/{id}/{begin}/{end}")
    public ResponseEntity checkdate(@PathVariable Integer id,@PathVariable String begin, @PathVariable String end) {
        return new ResponseEntity(broadcastDetailsService.checkdate(id,begin+":00",end+":00"), HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public void delete(@PathVariable String id,HttpServletResponse response ) {
        broadcastDetailsService.delete(Integer.parseInt(id));
        try {
            response.sendRedirect("http://localhost:8084/broadcast");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping
    public String bPage(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        model.addAttribute("user", username);
        model.addAttribute("role", userMgtService.usernamebyrole(username));
        model.addAttribute("employees", employeeService.findAll());

        return "broadcast/broadcastdetails-manage";
    }
}
