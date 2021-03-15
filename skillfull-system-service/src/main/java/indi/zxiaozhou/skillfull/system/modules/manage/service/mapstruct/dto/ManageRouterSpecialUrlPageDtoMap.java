// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.manage.service.mapstruct.dto;

import indi.zxiaozhou.skillfull.coremvc.base.service.mapstruct.BaseDtoMap;
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageRouterSpecialUrlEntity;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageRouterSpecialUrlPageDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 路由特殊地址(ManageRouterSpecialUrl)PageDto与Entity相互转换
 *
 * @author zxiaozhou
 * @since 2020-12-31 22:50:59
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ManageRouterSpecialUrlPageDtoMap extends BaseDtoMap<ManageRouterSpecialUrlPageDto, ManageRouterSpecialUrlEntity> {
}