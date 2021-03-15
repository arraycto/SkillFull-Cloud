// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.rbac.service.mapstruct.vo;

import indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo.RbacCorrelateUserQueryVo;
import indi.zxiaozhou.skillfull.auth.modules.rbac.entity.RbacCorrelateUserEntity;
import indi.zxiaozhou.skillfull.coremvc.base.service.mapstruct.BaseVoMap;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 用户关联关系表(RbacCorrelateUser)QueryVo与Entity相互转换
 *
 * @author zxiaozhou
 * @since 2020-11-02 09:37:36
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RbacCorrelateUserQueryVoMap extends BaseVoMap<RbacCorrelateUserQueryVo, RbacCorrelateUserEntity> {
}