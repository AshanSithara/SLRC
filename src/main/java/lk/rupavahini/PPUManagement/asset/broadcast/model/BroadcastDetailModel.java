/*
 *Time   :- 10:08 PM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.asset.broadcast.model;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BroadcastDetailModel {

    private String programmebegintime;
    private String programmeendtime;
    private int broadcastid;
    private int clibraryid;
    private String createdBy;
    private String status;
    private String cccode;

    public BroadcastDetailModel(String programmebegintime,String programmeendtime, int broadcastid, int clibraryid) {
        this.programmebegintime = programmebegintime;
        this.programmeendtime =  programmeendtime;
        this.broadcastid = broadcastid;
        this.clibraryid = clibraryid;
    }

}
