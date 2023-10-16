package io.daocloud.mspider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@TableName("lane")
public class Lane {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String laneNo;

    private LocalDateTime createTime;
}
