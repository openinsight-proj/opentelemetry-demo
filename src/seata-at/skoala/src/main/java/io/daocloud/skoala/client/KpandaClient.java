package io.daocloud.skoala.client;

import io.daocloud.skoala.controller.req.UpdateSvcReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name = "kpanda-service")
public interface KpandaClient {

    @RequestMapping(value = "/svc/", method = RequestMethod.PUT)
    Boolean updateSvc(@RequestBody UpdateSvcReq updateSvcReq);
}
