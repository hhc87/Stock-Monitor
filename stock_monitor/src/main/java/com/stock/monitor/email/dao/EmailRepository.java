package com.stock.monitor.email.dao;

import com.stock.monitor.email.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Andrew He
 * @Date: Created in 10:57 2018/5/31
 * @Description:
 * @Modified by:
 */
public interface EmailRepository extends JpaRepository<Email, Long> {
}
