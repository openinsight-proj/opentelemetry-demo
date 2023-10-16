package io.daocloud.skoala.controller.req;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateSvcReq {
    private Long id;
    private String label;
}
