package lk.rupavahini.PPUManagement.asset.event.dao;


import lk.rupavahini.PPUManagement.asset.commonAsset.model.EventHourReportModel;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.EventReturnModel;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.PpuHourReportModel;
import lk.rupavahini.PPUManagement.asset.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface EventDAO extends JpaRepository<Event,Long> {



    public List<Event> findByStartGreaterThanEqualAndFinishLessThanEqual(LocalDateTime start, LocalDateTime end);

    @Query("select e from Event e where e.eventtype='SingleDay' and e.status='1' " )
    public List<Event> eventsone();

    @Query("select e from Event e where e.eventtype='BulkDays' and e.status='1' " )
    public List<Event> eventsbulk();

    /* This one uses an @Query */
    @Query("select b from Event b where  b.status='1' and b.start >= ?1 and b.finish <= ?2")
    public List<Event> findByDateBetween(LocalDateTime start, LocalDateTime end);

    //Event Day By hour Query
    @Query(value = "select new lk.rupavahini.PPUManagement.asset.commonAsset.model.EventHourReportModel(e.id,e.title,TIME_TO_SEC(TIMESTAMPDIFF(hour, e.start, e.finish)) as hourcount,e.studio.studioName as studio_name ) from Event e where date(e.start)=date(:searchdate)  and e.status='1' ",nativeQuery = false )
    public List<EventHourReportModel> reporteventhour(@Param("searchdate") String date);



    //Search By day
    @Query(value = "select new lk.rupavahini.PPUManagement.asset.commonAsset.model.EventHourReportModel(sum(TIME_TO_SEC(TIMESTAMPDIFF(hour, e.start, e.finish))) as hourcount, date(e.start) as month_date ) from Event e where month(e.start)=month(:searchdate)  and e.status='1' group by date(e.start)  ",nativeQuery = false )
    public List<EventHourReportModel> returndaybyhourreport(@Param("searchdate") String date);


//    @Query("select b from Event b where b.start between ?1 and ?2 and b.end between ?1 and ?2")
//    List<Event> findByDatesBetween(Date start, Date end);
@Query(value = "SELECT new lk.rupavahini.PPUManagement.asset.commonAsset.model.EventReturnModel(e.id,e.description,time(e.finish) as finishtime,time(e.start) as starttime,date(e.start) as eventdate,e.title,e.eventtype )  from Event e where date(e.start)=:searchdate and e.status='1' and (time(e.start) between :begintimeto and :begintimefrom and time(e.finish) between :endtimeto and :endtimefrom)  ",
        nativeQuery = false)
List<EventReturnModel> getsearchdata(
        @Param("searchdate") Date searchdate, @Param("begintimeto") Date begintimeto, @Param("begintimefrom") Date begintimefrom, @Param("endtimeto") Date endtimeto, @Param("endtimefrom") Date endtimefrom);


}
