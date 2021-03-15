// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.rbac.service.mapstruct.dto;

import indi.zxiaozhou.skillfull.auth.modules.rbac.entity.RbacPositionEntity;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacPositionDto;
import indi.zxiaozhou.skillfull.coremvc.base.service.mapstruct.BaseDtoMap;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 职位表(RbacPosition)Dto与Entity相互转换
 *
 * @author zxiaozhou
 * @since 2021-01-19 18:17:59
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RbacPositionDtoMap extends BaseDtoMap<RbacPositionDto, RbacPositionEntity> {
}