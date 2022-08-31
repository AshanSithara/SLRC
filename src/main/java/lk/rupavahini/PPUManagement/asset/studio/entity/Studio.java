package lk.rupavahini.PPUManagement.asset.studio.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.rupavahini.PPUManagement.asset.event.entity.Event;
import lk.rupavahini.PPUManagement.asset.programme.entity.Programme;
import lk.rupavahini.PPUManagement.util.audit.AuditEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("Studio")
@ToString
public class Studio extends AuditEntity {

    @Size(min = 5, message = "Please Enter Studio Name")
    private String studioName;

    @Size(min = 2 , message = "Enter Studio Location")
    private String location;

    @Size(min = 2 , message = "Enter Studio Code")
    private String code;


    @OneToMany(mappedBy = "studio",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Programme> programme=new ArrayList<>();

    @OneToMany(mappedBy = "studio",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Event> event=new ArrayList<>();



    public Studio( @Size(min = 5, message = "Please Enter Studio Name") String studioName, @Size(min = 2, message = "Enter Studio Condition") String location, @Size(min = 2, message = "Enter Studio Condition") String code) {

        this.studioName = studioName;
        this.location = location;
        this.code = code;
    }



}
