package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *服务中心：(ServiceCentre)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ServiceCentre")
public class ServiceCentre implements Serializable {

    //ServiceCentre编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_centre_id")
    private Integer service_centre_id;
   // 服务类型
   @Basic
    private String service_type;
   // 单次价格
   @Basic
    private String single_price;
   //  店铺地址
   @Basic
    private String shop_address;
   // 联系人
   @Basic
    private String contacts;
   // 联系电话
   @Basic
    private String contact_number;
   // 备注
   @Basic
    private String remarks;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
