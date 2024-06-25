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
<<<<<<< HEAD
<<<<<<< HEAD
    private String createdby;
=======
>>>>>>> 4609734 (Initial commit)
=======
    private String createdby;
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
}
