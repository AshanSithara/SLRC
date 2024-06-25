/*
 *Time   :- 2:05 PM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.asset.broadcast.service;


import lk.rupavahini.PPUManagement.asset.broadcast.entity.BroadcastDetails;
import lk.rupavahini.PPUManagement.asset.broadcast.model.BroadcastDetailModel;

import java.util.List;


public interface BroadcastDetailsService {

    BroadcastDetails save(BroadcastDetailModel broadcastDetailModel);
    boolean delete(Integer id);
    boolean checkdate(Integer id,String start,String end);
    List<BroadcastDetails> findall();

    void updateOnAirBroadCastStatus();
    void updateFinishBroadCastStatus();
}
