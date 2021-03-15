// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.corecommon.constant;

import indi.zxiaozhou.skillfull.corecommon.annotation.ConstantType;
import indi.zxiaozhou.skillfull.corecommon.constant.model.ConstantDictModel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息业务类型
 *
 * @author zxiaozhou
 * @date 2020-09-12 10:52
 * @since JDK11
 */
@Getter
@ConstantType("MsgBusinessType")
public enum MsgBusinessType implements ISuperType {
    /**
     * 退出登录
     */
    LOGOUT("logout", "退出登录"),

    /**
     * 刷新消息
     */
    REFRESH_NOTICE("refresh_notice", "刷新消息");

    /**
     * 加密方式
     */
    private final String type;

    /**
     * 加密方式描述
     */
    private final String typeDescribe;


    MsgBusinessType(String type, String typeDescribe) {
        this.type = type;
        this.typeDescribe = typeDescribe;
    }


    /**
     * 判断某个类型是否存在
     *
     * @param type ${@link String} 类型
     * @return boolean true-存在,false-不存在
     * @author zxiaozhou
     * @date 2020-09-11 16:02
     */
    public static boolean isHaveByType(String type) {
        MsgBusinessType[] values = MsgBusinessType.values();
        for (MsgBusinessType value : values) {
            if (value.type.equals(type)) {
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
        MsgBusinessType[] values = MsgBusinessType.values();
        StringBuilder sb = new StringBuilder();
        for (MsgBusinessType value : values) {
            sb.append("、").append(value.type);
        }
        return sb.toString().replaceFirst("、", "");
    }


    @Override
    public List<ConstantDictModel> getConstantDict() {
        List<ConstantDictModel> constantDictDtoList = new ArrayList<>();
        MsgBusinessType[] values = MsgBusinessType.values();
        for (MsgBusinessType value : values) {
            ConstantDictModel dictDto = new ConstantDictModel();
            dictDto.setType(value.getType());
            dictDto.setTypeDescribe(value.getTypeDescribe());
            dictDto.setTypeName(value.getTypeDescribe());
            constantDictDtoList.add(dictDto);
        }
        return constantDictDtoList;
    }
}
