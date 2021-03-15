// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.corecommon.validation.validator;

import indi.zxiaozhou.skillfull.corecommon.validation.annotation.PathNotBlank;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 空或者null判断
 *
 * @author zxiaozhou
 * @date 2019-06-18 10:44
 * @since JDK11
 */
public class PathNotBlankValidator implements ConstraintValidator<PathNotBlank, String> {
    private static final String PATH_NULL_VALUE = "undefined";

    @Override
    public void initialize(PathNotBlank constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isBlank(value)) {
            return false;
        }
        return !PATH_NULL_VALUE.equalsIgnoreCase(value);
    }
}
