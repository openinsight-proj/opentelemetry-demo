package io.daocloud.kpanda.converter;

import io.daocloud.kpanda.controller.req.UpdateSvcReq;
import io.daocloud.kpanda.entity.Svc;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SvcConverter {
    SvcConverter INSTANCE = Mappers.getMapper(SvcConverter.class);

    Svc convertReq(UpdateSvcReq req);
}
