package lk.rupavahini.PPUManagement.asset.clibrary.controller;


import lk.rupavahini.PPUManagement.asset.clibrary.entity.Clibrary;
import lk.rupavahini.PPUManagement.asset.clibrary.service.ClibraryService;
<<<<<<< HEAD
import lk.rupavahini.PPUManagement.asset.commonAsset.model.ClibraryModel;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.ProgrammeModel;
import lk.rupavahini.PPUManagement.asset.event.entity.Event;
import lk.rupavahini.PPUManagement.asset.programme.service.ProgrammeService;
import lk.rupavahini.PPUManagement.auth.service.UserMgtService;
import lk.rupavahini.PPUManagement.email.model.EmailModel;
import lk.rupavahini.PPUManagement.util.service.MakeAutoGenerateNumberService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
=======
import lk.rupavahini.PPUManagement.auth.service.UserMgtService;
import lk.rupavahini.PPUManagement.util.interfaces.AbstractService;
import lk.rupavahini.PPUManagement.util.service.MakeAutoGenerateNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
>>>>>>> 4609734 (Initial commit)
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
=======
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
>>>>>>> 4609734 (Initial commit)

@Service
@RequestMapping("/clibrary")
public class ClibraryController {
    private final ClibraryService clibraryService;
<<<<<<< HEAD
    private final ProgrammeService programmeService;
    private final MakeAutoGenerateNumberService makeAutoGenerateNumberService;

    private final Path fileStorageLocation;

=======
    private final MakeAutoGenerateNumberService makeAutoGenerateNumberService;

>>>>>>> 4609734 (Initial commit)
    @Autowired
    private UserMgtService userMgtService;

    @Autowired
<<<<<<< HEAD
    public ClibraryController(ClibraryService clibraryService, ProgrammeService programmeService, MakeAutoGenerateNumberService makeAutoGenerateNumberService) {
        this.fileStorageLocation = Paths.get("D:\\jarfile\\Employee_report.pdf")
                .toAbsolutePath().normalize();
        this.clibraryService = clibraryService;
        this.programmeService = programmeService;
        this.makeAutoGenerateNumberService = makeAutoGenerateNumberService;
    }

    //PDF download file
    @GetMapping("/newDownload")/*
    public ResponseEntity<Resource> downloadFile(HttpServletRequest request) {

        String fileName = "";
        Resource resource = null;
        if(fileName !=null && !fileName.isEmpty()) {
            try {
                resource = loadFileAsResource(fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Try to determine file's content type
            String contentType = null;
            try {
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            } catch (IOException ex) {
                //logger.info("Could not determine file type.");
            }

            // Fallback to the default content type if type could not be determined

            if(contentType == null) {
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    //Report PDF
    public Resource loadFileAsResource(String fileName) throws Exception {

        try {

            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();

            Resource resource = new UrlResource(filePath.toUri());

            if(resource.exists()) {

                return resource;

            } else {

                throw new FileNotFoundException("File not found " + fileName);

            }

        } catch (MalformedURLException ex) {

            throw new FileNotFoundException("File not found " + fileName);

        }

    }
*/
=======
    public ClibraryController(ClibraryService clibraryService, MakeAutoGenerateNumberService makeAutoGenerateNumberService) {
        this.clibraryService = clibraryService;
        this.makeAutoGenerateNumberService = makeAutoGenerateNumberService;
    }

>>>>>>> 4609734 (Initial commit)
    private String commonThings(Model model, Clibrary clibrary, Boolean addState) {
        model.addAttribute("clibrary", clibrary);
        model.addAttribute("addStatus", addState);
        return "clibrary/addclibrary";
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
        model.addAttribute("clibrary", clibraryService.findAll());
        return "clibrary/clibrary";
    }


    @GetMapping("/add")
    public String addForm(Model model) {
        return commonThings(model, new Clibrary(), true);
    }

    @PostMapping(value = {"/save", "/update"})
    public String persist(@Valid @ModelAttribute Clibrary clibrary, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            return commonThings(model, clibrary, true);
        }

        //if clibrary has id that clibrary is not a new clibrary
        if (clibrary.getId() == null) {
            //if there is not clibrary in db
            Clibrary Clibarry = clibraryService.lastClibrary();

            //get Studio name
            if (clibrary.getProgrammeName() != null) {
                //  emailService.sendEmail(clibrary.getEmail(), "Welcome Message", "Welcome to Kmart Super...");
            }

            //get Studio Location
            if (clibrary.getEpisodeNumber() != null) {
                //  emailService.sendEmail(clibrary.getEmail(), "Welcome Message", "Welcome to Kmart Super...");
            }

            if (clibrary.getId() == null) {
                //if there is not clibrary in db
                Clibrary DBClibrary = clibraryService.lastClibrary();

                if (DBClibrary == null) {
                    //need to generate new one
                    clibrary.setCode("cas" + makeAutoGenerateNumberService.numberAutoGen(null).toString());
                } else {
                    System.out.println("last clibrary not null");
                    //if there is clibrary in db need to get that clibrary's code and increase its value
                    String previousCode = DBClibrary.getCode().substring(3);
                    clibrary.setCode("cas" + makeAutoGenerateNumberService.numberAutoGen(previousCode).toString());
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
<<<<<<< HEAD

        model.addAttribute("role", userMgtService.usernamebyrole(username));
        model.addAttribute("username", username);
=======
>>>>>>> 4609734 (Initial commit)
        clibrary.setCreatedAt(LocalDateTime.now());
        clibrary.setUpdatedAt(LocalDateTime.now());
        clibrary.setUpdatedBy(username);
        clibrary.setCreatedBy(username);
        redirectAttributes.addFlashAttribute("clibraryDetail",
                clibraryService.persist(clibrary));
        return "redirect:/clibrary";
    }



<<<<<<< HEAD


=======
>>>>>>> 4609734 (Initial commit)
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        return commonThings(model, clibraryService.findById(id), false);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        clibraryService.delete(id);
        return "redirect:/clibrary";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Integer id, Model model) {
        model.addAttribute("clibraryDetail", clibraryService.findById(id));
        return "clibrary/clibrary-detail";
    }
<<<<<<< HEAD

  /*  @RequestMapping(value = "/clibrary/value", method = RequestMethod.GET)
    public ResponseEntity getProgramme() {
        List<ProgrammeModel> programmes = programmeService.getallProgramme();
        return new ResponseEntity(programmes, HttpStatus.OK);
    }*/

    @GetMapping(value = "/all")
    public ResponseEntity findAll() {
        List<Clibrary> all = clibraryService.findAll();
        List<ClibraryModel> clibraries = new ArrayList<>();
        for (Clibrary clibrary:all){
            clibraries.add(new ClibraryModel(clibrary.getId(),clibrary.getProgrammeName(),clibrary.getEpisodeNumber(),clibrary.getCode()));
        }
        return new ResponseEntity(clibraries, HttpStatus.OK);
    }




=======
>>>>>>> 4609734 (Initial commit)
}
