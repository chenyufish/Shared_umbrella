package com.fishman.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: CurrentUser <br/>
 *
 * @author fishman
 * @date 2024/6/23 
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("currentuser")
public class CurrentUser {

    @TableField("uId")
    private int uId;
    @TableField("token")
    private String token;

}
