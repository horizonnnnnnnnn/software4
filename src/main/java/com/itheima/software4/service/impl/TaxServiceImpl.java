package com.itheima.software4.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.software4.domain.Tax;
import com.itheima.software4.mapper.TaxMapper;
import com.itheima.software4.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxServiceImpl implements TaxService {

    private static final double[] TAX_RATES = {0.03, 0.1, 0.2, 0.25, 0.3, 0.35, 0.45};
    private static final double[] TAX_THRESHOLDS = {3000, 12000, 25000, 35000, 55000, 80000};
    private static final double TAX_FREE_THRESHOLD = 5000;
    @Autowired
    private TaxMapper taxMapper;

    public Tax calculateTax(Tax tax) {

        QueryWrapper<Tax> wrapper = new QueryWrapper<Tax>()
                .select("income","tax")
                .eq("income",tax.getIncome());

        Tax tax2 = taxMapper.selectOne(wrapper);
        if (tax2 != null) {
            return tax2;
        }

        if (tax.getIncome() <= TAX_FREE_THRESHOLD) {
            tax.setTax(0);
            return tax;
        }
        double taxableIncome = tax.getIncome() - TAX_FREE_THRESHOLD;
        double tax1 = 0;

        for (int i = 0; i < TAX_RATES.length; i++) {
            if (taxableIncome <= TAX_THRESHOLDS[i]) {
                tax1 += taxableIncome * TAX_RATES[i];
                break;
            } else {
                tax1 += TAX_THRESHOLDS[i] * TAX_RATES[i];
                taxableIncome -= TAX_THRESHOLDS[i];
            }
        }
        tax.setTax(tax1);
        taxMapper.insert(tax);
        return tax;
    }
}
