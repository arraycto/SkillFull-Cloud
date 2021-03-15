// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.common.service.mapstruct.dto;

import indi.zxiaozhou.skillfull.auth.modules.common.entity.CommonUserAgentEntity;
import indi.zxiaozhou.skillfull.auth.modules.common.service.dto.CommonUserAgentPageDto;
import indi.zxiaozhou.skillfull.coremvc.base.service.mapstruct.BaseDtoMap;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 用户-代理人表(CommonUserAgent)PageDto与Entity相互转换
 *
 * @author zxiaozhou
 * @since 2021-02-12 19:40:46
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommonUserAgentPageDtoMap extends BaseDtoMap<CommonUserAgentPageDto, CommonUserAgentEntity> {
}