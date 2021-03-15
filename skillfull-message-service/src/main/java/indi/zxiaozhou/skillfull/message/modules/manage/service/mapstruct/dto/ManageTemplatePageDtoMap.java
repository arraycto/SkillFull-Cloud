// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.message.modules.manage.service.mapstruct.dto;


import indi.zxiaozhou.skillfull.message.core.base.service.mapstruct.BaseDtoMap;
import indi.zxiaozhou.skillfull.message.modules.manage.entity.ManageTemplateEntity;
import indi.zxiaozhou.skillfull.message.modules.manage.service.dto.ManageTemplatePageDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 消息模板(ManageTemplate)PageDto与Entity相互转换
 *
 * @author zxiaozhou
 * @since 2021-02-12 19:58:18
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ManageTemplatePageDtoMap extends BaseDtoMap<ManageTemplatePageDto, ManageTemplateEntity> {
}