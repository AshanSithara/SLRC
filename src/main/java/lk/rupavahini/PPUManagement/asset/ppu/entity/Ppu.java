package lk.rupavahini.PPUManagement.asset.ppu.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
<<<<<<< HEAD
import lk.rupavahini.PPUManagement.asset.event.entity.Event;
import lk.rupavahini.PPUManagement.asset.ppuevent.entity.PPUEvent;
=======
>>>>>>> 4609734 (Initial commit)
import lk.rupavahini.PPUManagement.asset.programme.entity.Programme;
import lk.rupavahini.PPUManagement.util.audit.AuditEntity;
import lombok.*;

<<<<<<< HEAD
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
=======
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
>>>>>>> 4609734 (Initial commit)

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("Ppu")
@ToString
public class Ppu extends AuditEntity {

    @Size(min = 5, message = "Please Enter Studio Name")
    private String PPUname;

    @Size(min = 2 , message = "Please Enter PPU Name")
    private String PPUlocation;

    @Size(min = 2 , message = "Enter PPU Code")
    private String PPUcode;

<<<<<<< HEAD
    @OneToMany(mappedBy = "ppu",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<PPUEvent> ppuEvents=new ArrayList<>();

=======
>>>>>>> 4609734 (Initial commit)

    public Ppu(@Size(min = 5, message = "Please Enter PPU Name") String PPUname, @Size(min = 2, message = "Enter PPU Code") String PPUcode) {
        this.PPUname = PPUname;
        this.PPUcode = PPUcode;
    }
}
