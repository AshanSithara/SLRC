package lk.rupavahini.PPUManagement.asset.event.entity;

import lk.rupavahini.PPUManagement.asset.programme.entity.Programme;
import lk.rupavahini.PPUManagement.asset.studio.entity.Studio;
import lombok.*;
<<<<<<< HEAD
<<<<<<< HEAD
import org.springframework.data.annotation.CreatedBy;
=======
>>>>>>> 4609734 (Initial commit)
=======
import org.springframework.data.annotation.CreatedBy;
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
<<<<<<< HEAD
<<<<<<< HEAD
public class Event  {
=======
public class Event {
>>>>>>> 4609734 (Initial commit)
=======
public class Event  {
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime start;
//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime finish;
    private String eventtype;
<<<<<<< HEAD
<<<<<<< HEAD
    private String status;
=======
>>>>>>> 4609734 (Initial commit)
=======
    private String status;
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
    @ManyToOne
   // @JoinColumn(name = "studio_id",referencedColumnName = "id")
    private Studio studio;

    @ManyToOne
   // @JoinColumn(name = "programme_id",referencedColumnName = "id")
    private Programme programme;

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
    @CreatedBy
    @Basic( optional = false )
    @Column( updatable = false )
    private String createdBy;

<<<<<<< HEAD
=======
>>>>>>> 4609734 (Initial commit)
=======
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1

    public Event(Long id, String title, String description, LocalDateTime start, LocalDateTime finish, String eventtype) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.start = start;
        this.finish = finish;
        this.eventtype = eventtype;
    }

    public Event( String title, String description, LocalDateTime start, LocalDateTime finish, String eventtype) {
        this.title = title;
        this.description = description;
        this.start = start;
        this.finish = finish;
        this.eventtype = eventtype;
    }
}
