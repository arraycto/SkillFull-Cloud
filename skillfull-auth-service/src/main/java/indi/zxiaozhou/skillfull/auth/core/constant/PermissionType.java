// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.core.constant;

import lombok.Getter;

/**
 * 权限类型
 *
 * @author zxiaozhou
 * @date 2020-09-12 10:52
 * @since JDK11
 */
@Getter
public enum PermissionType {
    /**
     * 目录
     */
    PARENT_MENU(0, "目录"),

    /**
     * 菜单
     */
    SUB_MENU(1, "菜单"),

    /**
     * 按钮权限
     */
    BOTTOM(2, "按钮权限");

    /**
     * 类型
     */
    private final int type;

    /**
     * 描述
     */
    private final String describe;

    PermissionType(int type, String describe) {
        this.type = type;
        this.describe = describe;
    }


    /**
     * 判断某个类型是否存在
     *
     * @param type ${@link String} 类型
     * @return boolean true-存在,false-不存在
     * @author zxiaozhou
     * @date 2020-09-11 16:02
     */
    public static boolean isHaveByType(int type) {
        PermissionType[] values = PermissionType.values();
        for (PermissionType value : values) {
            if (value.type == type) {
                return true;
            }
        }
        return false;
    }


    /**
     * 获取所有的类型
     *
     * @return String ${@link String} 拼接为字符串返回,多个顿号隔开
     * @author zxiaozhou
     * @date 2020-09-11 16:45
     */
    public static String getAllType() {
        PermissionType[] values = PermissionType.values();
        StringBuilder sb = new StringBuilder();
        for (PermissionType value : values) {
            sb.append("、").append(value.type);
        }
        return sb.toString().replaceFirst("、", "");
    }
}
