/*
 *Time   :- 4:31 PM
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
public class SearchEventAdvanceModel {

    private Date date;
    private Date begintimeto;
    private Date begintimefrom;
    private Date endtimeto;
    private Date endtimefrom;
}
