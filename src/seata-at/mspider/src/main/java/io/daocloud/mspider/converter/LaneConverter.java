package io.daocloud.mspider.converter;

import io.daocloud.mspider.controller.req.CreateLaneReq;
import io.daocloud.mspider.entity.Lane;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LaneConverter {
    LaneConverter INSTANCE = Mappers.getMapper(LaneConverter.class);

    Lane convertReq(CreateLaneReq req);
}
