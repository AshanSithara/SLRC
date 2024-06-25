package lk.rupavahini.PPUManagement.asset.clibrary.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
<<<<<<< HEAD
import lk.rupavahini.PPUManagement.asset.broadcast.entity.BroadcastDetails;
=======
>>>>>>> 4609734 (Initial commit)
import lk.rupavahini.PPUManagement.util.audit.AuditEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

<<<<<<< HEAD
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
=======
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.time.LocalDate;
>>>>>>> 4609734 (Initial commit)

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("Clibrary")
@ToString
public class Clibrary extends AuditEntity {

    @Size(min = 5, message = "Please Enter Programme Name")
    private String programmeName;

    @Size(min = 2 , message = "Enter Episode Number")
    private String episodeNumber;

    @Size(min = 2 , message = "Enter Studio Condition")
    private String code;

<<<<<<< HEAD
    public Clibrary(@Size(min = 5, message = "Please Enter Programme Name") String programmeName) {
        this.programmeName = programmeName;
    }

    @OneToMany(mappedBy = "clibrary",cascade = CascadeType.ALL)
    private List<BroadcastDetails> broadcastDetails=new ArrayList<>();

    /* public Clibrary( @Size(min = 5, message = "Please Enter Programme Name") String programmeName, @Size(min = 2, message = "Enter  Episode Number") String episodeNumber, @Size(min = 2, message = "Enter Clibrary code") String code) {
=======

   /* public Clibrary( @Size(min = 5, message = "Please Enter Programme Name") String programmeName, @Size(min = 2, message = "Enter  Episode Number") String episodeNumber, @Size(min = 2, message = "Enter Clibrary code") String code) {
>>>>>>> 4609734 (Initial commit)

        this.programmeName = programmeName;
        this.episodeNumber = episodeNumber;
        this.code = code;
    }*/


}
