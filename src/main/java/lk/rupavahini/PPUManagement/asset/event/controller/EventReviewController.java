/*
 *Time   :- 6:58 PM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.asset.event.controller;

import lk.rupavahini.PPUManagement.asset.event.entity.Event;
import lk.rupavahini.PPUManagement.asset.event.service.EventService;
import lk.rupavahini.PPUManagement.asset.ppuevent.entity.PPUEvent;
import lk.rupavahini.PPUManagement.asset.ppuevent.service.PPUEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
public class EventReviewController {

    @Autowired
    private EventService eventService;

    @Autowired
    private PPUEventService ppuEventService;

    @GetMapping(value = "/ppu-event-review")
    public ModelAndView viewPPUEventReviewPage() {
        ModelAndView modelAndView = new ModelAndView("review/ppu-review");
        List<PPUEvent> events = ppuEventService.events();
        List<PPUEvent> statusevent =new ArrayList<>();
        for (PPUEvent event:events){
            if (event.getStatus().equalsIgnoreCase("0")) {
                statusevent.add(event);
            }
        }
        modelAndView.addObject("data",statusevent );
        return modelAndView;
    }

    @GetMapping(value = "/event-review")
    public ModelAndView viewEventReviewPage() {
        ModelAndView modelAndView = new ModelAndView("review/event-review");
        List<Event> events = eventService.events();
        List<Event> statusevent =new ArrayList<>();
        for (Event event:events){
            if (event.getStatus().equalsIgnoreCase("0")) {
                statusevent.add(event);
            }
        }
        modelAndView.addObject("events",statusevent );
        return modelAndView;
    }

    @GetMapping("/event/review/decline/{id}")
    public String declineEvent(@PathVariable Long id, Model model) {
        eventService.updatestatus(id, "2");
        return "redirect:/event-review";
    }

    @GetMapping("/event/review/accept/{id}")
    public String acceptEvent(@PathVariable Long id, Model model) {

        eventService.updatestatus(id, "1");
        return "redirect:/event-review";
    }

    @GetMapping("/ppu-event/review/decline/{id}")
    public String declinePPUEvent(@PathVariable Long id, Model model) {
        ppuEventService.updatestatus(id, "2");
        return "redirect:/ppu-event-review";
    }

    @GetMapping("/ppu-event/review/accept/{id}")
    public String acceptPPUEvent(@PathVariable Long id, Model model) {

        ppuEventService.updatestatus(id, "1");
        return "redirect:/ppu-event-review";
    }
}
