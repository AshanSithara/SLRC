package lk.rupavahini.PPUManagement.asset.team.controller;

import lk.rupavahini.PPUManagement.asset.commonAsset.model.TeamModel;
import lk.rupavahini.PPUManagement.asset.team.entity.Team;
import lk.rupavahini.PPUManagement.asset.team.service.TeamService;
import lk.rupavahini.PPUManagement.auth.service.UserMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    private UserMgtService userMgtService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    /*
     * Send all team to font end
     * */
    @RequestMapping
    public String teamPage(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
<<<<<<< HEAD
<<<<<<< HEAD
        model.addAttribute("user", username);
        model.addAttribute("role", userMgtService.usernamebyrole(username));
=======
        model.addAttribute("role",userMgtService.usernamebyrole(username));
>>>>>>> 4609734 (Initial commit)
=======
        model.addAttribute("user", username);
        model.addAttribute("role", userMgtService.usernamebyrole(username));
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
        model.addAttribute("team", teamService.findAll());
        return "team/team";
    }

    /*
     * Send one-{find using id} team details to font-end view
     * */
    @GetMapping(value = "/{id}")
    public String teamView(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("team", teamService.findById(id));
        model.addAttribute("addStatus", false);
        return "team/addTeam";
    }

    /*
     * Send one-{find using id} team to font-end to edit
     * */
    @GetMapping(value = "/edit/{id}")
    public String editTeamFrom(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("team", teamService.findById(id));
        model.addAttribute("addStatus", false);
        return "team/addTeam";
    }

    /*
     * Send form view team to font-end to add new team
     * */
    @GetMapping(value = {"/add"})
    public String teamAddFrom(Model model) {
        model.addAttribute("addStatus", true);
        model.addAttribute("team", new Team());
        return "team/addTeam";
    }
    /*
     * New Working palace add and stored team edit using following method
     * */

    @PostMapping(value = {"/add", "/update"})
    public String addteam(@Valid @ModelAttribute Team team, BindingResult result, Model model
            , RedirectAttributes redirectAttributes) {

        if (result.hasErrors() && team.getId() == null) {
            model.addAttribute("addStatus", true);
            model.addAttribute("team", team);
            return "team/addTeam";
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        team.setCreatedAt(LocalDateTime.now());
        team.setUpdatedAt(LocalDateTime.now());
        team.setUpdatedBy(username);
        team.setCreatedBy(username);
        try {
            teamService.persist(team);
            return "redirect:/team";
        } catch (Exception e) {
            ObjectError error = new ObjectError("team",
                    "This team is already in the System <br/>System message -->" + e.toString());
            result.addError(error);
            model.addAttribute("addStatus", false);
            model.addAttribute("team", team);
            return "team/addteam";
        }

    }

    /*
     * delete team from database
     * */
    @GetMapping(value = "/remove/{id}")
    public String removeteam(@PathVariable("id") Integer id) {
        teamService.delete(id);
        return "redirect:/team";
    }

    /*
     * search team in the database
     * */
    @GetMapping(value = "/search")
    public String search(Model model, Team team) {
        model.addAttribute("team", teamService.search(team));
        return "team/team";
    }

    @GetMapping(value = "/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TeamModel> getallteamdata() {
        return new ResponseEntity(teamService.findAllnew(), HttpStatus.OK);
    }
}
