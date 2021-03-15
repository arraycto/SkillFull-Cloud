// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.security.mapstruct;


import indi.zxiaozhou.skillfull.auth.modules.rbac.entity.RbacUserEntity;
import indi.zxiaozhou.skillfull.auth.security.model.TokenUserInfo;
import indi.zxiaozhou.skillfull.coremvc.base.service.mapstruct.BaseDtoMap;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * TokenUserInfo.UserInfo与UserEntity相互转换
 *
 * @author zxiaozhou
 * @since 2020-07-18 22:17:06
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserInfoMap extends BaseDtoMap<TokenUserInfo.UserInfo, RbacUserEntity> {
}