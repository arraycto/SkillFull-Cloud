// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.storage.modules.oss.service.mapstruct.vo;

import indi.zxiaozhou.skillfull.coremvc.base.service.mapstruct.BaseDtoMap;
import indi.zxiaozhou.skillfull.storage.modules.oss.controller.vo.OssFileVo;
import indi.zxiaozhou.skillfull.storage.modules.oss.service.dto.OssFileUploadBatchDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * OssFileVo UploadFail
 *
 * @author zxiaozhou
 * @since 2020-10-23 22:25:31
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OssFileVoUploadFailMap extends BaseDtoMap<OssFileVo, OssFileUploadBatchDto.UploadFail> {
}