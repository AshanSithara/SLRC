/*
 *Time   :- 2:11 PM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.asset.broadcast.model;


import lombok.*;

import java.util.Date;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BroadcastModel {

    private int id;

    private String broadcastcode;

    private String name;

    private Date broadcastdate;

    private String createdBy;

    public BroadcastModel(String name, Date broadcastdate) {
        this.name = name;
        this.broadcastdate = broadcastdate;
    }

    public BroadcastModel(int id, String name, Date broadcastdate) {
        this.id = id;
        this.name = name;
        this.broadcastdate = broadcastdate;
    }
}
