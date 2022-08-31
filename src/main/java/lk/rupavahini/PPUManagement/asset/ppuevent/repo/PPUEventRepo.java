/*
 *Time   :- 11:57 AM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.asset.ppuevent.repo;

import lk.rupavahini.PPUManagement.asset.commonAsset.model.EventHourReportModel;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.PpuHourReportModel;
import lk.rupavahini.PPUManagement.asset.ppuevent.entity.PPUEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PPUEventRepo  extends JpaRepository<PPUEvent,Long> {

    //PPU Day By hour Query
    @Query(value = "select new lk.rupavahini.PPUManagement.asset.commonAsset.model.PpuHourReportModel(e.id,e.title,TIME_TO_SEC(TIMESTAMPDIFF(hour, e.start, e.finish)) as hourcount,e.ppu.PPUname as ppu_name ) from PPUEvent e where date(e.start)=date(:searchdate)  and e.status='1' ",nativeQuery = false )
    public List<PpuHourReportModel> ppuhour(@Param("searchdate") String date);

    //Search By day PPU
    @Query(value = "select new lk.rupavahini.PPUManagement.asset.commonAsset.model.PpuHourReportModel(sum(TIME_TO_SEC(TIMESTAMPDIFF(hour, e.start, e.finish))) as hourcount, date(e.start) as month_date ) from PPUEvent e where month(e.start)=month(:searchdate)  and e.status='1' group by date(e.start)  ",nativeQuery = false )
    public List<PpuHourReportModel> getppudayreport(@Param("searchdate") String date);
}
