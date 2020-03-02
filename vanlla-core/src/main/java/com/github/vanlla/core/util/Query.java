package com.github.vanlla.core.util;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.vanlla.core.xss.SQLFilter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查找Query
 *
 * @author Vanlla
 * @since 1.0
 */
public class Query<T> extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    private Page<T> page;
    private int currPage = 1;
    private int limit = 10;

    public Query(Map<String, Object> params) {
        this.putAll(params);
        if (params.get("page") != null) {
            this.currPage = Integer.parseInt((String) params.get("page"));
        }

        if (params.get("limit") != null) {
            this.limit = Integer.parseInt((String) params.get("limit"));
        }

        this.put("offset", (this.currPage - 1) * this.limit);
        this.put("page", this.currPage);
        this.put("limit", this.limit);
        String sidx = SQLFilter.sqlInject((String) params.get("sidx"));
        String order = SQLFilter.sqlInject((String) params.get("order"));
        this.put("sidx", sidx);
        this.put("order", order);
        this.page = new Page(this.currPage, this.limit);
        if (StrUtil.isNotBlank(sidx) && StrUtil.isNotBlank(order)) {
            if ("asc".equals(order)) {
                this.page.addOrder(OrderItem.asc(sidx));
            } else {
                this.page.addOrder(OrderItem.asc(sidx));
            }

        }

        //支持 +id/-id 排序方式
        String sort = (String) params.remove("sort");
        if (StrUtil.isNotBlank(sort)) {
            sidx = SQLFilter.sqlInject(StrUtil.toUnderlineCase(sort.substring(1)));
            if (sort.startsWith("+")) {
                this.page.addOrder(OrderItem.asc(sidx));
            } else {
                this.page.addOrder(OrderItem.asc(sidx));
            }
        }


    }

    public Page<T> getPage() {
        return this.page;
    }

    public int getCurrPage() {
        return this.currPage;
    }

    public int getLimit() {
        return this.limit;
    }
}