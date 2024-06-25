package lk.rupavahini.PPUManagement.asset.ppu.controller;




import lk.rupavahini.PPUManagement.asset.commonAsset.model.PpuModel;
import lk.rupavahini.PPUManagement.asset.ppu.entity.Ppu;
import lk.rupavahini.PPUManagement.asset.ppu.service.PpuService;
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
@RequestMapping("/ppu")
public class PpuController {
    private final PpuService ppuService;
    private final MakeAutoGenerateNumberService makeAutoGenerateNumberService;

    @Autowired
    private UserMgtService userMgtService;


    @Autowired
    public PpuController(PpuService ppuService, MakeAutoGenerateNumberService makeAutoGenerateNumberService) {
        this.ppuService = ppuService;
        this.makeAutoGenerateNumberService = makeAutoGenerateNumberService;
    }

    private String commonThings(Model model, Ppu ppu, Boolean addState) {
        model.addAttribute("ppu", ppu);
        model.addAttribute("addStatus", addState);
        return "ppu/addPpu";
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

        model.addAttribute("ppu", ppuService.findAll());
        return "ppu/ppu";
    }

    @GetMapping(value = "/all")
    public ResponseEntity findAll() {
        List<Ppu> all = ppuService.findAll();
        List<PpuModel> datalist=new ArrayList<>();
        for (Ppu p:all){
            datalist.add(new PpuModel(p.getId(),p.getPPUname()));

        }
        return new ResponseEntity(datalist, HttpStatus.OK);
    }


    @GetMapping("/add")
    public String addForm(Model model) {
        return commonThings(model, new Ppu(), true);
    }

    @PostMapping(value = {"/save", "/update"})
    public String persist(@Valid @ModelAttribute Ppu ppu, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            return commonThings(model, ppu, true);
        }

        //if studio has id that studio is not a new studio
        if (ppu.getId() == null) {
            //if there is not studio in db
            Ppu DBSupplier = ppuService.lastPpu();

            //get Studio name
            if (ppu.getPPUcode() != null) {
                //  emailService.sendEmail(studio.getEmail(), "Welcome Message", "Welcome to Kmart Super...");
            }

            //get Studio Location
            if (ppu.getPPUlocation() != null) {
                //  emailService.sendEmail(studio.getEmail(), "Welcome Message", "Welcome to Kmart Super...");
            }

            if (ppu.getId() == null) {
                //if there is not studio in db
                Ppu DBStudio = ppuService.lastPpu();

                if (DBStudio == null) {
                    //need to generate new one
                    ppu.setPPUcode("St" + makeAutoGenerateNumberService.numberAutoGen(null).toString());
                } else {
                    System.out.println("last studio not null");
                    //if there is studio in db need to get that studio's code and increase its value
                    String previousCode = DBStudio.getPPUcode().substring(2);
                    ppu.setPPUcode("St" + makeAutoGenerateNumberService.numberAutoGen(previousCode).toString());
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
        ppu.setCreatedAt(LocalDateTime.now());
        ppu.setUpdatedAt(LocalDateTime.now());
        ppu.setUpdatedBy(username);
        ppu.setCreatedBy(username);

        redirectAttributes.addFlashAttribute("ppuDetail",
                ppuService.persist(ppu));
        return "redirect:/ppu";
    }



    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        return commonThings(model, ppuService.findById(id), false);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        ppuService.delete(id);
        return "redirect:/ppu";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Integer id, Model model) {
        model.addAttribute("ppuDetail", ppuService.findById(id));
        return "ppu/ppu-detail";
    }
}
