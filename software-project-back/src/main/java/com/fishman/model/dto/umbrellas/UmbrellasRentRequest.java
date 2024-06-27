package com.fishman.model.dto.umbrellas;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户更新雨伞信息请求
 *
 * @author fishman
 * 
 */
@Data
public class UmbrellasRentRequest implements Serializable {

    /**
     * 用户昵称
     */
    private long umbrellaId;
    private String name;

    /**
     * todo 更新图片
     */
//    private String userAvatar;

    /**
     * 简介
     */
    private String status;

    private static final long serialVersionUID = 1L;
}