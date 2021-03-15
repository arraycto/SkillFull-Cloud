// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.message.modules.manage.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import indi.zxiaozhou.skillfull.message.core.base.mapper.BaseMapper;
import indi.zxiaozhou.skillfull.message.modules.manage.controller.vo.ManageSendRecordPageVo;
import indi.zxiaozhou.skillfull.message.modules.manage.controller.vo.ManageSendRecordQueryVo;
import indi.zxiaozhou.skillfull.message.modules.manage.entity.ManageSendRecordEntity;
import indi.zxiaozhou.skillfull.message.modules.manage.service.dto.ManageSendRecordDto;
import indi.zxiaozhou.skillfull.message.modules.manage.service.dto.ManageSendRecordPageDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * 消息发送记录表(ManageSendRecord)持久层
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:57:25
 * @since JDK1.8
 */
@Repository
public interface ManageSendRecordMapper extends BaseMapper<ManageSendRecordEntity> {
    /**
     * 分页查询
     *
     * @param vo   ${@link ManageSendRecordPageVo} 查询条件
     * @param page ${@link Page<ManageSendRecordPageDto>} 分页信息
     * @return IPage<ManageSendRecordPageDto> ${@link IPage<ManageSendRecordPageDto>} 结果
     * @author zxiaozhou
     * @date 2021-02-12 19:57:25
     */
    IPage<ManageSendRecordPageDto> pageByModel(Page<ManageSendRecordPageDto> page, @Param("query") ManageSendRecordPageVo vo);


    /**
     * 条件查询多条
     *
     * @param vo ${@link ManageSendRecordQueryVo} 查询条件
     * @return List<ManageSendRecordDto> ${@link List<ManageSendRecordDto>} 结果
     * @author zxiaozhou
     * @date 2021-02-12 19:57:25
     */
    List<ManageSendRecordDto> selectListByModel(ManageSendRecordQueryVo vo);


    /**
     * 通过消息id物理删除
     *
     * @param esId ${@link String} 消息id
     * @return int ${@link Integer} 成功状态:0-失败,1-成功
     * @author zxiaozhou
     * @date 2020-08-28 11:36
     */
    int physicalDeleteById(@Param("id") String esId);


    /**
     * 通过消息id物理批量删除
     *
     * @param idList ${@link Collection} 待删除id
     * @return int ${@link Integer} 成功状态:0-失败,大于1-成功
     * @author zxiaozhou
     * @date 2020-08-28 11:36
     */
    int physicalDeleteBatchIds(@Param("coll") Collection<String> idList);
}