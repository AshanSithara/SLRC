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
public class PpuHourReportModel {
    private Long id;
    private String title;
    private int hourcount;
    private long monthhourcount;
    private String ppu_name;
    private Date month_date;

    public PpuHourReportModel(Long id, String title, int hourcount, String ppu_name) {
        this.id = id;
        this.title = title;
        this.hourcount = hourcount;
        this.ppu_name = ppu_name;
    }

    public PpuHourReportModel(long monthhourcount, Date month_date) {
        this.monthhourcount = monthhourcount;
        this.month_date = month_date;
    }
}
