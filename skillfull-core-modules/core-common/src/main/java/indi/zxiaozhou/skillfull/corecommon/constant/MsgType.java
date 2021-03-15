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
 * 消息类型
 *
 * @author zxiaozhou
 * @date 2020-09-12 10:52
 * @since JDK11
 */
@Getter
@ConstantType("MsgType")
public enum MsgType implements ISuperType {
    /**
     * 一对一
     */
    ONE_TO_ONE(1, "一对一"),

    /**
     * 广播
     */
    BROADCAST(2, "广播"),

    /**
     * 更新token
     */
    REFRESH_TOKEN(3, "一对一");

    /**
     * 加密方式
     */
    private final int type;

    /**
     * 加密方式描述
     */
    private final String typeDescribe;


    MsgType(int type, String typeDescribe) {
        this.type = type;
        this.typeDescribe = typeDescribe;
    }


    /**
     * 判断某个类型是否存在
     *
     * @param type ${@link Integer} 类型
     * @return boolean true-存在,false-不存在
     * @author zxiaozhou
     * @date 2020-09-11 16:02
     */
    public static boolean isHaveByType(int type) {
        MsgType[] values = MsgType.values();
        for (MsgType value : values) {
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
        MsgType[] values = MsgType.values();
        StringBuilder sb = new StringBuilder();
        for (MsgType value : values) {
            sb.append("、").append(value.type);
        }
        return sb.toString().replaceFirst("、", "");
    }


    @Override
    public List<ConstantDictModel> getConstantDict() {
        List<ConstantDictModel> constantDictDtoList = new ArrayList<>();
        MsgType[] values = MsgType.values();
        for (MsgType value : values) {
            ConstantDictModel dictDto = new ConstantDictModel();
            dictDto.setType(value.getType() + "");
            dictDto.setTypeDescribe(value.getTypeDescribe());
            dictDto.setTypeName(value.getTypeDescribe());
            constantDictDtoList.add(dictDto);
        }
        return constantDictDtoList;
    }
}
