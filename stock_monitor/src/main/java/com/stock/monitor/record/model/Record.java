package com.stock.monitor.record.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author Andrew He
 * @Date: Created in 22:52 2018/4/26
 * @Description:
 * @Modified by:
 */
@Data
@Entity
@Table(name = "record")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "send_time")
    private String sendTime;

    @Column(name = "aim_address")
    private String aimAddress;
}
