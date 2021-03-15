// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.gateway.core.constant.typeimpl;

import indi.zxiaozhou.skillfull.corecommon.annotation.ConstantType;
import indi.zxiaozhou.skillfull.corecommon.constant.ISuperType;
import indi.zxiaozhou.skillfull.corecommon.constant.model.ConstantDictModel;
import indi.zxiaozhou.skillfull.gateway.modules.filter.*;
import lombok.Getter;
import org.springframework.cloud.gateway.filter.factory.RewritePathGatewayFilterFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器类型
 *
 * @author zxiaozhou
 * @date 2020-09-12 10:52
 * @since JDK11
 */
@Getter
@ConstantType("FilterType")
public enum FilterType implements ISuperType {
    /**
     * 鉴权过滤器
     */
    Authorize("Authorize", "鉴权过滤器", AuthorizeGatewayFilterFactory.class.getName()),

    /**
     * 解密过滤器
     */
    Decrypt("Decrypt", "处理请求解密", DecryptGatewayFilterFactory.class.getName()),

    /**
     * 加密过滤器
     */
    Encrypt("Encrypt", "处理响应加密", EncryptGatewayFilterFactory.class.getName()),

    /**
     * 验签过滤器
     */
    VerifySign("VerifySign", "处理请求验签", VerifySignGatewayFilterFactory.class.getName()),

    /**
     * 加签过滤器
     */
    AppendSign("AppendSign", "处理请求加签", AppendSignGatewayFilterFactory.class.getName()),

    /**
     * 日志记录过滤器
     */
    LogRecord("LogRecord", "日志记录过滤器", LogRecordGatewayFilterFactory.class.getName()),

    /**
     * token刷新过滤器
     */
    TokenRefresh("TokenRefresh", "处理token刷新", TokenRefreshGatewayFilterFactory.class.getName()),

    /**
     * 路由重写
     */
    RewritePath("RewritePath", "重写路由", RewritePathGatewayFilterFactory.class.getName());


    /**
     * 过滤器类型
     */
    private final String filterType;

    /**
     * 过滤器描述
     */
    private final String filterTypeDescribe;

    /**
     * 过滤器类型类名称
     */
    private final String filterTypeClassName;


    FilterType(String filterType, String filterTypeDescribe, String filterTypeClassName) {
        this.filterType = filterType;
        this.filterTypeDescribe = filterTypeDescribe;
        this.filterTypeClassName = filterTypeClassName;
    }


    /**
     * 判断某个类型是否存在
     *
     * @param filterType ${@link String} 类型
     * @return boolean true-存在,false-不存在
     * @author zxiaozhou
     * @date 2020-09-11 16:02
     */
    public static boolean isHaveByType(String filterType) {
        FilterType[] values = FilterType.values();
        for (FilterType value : values) {
            if (value.filterType.equals(filterType)) {
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
        FilterType[] values = FilterType.values();
        StringBuilder sb = new StringBuilder();
        for (FilterType value : values) {
            sb.append("、").append(value.filterType);
        }
        return sb.toString().replaceFirst("、", "");
    }


    @Override
    public List<ConstantDictModel> getConstantDict() {
        List<ConstantDictModel> constantDictDtoList = new ArrayList<>();
        FilterType[] values = FilterType.values();
        for (FilterType value : values) {
            ConstantDictModel dictDto = new ConstantDictModel();
            dictDto.setType(value.filterType);
            dictDto.setTypeDescribe(value.getFilterTypeDescribe());
            dictDto.setTypeName(value.getFilterTypeClassName());
            constantDictDtoList.add(dictDto);
        }
        return constantDictDtoList;
    }
}
