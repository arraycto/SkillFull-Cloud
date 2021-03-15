// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.common.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import indi.zxiaozhou.skillfull.coremvc.base.mapper.BaseMapper;
import indi.zxiaozhou.skillfull.system.modules.common.controller.vo.CommonSystemPageVo;
import indi.zxiaozhou.skillfull.system.modules.common.entity.CommonSystemEntity;
import indi.zxiaozhou.skillfull.system.modules.common.service.dto.CommonSystemPageDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 系统表(CommonSystem)持久层
 *
 * @author zxiaozhou
 * @date 2020-11-02 09:25:32
 * @since JDK11
 */
@Repository
public interface CommonSystemMapper extends BaseMapper<CommonSystemEntity> {
    /**
     * 分页查询
     *
     * @param vo   ${@link CommonSystemPageVo} 查询条件
     * @param page ${@link Page<CommonSystemPageDto>} 分页信息
     * @return IPage<CommonSystemPageDto> ${@link IPage<CommonSystemPageDto>} 结果
     * @author zxiaozhou
     * @date 2020-11-02 09:25:32
     */
    IPage<CommonSystemPageDto> pageByModel(Page<CommonSystemPageDto> page, @Param("query") CommonSystemPageVo vo);

    
    /**
     * 通过系统id物理删除
     *
     * @param systemId ${@link String} 系统id
     * @return int ${@link Integer} 成功状态:0-失败,1-成功
     * @author zxiaozhou
     * @date 2020-08-28 11:36
     */
    int physicalDeleteById(@Param("id") String systemId);
}