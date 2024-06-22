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
 * @TableName umbrellas
 */
@TableName(value ="umbrellas")
@Data
public class Umbrellas implements Serializable {
    /**
     * 
     */
    @TableId(value = "UmbrellaID", type = IdType.AUTO)
    private Integer umbrellaid;

    /**
     * 
     */
    @TableField(value = "Name")
    private String name;

    /**
     * 
     */
    @TableField(value = "Type")
    private String type;

    /**
     * 
     */
    @TableField(value = "Status")
    private Object status;

    /**
     * 
     */
    @TableField(value = "CreateTime")
    private Date createtime;

    /**
     * 
     */
    @TableField(value = "UpdateTime")
    private Date updatetime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}