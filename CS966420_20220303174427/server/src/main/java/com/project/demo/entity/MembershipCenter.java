package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *会员中心：(MembershipCenter)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "MembershipCenter")
public class MembershipCenter implements Serializable {

    //MembershipCenter编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "membership_center_id")
    private Integer membership_center_id;
   // 会员编号
   @Basic
    private String member_number;
   // 会员昵称
   @Basic
    private String member_nickname;
   // 会员等级
   @Basic
    private String membership_level;
   // 会员折扣
   @Basic
    private String member_discount;
   // 用户账号
   @Basic
    private Integer user_account;
    // 审核状态
    @Basic
    private String examine_state;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
