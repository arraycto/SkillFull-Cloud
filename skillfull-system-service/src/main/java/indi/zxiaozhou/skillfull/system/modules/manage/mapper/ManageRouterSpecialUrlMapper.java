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
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageRouterSpecialUrlPageVo;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageRouterSpecialUrlQueryVo;
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageRouterSpecialUrlEntity;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageRouterSpecialUrlDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageRouterSpecialUrlPageDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * 路由特殊地址(ManageRouterSpecialUrl)持久层
 *
 * @author zxiaozhou
 * @date 2020-12-31 22:50:37
 * @since JDK1.8
 */
@Repository
public interface ManageRouterSpecialUrlMapper extends BaseMapper<ManageRouterSpecialUrlEntity> {
    /**
     * 分页查询
     *
     * @param vo   ${@link ManageRouterSpecialUrlPageVo} 查询条件
     * @param page ${@link Page<ManageRouterSpecialUrlPageDto>} 分页信息
     * @return IPage<ManageRouterSpecialUrlPageDto> ${@link IPage<ManageRouterSpecialUrlPageDto>} 结果
     * @author zxiaozhou
     * @date 2020-12-31 22:50:37
     */
    IPage<ManageRouterSpecialUrlPageDto> pageByModel(Page<ManageRouterSpecialUrlPageDto> page, @Param("query") ManageRouterSpecialUrlPageVo vo);


    /**
     * 条件查询多条
     *
     * @param vo ${@link ManageRouterSpecialUrlQueryVo} 查询条件
     * @return List<ManageRouterSpecialUrlDto> ${@link List<ManageRouterSpecialUrlDto>} 结果
     * @author zxiaozhou
     * @date 2020-12-31 22:50:37
     */
    List<ManageRouterSpecialUrlDto> selectListByModel(ManageRouterSpecialUrlQueryVo vo);


    /**
     * 通过特殊路由id物理删除
     *
     * @param routeSpecialId ${@link String} 特殊路由id
     * @return int ${@link Integer} 成功状态:0-失败,1-成功
     * @author zxiaozhou
     * @date 2020-08-28 11:36
     */
    int physicalDeleteById(@Param("id") String routeSpecialId);


    /**
     * 通过特殊路由id物理批量删除
     *
     * @param idList ${@link Collection} 待删除id
     * @return int ${@link Integer} 成功状态:0-失败,大于1-成功
     * @author zxiaozhou
     * @date 2020-08-28 11:36
     */
    int physicalDeleteBatchIds(@Param("coll") Collection<String> idList);
}