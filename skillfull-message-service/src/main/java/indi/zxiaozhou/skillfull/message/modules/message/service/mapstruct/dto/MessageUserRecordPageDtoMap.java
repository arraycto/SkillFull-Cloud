// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.message.modules.message.service.mapstruct.dto;

import indi.zxiaozhou.skillfull.message.core.base.service.mapstruct.BaseDtoMap;
import indi.zxiaozhou.skillfull.message.modules.message.entity.MessageUserRecordEntity;
import indi.zxiaozhou.skillfull.message.modules.message.service.dto.MessageUserRecordPageDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 用户消息记录(MessageUserRecord)PageDto与Entity相互转换
 *
 * @author zxiaozhou
 * @since 2021-01-26 16:48:42
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageUserRecordPageDtoMap extends BaseDtoMap<MessageUserRecordPageDto, MessageUserRecordEntity> {
}