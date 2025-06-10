package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *预约管理：(BookingManagement)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "BookingManagement")
public class BookingManagement implements Serializable {

    //BookingManagement编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_management_id")
    private Integer booking_management_id;
   // 服务类型
   @Basic
    private String service_type;
   //  店铺地址
   @Basic
    private String shop_address;
   // 联系人
   @Basic
    private String contacts;
   // 联系电话
   @Basic
    private String contact_number;
   // 单次价格
   @Basic
    private String single_price;
   // 服务次数
   @Basic
    private Integer service_times;
   // 总价格
   @Basic
    private String total_price;
   // 预约时间
   @Basic
    private Timestamp time_of_appointment;
   // 用户账号
   @Basic
    private Integer user_account;
    // 支付状态
    @Basic
    private String pay_state;

    // 支付类型: 微信、支付宝、网银
    @Basic
    private String pay_type;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
