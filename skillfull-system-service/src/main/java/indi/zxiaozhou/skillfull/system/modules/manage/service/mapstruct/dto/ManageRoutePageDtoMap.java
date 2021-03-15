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
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageRouteEntity;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageRoutePageDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 路由(ManageRoute)PageDto与Entity相互转换
 *
 * @author zxiaozhou
 * @since 2020-09-12 16:33:37
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ManageRoutePageDtoMap extends BaseDtoMap<ManageRoutePageDto, ManageRouteEntity> {
}