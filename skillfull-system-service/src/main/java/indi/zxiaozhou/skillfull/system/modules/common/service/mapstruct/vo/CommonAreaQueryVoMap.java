// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.common.service.mapstruct.vo;

import indi.zxiaozhou.skillfull.coremvc.base.service.mapstruct.BaseVoMap;
import indi.zxiaozhou.skillfull.system.modules.common.controller.vo.CommonAreaQueryVo;
import indi.zxiaozhou.skillfull.system.modules.common.entity.CommonAreaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 区域表(CommonArea)QueryVo与Entity相互转换
 *
 * @author zxiaozhou
 * @since 2020-11-02 09:25:08
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommonAreaQueryVoMap extends BaseVoMap<CommonAreaQueryVo, CommonAreaEntity> {
}