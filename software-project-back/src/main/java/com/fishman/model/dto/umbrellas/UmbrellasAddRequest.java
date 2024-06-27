package com.fishman.model.dto.umbrellas;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户创建请求
 *
 * @author fishman
 * 
 */
@Data
public class UmbrellasAddRequest implements Serializable {

    /**
     * 雨伞名字
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 雨伞状态
     */
    private String status;


    private static final long serialVersionUID = 1L;
}