/*
 *Time   :- 11:27 AM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.asset.broadcast.controller;

import lk.rupavahini.PPUManagement.asset.broadcast.entity.Broadcast;
import lk.rupavahini.PPUManagement.asset.broadcast.entity.BroadcastDetails;
import lk.rupavahini.PPUManagement.asset.broadcast.model.BroadcastModel;
import lk.rupavahini.PPUManagement.asset.broadcast.service.BroadcastDetailsService;
import lk.rupavahini.PPUManagement.asset.broadcast.service.BroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Controller
@RequestMapping(value = "broadcast")
public class BroadcastController {

    @Autowired
    private BroadcastService broadcastService;

    @Autowired
    private BroadcastDetailsService broadcastDetailsService;

    @GetMapping(value = "edit/{id}")
    public ModelAndView viewEdit(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView("broadcast/broadcast-edit");
        Broadcast findone = broadcastService.findone(id);
        modelAndView.addObject("broadcast",findone);
        return modelAndView;
    }
    @GetMapping
    public ModelAndView viewAddBroadcast() {
        ModelAndView modelAndView = new ModelAndView("broadcast/add-to-broadcast");
        List<Broadcast> findall = broadcastService.findall();
        List<Broadcast> broadcasts = new ArrayList<>();
        for (Broadcast broadcast : findall) {
            if (broadcast.getStatus().equalsIgnoreCase("0")) {
                broadcast.setStatusdata("Not Broadcast Yet");
                broadcasts.add(broadcast);
            } else if (broadcast.getStatus().equalsIgnoreCase("1")) {
                broadcast.setStatusdata("ON AIR");
                broadcasts.add(broadcast);
            } else if (broadcast.getStatus().equalsIgnoreCase("2")) {
                broadcast.setStatusdata("PAUSED");
                broadcasts.add(broadcast);
            } else if (broadcast.getStatus().equalsIgnoreCase("3")) {
                broadcast.setStatusdata("BROADCAST FINISH");
                broadcasts.add(broadcast);
            } else if (broadcast.getStatus().equalsIgnoreCase("4")) {
                broadcast.setStatusdata("BROADCAST EXPIRE");
                broadcasts.add(broadcast);
            }
        }
        modelAndView.addObject("broadcasts", broadcasts);
        return modelAndView;
    }

    @GetMapping(value = "manage/{id}")
    public ModelAndView viewBroadcastDetailsManage(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("broadcast/broadcastdetails-manage");
        Broadcast findone = broadcastService.findone(id);
        List<BroadcastDetails> findall = broadcastDetailsService.findall();
        List<BroadcastDetails> broadcastDetails = new ArrayList<>();
        for (BroadcastDetails broadcastDetails1 : findall) {
            if (broadcastDetails1.getBroadcast().getId().equals(findone.getId())) {
                if (broadcastDetails1.getStatus().equalsIgnoreCase("0")) {
                    broadcastDetails1.setStatus("Pending");
                    broadcastDetails.add(broadcastDetails1);
                } else if (broadcastDetails1.getStatus().equalsIgnoreCase("1")) {
                    broadcastDetails1.setStatus("ON AIR");
                    broadcastDetails.add(broadcastDetails1);
                } else if (broadcastDetails1.getStatus().equalsIgnoreCase("2")) {
                    broadcastDetails1.setStatus("Broadcasted");
                    broadcastDetails.add(broadcastDetails1);
                }
            }
        }

        modelAndView.addObject("broadcastdetails", broadcastDetails);
        modelAndView.addObject("broadcast", findone);
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ResponseEntity addBroadcast(@RequestBody BroadcastModel broadcastModel) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        broadcastModel.setCreatedBy(username);
        Broadcast save = broadcastService.save(broadcastModel);
        save.setBroadcastDetails(null);
        return new ResponseEntity(save, HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity updateBroadcast(@RequestBody BroadcastModel broadcastModel) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        broadcastModel.setCreatedBy(username);
        Broadcast save = broadcastService.update(broadcastModel);
        save.setBroadcastDetails(null);
        return new ResponseEntity(save, HttpStatus.OK);
    }

    @GetMapping(value = "/update-status/{id}/{status}")
    public ModelAndView updatestatus(@PathVariable Integer id, @PathVariable String status) {
        broadcastService.updatestatus(id, status);
        ModelAndView modelAndView = new ModelAndView("broadcast/add-to-broadcast");
        List<Broadcast> findall = broadcastService.findall();
        List<Broadcast> broadcasts = new ArrayList<>();
        for (Broadcast broadcast : findall) {
            if (broadcast.getStatus().equalsIgnoreCase("0")) {
                broadcast.setStatusdata("Not Broadcast Yet");
                broadcasts.add(broadcast);
            } else if (broadcast.getStatus().equalsIgnoreCase("1")) {
                broadcast.setStatusdata("ON AIR");
                broadcasts.add(broadcast);
            } else if (broadcast.getStatus().equalsIgnoreCase("2")) {
                broadcast.setStatusdata("PAUSED");
                broadcasts.add(broadcast);
            } else if (broadcast.getStatus().equalsIgnoreCase("3")) {
                broadcast.setStatusdata("BROADCAST FINISH");
                broadcasts.add(broadcast);
            } else if (broadcast.getStatus().equalsIgnoreCase("4")) {
                broadcast.setStatusdata("BROADCAST EXPIRE");
                broadcasts.add(broadcast);
            }
        }
        modelAndView.addObject("broadcasts", broadcasts);
        return modelAndView;
    }

    @GetMapping(value = "/date-check/{date}")
    public ResponseEntity checkdate(@PathVariable String date) {
        Date endDate = null;
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            endDate = inputDateFormat.parse(date);
        } catch (ParseException e) {
            throw new BadDateFormatException("bad start date: " + date);
        }
        return new ResponseEntity(broadcastService.findBroadcastByBroadcastdate(endDate), HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class BadDateFormatException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public BadDateFormatException(String dateString) {
            super(dateString);
        }
    }

    @GetMapping("/real-time-broadcast")
    public ModelAndView viewrealtimedataView() {
        ModelAndView modelAndView = new ModelAndView("broadcast/broadcast-real-time");
        List<Broadcast> findall = broadcastService.findall();
        Broadcast broadcast1 = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        for (Broadcast broadcast : findall) {
            String strDate = dateFormat.format(broadcast.getBroadcastdate());
            if (strDate.equalsIgnoreCase(dtf.format(now))) {
                broadcast1 = broadcast;
            }
        }

        if (broadcast1.getStatus().equalsIgnoreCase("0")) {
            broadcast1.setStatusdata("Not Broadcast Yet");
        } else if (broadcast1.getStatus().equalsIgnoreCase("1")) {
            broadcast1.setStatusdata("ON AIR");
        } else if (broadcast1.getStatus().equalsIgnoreCase("2")) {
            broadcast1.setStatusdata("PAUSED");
        } else if (broadcast1.getStatus().equalsIgnoreCase("3")) {
            broadcast1.setStatusdata("BROADCAST FINISH");
        } else if (broadcast1.getStatus().equalsIgnoreCase("4")) {
            broadcast1.setStatusdata("BROADCAST EXPIRE");
        }

        List<BroadcastDetails> findall1 = broadcastDetailsService.findall();
        List<BroadcastDetails> broadcastDetails1 = new ArrayList<>();
        for (BroadcastDetails broadcastDetails : findall1) {
            if (broadcast1.getId().equals(broadcastDetails.getBroadcast().getId())) {
                if (broadcastDetails.getStatus().equalsIgnoreCase("0")) {
                    broadcastDetails.setStatus("Pending");
                    broadcastDetails1.add(broadcastDetails);
                } else if (broadcastDetails.getStatus().equalsIgnoreCase("1")) {
                    broadcastDetails.setStatus("ON AIR");
                    broadcastDetails1.add(broadcastDetails);
                } else if (broadcastDetails.getStatus().equalsIgnoreCase("2")) {
                    broadcastDetails.setStatus("Was Broadcast");
                    broadcastDetails1.add(broadcastDetails);
                }

            }
        }
        modelAndView.addObject("broadcast", broadcast1);
        modelAndView.addObject("broadcastdetails", broadcastDetails1);
        return modelAndView;
    }



}
