package lk.rupavahini.PPUManagement.asset.commonAsset.model;

import lk.rupavahini.PPUManagement.asset.employee.entity.MemberTeam;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class TeamModel {

    private int id;

    private String teamName;

    private List<MemberTeam> team=new ArrayList<>();

    public TeamModel(int id, String teamName) {
        this.id = id;
        this.teamName = teamName;
    }
}
