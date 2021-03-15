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
import indi.zxiaozhou.skillfull.message.modules.manage.controller.vo.ManageTemplatePageVo;
import indi.zxiaozhou.skillfull.message.modules.manage.controller.vo.ManageTemplateQueryVo;
import indi.zxiaozhou.skillfull.message.modules.manage.entity.ManageTemplateEntity;
import indi.zxiaozhou.skillfull.message.modules.manage.service.dto.ManageTemplateDto;
import indi.zxiaozhou.skillfull.message.modules.manage.service.dto.ManageTemplatePageDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * 消息模板(ManageTemplate)持久层
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:57:56
 * @since JDK1.8
 */
@Repository
public interface ManageTemplateMapper extends BaseMapper<ManageTemplateEntity> {
    /**
     * 分页查询
     *
     * @param vo   ${@link ManageTemplatePageVo} 查询条件
     * @param page ${@link Page<ManageTemplatePageDto>} 分页信息
     * @return IPage<ManageTemplatePageDto> ${@link IPage<ManageTemplatePageDto>} 结果
     * @author zxiaozhou
     * @date 2021-02-12 19:57:56
     */
    IPage<ManageTemplatePageDto> pageByModel(Page<ManageTemplatePageDto> page, @Param("query") ManageTemplatePageVo vo);


    /**
     * 条件查询多条
     *
     * @param vo ${@link ManageTemplateQueryVo} 查询条件
     * @return List<ManageTemplateDto> ${@link List<ManageTemplateDto>} 结果
     * @author zxiaozhou
     * @date 2021-02-12 19:57:56
     */
    List<ManageTemplateDto> selectListByModel(ManageTemplateQueryVo vo);


    /**
     * 通过模板id物理删除
     *
     * @param templateId ${@link String} 模板id
     * @return int ${@link Integer} 成功状态:0-失败,1-成功
     * @author zxiaozhou
     * @date 2020-08-28 11:36
     */
    int physicalDeleteById(@Param("id") String templateId);


    /**
     * 通过模板id物理批量删除
     *
     * @param idList ${@link Collection} 待删除id
     * @return int ${@link Integer} 成功状态:0-失败,大于1-成功
     * @author zxiaozhou
     * @date 2020-08-28 11:36
     */
    int physicalDeleteBatchIds(@Param("coll") Collection<String> idList);
}