package io.daocloud.skoala.controller;

import io.daocloud.skoala.controller.req.CreateLaneReq;
import io.daocloud.skoala.service.LaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lanes")
public class LaneController {

    @Autowired
    private LaneService laneService;

    @PostMapping("/")
    public void createLane(@RequestBody CreateLaneReq req) throws Exception {
        laneService.createLane(req.getName(), req.getLabel());
    }

    @PostMapping("/err")
    public void createLaneErr(@RequestBody CreateLaneReq req) throws Exception {
        laneService.createLaneErr(req.getName(), req.getLabel());
    }
}
