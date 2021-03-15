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

import indi.zxiaozhou.skillfull.auth.modules.rbac.entity.RbacOrgEntity;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacOrgHasChildrenDto;
import indi.zxiaozhou.skillfull.coremvc.base.service.mapstruct.BaseDtoMap;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 组织表(RbacOrg)Dto与Entity相互转换
 *
 * @author zxiaozhou
 * @since 2021-01-19 12:59:57
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RbacOrgTreeHasChildrenDtoMap extends BaseDtoMap<RbacOrgHasChildrenDto, RbacOrgEntity> {
}