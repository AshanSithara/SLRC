/*
 *Time   :- 11:25 AM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.asset.ppuevent.controller;

import lk.rupavahini.PPUManagement.asset.commonAsset.model.*;
import lk.rupavahini.PPUManagement.asset.event.entity.Event;
import lk.rupavahini.PPUManagement.asset.event.service.EventService;
import lk.rupavahini.PPUManagement.asset.ppu.service.PpuService;
import lk.rupavahini.PPUManagement.asset.ppuevent.entity.PPUEvent;
import lk.rupavahini.PPUManagement.asset.ppuevent.model.PPUEventModel;
import lk.rupavahini.PPUManagement.asset.ppuevent.service.PPUEventService;
import lk.rupavahini.PPUManagement.asset.programme.service.ProgrammeService;
import lk.rupavahini.PPUManagement.asset.studio.service.StudioService;
import lk.rupavahini.PPUManagement.auth.service.UserMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RestController
@CrossOrigin
public class PPUEventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private StudioService studioService;

    @Autowired
    private UserMgtService userMgtService;

    @Autowired
    private ProgrammeService programmeService;

    @Autowired
    private PpuService ppuService;

    @Autowired
    private PPUEventService ppuEventService;

    @GetMapping(value = "/ppu-calendar-view")
    public ModelAndView viewCalendar() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        ModelAndView modelAndView = new ModelAndView("calendar/ppu-calendar-view");
        modelAndView.addObject("role",userMgtService.usernamebyrole(username) );
        return modelAndView;
    }




    @GetMapping(value = "/allppuevents")
    //create calendar model event list
    public List<PPUEventModel> allDetailsEvents() {

        List<PPUEvent> events = ppuEventService.events();
        List<PPUEventModel> eventModels = new ArrayList<>();
        for (PPUEvent event : events) {
            if (event.getStatus().equalsIgnoreCase("1")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = event.getStart().format(formatter);
                String formattedDateTime1 = event.getFinish().format(formatter);
                eventModels.add(new PPUEventModel(event.getId(), event.getTitle(), event.getDescription(), formattedDateTime, formattedDateTime1, event.getStatus(), event.getPpu().getId(), event.getStudio().getId()));
            }
        }
        return eventModels;

    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class BadDateFormatException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public BadDateFormatException(String dateString) {
            super(dateString);
        }
    }

    @RequestMapping(value = "/ppu-event/add", method = RequestMethod.POST)
    public ResponseEntity addEvent(@RequestBody EventDataCollectModel event) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        event.setCreatedby(username);
        PPUEvent created = ppuEventService.save(event);
        created.setStudio(null);
        created.setPpu(null);
        return new ResponseEntity(created, HttpStatus.OK);
    }

    @RequestMapping(value="/ppu-event/update", method=RequestMethod.POST)
    public ResponseEntity updateEvent(@RequestBody EventDataCollectModel event) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        event.setCreatedby(username);
        PPUEvent created = ppuEventService.update(event);
        created.setStudio(null);
        created.setPpu(null);
        return new ResponseEntity(created, HttpStatus.OK);
    }

    @RequestMapping(value="/ppu-event/delete/{id}", method=RequestMethod.POST)
    public ResponseEntity removeEvent(@PathVariable(value = "id") long id) {
        boolean delete = ppuEventService.delete(id);
        return new ResponseEntity(delete, HttpStatus.OK);
    }


    @RequestMapping(value="/ppu/value", method=RequestMethod.GET)
    public ResponseEntity getppu() {
        List<PpuModel> ppuModels = ppuService.getallPpu();
        return new ResponseEntity(ppuModels, HttpStatus.OK);
    }

}

