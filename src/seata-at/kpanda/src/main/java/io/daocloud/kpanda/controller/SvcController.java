package io.daocloud.kpanda.controller;

import io.daocloud.kpanda.controller.req.UpdateSvcReq;
import io.daocloud.kpanda.service.ISvcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/svc")
public class SvcController {
    @Autowired
    private ISvcService svcService;

    @PutMapping("/")
    Boolean updateSvc(@RequestBody UpdateSvcReq req){
        svcService.updateSvc(req);
        return true;
    }
}
