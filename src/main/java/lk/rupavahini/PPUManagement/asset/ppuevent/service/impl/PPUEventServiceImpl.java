/*
 *Time   :- 12:01 PM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.asset.ppuevent.service.impl;

import lk.rupavahini.PPUManagement.asset.commonAsset.model.EventDataCollectModel;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.PpuHourReportModel;
import lk.rupavahini.PPUManagement.asset.event.entity.Event;
import lk.rupavahini.PPUManagement.asset.ppu.dao.PpuDao;
import lk.rupavahini.PPUManagement.asset.ppu.entity.Ppu;
import lk.rupavahini.PPUManagement.asset.ppuevent.entity.PPUEvent;
import lk.rupavahini.PPUManagement.asset.ppuevent.repo.PPUEventRepo;
import lk.rupavahini.PPUManagement.asset.ppuevent.service.PPUEventService;
import lk.rupavahini.PPUManagement.asset.programme.dao.ProgrammeDao;
import lk.rupavahini.PPUManagement.asset.programme.entity.Programme;
import lk.rupavahini.PPUManagement.asset.studio.dao.StudioDao;
import lk.rupavahini.PPUManagement.asset.studio.entity.Studio;
import lk.rupavahini.PPUManagement.auth.entity.UserMgt;
import lk.rupavahini.PPUManagement.auth.service.UserMgtService;
import lk.rupavahini.PPUManagement.email.service.EmailService;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PPUEventServiceImpl implements PPUEventService {

    @Autowired
    private PpuDao ppuDao;

    @Autowired
    private StudioDao studioDao;

    @Autowired
    private PPUEventRepo ppuEventRepo;

    @Autowired
    private UserMgtService userMgtService;

    @Autowired
    private EmailService emailService;


    @Override
    public List<PPUEvent> events() {
        return ppuEventRepo.findAll();
    }


    @Override
    public List<PPUEvent> findByDatesBetween(Date start, Date end) {
        return null;
    }

    @Override
    public List<PPUEvent> findByDateBetween(LocalDateTime start, LocalDateTime end) {
        return null;
    }

    @Override
    public PPUEvent save(EventDataCollectModel event) {
        PPUEvent event1 = new PPUEvent(event.getTitle(), event.getDescription(), event.getStart(), event.getFinish(), "0");
        Ppu ppu = ppuDao.getOne(event.getProgrammeid());
        Studio studio = studioDao.getOne(event.getStudioid());
        event1.setCreatedBy(event.getCreatedby());
        event1.setPpu(ppu);
        event1.setStudio(studio);
        PPUEvent save = ppuEventRepo.save(event1);
        List<UserMgt> alluser = userMgtService.getAlluser();
        String msg = "New PPU Event " + save.getTitle() + " is pending to Review. Please check and submit it.";
        for (UserMgt userMgt : alluser) {
            if (userMgt.getType().equalsIgnoreCase("Booking Officer")) {
                emailService.sendSMS("94" + userMgt.getMobilenumber(), msg);
            }
          /*  if (userMgt.getType().equalsIgnoreCase("Editor")) {
                emailService.sendSMS("94" + userMgt.getMobilenumber(), msg);
            }*/
        }
        return save;

    }

/*    @Override
      public PPUEvent save(EventDataCollectModel event) {
         PPUEvent event1 = new PPUEvent(event.getTitle(), event.getDescription(), event.getStart(), event.getFinish(), "0");
         Ppu ppu = ppuDao.getOne(event.getProgrammeid());
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

    }*/

    @Override
    public PPUEvent update(EventDataCollectModel event) {
        PPUEvent data_not_found = null;
        try {
            data_not_found = ppuEventRepo.findById(event.getId()).orElseThrow(() -> new Exception("Data not Found"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (data_not_found == null) {
            return null;
        } else {

            PPUEvent event1 = new PPUEvent(event.getTitle(), event.getDescription(), event.getStart(), event.getFinish(), "0");
            Ppu ppu = ppuDao.getOne(event.getProgrammeid());
            Studio studio = studioDao.getOne(event.getStudioid());
            event1.setCreatedBy(event.getCreatedby());
            event1.setId(data_not_found.getId());
            event1.setPpu(ppu);
            event1.setStudio(studio);
            PPUEvent save = ppuEventRepo.save(event1);
            String msg = "Updated PPU Event is pending to Review. Please check and submit it.";
            List<UserMgt> alluser = userMgtService.getAlluser();
            for (UserMgt userMgt : alluser) {
                if (userMgt.getType().equalsIgnoreCase("Editor")) {
                    emailService.sendSMS("94" + userMgt.getMobilenumber(), msg);
                }
            }
            return save;
        }
    }

    @Override
    public PPUEvent updatestatus(Long id, String status) {
        PPUEvent event = null;
        try {
            event = ppuEventRepo.findById(id).orElseThrow(() -> new Exception("Data not Found"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (event != null) {
            UserMgt userMgt = userMgtService.findbyusernameforUserMgt(event.getCreatedBy());
            String msg1 = "You Submitted PPU Event approved ";
            String msg2 = "You Submitted PPU Event Declare ";
            event.setStatus(status);
            if (event.getStatus().equalsIgnoreCase("1")) {
                event = ppuEventRepo.save(event);
                emailService.sendSMS("94" + userMgt.getMobilenumber(), msg1);
            } else {
                event = ppuEventRepo.save(event);
                emailService.sendSMS("94" + userMgt.getMobilenumber(), msg2);
            }
            return event;
        } else {
            return null;
        }
    }

    @Override
    public boolean delete(Long id) {
        PPUEvent data_not_found = null;
        try {
            data_not_found = ppuEventRepo.findById(id).orElseThrow(() -> new Exception("Data not Found"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (data_not_found == null) {
            return false;
        } else {
            ppuEventRepo.delete(data_not_found);
            return true;
        }
    }
    //PPU Day Report
    @Override
    public List<PpuHourReportModel> getppureport(String date) {
        return ppuEventRepo.ppuhour(date);
    }

    //PPU Hour report
    @Override
    public List<PpuHourReportModel> getppudayreport(String date){return ppuEventRepo.getppudayreport(date);}
}
