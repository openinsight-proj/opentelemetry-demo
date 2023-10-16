package io.daocloud.skoala.service;

import io.daocloud.skoala.client.KpandaClient;
import io.daocloud.skoala.client.MspiderClient;
import io.daocloud.skoala.controller.req.CreateLaneReq;
import io.daocloud.skoala.controller.req.UpdateSvcReq;
import io.daocloud.skoala.utils.id.DistributedId;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaneService {
    @Autowired
    private KpandaClient kpandaClient;

    @Autowired
    private MspiderClient mspiderClient;

    /**
     * skoala 服务创建泳道业务
     * 前端业务入口，管理全局事务
     * TM 角色
     * 验证正常流程
     */
    @GlobalTransactional
    public void createLane(String name, String label) throws Exception {
        // 分支事务1: 调用 kpanda 服务更新 k8s svc label
        // RM 角色
        UpdateSvcReq updateSvcReq = new UpdateSvcReq()
                .setId(1L)
                .setLabel(label);
        kpandaClient.updateSvc(updateSvcReq);

        // 为了方便测试验证，每执行完一次分支事务，睡眠10s
        Thread.sleep(10000);

        // 分支事务2: 调用 mspider 服务创建 k8s lane cr
        // RM 角色
        String laneNo = DistributedId.getInstance().getFastSimpleUUID();
        CreateLaneReq createLaneReq = new CreateLaneReq()
                .setName(name)
                .setLaneNo(laneNo);
        mspiderClient.createLane(createLaneReq);

        // 为了方便测试验证，每执行完一次分支事务，睡眠10s
        Thread.sleep(10000);

    }

    /**
     * skoala 服务创建泳道业务
     * 前端业务入口，管理全局事务
     * TM 角色
     * 验证异常回滚流程
     */
    @GlobalTransactional
    public void createLaneErr(String name, String label) throws Exception{
        // 分支事务1: 调用 kpanda 服务更新 k8s svc label
        // RM 角色
        UpdateSvcReq updateSvcReq = new UpdateSvcReq()
                .setId(1L)
                .setLabel(label);
        kpandaClient.updateSvc(updateSvcReq);

        // 为了方便测试验证，每执行完一次分支事务，睡眠10s
        Thread.sleep(10000);

        // 分支事务2: 调用 mspider 服务创建 k8s lane cr
        // RM 角色
        String laneNo = DistributedId.getInstance().getFastSimpleUUID();
        CreateLaneReq createLaneReq = new CreateLaneReq()
                .setName(name)
                .setLaneNo(laneNo);
        mspiderClient.createLane(createLaneReq);

        // 为了方便测试验证，每执行完一次分支事务，睡眠10s
        Thread.sleep(10000);

        throw new RuntimeException("发生异常，分布式事务回滚");
    }
}
