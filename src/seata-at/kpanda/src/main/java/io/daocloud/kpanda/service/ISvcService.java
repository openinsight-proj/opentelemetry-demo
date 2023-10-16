package io.daocloud.kpanda.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.daocloud.kpanda.controller.req.UpdateSvcReq;
import io.daocloud.kpanda.entity.Svc;

public interface ISvcService extends IService<Svc> {
    void updateSvc(UpdateSvcReq req);
}
