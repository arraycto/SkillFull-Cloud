// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.logging.modules.manage.service;

import indi.zxiaozhou.skillfull.corecommon.base.model.OperateLogModel;
import indi.zxiaozhou.skillfull.coremvc.base.service.BaseService;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.logging.modules.manage.controller.vo.OperatePageVo;
import indi.zxiaozhou.skillfull.logging.modules.manage.entity.OperateEntity;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.dto.OperateDto;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.dto.OperatePageDto;

import java.util.List;

/**
 * 操作日志(Operate)业务层接口
 *
 * @author zxiaozhou
 * @date 2021-01-12 14:40:38
 * @since JDK11
 */
public interface IOperateService extends BaseService<OperateEntity> {
    /**
     * 保存
     *
     * @param model ${@link OperateLogModel}
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-01-13 00:28
     */
    void save(OperateLogModel model) throws RuntimeException;


    /**
     * 分页查询
     *
     * @param vo ${@link OperatePageVo} 操作日志分页查询Vo
     * @return PageDto<OperatePageDto> ${@link PageDto<OperatePageDto>} 分页查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-01-12 14:40:38
     */
    PageDto<OperatePageDto> pageByModel(OperatePageVo vo) throws RuntimeException;


    /**
     * 通过id查询详情
     *
     * @param sysLogId ${@link String} 系统日志id
     * @return OperateDto ${@link OperateDto} 查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-01-12 14:40:38
     */
    OperateDto getById(String sysLogId) throws RuntimeException;


    /**
     * 通过sysLogId删除
     *
     * @param sysLogId ${@link String} 系统日志id
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-08-28 12:20
     */
    void deleteById(String sysLogId) throws RuntimeException;


    /**
     * 操作日志批量删除
     *
     * @param sysLogIds ${@link List<String>}
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-10-24 22:59
     */
    void deleteBatch(List<String> sysLogIds) throws RuntimeException;
}