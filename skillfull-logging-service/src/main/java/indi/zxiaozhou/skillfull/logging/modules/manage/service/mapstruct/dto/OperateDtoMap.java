// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.logging.modules.manage.service.mapstruct.dto;

import indi.zxiaozhou.skillfull.coremvc.base.service.mapstruct.BaseDtoMap;
import indi.zxiaozhou.skillfull.logging.modules.manage.entity.OperateEntity;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.dto.OperateDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 操作日志(Operate)Dto与Entity相互转换
 *
 * @author zxiaozhou
 * @since 2021-01-12 14:40:41
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OperateDtoMap extends BaseDtoMap<OperateDto, OperateEntity> {
}