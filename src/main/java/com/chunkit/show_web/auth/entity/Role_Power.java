package com.chunkit.show_web.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role_Power {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色id
     */
    @Column(name = "roleId")
    private Integer roleid;

    /**
     * 权限id
     */
    @Column(name = "powerId")
    private Integer powerid;


}