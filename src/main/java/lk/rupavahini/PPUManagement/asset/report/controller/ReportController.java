/*
 *Time   :- 3:08 PM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */



package lk.rupavahini.PPUManagement.asset.report.controller;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.SearchEventAdvanceModel;
import lk.rupavahini.PPUManagement.asset.employee.service.EmployeeService;
import lk.rupavahini.PPUManagement.asset.event.service.EventService;
<<<<<<< HEAD
import lk.rupavahini.PPUManagement.asset.ppuevent.service.PPUEventService;
=======
>>>>>>> 4609734 (Initial commit)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Controller
@RestController
@RequestMapping(value = "/report")
@CrossOrigin
public class ReportController {

    @Autowired
    private EventService eventService;
    @Autowired
    private EmployeeService employeeService;

<<<<<<< HEAD
    @Autowired
    private PPUEventService ppuEventService;

=======
>>>>>>> 4609734 (Initial commit)
    @GetMapping(value = "/reports")
    public ModelAndView viewReports() {
        return new ModelAndView("report/reports");
    }

<<<<<<< HEAD
    //Advance Search
=======
>>>>>>> 4609734 (Initial commit)
    @GetMapping(value = "/event-report-view")
    public ModelAndView viewEventReport() {
        return new ModelAndView("report/event-report");
    }

    @GetMapping(value = "/employee-report-view")
    public ModelAndView viewEmployeeReport() {
        return new ModelAndView("report/employee-report");
    }

    @GetMapping(value = "/daybyhours-report-view")
    public ModelAndView viewdaybyhours() {
        return new ModelAndView("report/day-by-hours-report");
    }

    @GetMapping(value = "/event-by-hour-report")
    public ModelAndView vieweventbyhoursReport() {
        return new ModelAndView("report/event-by-hour-report");
    }
<<<<<<< HEAD

    @GetMapping(value = "/ppu-by-hour-report")
    public ModelAndView viewppubyhoursReport() {
        return new ModelAndView("report/ppu-report-view");
    }

    //ppu day report
    @GetMapping(value = "/ppu-by-day-report")
    public ModelAndView viewppubydaysReport() {
        return new ModelAndView("report/ppu-day-report");
    }



=======
>>>>>>> 4609734 (Initial commit)
    @GetMapping(value = "/hour-report/{date}")
    public ResponseEntity returnhourreport(@PathVariable(value = "date") String date) {
        Date date1= null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LocalDateTime startDateTime = LocalDateTime.ofInstant(date1.toInstant(),
                ZoneId.systemDefault());
        return new ResponseEntity(eventService.getreporthour(date),HttpStatus.OK);
    }

<<<<<<< HEAD
    // PPU Hour Report
    @GetMapping(value = "/ppu-hour-report/{date}")
    public ResponseEntity returnppuhour(@PathVariable(value = "date") String date) {
        Date date1= null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LocalDateTime startDateTime = LocalDateTime.ofInstant(date1.toInstant(),
                ZoneId.systemDefault());
        return new ResponseEntity(ppuEventService.getppureport(date),HttpStatus.OK);
    }




=======
>>>>>>> 4609734 (Initial commit)
    @GetMapping(value = "/daybyhour-report/{month}")
    public ResponseEntity returndaybyhourreport(@PathVariable(value = "month") String month) {
        month=month+"-01";
        return new ResponseEntity(eventService.returndaybyhourreport(month),HttpStatus.OK);
    }

<<<<<<< HEAD
    //PPU day report
    @GetMapping(value = "/ppu-day-report/{month}")
    public ResponseEntity returnppudayreport(@PathVariable(value = "month") String month) {
        month=month+"-01";
        return new ResponseEntity(ppuEventService.getppudayreport(month),HttpStatus.OK);
    }

=======
>>>>>>> 4609734 (Initial commit)
    @PostMapping(value = "/search/event/getdata")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity viewEventReport(@RequestParam String date, @RequestParam String begintimeto, @RequestParam String begintimefrom, @RequestParam String endtimeto, @RequestParam String endtimefrom) {
        Date date1= null;
        Date begintimetodate1= null;
        Date begintimefromdate1= null;
        Date endtimetodate1= null;
        Date endtimefromdate1= null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            begintimetodate1 = new SimpleDateFormat("HH:mm").parse(begintimeto);
            begintimefromdate1 = new SimpleDateFormat("HH:mm").parse(begintimefrom);
            endtimetodate1 = new SimpleDateFormat("HH:mm").parse(endtimeto);
            endtimefromdate1 = new SimpleDateFormat("HH:mm").parse(endtimefrom);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(eventService.getsearchdata(new SearchEventAdvanceModel(date1,begintimetodate1,begintimefromdate1,endtimetodate1,endtimefromdate1)), HttpStatus.OK);
    }

    @PostMapping(value = "/search/employee/getdata")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getemployeesearchdata(@RequestParam String searchcombo) {

        return new ResponseEntity(employeeService.getemployeesearchdata(Integer.parseInt(searchcombo)), HttpStatus.OK);
    }
}
