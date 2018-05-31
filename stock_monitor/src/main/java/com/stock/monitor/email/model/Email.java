package com.stock.monitor.email.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author Andrew He
 * @Date: Created in 10:57 2018/5/31
 * @Description:
 * @Modified by:
 */
@Entity
@Data
@Table(name = "email")
public class Email {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String emailAddress;
}
