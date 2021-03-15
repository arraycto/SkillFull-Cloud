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

import indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo.RbacUserPageVo;
import indi.zxiaozhou.skillfull.auth.modules.rbac.entity.RbacUserEntity;
import indi.zxiaozhou.skillfull.coremvc.base.service.mapstruct.BaseVoMap;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 用户表(User)PageVo与Entity相互转换
 *
 * @author zxiaozhou
 * @since 2020-09-26 17:16:18
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RbacUserPageVoMap extends BaseVoMap<RbacUserPageVo, RbacUserEntity> {
}