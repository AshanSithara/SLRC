/*
 *Time   :- 2:04 PM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.asset.broadcast.repo;

import lk.rupavahini.PPUManagement.asset.broadcast.entity.BroadcastDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface BroadcastDetailsRepo extends JpaRepository<BroadcastDetails,Integer> {



    @Query(value = "SELECT e.*  from broadcast_details e,broadcast b where e.broadcast_id=:bid and date(b.broadcastdate)=:searchdate and ( time(e.programmebegintime) between time(:start) and time(:endtime) or time(e.programmeendtime) between time(:start) and time(:endtime)) group by e.id ",
            nativeQuery = true)
    List<BroadcastDetails> checkdatetime(@Param("bid") Integer id,
                                         @Param("searchdate") Date searchdate, @Param("start") String start, @Param("endtime") String end);
    @Modifying
    @Query(value = "UPDATE broadcast b,broadcast_details bd SET bd.`status`='1' WHERE b.id=bd.broadcast_id AND b.broadcastdate=CURDATE() \n" +
            "AND b.`status`='1' AND bd.programmebegintime<=time(NOW()) AND bd.`status`='0'",
    nativeQuery = true)
    @Transactional
    void updateOnAirProgram();

    @Modifying
    @Query(value = "UPDATE broadcast b,broadcast_details bd SET bd.`status`='2' WHERE b.id=bd.broadcast_id AND b.broadcastdate=CURDATE() \n" +
            "AND b.`status`='1' AND bd.programmeendtime<=time(NOW()) AND bd.`status`='1'",
            nativeQuery = true)
    @Transactional
    void updateEndProgram();

    @Query(value = "SELECT bd.id FROM broadcast b,broadcast_details bd \n" +
            "WHERE b.id=bd.broadcast_id AND b.broadcastdate=CURDATE()\n" +
            "            AND b.`status`='1' AND bd.programmebegintime<=time(NOW()) AND bd.`status`='0'",
    nativeQuery = true)
    List<Integer> getOnAirProgramsIds();

    @Query(value = "SELECT bd.id FROM broadcast b,broadcast_details bd \n" +
            "WHERE b.id=bd.broadcast_id AND b.broadcastdate=CURDATE()\n" +
            "            AND b.`status`='1' AND bd.programmebegintime<=time(NOW()) AND bd.`status`='1'",
            nativeQuery = true)
    List<Integer> getEndProgramsIds();


    //Broadcast Delete query
    @Modifying
    @Query(value = "delete from broadcast_details where id=:dataid ",
            nativeQuery = true)
    @Transactional
    void deletebroadcast_details(@Param("dataid") Integer dataid);
}
