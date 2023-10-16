package io.daocloud.mspider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.daocloud.mspider.controller.req.CreateLaneReq;
import io.daocloud.mspider.converter.LaneConverter;
import io.daocloud.mspider.entity.Lane;
import io.daocloud.mspider.mapper.LaneMapper;
import io.daocloud.mspider.service.ILaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaneServiceImpl extends ServiceImpl<LaneMapper, Lane> implements ILaneService {

    @Autowired
    private LaneMapper laneMapper;

    @Override
    public void createLane(CreateLaneReq req) throws RuntimeException {
        Lane lane = LaneConverter.INSTANCE.convertReq(req);
        laneMapper.insert(lane);
    }
}
