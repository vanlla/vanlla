package com.github.vanlla.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 菜单VO类,用户数据交换
 *
 * @author Vanlla
 * @since 0.1
 */
@Data
@ApiModel(description = "菜单管理")
public class MenuVO {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单编号
     */
    @ApiModelProperty(value = "菜单编号", example = "1")
    private Long menuId;
    /**
     * 父菜单ID
     */
    @ApiModelProperty(value = "父菜单ID", example = "1")
    private Long parentId;

    /**
     * 父菜单名称
     */
    @ApiModelProperty(value = "父菜单名称", example = "1")
    private String parentName;
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型", example = "1")
    private Integer type;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", example = "测试名称001")
    private String name;
    /**
     * 菜单URL
     */
    @ApiModelProperty(value = "菜单URL", example = "测试菜单URL001")
    private String url;
    /**
     * 授权
     */
    @ApiModelProperty(value = "授权", example = "测试授权001")
    private String perms;
    /**
     * 菜单标题
     */
    @ApiModelProperty(value = "菜单标题", example = "菜单标题001")
    private String title;
    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标", example = "测试菜单图标001")
    private String icon;
    /**
     * 1为叶子节点，0不是叶子节点
     */
    @ApiModelProperty(value = "1为叶子节点，0不是叶子节点", example = "测试1为叶子节点，0不是叶子节点001")
    private String isLeaf;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", example = "1")
    private Integer orderNo;
    /**
     * 读写权限 0为只读，1为读写
     */
    @ApiModelProperty(value = "读写权限 0为只读，1为读写", example = "测试读写权限 0为只读，1为读写001")
    private String rwAccess;
    /**
     * 是否系统菜单，0为系统菜单，1或空为业务菜单
     */
    @ApiModelProperty(value = "是否系统菜单，0为系统菜单，1或空为业务菜单", example = "测试是否系统菜单，0为系统菜单，1或空为业务菜单001")
    private String isSysmenu;

}
