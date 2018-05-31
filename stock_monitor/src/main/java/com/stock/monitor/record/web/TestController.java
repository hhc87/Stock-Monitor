package com.stock.monitor.record.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Andrew He
 * @Date: Created in 14:08 2018/5/4
 * @Description:
 * @Modified by:
 */
@RestController
public class TestController {

    /**
     * docker部署后的接口测试
     *
     * @return
     */
    @RequestMapping(value = "/gay", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String rua() {
        return "Rua !!!";
    }
}
