package com.itheima.software4.controller;

import com.itheima.software4.domain.Result;
import com.itheima.software4.domain.Tax;
import com.itheima.software4.service.TaxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/taxes")
@Api(tags = "个人所得税计算接口")
public class TaxController {

    @Autowired
    private TaxService taxService;

    @PostMapping("/calculate")
    @ApiOperation("计算个人所得税")
    public Result calculateTax(@RequestBody Tax tax) {
        Tax tax1 = taxService.calculateTax(tax);
        return Result.success(tax1);
    }
}
