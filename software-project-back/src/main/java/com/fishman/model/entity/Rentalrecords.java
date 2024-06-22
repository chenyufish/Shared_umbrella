package com.fishman.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName rentalrecords
 */
@TableName(value ="rentalrecords")
@Data
public class Rentalrecords implements Serializable {
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
    @TableField(value = "UmbrellaID")
    private Integer umbrellaid;

    /**
     * 
     */
    @TableField(value = "RentalTime")
    private Date rentaltime;

    /**
     * 
     */
    @TableField(value = "ReturnTime")
    private Date returntime;

    /**
     * 
     */
    @TableField(value = "Status")
    private Object status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}