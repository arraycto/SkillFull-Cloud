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
import indi.zxiaozhou.skillfull.message.modules.manage.entity.ManageSendRecordEntity;
import indi.zxiaozhou.skillfull.message.modules.manage.service.dto.ManageSendRecordDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 消息发送记录表(ManageSendRecord)Dto与Entity相互转换
 *
 * @author zxiaozhou
 * @since 2021-02-12 19:57:40
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ManageSendRecordDtoMap extends BaseDtoMap<ManageSendRecordDto, ManageSendRecordEntity> {
}