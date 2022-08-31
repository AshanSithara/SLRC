package lk.rupavahini.PPUManagement.asset.event.service;



import lk.rupavahini.PPUManagement.asset.commonAsset.model.*;
import lk.rupavahini.PPUManagement.asset.event.entity.Event;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface EventService {

    public List<Event> events() ;
    public Event updatestatus(Long id,String status) ;
    public List<Event> eventsone() ;
    public List<Event> eventsbulk() ;
    List<Event> findByDatesBetween(Date start, Date end);
    public List<Event> findByDateBetween(LocalDateTime start, LocalDateTime end);
    public Event save(EventDataCollectModel event);
    public Event update(EventDataCollectModel event);
    public boolean delete(Long id);
    public List<EventReturnModel> getsearchdata(SearchEventAdvanceModel searchEventAdvanceModel);
    public List<EventHourReportModel> getreporthour(String date);
    public List<EventHourReportModel> returndaybyhourreport(String date);
}
