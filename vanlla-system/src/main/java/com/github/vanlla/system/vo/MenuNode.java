package com.github.vanlla.system.vo;

import lombok.Data;

import java.util.List;

/**
 * 权限树节点
 *
 * @author Ivan
 * @since 0.1
 */
@Data
public class MenuNode {

    private Long id;

    private String name;

    private Boolean isLeaf;

    private String title;

    private String icon;

    private String url;

    private List<MenuNode> children;


}
