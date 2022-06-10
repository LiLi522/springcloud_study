package com.xx.springcloud.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    /**
     * idbigint(11) NOT NULL
     * user_idbigint(11) NULL用户id
     * totaldecimal(10,0) NULL总额度
     * useddecimal(10,0) NULL已用额度
     * residuedecimal(10,0) NULL剩余可用额度
     * **/
    private Long id;

    private Long userId;//用户id

    private BigDecimal total;//总额度

    private BigDecimal used;//已用额度

    private BigDecimal residue;//剩余可用额度
}
