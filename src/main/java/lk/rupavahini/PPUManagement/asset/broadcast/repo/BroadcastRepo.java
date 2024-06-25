/*
 *Time   :- 11:28 AM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.asset.broadcast.repo;

import lk.rupavahini.PPUManagement.asset.broadcast.entity.Broadcast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface BroadcastRepo extends JpaRepository<Broadcast,Integer> {

    Broadcast findBroadcastByBroadcastdate(Date date);
    Broadcast findBroadcastById(Integer id);

    @Modifying
    @Query(value = "UPDATE broadcast b SET b.`status`='1' WHERE b.`status`='0' AND b.broadcastdate=CURDATE()",
    nativeQuery = true)
    @Transactional
    void updateOnAirBroadCastStatus();

    @Modifying
    @Query(value = "UPDATE broadcast b SET b.`status`='3' WHERE b.`status`='1' AND b.broadcastdate<CURDATE();",
            nativeQuery = true)
    @Transactional
    void updateFinishBroadCastStatus();

    @Modifying
    @Query(value = "UPDATE broadcast b SET b.`status`='4' WHERE b.`status`='2' AND b.broadcastdate<CURDATE();",
            nativeQuery = true)
    @Transactional
    void updateExpireBroadCastStatus();

    @Modifying
    @Query(value = "UPDATE broadcast b,broadcast_details bd SET bd.`status`='0' WHERE b.id=bd.broadcast_id AND b.broadcastdate=CURDATE() \n" +
            "AND b.`status`='2' AND bd.`status`='1'",
            nativeQuery = true)
    @Transactional
    void updatePushBroadCastStatus();

    @Query(value = "SELECT b.id FROM\n" +
            "broadcast b WHERE b.`status`='0' AND b.broadcastdate=CURDATE()",
    nativeQuery = true)
    List<Integer> getOnAirBroadcastIds();

    @Query(value = "SELECT b.id FROM\n" +
            "broadcast b WHERE b.`status`='1' AND b.broadcastdate<CURDATE()",
            nativeQuery = true)

    List<Integer> getFinishBroadcastIds();

    @Query(value = "SELECT b.id FROM\n" +
            "broadcast b WHERE b.`status`='2' AND b.broadcastdate<CURDATE();",
            nativeQuery = true)
    List<Integer> getExpiredBroadcastIds();

}
