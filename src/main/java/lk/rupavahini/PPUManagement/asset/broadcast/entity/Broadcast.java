/*
 *Time   :- 11:27 AM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.asset.broadcast.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Broadcast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String broadcastcode;

    private String name;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date broadcastdate;

    private String createdBy;

    private String status;

    @Transient
    private String statusdata;

    @OneToMany(mappedBy = "broadcast",cascade = CascadeType.ALL)
    private List<BroadcastDetails> broadcastDetails=new ArrayList<>();

    public Broadcast(String broadcastcode, String name, Date broadcastdate, String createdBy) {
        this.broadcastcode = broadcastcode;
        this.name = name;
        this.broadcastdate = broadcastdate;
        this.createdBy = createdBy;
    }
}
