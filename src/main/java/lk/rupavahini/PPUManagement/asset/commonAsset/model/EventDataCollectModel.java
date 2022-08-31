package lk.rupavahini.PPUManagement.asset.commonAsset.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventDataCollectModel {
    private Long id;

    private String title;
    private String description;
    private LocalDateTime start;
    private LocalDateTime finish;
    private String eventtype;
    private int programmeid;
    private int studioid;
    private String createdby;
}
