package com.stock.monitor.stock.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author Andrew He
 * @Date: Created in 15:30 2018/4/26
 * @Description:
 * @Modified by:
 */
//@Data
//@Entity
//@Table(name = "stock")
public class Stock {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "open_price")
    private float openPrice;

    @Column(name = "recent_price")
    private float recentPrice;

}
