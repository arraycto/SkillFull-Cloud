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

import indi.zxiaozhou.skillfull.corecommon.base.model.DataLogModel;
import indi.zxiaozhou.skillfull.coremvc.base.service.BaseService;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.logging.modules.manage.controller.vo.DataPageVo;
import indi.zxiaozhou.skillfull.logging.modules.manage.entity.DataEntity;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.dto.DataDto;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.dto.DataPageDto;

import java.util.List;

/**
 * 数据日志(Data)业务层接口
 *
 * @author zxiaozhou
 * @date 2021-01-12 14:38:51
 * @since JDK11
 */
public interface IDataService extends BaseService<DataEntity> {
    /**
     * 保存
     *
     * @param model ${@link DataLogModel}
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-01-13 00:28
     */
    void save(DataLogModel model) throws RuntimeException;

    /**
     * 分页查询
     *
     * @param vo ${@link DataPageVo} 数据日志分页查询Vo
     * @return PageDto<DataPageDto> ${@link PageDto<DataPageDto>} 分页查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-01-12 14:38:51
     */
    PageDto<DataPageDto> pageByModel(DataPageVo vo) throws RuntimeException;


    /**
     * 通过id查询详情
     *
     * @param dataLogId ${@link String} 数据日志id
     * @return DataDto ${@link DataDto} 查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-01-12 14:38:51
     */
    DataDto getById(String dataLogId) throws RuntimeException;


    /**
     * 通过dataLogId删除
     *
     * @param dataLogId ${@link String} 数据日志id
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-08-28 12:20
     */
    void deleteById(String dataLogId) throws RuntimeException;


    /**
     * 数据日志批量删除
     *
     * @param dataLogIds ${@link List<String>}
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-10-24 22:59
     */
    void deleteBatch(List<String> dataLogIds) throws RuntimeException;
}