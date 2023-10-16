package io.daocloud.kpanda.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.daocloud.kpanda.controller.req.UpdateSvcReq;
import io.daocloud.kpanda.converter.SvcConverter;
import io.daocloud.kpanda.entity.Svc;
import io.daocloud.kpanda.mapper.SvcMapper;
import io.daocloud.kpanda.service.ISvcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SvcServiceImpl extends ServiceImpl<SvcMapper, Svc> implements ISvcService {
    @Autowired
    private SvcMapper svcMapper;

    @Override
    public void updateSvc(UpdateSvcReq req) {
        Svc svc = SvcConverter.INSTANCE.convertReq(req);
        svcMapper.updateById(svc);
    }

}
