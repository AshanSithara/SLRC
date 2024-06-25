/*
 *Time   :- 2:05 PM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.asset.broadcast.entity;

import lk.rupavahini.PPUManagement.asset.clibrary.entity.Clibrary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BroadcastDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String status;

    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date programmebegintime;

    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date programmeendtime;

    @ManyToOne(cascade = CascadeType.ALL)
    private Broadcast broadcast;

    @ManyToOne(cascade = CascadeType.ALL)
    private Clibrary clibrary;

    private String createdBy;

    public BroadcastDetails(String status, Date programmebegintime,Date programmeendtime, Broadcast broadcast, Clibrary clibrary, String createdBy) {
        this.status = status;
        this.programmebegintime = programmebegintime;
        this.programmeendtime = programmeendtime;
        this.broadcast = broadcast;
        this.clibrary = clibrary;
        this.createdBy = createdBy;
    }
}
