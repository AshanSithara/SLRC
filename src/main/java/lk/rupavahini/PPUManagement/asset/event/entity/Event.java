package lk.rupavahini.PPUManagement.asset.event.entity;

import lk.rupavahini.PPUManagement.asset.programme.entity.Programme;
import lk.rupavahini.PPUManagement.asset.studio.entity.Studio;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Event  {
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
    private String status;
    @ManyToOne
   // @JoinColumn(name = "studio_id",referencedColumnName = "id")
    private Studio studio;

    @ManyToOne
   // @JoinColumn(name = "programme_id",referencedColumnName = "id")
    private Programme programme;

    @CreatedBy
    @Basic( optional = false )
    @Column( updatable = false )
    private String createdBy;


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
