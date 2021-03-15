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
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageServiceEntity;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageServicePageDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 服务管理(ManageService)PageDto与Entity相互转换
 *
 * @author zxiaozhou
 * @since 2020-09-13 17:50:24
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ManageServicePageDtoMap extends BaseDtoMap<ManageServicePageDto, ManageServiceEntity> {
}