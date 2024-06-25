/*
 *Time   :- 11:25 AM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.asset.ppuevent.entity;

import lk.rupavahini.PPUManagement.asset.ppu.entity.Ppu;
import lk.rupavahini.PPUManagement.asset.programme.entity.Programme;
import lk.rupavahini.PPUManagement.asset.studio.entity.Studio;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class PPUEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    private LocalDateTime start;

    private LocalDateTime finish;

    private String status;
    @ManyToOne
    private Studio studio;

    @ManyToOne
    private Ppu ppu;

    @CreatedBy
    @Basic( optional = false )
    @Column( updatable = false )
    private String createdBy;

    public PPUEvent(Long id, String title, String description, LocalDateTime start, LocalDateTime finish, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.start = start;
        this.finish = finish;
        this.status = status;
    }

    public PPUEvent( String title, String description, LocalDateTime start, LocalDateTime finish, String status) {
        this.title = title;
        this.description = description;
        this.start = start;
        this.finish = finish;
        this.status = status;
    }
}

