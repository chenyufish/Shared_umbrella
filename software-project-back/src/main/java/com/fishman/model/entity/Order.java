package com.fishman.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;


/**
 * ClassName: Order <br/>
 *
 * @author fishman
 * @date 2024/6/29
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("`order`")
public class Order {
    @TableId(value = "dId",type = IdType.AUTO)
    private Integer dId;

    @TableField("dStatue")
    private Boolean dStatue;

    @TableField("dDate")
    private Timestamp dDate;

    @TableField("dAmount")
    private Double dAmount;

    @TableField("gId")
    private Integer gId;

    @TableField("bId")
    private Integer bId;
    /**
     * 租借地址
     */
    @TableField("dAddress")
    private String dAddress;


}
