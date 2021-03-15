// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.manage.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import indi.zxiaozhou.skillfull.coremvc.base.mapper.BaseMapper;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageRoutePageVo;
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageRouteEntity;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageRoutePageDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 路由(ManageRoute)持久层
 *
 * @author zxiaozhou
 * @date 2020-09-12 16:33:35
 * @since JDK1.8
 */
@Repository
public interface ManageRouteMapper extends BaseMapper<ManageRouteEntity> {
    /**
     * 分页查询
     *
     * @param vo   ${@link ManageRoutePageVo} 查询条件
     * @param page ${@link Page<ManageRoutePageDto>} 分页信息
     * @return IPage<ManageRoutePageDto> ${@link IPage<ManageRoutePageDto>} 结果
     * @author zxiaozhou
     * @date 2020-09-12 16:33:35
     */
    IPage<ManageRoutePageDto> pageByModel(Page<ManageRoutePageDto> page, @Param("query") ManageRoutePageVo vo);
}