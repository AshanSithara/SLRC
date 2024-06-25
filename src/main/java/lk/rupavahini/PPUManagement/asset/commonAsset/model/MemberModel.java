package lk.rupavahini.PPUManagement.asset.commonAsset.model;

import lk.rupavahini.PPUManagement.asset.employee.entity.Employee;
import lk.rupavahini.PPUManagement.asset.employee.entity.MemberTeam;
import lk.rupavahini.PPUManagement.asset.team.entity.Team;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberModel {

    private Integer id;
    private String memberName;
   private Employee employee;
   private Team team;
   private List<MemberTeam> member=new ArrayList<>();
}
