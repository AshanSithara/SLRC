package lk.rupavahini.PPUManagement.asset.episode.controller;




import lk.rupavahini.PPUManagement.asset.commonAsset.model.Enum.Gender;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.Enum.Title;
import lk.rupavahini.PPUManagement.asset.employee.entity.Enum.Designation;
import lk.rupavahini.PPUManagement.asset.episode.entty.Episode;
import lk.rupavahini.PPUManagement.asset.episode.service.EpisodeService;
import lk.rupavahini.PPUManagement.auth.service.UserMgtService;
import lk.rupavahini.PPUManagement.util.service.MakeAutoGenerateNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/episode")
public class EpisodeController {
    private final EpisodeService episodeService;
    private final MakeAutoGenerateNumberService makeAutoGenerateNumberService;

    @Autowired
    private UserMgtService userMgtService;

    @Autowired
    public EpisodeController(EpisodeService episodeService, MakeAutoGenerateNumberService makeAutoGenerateNumberService) {
        this.episodeService = episodeService;
        this.makeAutoGenerateNumberService = makeAutoGenerateNumberService;
    }

    private String commonThings(Model model, Episode episode, Boolean addState) {

        model.addAttribute("episode", episode);
        model.addAttribute("addStatus", addState);
        return "episode/addEpisode";
    }

    @GetMapping
    public String findAll(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        model.addAttribute("role",userMgtService.usernamebyrole(username));
        model.addAttribute("episode", episodeService.findAll());
        return "episode/episode";
    }


    @GetMapping("/add")
    public String addForm(Model model) {
        return commonThings(model, new Episode(), true);
    }

    @PostMapping(value = {"/save", "/update"})
    public String persist(@Valid @ModelAttribute Episode episode, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            return commonThings(model, episode, true);
        }

        //if episode has id that episode is not a new episode
        if (episode.getId() == null) {
            //if there is not episode in db
            Episode DBSupplier = episodeService.lastEpisode();

            //get episode name
            if (episode.getProgrammeCode() != null) {
                //  emailService.sendEmail(episode.getEmail(), "Welcome Message", "Welcome to Kmart Super...");
            }

            //get Stdudio Location
            if (episode.getEpisodeNumber() != null) {
                //  emailService.sendEmail(episode.getEmail(), "Welcome Message", "Welcome to Kmart Super...");
            }

            if (episode.getId() == null) {
                //if there is not episode in db
                Episode DBepisode = episodeService.lastEpisode();

                if (DBepisode == null) {
                    //need to generate new one
                    episode.setCode("Ep" + makeAutoGenerateNumberService.numberAutoGen(null).toString());
                } else {
                    System.out.println("last episode not null");
                    //if there is episode in db need to get that episode's code and increase its value
                    String previousCode = DBepisode.getCode().substring(2);
                    episode.setCode("Ep" + makeAutoGenerateNumberService.numberAutoGen(previousCode).toString());
                }

            }
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        episode.setCreatedAt(LocalDateTime.now());
        episode.setUpdatedAt(LocalDateTime.now());
        episode.setUpdatedBy(username);
        episode.setCreatedBy(username);
        redirectAttributes.addFlashAttribute("episodeDetail",
                episodeService.persist(episode));
        return "redirect:/episode";
    }



    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        return commonThings(model, episodeService.findById(id), false);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        episodeService.delete(id);
        return "redirect:/episode";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Integer id, Model model) {
        model.addAttribute("episodeDetail", episodeService.findById(id));
        return "episode/episode-details";
    }
}
