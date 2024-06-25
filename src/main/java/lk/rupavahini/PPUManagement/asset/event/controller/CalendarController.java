package lk.rupavahini.PPUManagement.asset.event.controller;


import lk.rupavahini.PPUManagement.asset.commonAsset.model.EventDataCollectModel;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.EventModel;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.ProgrammeModel;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.StudioModel;
import lk.rupavahini.PPUManagement.asset.event.entity.Event;
import lk.rupavahini.PPUManagement.asset.event.service.EventService;
import lk.rupavahini.PPUManagement.asset.programme.entity.Programme;
import lk.rupavahini.PPUManagement.asset.programme.service.ProgrammeService;
import lk.rupavahini.PPUManagement.asset.studio.entity.Studio;
import lk.rupavahini.PPUManagement.asset.studio.service.StudioService;
import lk.rupavahini.PPUManagement.auth.service.UserMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
<<<<<<< HEAD
<<<<<<< HEAD
import org.springframework.ui.Model;
=======
>>>>>>> 4609734 (Initial commit)
=======
import org.springframework.ui.Model;
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RestController
@CrossOrigin
public class CalendarController {

    @Autowired
    private EventService eventService;

    @Autowired
    private StudioService studioService;

    @Autowired
    private UserMgtService userMgtService;

    @Autowired
    private ProgrammeService programmeService;

    @GetMapping(value = "/calendar-view")
    public ModelAndView viewCalendar() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
<<<<<<< HEAD
<<<<<<< HEAD
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
=======
        String username=null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
>>>>>>> 4609734 (Initial commit)
=======
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
        } else {
            username = principal.toString();
        }
        ModelAndView modelAndView = new ModelAndView("calendar/calendar");
<<<<<<< HEAD
<<<<<<< HEAD
        modelAndView.addObject("role", userMgtService.usernamebyrole(username));
=======
        modelAndView.addObject("role",userMgtService.usernamebyrole(username) );
>>>>>>> 4609734 (Initial commit)
=======
        modelAndView.addObject("role", userMgtService.usernamebyrole(username));
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
        return modelAndView;
    }


<<<<<<< HEAD
<<<<<<< HEAD
=======


>>>>>>> 4609734 (Initial commit)
=======
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
    @GetMapping(value = "/allevents")
    //create calendar model event list
    public List<EventModel> allEvents() {

        List<Event> events = eventService.events();
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
        List<EventModel> eventModels = new ArrayList<>();
        for (Event event : events) {
            if (event.getStatus().equalsIgnoreCase("1")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = event.getStart().format(formatter);

                String formattedDateTime1 = event.getFinish().format(formatter);

                eventModels.add(new EventModel(event.getId(), event.getTitle(), event.getDescription(), formattedDateTime, formattedDateTime1, event.getEventtype(), event.getProgramme().getId(), event.getStudio().getId()));

            }
<<<<<<< HEAD
=======
        List<EventModel> eventModels=new ArrayList<>();
        for (Event event:events){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = event.getStart().format(formatter);

            String formattedDateTime1 = event.getFinish().format(formatter);

            eventModels.add(new EventModel(event.getId(),event.getTitle(),event.getDescription(),formattedDateTime,formattedDateTime1,event.getEventtype(),event.getProgramme().getId(),event.getStudio().getId()));

>>>>>>> 4609734 (Initial commit)
=======
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
        }
        return eventModels;

    }

    @GetMapping(value = "/allevents/one")
    public List<EventModel> allEventsone() {

        List<Event> eventsone = eventService.eventsone();
<<<<<<< HEAD
<<<<<<< HEAD
        List<EventModel> eventModels = new ArrayList<>();
        for (Event event : eventsone) {
=======
        List<EventModel> eventModels=new ArrayList<>();
        for (Event event:eventsone){
>>>>>>> 4609734 (Initial commit)
=======
        List<EventModel> eventModels = new ArrayList<>();
        for (Event event : eventsone) {
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = event.getStart().format(formatter);

            String formattedDateTime1 = event.getFinish().format(formatter);

<<<<<<< HEAD
<<<<<<< HEAD
            eventModels.add(new EventModel(event.getId(), event.getTitle(), event.getDescription(), formattedDateTime, formattedDateTime1, event.getEventtype(), event.getProgramme().getId(), event.getStudio().getId()));

=======
            eventModels.add(new EventModel(event.getId(),event.getTitle(),event.getDescription(),formattedDateTime,formattedDateTime1,event.getEventtype(),event.getProgramme().getId(),event.getStudio().getId()));
>>>>>>> 4609734 (Initial commit)
=======
            eventModels.add(new EventModel(event.getId(), event.getTitle(), event.getDescription(), formattedDateTime, formattedDateTime1, event.getEventtype(), event.getProgramme().getId(), event.getStudio().getId()));

>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
        }
        return eventModels;
    }

    @GetMapping(value = "/allevents/bulk")
    public List<EventModel> allEventsbulk() {
        List<Event> eventsbulk = eventService.eventsbulk();
<<<<<<< HEAD
<<<<<<< HEAD
        List<EventModel> eventModels = new ArrayList<>();
        for (Event event : eventsbulk) {
=======
        List<EventModel> eventModels=new ArrayList<>();
        for (Event event:eventsbulk){
>>>>>>> 4609734 (Initial commit)
=======
        List<EventModel> eventModels = new ArrayList<>();
        for (Event event : eventsbulk) {
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = event.getStart().format(formatter);

            String formattedDateTime1 = event.getFinish().format(formatter);

<<<<<<< HEAD
<<<<<<< HEAD
            eventModels.add(new EventModel(event.getId(), event.getTitle(), event.getDescription(), formattedDateTime, formattedDateTime1, event.getEventtype(), event.getProgramme().getId(), event.getStudio().getId()));
=======
            eventModels.add(new EventModel(event.getId(),event.getTitle(),event.getDescription(),formattedDateTime,formattedDateTime1,event.getEventtype(),event.getProgramme().getId(),event.getStudio().getId()));
>>>>>>> 4609734 (Initial commit)
=======
            eventModels.add(new EventModel(event.getId(), event.getTitle(), event.getDescription(), formattedDateTime, formattedDateTime1, event.getEventtype(), event.getProgramme().getId(), event.getStudio().getId()));
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
        }
        return eventModels;

    }


    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public List<Event> getEventsInRange(@RequestParam(value = "start", required = true) String start,
                                        @RequestParam(value = "end", required = true) String end) {
        Date startDate = null;
        Date endDate = null;
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            startDate = inputDateFormat.parse(start);
        } catch (ParseException e) {
            throw new BadDateFormatException("bad start date: " + start);
        }

        try {
            endDate = inputDateFormat.parse(end);
        } catch (ParseException e) {
            throw new BadDateFormatException("bad end date: " + end);
        }

        LocalDateTime startDateTime = LocalDateTime.ofInstant(startDate.toInstant(),
                ZoneId.systemDefault());

        LocalDateTime endDateTime = LocalDateTime.ofInstant(endDate.toInstant(),
                ZoneId.systemDefault());

        return eventService.findByDateBetween(startDateTime, endDateTime);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class BadDateFormatException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public BadDateFormatException(String dateString) {
            super(dateString);
        }
    }

    @RequestMapping(value = "/event/add", method = RequestMethod.POST)
    public ResponseEntity addEvent(@RequestBody EventDataCollectModel event) {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        event.setCreatedby(username);
<<<<<<< HEAD
=======
>>>>>>> 4609734 (Initial commit)
=======
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
        Event created = eventService.save(event);
        created.setStudio(null);
        created.setProgramme(null);
        return new ResponseEntity(created, HttpStatus.OK);
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
    @RequestMapping(value = "/event/update", method = RequestMethod.POST)
    public ResponseEntity updateEvent(@RequestBody EventDataCollectModel event) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        event.setCreatedby(username);
<<<<<<< HEAD
=======
    @RequestMapping(value="/event/update", method=RequestMethod.POST)
    public ResponseEntity updateEvent(@RequestBody EventDataCollectModel event) {
>>>>>>> 4609734 (Initial commit)
=======
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
        Event created = eventService.update(event);
        created.setStudio(null);
        created.setProgramme(null);
        return new ResponseEntity(created, HttpStatus.OK);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    @RequestMapping(value = "/event/delete/{id}", method = RequestMethod.POST)
=======
    @RequestMapping(value="/event/delete/{id}", method=RequestMethod.POST)
>>>>>>> 4609734 (Initial commit)
=======
    @RequestMapping(value = "/event/delete/{id}", method = RequestMethod.POST)
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
    public ResponseEntity removeEvent(@PathVariable(value = "id") long id) {
        boolean delete = eventService.delete(id);
        return new ResponseEntity(delete, HttpStatus.OK);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    @RequestMapping(value = "/studio/value", method = RequestMethod.GET)
=======
    @RequestMapping(value="/studio/value", method=RequestMethod.GET)
>>>>>>> 4609734 (Initial commit)
=======
    @RequestMapping(value = "/studio/value", method = RequestMethod.GET)
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
    public ResponseEntity getStudio() {
        List<StudioModel> studios = studioService.getallStudio();
        return new ResponseEntity(studios, HttpStatus.OK);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    @RequestMapping(value = "/programme/value", method = RequestMethod.GET)
=======
    @RequestMapping(value="/programme/value", method=RequestMethod.GET)
>>>>>>> 4609734 (Initial commit)
=======
    @RequestMapping(value = "/programme/value", method = RequestMethod.GET)
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
    public ResponseEntity getProgramme() {
        List<ProgrammeModel> programmes = programmeService.getallProgramme();
        return new ResponseEntity(programmes, HttpStatus.OK);
    }

<<<<<<< HEAD
<<<<<<< HEAD



=======
>>>>>>> 4609734 (Initial commit)
=======



>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
}
