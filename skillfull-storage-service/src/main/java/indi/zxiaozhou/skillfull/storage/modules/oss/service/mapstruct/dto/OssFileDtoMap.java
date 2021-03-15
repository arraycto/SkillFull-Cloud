// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.storage.modules.oss.service.mapstruct.dto;

import indi.zxiaozhou.skillfull.coremvc.base.service.mapstruct.BaseDtoMap;
import indi.zxiaozhou.skillfull.storage.modules.oss.entity.OssFileEntity;
import indi.zxiaozhou.skillfull.storage.modules.oss.service.dto.OssFileDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * oss文件(OssFile)Dto与Entity相互转换
 *
 * @author zxiaozhou
 * @since 2020-10-26 12:25:52
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OssFileDtoMap extends BaseDtoMap<OssFileDto, OssFileEntity> {
}