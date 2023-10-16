package io.daocloud.skoala.controller.req;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateLaneReq {
    private String name;

    private String label;

    private String laneNo;
}
