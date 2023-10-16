package io.daocloud.kpanda.controller.req;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class UpdateSvcReq implements Serializable {
    private Long id;
    private String label;
}
