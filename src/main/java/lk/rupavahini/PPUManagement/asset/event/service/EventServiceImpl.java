package lk.rupavahini.PPUManagement.asset.event.service;


<<<<<<< HEAD
import lk.rupavahini.PPUManagement.asset.commonAsset.model.*;
import lk.rupavahini.PPUManagement.asset.employee.entity.Employee;
=======
import lk.rupavahini.PPUManagement.asset.commonAsset.model.EventDataCollectModel;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.EventHourReportModel;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.EventReturnModel;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.SearchEventAdvanceModel;
>>>>>>> 4609734 (Initial commit)
import lk.rupavahini.PPUManagement.asset.event.dao.EventDAO;
import lk.rupavahini.PPUManagement.asset.event.entity.Event;
import lk.rupavahini.PPUManagement.asset.programme.dao.ProgrammeDao;
import lk.rupavahini.PPUManagement.asset.programme.entity.Programme;
import lk.rupavahini.PPUManagement.asset.studio.dao.StudioDao;
import lk.rupavahini.PPUManagement.asset.studio.entity.Studio;
<<<<<<< HEAD
import lk.rupavahini.PPUManagement.auth.entity.UserMgt;
import lk.rupavahini.PPUManagement.auth.service.UserMgtService;
import lk.rupavahini.PPUManagement.email.service.EmailService;
=======
>>>>>>> 4609734 (Initial commit)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private ProgrammeDao programmeDAO;

    @Autowired
    private StudioDao studioDAO;

<<<<<<< HEAD
    @Autowired
    private UserMgtService userMgtService;
    @Autowired
    private EmailService emailService;
=======
>>>>>>> 4609734 (Initial commit)


    public List<Event> events() {
        return eventDAO.findAll();
    }

    @Override
<<<<<<< HEAD
    public Event updatestatus(Long id, String status) {
        Event event = null;
        try {
            event = eventDAO.findById(id).orElseThrow(() -> new Exception("Data not Found"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (event == null) {
            return null;
        } else {
            UserMgt userMgt = userMgtService.findbyusernameforUserMgt(event.getCreatedBy());
            String msg1="You Submitted Event approved ";
            String msg2="You Submitted Event Declare ";
            event.setStatus(status);
            if (event.getStatus().equalsIgnoreCase("1")) {
                event= eventDAO.save(event);
                emailService.sendSMS("94"+userMgt.getMobilenumber(),msg1);
            } else {
                event = eventDAO.save(event);
                emailService.sendSMS("94"+userMgt.getMobilenumber(),msg2);
            }
            return event;
        }
    }

    @Override
=======
>>>>>>> 4609734 (Initial commit)
    public List<Event> eventsone() {
        return eventDAO.eventsone();
    }

    @Override
    public List<Event> eventsbulk() {
        return eventDAO.eventsbulk();
    }

    @Override
    public List<Event> findByDatesBetween(Date start, Date end) {
//        return eventDAO.findByDatesBetween(start,end);
        return null;
    }

    @Override
    public List<Event> findByDateBetween(LocalDateTime start, LocalDateTime end) {
<<<<<<< HEAD
        return eventDAO.findByDateBetween(start, end);
=======
        return eventDAO.findByDateBetween(start,end);
>>>>>>> 4609734 (Initial commit)
    }

    @Override
    public Event save(EventDataCollectModel event) {
<<<<<<< HEAD
        Event event1 = new Event(event.getId(), event.getTitle(), event.getDescription(), event.getStart(), event.getFinish(), event.getEventtype());
        Programme programme = programmeDAO.getOne(event.getProgrammeid());
        Studio studio = studioDAO.getOne(event.getStudioid());
        event1.setCreatedBy(event.getCreatedby());
        event1.setProgramme(programme);
        event1.setStatus("0");
        event1.setStudio(studio);
        List<UserMgt> alluser = userMgtService.getAlluser();
        String msg="New Event is pending to Review. Please check and submit it.";
        Event save = eventDAO.save(event1);
        for (UserMgt userMgt : alluser) {
            if (userMgt.getType().equalsIgnoreCase("Booking Officer")){
                emailService.sendSMS("94"+userMgt.getMobilenumber(),msg);
            }
        }
        return save;

=======
        Event event1=new Event(event.getId(),event.getTitle(),event.getDescription(),event.getStart(),event.getFinish(), event.getEventtype());
        Programme programme = programmeDAO.getOne(event.getProgrammeid());
        Studio studio = studioDAO.getOne(event.getStudioid());
        event1.setProgramme(programme);
        event1.setStudio(studio);
        return eventDAO.save(event1);
>>>>>>> 4609734 (Initial commit)
    }

    @Override
    public Event update(EventDataCollectModel event) {
<<<<<<< HEAD
        Event data_not_found = null;
=======
        Event data_not_found=null;
>>>>>>> 4609734 (Initial commit)
        try {
            data_not_found = eventDAO.findById(event.getId()).orElseThrow(() -> new Exception("Data not Found"));
        } catch (Exception e) {
            e.printStackTrace();
        }
<<<<<<< HEAD
        if (data_not_found == null) {
            return null;
        } else {
            event.setId(data_not_found.getId());
            Event event1 = new Event(event.getId(), event.getTitle(), event.getDescription(), event.getStart(), event.getFinish(), event.getEventtype());
=======
        if (data_not_found==null){
            return null;
        }else{
            event.setId(data_not_found.getId());
            Event event1=new Event(event.getId(),event.getTitle(),event.getDescription(),event.getStart(),event.getFinish(), event.getEventtype());
>>>>>>> 4609734 (Initial commit)
            Programme programme = programmeDAO.getOne(event.getProgrammeid());
            Studio studio = studioDAO.getOne(event.getStudioid());
            event1.setProgramme(programme);
            event1.setStudio(studio);
<<<<<<< HEAD
            event1.setStatus("0");
            List<UserMgt> alluser = userMgtService.getAlluser();
            String msg="Updated Event is pending to Review. Please check and submit it.";
            Event save = eventDAO.save(event1);
            for (UserMgt userMgt : alluser) {
                if (userMgt.getType().equalsIgnoreCase("Booking Officer")){
                    emailService.sendSMS("94"+userMgt.getMobilenumber(),msg);
                }
            }
            return save;
=======
            return eventDAO.save(event1);
>>>>>>> 4609734 (Initial commit)

        }
    }

    @Override
    public boolean delete(Long id) {
<<<<<<< HEAD
        Event data_not_found = null;
=======
        Event data_not_found=null;
>>>>>>> 4609734 (Initial commit)
        try {
            data_not_found = eventDAO.findById(id).orElseThrow(() -> new Exception("Data not Found"));
        } catch (Exception e) {
            e.printStackTrace();
        }
<<<<<<< HEAD
        if (data_not_found == null) {
            return false;
        } else {
            eventDAO.delete(data_not_found);
            return true;
=======
        if (data_not_found==null){
            return false;
        }else{
             eventDAO.delete(data_not_found);
             return true;
>>>>>>> 4609734 (Initial commit)
        }
    }

    @Override
    public List<EventReturnModel> getsearchdata(SearchEventAdvanceModel searchEventAdvanceModel) {
        List<EventReturnModel> getsearchdata = eventDAO.getsearchdata(
                searchEventAdvanceModel.getDate(),
                searchEventAdvanceModel.getBegintimeto(),
                searchEventAdvanceModel.getBegintimefrom(),
                searchEventAdvanceModel.getEndtimeto(),
                searchEventAdvanceModel.getEndtimefrom()
        );
<<<<<<< HEAD
        List<EventReturnModel> events = new ArrayList<>();
        for (EventReturnModel eventReturnModel : getsearchdata) {
            String action = " <button type=\"button\" id=" + eventReturnModel.getId() + " title=\"Delete Evrnt\" class=\"btn btn-secondary btn-warning\" onclick=\"deleteevent(" + eventReturnModel.getId() + ")\" \"><i class=\"icon ion-trash-a\"></i></button>\n" +
                    "                                    <button type=\"button\" id=" + eventReturnModel.getId() + " title=\"Edit Event\" onclick=\"updateEventLoadModel(" + eventReturnModel.getId() + ")\" class=\"btn btn-secondary btn-success\" \" ><i class=\"icon ion-more\"></i></button>\n" +
=======
        List<EventReturnModel> events=new ArrayList<>();
        for (EventReturnModel eventReturnModel:getsearchdata){
            String action=" <button type=\"button\" id="+eventReturnModel.getId()+" title=\"Delete Evrnt\" class=\"btn btn-secondary btn-warning\" onclick=\"deleteevent("+eventReturnModel.getId()+")\" \"><i class=\"icon ion-trash-a\"></i></button>\n" +
                    "                                    <button type=\"button\" id="+eventReturnModel.getId()+" title=\"Edit Event\" onclick=\"updateEventLoadModel("+eventReturnModel.getId()+")\" class=\"btn btn-secondary btn-success\" \" ><i class=\"icon ion-more\"></i></button>\n" +
>>>>>>> 4609734 (Initial commit)
                    "                                ";
            eventReturnModel.setAction(action);
            events.add(eventReturnModel);
        }
        return events;
    }

<<<<<<< HEAD
    //PPU hour Report
=======
>>>>>>> 4609734 (Initial commit)
    @Override
    public List<EventHourReportModel> getreporthour(String date) {
        return eventDAO.reporteventhour(date);
    }

<<<<<<< HEAD

=======
>>>>>>> 4609734 (Initial commit)
    @Override
    public List<EventHourReportModel> returndaybyhourreport(String date) {
        return eventDAO.returndaybyhourreport(date);
    }
}

