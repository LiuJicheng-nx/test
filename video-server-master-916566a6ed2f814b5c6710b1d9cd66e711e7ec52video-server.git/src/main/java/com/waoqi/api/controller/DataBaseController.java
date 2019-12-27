package com.waoqi.api.controller;

import com.waoqi.common.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shiliang.gao
 * @since 2019-11-25
 */
@RestController()
@RequestMapping("/ds/v1")
public class DataBaseController {

    @GetMapping("/hello")
    Result AppVersion() {
        return Result.success("这是数据");
    }

}
