package lk.rupavahini.PPUManagement.asset.programme.controller;


import lk.rupavahini.PPUManagement.asset.programme.entity.Programme;
import lk.rupavahini.PPUManagement.asset.programme.service.ProgrammeService;
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
@RequestMapping("/programme")
public class ProgrammeController {
    private final ProgrammeService programmeService;
    private final MakeAutoGenerateNumberService makeAutoGenerateNumberService;

    @Autowired
    private UserMgtService userMgtService;

    @Autowired
    public ProgrammeController(ProgrammeService programmeService, MakeAutoGenerateNumberService makeAutoGenerateNumberService) {
        this.programmeService = programmeService;
        this.makeAutoGenerateNumberService = makeAutoGenerateNumberService;
    }



    private String commonThings(Model model, Programme programme, Boolean addState) {
        model.addAttribute("programme", programme);
        model.addAttribute("addStatus", addState);
        return "programme/addProgramme";
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
        model.addAttribute("programme", programmeService.findAll());
        return "programme/programme";
    }


    @GetMapping("/add")
    public String addForm(Model model) {
        return commonThings(model, new Programme(), true);
    }

    @PostMapping(value = {"/save", "/update"})
    public String persist(@Valid @ModelAttribute Programme programme, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            return commonThings(model, programme, true);
        }

        //if programme has id that programme is not a new programme
        if (programme.getId() == null) {
            //if there is not programme in db
            Programme DBSupplier = programmeService.lastProgramme();

            if (DBSupplier == null) {
                //need to generate new one
                programme.setCode("SS" + makeAutoGenerateNumberService.numberAutoGen(null).toString());
            } else {
                System.out.println("last programme not null");
                //if there is programme in db need to get that programme's code and increase its value
                String previousCode = DBSupplier.getCode().substring(2);
                programme.setCode("SS" + makeAutoGenerateNumberService.numberAutoGen(previousCode).toString());
            }
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        programme.setCreatedAt(LocalDateTime.now());
        programme.setUpdatedAt(LocalDateTime.now());
        programme.setUpdatedBy(username);
        programme.setCreatedBy(username);
        redirectAttributes.addFlashAttribute("programmeDetail",
                programmeService.persist(programme));

        return "redirect:/programme";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        return commonThings(model, programmeService.findById(id), false);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        programmeService.delete(id);
        return "redirect:/programme";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Integer id, Model model) {
        model.addAttribute("programmeDetail", programmeService.findById(id));
        return "programme/programme-detail";
    }
<<<<<<< HEAD

=======
>>>>>>> 4609734 (Initial commit)
}
