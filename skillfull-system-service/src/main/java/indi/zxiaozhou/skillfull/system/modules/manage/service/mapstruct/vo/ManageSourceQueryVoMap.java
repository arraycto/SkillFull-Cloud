// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.manage.service.mapstruct.vo;

import indi.zxiaozhou.skillfull.coremvc.base.service.mapstruct.BaseVoMap;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageSourceQueryVo;
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageSourceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 数据源表(ManageSource)QueryVo与Entity相互转换
 *
 * @author zxiaozhou
 * @since 2020-11-02 07:58:40
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ManageSourceQueryVoMap extends BaseVoMap<ManageSourceQueryVo, ManageSourceEntity> {
}