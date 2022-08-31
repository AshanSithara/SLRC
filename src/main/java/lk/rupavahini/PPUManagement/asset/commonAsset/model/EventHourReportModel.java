/*
 *Time   :- 2:42 AM
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
public class EventHourReportModel {

    private Long id;
    private String title;
    private int hourcount;
    private long monthhourcount;
    private String studio_name;
    private Date month_date;

    public EventHourReportModel(Long id, String title, int hourcount, String studio_name) {
        this.id = id;
        this.title = title;
        this.hourcount = hourcount;
        this.studio_name = studio_name;
    }

    public EventHourReportModel(long monthhourcount, Date month_date) {
        this.monthhourcount = monthhourcount;
        this.month_date = month_date;
    }
}
