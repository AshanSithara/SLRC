package lk.rupavahini.PPUManagement.asset.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lk.rupavahini.PPUManagement.asset.employee.entity.Employee;
import lk.rupavahini.PPUManagement.asset.employee.entity.MemberTeam;
import lk.rupavahini.PPUManagement.asset.team.entity.Team;
import lk.rupavahini.PPUManagement.util.audit.AuditEntity;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = "createdDate", allowGetters = true)
public class Member extends AuditEntity {

    @OneToOne
    @NotNull
    private Employee employee;



    @Column(nullable = false,unique = true)
    @Size(min = 5, message = "user name should include at least five characters")
    private String memberName;


    @Column(nullable = false)
    private boolean enabled;

   /* @OneToMany(mappedBy = "team",fetch = FetchType.EAGER)
    private List<UserSessionLog> userSessionLogs;*/

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<MemberTeam> member=new ArrayList<>();






    /*@ManyToMany(fetch = FetchType.EAGER)
    @Fetch( FetchMode.SUBSELECT)
    @JoinTable(name = "user_working_place",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "working_place_id"))
    private Set< WorkingPlace > workingPlaces;*/

}
