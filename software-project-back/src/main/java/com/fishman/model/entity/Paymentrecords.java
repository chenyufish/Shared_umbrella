package com.fishman.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @TableName paymentrecords
 */
@TableName(value ="paymentrecords")
@Data
public class Paymentrecords implements Serializable {
    /**
     * 
     */
    @TableId(value = "RecordID", type = IdType.AUTO)
    private Integer recordid;

    /**
     * 
     */
    @TableField(value = "UserID")
    private Integer userid;

    /**
     * 
     */
    @TableField(value = "PaymentTime")
    private Date paymenttime;

    /**
     * 
     */
    @TableField(value = "Amount")
    private BigDecimal amount;

    /**
     * 
     */
    @TableField(value = "PaymentMethod")
    private String paymentmethod;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}