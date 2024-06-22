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
 * @TableName users
 */
@TableName(value ="users")
@Data
public class Users implements Serializable {
    /**
     * 
     */
    @TableId(value = "UserID", type = IdType.AUTO)
    private Integer userid;

    /**
     * 
     */
    @TableField(value = "Username")
    private String username;

    /**
     * 
     */
    @TableField(value = "Password")
    private String password;

    /**
     * 
     */
    @TableField(value = "Name")
    private String name;

    /**
     * 
     */
    @TableField(value = "Phone")
    private String phone;

    /**
     * 
     */
    @TableField(value = "Role")
    private Integer role;

    /**
     * 
     */
    @TableField(value = "isDelete")
    private Integer isdelete;

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