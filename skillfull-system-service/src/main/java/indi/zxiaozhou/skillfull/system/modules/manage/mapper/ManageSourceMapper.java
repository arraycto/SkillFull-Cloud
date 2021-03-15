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
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageSourcePageVo;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageSourceQueryVo;
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageSourceEntity;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageSourceDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageSourcePageDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据源表(ManageSource)持久层
 *
 * @author zxiaozhou
 * @date 2020-11-02 07:58:34
 * @since JDK1.8
 */
@Repository
public interface ManageSourceMapper extends BaseMapper<ManageSourceEntity> {
    /**
     * 分页查询
     *
     * @param vo   ${@link ManageSourcePageVo} 查询条件
     * @param page ${@link Page<ManageSourcePageDto>} 分页信息
     * @return IPage<ManageSourcePageDto> ${@link IPage<ManageSourcePageDto>} 结果
     * @author zxiaozhou
     * @date 2020-11-02 07:58:34
     */
    IPage<ManageSourcePageDto> pageByModel(Page<ManageSourcePageDto> page, @Param("query") ManageSourcePageVo vo);


    /**
     * 条件查询多条
     *
     * @param vo ${@link ManageSourceQueryVo} 查询条件
     * @return List<ManageSourceDto> ${@link List<ManageSourceDto>} 结果
     * @author zxiaozhou
     * @date 2020-11-02 07:58:34
     */
    List<ManageSourceDto> selectListByModel(ManageSourceQueryVo vo);


    /**
     * 通过数据源id物理删除
     *
     * @param dataSourceId ${@link String} 数据源id
     * @return int ${@link Integer} 成功状态:0-失败,1-成功
     * @author zxiaozhou
     * @date 2020-08-28 11:36
     */
    int physicalDeleteById(@Param("id") String dataSourceId);
}