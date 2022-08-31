/*
 *Time   :- 1:21 PM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.asset.ppuevent.model;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter

public class PPUEventModel {

    private Long id;

    private String title;
    private String description;
    private String start;
    private String finish;
    private String status;
    private int programme_id;
    private int studio_id;
}
