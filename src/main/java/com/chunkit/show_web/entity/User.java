package com.chunkit.show_web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
     * 用户id
     */
    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;

    /**
     * 用户名
     */
    @Column(name = "userName")
    private String username;

    /**
     * 密码
     */
    @Column(name = "passWord")
    private String password;

    /**
     * 账户名
     */
    @Column(name = "accountName")
    private String accountname;


    public Integer getUserid() {
        return userid;
    }
}