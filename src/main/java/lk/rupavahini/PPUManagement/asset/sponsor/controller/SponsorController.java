package lk.rupavahini.PPUManagement.asset.sponsor.controller;


import lk.rupavahini.PPUManagement.asset.commonAsset.model.SponsorModel;
import lk.rupavahini.PPUManagement.asset.sponsor.entity.Sponsor;
import lk.rupavahini.PPUManagement.asset.sponsor.service.SponsorService;
import lk.rupavahini.PPUManagement.auth.entity.UserMgt;
import lk.rupavahini.PPUManagement.auth.service.UserMgtService;
import lk.rupavahini.PPUManagement.util.service.MakeAutoGenerateNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sponsor")
public class SponsorController {
    private final SponsorService sponsorService;
    @Autowired
    private UserMgtService userMgtService;
    private final MakeAutoGenerateNumberService makeAutoGenerateNumberService;

    @Autowired
    public SponsorController(SponsorService sponsorService, MakeAutoGenerateNumberService makeAutoGenerateNumberService) {
        this.sponsorService = sponsorService;
        this.makeAutoGenerateNumberService = makeAutoGenerateNumberService;
    }

    private String commonThings(Model model, Sponsor sponsor, Boolean addState) {
        model.addAttribute("sponsor", sponsor);
        model.addAttribute("addStatus", addState);
        return "sponsor/addSponsor";
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
        model.addAttribute("sponsor", sponsorService.findAll());
        return "sponsor/sponsor";
    }
    @GetMapping(value = "/all")
    public ResponseEntity findAll() {
        List<Sponsor> all = sponsorService.findAll();
        List<SponsorModel> datalist=new ArrayList<>();
        for (Sponsor s:all){
                datalist.add(new SponsorModel(s.getId(),s.getName()));
        }
        return new ResponseEntity(datalist, HttpStatus.OK);
    }


    @GetMapping("/add")
    public String addForm(Model model) {
        return commonThings(model, new Sponsor(), true);
    }

    @PostMapping(value = {"/save", "/update"})
    public String persist(@Valid @ModelAttribute Sponsor sponsor, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        
        if (bindingResult.hasErrors()) {
            return commonThings(model, sponsor, true);
        }

//        if (sponsor.getContactOne() != null) {
//            sponsor.setContactOne(makeAutoGenerateNumberService.phoneNumberLengthValidator(sponsor.getContactOne()));
//        }
//        if (sponsor.getContactTwo() != null) {
//            sponsor.setContactTwo(makeAutoGenerateNumberService.phoneNumberLengthValidator(sponsor.getContactTwo()));
//        }

        //if sponsor has id that sponsor is not a new sponsor
        if (sponsor.getId() == null) {
            //if there is not sponsor in db
            Sponsor DBSponsor = sponsorService.lastSponsor();

            if (DBSponsor == null) {
                //need to generate new one
                sponsor.setCode("SS" + makeAutoGenerateNumberService.numberAutoGen(null).toString());
            } else {
                System.out.println("last sponsor not null");
                //if there is sponsor in db need to get that sponsor's code and increase its value
                String previousCode = DBSponsor.getCode().substring(2);
                sponsor.setCode("SS" + makeAutoGenerateNumberService.numberAutoGen(previousCode).toString());
            }
            //send welcome message and email
            if (sponsor.getEmail() != null) {
                //  emailService.sendEmail(sponsor.getEmail(), "Welcome Message", "Welcome to Kmart Super...");
            }
        }
        sponsor.setCreatedAt(LocalDateTime.now());
        sponsor.setUpdatedAt(LocalDateTime.now());
        sponsor.setUpdatedBy(username);
        sponsor.setCreatedBy(username);
        redirectAttributes.addFlashAttribute("sponsorDetail",
<<<<<<< HEAD
                userMgtService.addSponsorUserMgt(sponsor,new UserMgt(sponsor.getUsername(),sponsor.getPassword(),"Sponsor","1",sponsor.getContactOne())));
=======
                userMgtService.addSponsorUserMgt(sponsor,new UserMgt(sponsor.getUsername(),sponsor.getPassword(),"Sponsor","1")));
>>>>>>> 4609734 (Initial commit)
//                sponsorService.persist(sponsor));
        return "redirect:/sponsor";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        return commonThings(model, sponsorService.findById(id), false);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        sponsorService.delete(id);
        return "redirect:/sponsor";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Integer id, Model model) {
        model.addAttribute("sponsorDetail", sponsorService.findById(id));
        return "sponsor/sponsor-detail";
    }
}
