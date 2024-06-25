package lk.rupavahini.PPUManagement.asset.studio.controller;




import lk.rupavahini.PPUManagement.asset.studio.entity.Studio;
import lk.rupavahini.PPUManagement.asset.studio.service.StudioService;
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
@RequestMapping("/studio")
public class StudioController {
    private final StudioService studioService;
    private final MakeAutoGenerateNumberService makeAutoGenerateNumberService;

    @Autowired
    private UserMgtService userMgtService;

    @Autowired
    public StudioController(StudioService studioService, MakeAutoGenerateNumberService makeAutoGenerateNumberService) {
        this.studioService = studioService;
        this.makeAutoGenerateNumberService = makeAutoGenerateNumberService;
    }

    private String commonThings(Model model, Studio studio, Boolean addState) {
        model.addAttribute("studio", studio);
        model.addAttribute("addStatus", addState);
        return "studio/addstudio";
    }

    @RequestMapping(name = "/",method = RequestMethod.GET)
    public String findAll(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        model.addAttribute("role",userMgtService.usernamebyrole(username));
        model.addAttribute("studio", studioService.findAll());
        return "studio/studio";
    }


    @GetMapping("/add")
    public String addForm(Model model) {
        return commonThings(model, new Studio(), true);
    }

    @PostMapping(value = {"/save", "/update"})
    public String persist(@Valid @ModelAttribute Studio studio, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            return commonThings(model, studio, true);
        }

        //if studio has id that studio is not a new studio
        if (studio.getId() == null) {
            //if there is not studio in db
            Studio DBSupplier = studioService.lastStudio();

            //get Studio name
            if (studio.getStudioName() != null) {
                //  emailService.sendEmail(studio.getEmail(), "Welcome Message", "Welcome to Kmart Super...");
            }

            //get Studio Location
            if (studio.getLocation() != null) {
                //  emailService.sendEmail(studio.getEmail(), "Welcome Message", "Welcome to Kmart Super...");
            }

            if (studio.getId() == null) {
                //if there is not studio in db
                Studio DBStudio = studioService.lastStudio();

                if (DBStudio == null) {
                    //need to generate new one
                    studio.setCode("St" + makeAutoGenerateNumberService.numberAutoGen(null).toString());
                } else {
                    System.out.println("last studio not null");
                    //if there is studio in db need to get that studio's code and increase its value
                    String previousCode = DBStudio.getCode().substring(2);
                    studio.setCode("St" + makeAutoGenerateNumberService.numberAutoGen(previousCode).toString());
                }

            }
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        studio.setCreatedAt(LocalDateTime.now());
        studio.setUpdatedAt(LocalDateTime.now());
        studio.setUpdatedBy(username);
        studio.setCreatedBy(username);
        redirectAttributes.addFlashAttribute("studioDetail",
                studioService.persist(studio));
        return "redirect:/studio";
    }



    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        return commonThings(model, studioService.findById(id), false);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        studioService.delete(id);
        return "redirect:/studio";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Integer id, Model model) {
        model.addAttribute("studioDetail", studioService.findById(id));
        return "studio/studio-detail";
    }
}
