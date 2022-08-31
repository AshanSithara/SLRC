/*
 *Time   :- 12:00 PM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.asset.ppuevent.service;

import lk.rupavahini.PPUManagement.asset.commonAsset.model.EventDataCollectModel;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.EventHourReportModel;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.PpuHourReportModel;
import lk.rupavahini.PPUManagement.asset.ppuevent.entity.PPUEvent;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface PPUEventService {

    public List<PPUEvent> events() ;

    List<PPUEvent> findByDatesBetween(Date start, Date end);
    public List<PPUEvent> findByDateBetween(LocalDateTime start, LocalDateTime end);
    public PPUEvent save(EventDataCollectModel event);
    public PPUEvent update(EventDataCollectModel event);
    public PPUEvent updatestatus(Long id,String status);
    public List<PpuHourReportModel> getppureport(String date);
    public List<PpuHourReportModel> getppudayreport(String date);
    public boolean delete(Long id);

}
