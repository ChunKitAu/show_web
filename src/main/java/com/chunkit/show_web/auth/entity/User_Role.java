package com.chunkit.show_web.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User_Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户Id
     */
    @Column(name = "userId")
    private Integer userid;

    /**
     * 角色Id
     */
    @Column(name = "roleId")
    private Integer roleid;


}