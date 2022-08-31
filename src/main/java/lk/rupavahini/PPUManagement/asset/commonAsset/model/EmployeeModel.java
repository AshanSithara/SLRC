package lk.rupavahini.PPUManagement.asset.commonAsset.model;

import lk.rupavahini.PPUManagement.asset.commonAsset.model.Enum.Title;
import lk.rupavahini.PPUManagement.asset.employee.entity.Enum.Designation;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;


@NoArgsConstructor
@ToString
@Getter
@Setter
public class EmployeeModel {

    private int id;
    @Size(min = 5, message = "Your name cannot be accepted")
    private String name;//

    @Size(max = 12, min = 10, message = "NIC number is contained numbers between 9 and X/V or 12 ")
    @Column(unique = true)
    private String nic;//



    @Column(unique = true)
    private String email;//



    @Enumerated( EnumType.STRING )
    private Title title;



    @Enumerated(EnumType.STRING)
    private Designation designation;

    public EmployeeModel(int id, @Size(min = 5, message = "Your name cannot be accepted") String name, @Size(max = 12, min = 10, message = "NIC number is contained numbers between 9 and X/V or 12 ") String nic, String email, Title title, Designation designation) {
        this.id = id;
        this.name = name;
        this.nic = nic;
        this.email = email;
        this.title = title;
        this.designation = designation;
    }

    public EmployeeModel(int id, @Size(min = 5, message = "Your name cannot be accepted") String name) {
        this.id = id;
        this.name = name;
    }
}
