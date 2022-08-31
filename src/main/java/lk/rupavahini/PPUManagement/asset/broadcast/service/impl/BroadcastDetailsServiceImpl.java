/*
 *Time   :- 2:06 PM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.asset.broadcast.service.impl;

import lk.rupavahini.PPUManagement.asset.broadcast.entity.Broadcast;
import lk.rupavahini.PPUManagement.asset.broadcast.entity.BroadcastDetails;
import lk.rupavahini.PPUManagement.asset.broadcast.model.BroadcastDetailModel;
import lk.rupavahini.PPUManagement.asset.broadcast.repo.BroadcastDetailsRepo;
import lk.rupavahini.PPUManagement.asset.broadcast.repo.BroadcastRepo;
import lk.rupavahini.PPUManagement.asset.broadcast.service.BroadcastDetailsService;
import lk.rupavahini.PPUManagement.asset.clibrary.entity.Clibrary;
import lk.rupavahini.PPUManagement.asset.clibrary.service.ClibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BroadcastDetailsServiceImpl  implements BroadcastDetailsService {

    @Autowired
    private BroadcastRepo broadcastRepo;
    @Autowired
    private ClibraryService clibraryService;
    @Autowired
    private BroadcastDetailsRepo broadcastDetailsRepo;
    @Override
    public BroadcastDetails save(BroadcastDetailModel broadcastDetailModel) {
        Clibrary clibrary = clibraryService.findById(broadcastDetailModel.getClibraryid());
        Broadcast broadcast = broadcastRepo.findBroadcastById(broadcastDetailModel.getBroadcastid());
        if (broadcast != null && clibrary != null){
            Date begintime = null;
            Date endtime = null;
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            try {
                broadcastDetailModel.setProgrammebegintime(broadcast.getBroadcastdate()+" "+broadcastDetailModel.getProgrammebegintime());
                broadcastDetailModel.setProgrammeendtime(broadcast.getBroadcastdate()+" "+broadcastDetailModel.getProgrammeendtime());
                begintime = inputDateFormat.parse(broadcastDetailModel.getProgrammebegintime());
                endtime = inputDateFormat.parse(broadcastDetailModel.getProgrammeendtime());
            } catch (ParseException e) {
                e.getMessage();
            }
            BroadcastDetails broadcastDetails = new BroadcastDetails("0",begintime,endtime,broadcast,clibrary,broadcastDetailModel.getCreatedBy());
            return broadcastDetailsRepo.save(broadcastDetails);
        }else {
            return null;
        }
    }

    @Override
    public boolean delete(Integer id) {
        BroadcastDetails broadcastDetails=null;
        try {
            broadcastDetails= broadcastDetailsRepo.findById(id).orElseThrow(() -> new Exception());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (broadcastDetails!=null){
            broadcastDetailsRepo.deletebroadcast_details(broadcastDetails.getId());
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean checkdate(Integer id,String start, String end) {

        Broadcast broadcast = broadcastRepo.findBroadcastById(id);
        if (broadcast!=null){
            List<BroadcastDetails> broadcastDetails = broadcastDetailsRepo.checkdatetime(broadcast.getId(),broadcast.getBroadcastdate(), start, end);
            if (broadcastDetails.isEmpty()){
                return true;
            }else {
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public List<BroadcastDetails> findall() {
        return broadcastDetailsRepo.findAll();
    }

    @Override
    public void updateOnAirBroadCastStatus() {
        broadcastDetailsRepo.updateOnAirProgram();
    }

    @Override
    public void updateFinishBroadCastStatus() {
        broadcastDetailsRepo.updateEndProgram();
    }
}
