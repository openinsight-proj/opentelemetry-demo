package io.daocloud.mspider.controller.req;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateLaneReq {
    private String name;

    private String laneNo;
}
