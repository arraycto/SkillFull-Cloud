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
import indi.zxiaozhou.skillfull.logging.modules.manage.controller.vo.DataPageVo;
import indi.zxiaozhou.skillfull.logging.modules.manage.entity.DataEntity;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.dto.DataPageDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * 数据日志(Data)持久层
 *
 * @author zxiaozhou
 * @date 2021-01-12 14:38:35
 * @since JDK11
 */
@Repository
public interface DataMapper extends BaseMapper<DataEntity> {
    /**
     * 分页查询
     *
     * @param vo   ${@link DataPageVo} 查询条件
     * @param page ${@link Page<DataPageDto>} 分页信息
     * @return IPage<DataPageDto> ${@link IPage<DataPageDto>} 结果
     * @author zxiaozhou
     * @date 2021-01-12 14:38:35
     */
    IPage<DataPageDto> pageByModel(Page<DataPageDto> page, @Param("query") DataPageVo vo);


    /**
     * 通过数据日志id物理删除
     *
     * @param dataLogId ${@link String} 数据日志id
     * @return int ${@link Integer} 成功状态:0-失败,1-成功
     * @author zxiaozhou
     * @date 2020-08-28 11:36
     */
    int physicalDeleteById(@Param("id") String dataLogId);


    /**
     * 通过数据日志id物理批量删除
     *
     * @param idList ${@link Collection} 待删除id
     * @return int ${@link Integer} 成功状态:0-失败,大于1-成功
     * @author zxiaozhou
     * @date 2020-08-28 11:36
     */
    int physicalDeleteBatchIds(@Param("coll") Collection<String> idList);
}