// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.common.service.mapstruct.vo;

import indi.zxiaozhou.skillfull.coremvc.base.service.mapstruct.BaseVoMap;
import indi.zxiaozhou.skillfull.system.modules.common.controller.vo.CommonCategoryQueryVo;
import indi.zxiaozhou.skillfull.system.modules.common.entity.CommonCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 分类字典表(CommonCategory)QueryVo与Entity相互转换
 *
 * @author zxiaozhou
 * @since 2021-01-07 23:40:39
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommonCategoryQueryVoMap extends BaseVoMap<CommonCategoryQueryVo, CommonCategoryEntity> {
}