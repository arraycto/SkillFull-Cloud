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

import indi.zxiaozhou.skillfull.auth.modules.rbac.entity.RbacPermissionDataRuleEntity;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacPermissionDataRuleDto;
import indi.zxiaozhou.skillfull.coremvc.base.service.mapstruct.BaseDtoMap;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 权限数据填值规则表(RbacPermissionDataRule)Dto与Entity相互转换
 *
 * @author zxiaozhou
 * @since 2020-11-02 09:41:30
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RbacPermissionDataRuleDtoMap extends BaseDtoMap<RbacPermissionDataRuleDto, RbacPermissionDataRuleEntity> {
}