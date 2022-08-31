package lk.rupavahini.PPUManagement.asset.team.entity;

import lk.rupavahini.PPUManagement.asset.employee.entity.MemberTeam;
import lk.rupavahini.PPUManagement.asset.member.entity.Member;
import lk.rupavahini.PPUManagement.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team extends AuditEntity {

    @NotNull
    @Column( unique = true )
    private String teamName;



    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL)
    private List<MemberTeam> team=new ArrayList<>();
}
