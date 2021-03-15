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
import indi.zxiaozhou.skillfull.message.modules.manage.service.dto.ManageTemplateDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 消息模板(ManageTemplate)Dto与Entity相互转换
 *
 * @author zxiaozhou
 * @since 2021-02-12 19:58:17
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ManageTemplateDtoMap extends BaseDtoMap<ManageTemplateDto, ManageTemplateEntity> {
}