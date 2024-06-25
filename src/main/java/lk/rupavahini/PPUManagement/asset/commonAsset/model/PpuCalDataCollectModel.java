package lk.rupavahini.PPUManagement.asset.commonAsset.model;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PpuCalDataCollectModel {
    private Long id;

    private String title;
    private LocalDateTime start;
    private LocalDateTime finish;
    private String eventtype;
    private int ppuid;
    private int programid;
}
