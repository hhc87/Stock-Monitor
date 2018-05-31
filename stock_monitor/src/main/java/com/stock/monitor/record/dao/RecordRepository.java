package com.stock.monitor.record.dao;

import com.stock.monitor.record.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @Author Andrew He
 * @Date: Created in 22:59 2018/4/26
 * @Description:
 * @Modified by:
 */
public interface RecordRepository extends JpaRepository<Record, Long> {

    Optional<Record> findById(Long Id);
}

