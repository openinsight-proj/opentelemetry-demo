package io.daocloud.mspider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.daocloud.mspider.controller.req.CreateLaneReq;
import io.daocloud.mspider.entity.Lane;

public interface ILaneService extends IService<Lane> {
    void createLane(CreateLaneReq req) throws RuntimeException;
}
