package io.daocloud.skoala.client;

import io.daocloud.skoala.controller.req.CreateLaneReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name = "mspider-service")
public interface MspiderClient {

    @RequestMapping(value = "/lanes/", method = RequestMethod.POST)
    Boolean createLane(@RequestBody CreateLaneReq req);
}
