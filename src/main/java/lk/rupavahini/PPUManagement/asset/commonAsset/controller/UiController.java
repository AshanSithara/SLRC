package lk.rupavahini.PPUManagement.asset.commonAsset.controller;

<<<<<<< HEAD
import lk.rupavahini.PPUManagement.asset.commonAsset.model.ProfileModel;
import lk.rupavahini.PPUManagement.asset.employee.entity.Employee;
import lk.rupavahini.PPUManagement.asset.employee.service.EmployeeFilesService;
import lk.rupavahini.PPUManagement.asset.employee.service.EmployeeService;
import lk.rupavahini.PPUManagement.asset.event.entity.Event;
import lk.rupavahini.PPUManagement.asset.event.service.EventService;
import lk.rupavahini.PPUManagement.asset.ppuevent.entity.PPUEvent;
import lk.rupavahini.PPUManagement.asset.ppuevent.service.PPUEventService;
import lk.rupavahini.PPUManagement.asset.sponsor.entity.Sponsor;
import lk.rupavahini.PPUManagement.asset.sponsor.service.SponsorService;
import lk.rupavahini.PPUManagement.asset.userManagement.service.UserService;
import lk.rupavahini.PPUManagement.auth.entity.UserMgt;
import lk.rupavahini.PPUManagement.auth.service.UserMgtService;
=======
import lk.rupavahini.PPUManagement.asset.userManagement.service.UserService;
>>>>>>> 4609734 (Initial commit)
import lk.rupavahini.PPUManagement.util.service.DateTimeAgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestMapping;
=======
>>>>>>> 4609734 (Initial commit)
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
=======
import java.util.HashSet;
>>>>>>> 4609734 (Initial commit)

@Controller
public class UiController {

    private final UserService userService;
    private final DateTimeAgeService dateTimeAgeService;
<<<<<<< HEAD
    @Autowired
    private EmployeeFilesService employeeFilesService;
    @Autowired
    private UserMgtService userMgtService;
    @Autowired
    private SponsorService sponsorService;
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EventService eventService;

    @Autowired
    private PPUEventService ppuEventService;

=======
>>>>>>> 4609734 (Initial commit)

    @Autowired
    public UiController(UserService userService, DateTimeAgeService dateTimeAgeService) {
        this.userService = userService;
        this.dateTimeAgeService = dateTimeAgeService;
    }

<<<<<<< HEAD
    @GetMapping(value = "/error")
    public String errorpage() {
        return "error";
    }

    @GetMapping(value = "/approve")
    public String approve(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        model.addAttribute("user", username);
        model.addAttribute("role", userMgtService.usernamebyrole(username));
        return "approve/approve";
    }
    @GetMapping(value = "/resource")
    public String resource(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        model.addAttribute("user", username);
        model.addAttribute("role", userMgtService.usernamebyrole(username));
        return "resource/resource";
    }




    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        model.addAttribute("user", username);
        return "index";
    }

    @GetMapping(value = "user-profile")
    public String userprofile(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        ProfileModel profileModel = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        boolean result = true;
        UserMgt userMgt = userMgtService.findbyusernameforUserMgt(username);
        if (userMgt.getType().equalsIgnoreCase("Sponsor")) {
            result = false;
            Sponsor sponsorByUsername = sponsorService.findSponsorByUsername(username);

            profileModel = new ProfileModel(sponsorByUsername.getName(), sponsorByUsername.getUsername(), sponsorByUsername.getContactOne(), sponsorByUsername.getEmail(), sponsorByUsername.getAddress());
        } else if (userMgt.getType().equalsIgnoreCase("Admin")) {
            result = false;
            profileModel = new ProfileModel(userMgt.getUsername(), userMgt.getMobilenumber());
        } else {
            result = true;
            Employee employee = userMgtService.findByUserName(username);
            model.addAttribute("files", employeeFilesService.employeeFileDownloadLinks(employee));
            profileModel = new ProfileModel(employee.getName(), employee.getUsername(), employee.getNic(), employee.getMobileOne(), employee.getEmail(), employee.getAddress(), employee.getGender().getGender(), employee.getTitle().getTitle(), employee.getDesignation().getDesignation(), employee.getCivilStatus().getCivilStatus(), employee.getEmployeeStatus().getEmployeeStatus(), employee.getDateOfBirth().toString());
        }

        List<Event> events = eventService.events();
        List<Event> statusevent = new ArrayList<>();
        for (Event event : events) {
            if (event.getCreatedBy().equalsIgnoreCase(username)) {
                if (event.getStatus().equalsIgnoreCase("0")) {
                    event.setStatus("Pending");
                    statusevent.add(event);
                } else if (event.getStatus().equalsIgnoreCase("1")) {
                    event.setStatus("Approved");
                    statusevent.add(event);
                } else {
                    event.setStatus("Reject");
                    statusevent.add(event);
                }
            }
        }

        List<PPUEvent> ppuevents = ppuEventService.events();
        List<PPUEvent> ppustatusevent = new ArrayList<>();
        for (PPUEvent event : ppuevents) {
            if (event.getCreatedBy().equalsIgnoreCase(username)) {
                if (event.getStatus().equalsIgnoreCase("0")) {
                    event.setStatus("Pending");
                    ppustatusevent.add(event);
                } else if (event.getStatus().equalsIgnoreCase("1")) {
                    event.setStatus("Approved");
                    ppustatusevent.add(event);
                } else {
                    event.setStatus("Reject");
                    ppustatusevent.add(event);
                }
            }
        }
        model.addAttribute("data", ppustatusevent);
        model.addAttribute("events", statusevent);
        model.addAttribute("role", userMgtService.usernamebyrole(username));
        model.addAttribute("username", username);
        model.addAttribute("result", result);
        model.addAttribute("profile", profileModel);
        return "profile/user-profile";
    }

=======
    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        model.addAttribute("username",username);
        return "index";
    }

>>>>>>> 4609734 (Initial commit)
    @GetMapping(value = {"/home", "/mainWindow"})
    public String getHome(Model model) {
        //do some logic here if you want something to be done whenever
        /*User authUser = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        Set<Petition> petitionSet = new HashSet<>();
        minutePetitionService
                .findByEmployeeAndCreatedAtBetween(authUser.getEmployee(),
                        dateTimeAgeService
                                .dateTimeToLocalDateStartInDay(LocalDate.now()),
                        dateTimeAgeService
                                .dateTimeToLocalDateEndInDay(LocalDate.now())).forEach(
                minutePetition -> {
                    petitionSet.add(petitionService.findById(minutePetition.getPetition().getId()));
                });
        model.addAttribute("petitions", petitionSet.toArray());*/
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
<<<<<<< HEAD
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        model.addAttribute("user", username);
=======
        String username=null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        model.addAttribute("user",username);
>>>>>>> 4609734 (Initial commit)
        return "mainWindow";
    }

    @GetMapping(value = {"/login"})
    public ModelAndView getLogin(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (request.isRequestedSessionIdValid() && session != null) {
            session.invalidate();
        }
        handleLogOutResponse(response, request);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login/login"); // resources/template/login.html
        return modelAndView;
//        return "login/login";
    }

    private void handleLogOutResponse(HttpServletResponse response, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
<<<<<<< HEAD
        if (cookies != null) {
=======
        if (cookies!=null){
>>>>>>> 4609734 (Initial commit)
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(100);
                cookie.setValue(null);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }

    }

    @GetMapping(value = {"/login/error10"})
    public String getLogin10(Model model) {
        model.addAttribute("err", "You already entered wrong credential more than 10 times. \n Please meet the system" +
                " admin");
        return "login/login";
    }

    @GetMapping(value = {"/login/noUser"})
    public String getLoginNoUser(Model model) {
        model.addAttribute("err", "There is no user according to the user name. \n Please try again !!");
        return "login/login";
    }

    @GetMapping(value = {"/unicodeTamil"})
    public String getUnicodeTamil() {
        return "fragments/unicodeTamil";
    }

    @GetMapping(value = {"/unicodeSinhala"})
    public String getUnicodeSinhala() {
        return "fragments/unicodeSinhala";
    }
}
