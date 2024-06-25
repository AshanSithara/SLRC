/*
 *Time   :- 11:28 AM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.asset.broadcast.service;

import lk.rupavahini.PPUManagement.asset.broadcast.entity.Broadcast;
import lk.rupavahini.PPUManagement.asset.broadcast.model.BroadcastModel;

import java.util.Date;
import java.util.List;

public interface BroadcastService {

    Broadcast save(BroadcastModel broadcastModel);
    Broadcast update(BroadcastModel broadcastModel);
    boolean findBroadcastByBroadcastdate(Date date);
    Broadcast findone(Integer id);
    Broadcast updatestatus(Integer id,String status);
    List<Broadcast> findall();

    void updateOnAirBroadCastStatus();
    void updatePushBroadCastStatus();
    void updateFinishBroadCastStatus();
    void updateExpireBroadCastStatus();
}
