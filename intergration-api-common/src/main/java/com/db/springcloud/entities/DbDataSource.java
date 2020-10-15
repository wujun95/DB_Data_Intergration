package com.db.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wujun
 * @create 2020-10-10 21:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbDataSource {
    private String id;
    private String name;
}
