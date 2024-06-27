package com.fishman.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 *
 * @author fishman
 * 
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 4117806912933978266L;

    private String uNickname;

    private String uEmail;

    private String uPhone;

    private String uPassword;

    private String uCheckPassword;

    private String uAddress;
}
