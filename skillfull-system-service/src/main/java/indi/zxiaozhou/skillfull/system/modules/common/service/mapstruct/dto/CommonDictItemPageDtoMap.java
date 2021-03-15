// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.common.service.mapstruct.dto;

import indi.zxiaozhou.skillfull.coremvc.base.service.mapstruct.BaseDtoMap;
import indi.zxiaozhou.skillfull.system.modules.common.entity.CommonDictItemEntity;
import indi.zxiaozhou.skillfull.system.modules.common.service.dto.CommonDictItemPageDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 数据字典配置项表(CommonDictItem)PageDto与Entity相互转换
 *
 * @author zxiaozhou
 * @since 2020-11-02 09:25:29
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommonDictItemPageDtoMap extends BaseDtoMap<CommonDictItemPageDto, CommonDictItemEntity> {
}