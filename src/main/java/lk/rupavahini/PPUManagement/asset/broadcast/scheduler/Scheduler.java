package lk.rupavahini.PPUManagement.asset.broadcast.scheduler;

import lk.rupavahini.PPUManagement.asset.broadcast.service.BroadcastDetailsService;
import lk.rupavahini.PPUManagement.asset.broadcast.service.BroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Autowired
    private BroadcastService broadcastService;
    @Autowired
    private BroadcastDetailsService broadcastDetailsService;

    @Scheduled(cron = "*/10 * * * * *")
    public void cronJobSch() {

        broadcastService.updateOnAirBroadCastStatus();
        broadcastService.updateFinishBroadCastStatus();
        broadcastService.updateExpireBroadCastStatus();
        broadcastService.updatePushBroadCastStatus();
        broadcastDetailsService.updateOnAirBroadCastStatus();
        broadcastDetailsService.updateFinishBroadCastStatus();
    }
}
