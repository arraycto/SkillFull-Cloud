// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.user.service.mapstruct;

import indi.zxiaozhou.skillfull.auth.modules.user.service.dto.OnlineUserInfoDto;
import indi.zxiaozhou.skillfull.corecommon.base.model.UserAndAuthModel;
import indi.zxiaozhou.skillfull.coremvc.base.service.mapstruct.BaseDtoMap;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * UserAndAuthModel与OnlineUserInfoDto互转
 *
 * @author zxiaozhou
 * @since 2020-11-02 09:37:29
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserInfoToOnlineUserInfoDtoMap extends BaseDtoMap<UserAndAuthModel, OnlineUserInfoDto> {
}