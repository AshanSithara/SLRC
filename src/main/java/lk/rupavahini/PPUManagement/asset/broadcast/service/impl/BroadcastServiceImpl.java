/*
 *Time   :- 11:31 AM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.asset.broadcast.service.impl;

import lk.rupavahini.PPUManagement.asset.broadcast.entity.Broadcast;
import lk.rupavahini.PPUManagement.asset.broadcast.model.BroadcastModel;
import lk.rupavahini.PPUManagement.asset.broadcast.repo.BroadcastRepo;
import lk.rupavahini.PPUManagement.asset.broadcast.service.BroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class BroadcastServiceImpl implements BroadcastService {

    @Autowired
    private BroadcastRepo broadcastRepo;

    //Generate the broadcast Code
    @Override
    public Broadcast save(BroadcastModel broadcastModel) {
        Broadcast broadcast = new Broadcast(broadcastModel.getBroadcastcode(), broadcastModel.getName(), broadcastModel.getBroadcastdate(), broadcastModel.getCreatedBy());
        Long unique = (long) Math.floor(Math.random() * 900000000L);
        broadcast.setBroadcastcode(unique + "");
        broadcast.setStatus("0");
        return broadcastRepo.save(broadcast);
    }

    @Override
    public Broadcast update(BroadcastModel broadcastModel) {
        Broadcast broadcastById = broadcastRepo.findBroadcastById(broadcastModel.getId());
        if (broadcastById != null) {
            broadcastById.setBroadcastdate(broadcastModel.getBroadcastdate());
            broadcastById.setName(broadcastModel.getName());
            return broadcastRepo.save(broadcastById);
        } else {
            return null;
        }


    }

    @Override
    public boolean findBroadcastByBroadcastdate(Date date) {
        Broadcast broadcastByBroadcastdate = broadcastRepo.findBroadcastByBroadcastdate(date);
        if (broadcastByBroadcastdate == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Broadcast findone(Integer id) {
        return broadcastRepo.findBroadcastById(id);
    }

    @Override
    public Broadcast updatestatus(Integer id, String status) {
        Broadcast broadcastById = broadcastRepo.findBroadcastById(id);
        if (broadcastById != null) {
            broadcastById.setStatus(status);
            return broadcastRepo.save(broadcastById);
        } else {
            return null;
        }
    }

    @Override
    public List<Broadcast> findall() {
        return broadcastRepo.findAll();
    }

    @Override
    public void updateOnAirBroadCastStatus() {
//        List<Integer> onAirBroadcastIds = broadcastRepo.getOnAirBroadcastIds();
        broadcastRepo.updateOnAirBroadCastStatus();
    }

    @Override
    public void updatePushBroadCastStatus() {
        broadcastRepo.updatePushBroadCastStatus();
    }

    @Override
    public void updateFinishBroadCastStatus() {
        broadcastRepo.updateFinishBroadCastStatus();
    }

    @Override
    public void updateExpireBroadCastStatus() {
        broadcastRepo.updateExpireBroadCastStatus();
    }

}
