package com.fishman.model.dto.umbrellas;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 创建请求
 *
 * @author fishman
 * 
 */
@Data
public class UmbrellasQueryRequest implements Serializable {

    /**
     * 雨伞id
     */
    private Long umbrellasId;

    private static final long serialVersionUID = 1L;
}