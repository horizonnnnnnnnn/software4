package com.itheima.software4.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@TableName("tax")
public class Tax {
    private double income;

    private double tax;
}
