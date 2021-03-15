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
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageServicePageVo;
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageServiceEntity;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageServicePageDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 服务管理(ManageService)持久层
 *
 * @author zxiaozhou
 * @date 2020-09-13 17:50:23
 * @since JDK1.8
 */
@Repository
public interface ManageServiceMapper extends BaseMapper<ManageServiceEntity> {
    /**
     * 分页查询
     *
     * @param vo   ${@link ManageServicePageVo} 查询条件
     * @param page ${@link Page<ManageServicePageDto>} 分页信息
     * @return IPage<ManageServicePageDto> ${@link IPage<ManageServicePageDto>} 结果
     * @author zxiaozhou
     * @date 2020-09-13 17:50:23
     */
    IPage<ManageServicePageDto> pageByModel(Page<ManageServicePageDto> page, @Param("query") ManageServicePageVo vo);


}