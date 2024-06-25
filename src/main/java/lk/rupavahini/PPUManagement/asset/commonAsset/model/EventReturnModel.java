/*
 *Time   :- 7:29 PM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.asset.commonAsset.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventReturnModel {

    private Long id;

    private String description;
    private Date finishtime;
    private Date starttime;
    private Date eventdate;
    private String title;
    private String eventtype;
    private String action;
    private String eventdatereturn;

    public EventReturnModel(Long id, String description, Date finishtime, Date starttime, Date eventdate, String title, String eventtype) {
        this.id = id;
        this.description = description;
        this.finishtime = finishtime;
        this.starttime = starttime;
        this.eventdate = eventdate;
        this.title = title;
        this.eventtype = eventtype;
    }
}
