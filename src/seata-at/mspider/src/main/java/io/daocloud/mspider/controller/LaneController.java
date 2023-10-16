package io.daocloud.mspider.controller;

import io.daocloud.mspider.controller.req.CreateLaneReq;
import io.daocloud.mspider.service.ILaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lanes")
public class LaneController {

    @Autowired
    private ILaneService laneService;

    @PostMapping("/")
    Boolean createLane(@RequestBody CreateLaneReq req) {
        laneService.createLane(req);
        return true;
    }
}
