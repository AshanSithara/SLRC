package lk.rupavahini.PPUManagement.asset.employee.entity;

import lk.rupavahini.PPUManagement.asset.member.entity.Member;
import lk.rupavahini.PPUManagement.asset.team.entity.Team;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberTeam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Team team;
    @ManyToOne(cascade = CascadeType.ALL)
    private Member member;

    public MemberTeam(Team team, Member member) {
        this.team = team;
        this.member = member;
    }
}
