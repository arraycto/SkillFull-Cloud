// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.logging.modules.manage.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import indi.zxiaozhou.skillfull.coremvc.base.mapper.BaseMapper;
import indi.zxiaozhou.skillfull.logging.modules.manage.controller.vo.OperatePageVo;
import indi.zxiaozhou.skillfull.logging.modules.manage.entity.OperateEntity;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.dto.OperatePageDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * 操作日志(Operate)持久层
 *
 * @author zxiaozhou
 * @date 2021-01-12 14:40:24
 * @since JDK11
 */
@Repository
public interface OperateMapper extends BaseMapper<OperateEntity> {
    /**
     * 分页查询
     *
     * @param vo   ${@link OperatePageVo} 查询条件
     * @param page ${@link Page<OperatePageDto>} 分页信息
     * @return IPage<OperatePageDto> ${@link IPage<OperatePageDto>} 结果
     * @author zxiaozhou
     * @date 2021-01-12 14:40:24
     */
    IPage<OperatePageDto> pageByModel(Page<OperatePageDto> page, @Param("query") OperatePageVo vo);


    /**
     * 通过系统日志id物理删除
     *
     * @param sysLogId ${@link String} 系统日志id
     * @return int ${@link Integer} 成功状态:0-失败,1-成功
     * @author zxiaozhou
     * @date 2020-08-28 11:36
     */
    int physicalDeleteById(@Param("id") String sysLogId);


    /**
     * 通过系统日志id物理批量删除
     *
     * @param idList ${@link Collection} 待删除id
     * @return int ${@link Integer} 成功状态:0-失败,大于1-成功
     * @author zxiaozhou
     * @date 2020-08-28 11:36
     */
    int physicalDeleteBatchIds(@Param("coll") Collection<String> idList);
}