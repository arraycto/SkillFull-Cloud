// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.common.service.mapstruct.vo;

import indi.zxiaozhou.skillfull.auth.modules.common.controller.vo.CommonUserIdentityPageVo;
import indi.zxiaozhou.skillfull.auth.modules.common.entity.CommonUserIdentityEntity;
import indi.zxiaozhou.skillfull.coremvc.base.service.mapstruct.BaseVoMap;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 实名信息表(CommonUserIdentity)PageVo与Entity相互转换
 *
 * @author zxiaozhou
 * @since 2021-02-12 19:41:47
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommonUserIdentityPageVoMap extends BaseVoMap<CommonUserIdentityPageVo, CommonUserIdentityEntity> {
}