package com.fishman.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: User <br/>
 *
 * @author fishman
 * @date 2024/6/23 
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {
    @TableId(value = "uId",type = IdType.AUTO)
    private Integer uId;
    @TableField("uPhone")
    private String uPhone;
    @TableField("uNickname")
    private String uNickname;
    @TableField("uEmail")
    private String uEmail;
    @TableField("uPassword")
    private String uPassword;
    @TableField("uIsVerify")
    private Boolean uIsVerify;
    @TableField("uAddress")
    private String uAddress;
    @TableField("uLevel")
    private Integer uLevel;


}
