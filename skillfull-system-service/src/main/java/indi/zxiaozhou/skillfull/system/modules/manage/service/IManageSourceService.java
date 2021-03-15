// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.manage.service;

import indi.zxiaozhou.skillfull.coremvc.base.service.BaseService;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageSourcePageVo;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageSourceQueryVo;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageSourceVo;
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageSourceEntity;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageSourceDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageSourcePageDto;

import java.util.List;

/**
 * 数据源表(ManageSource)业务层接口
 *
 * @author zxiaozhou
 * @date 2020-11-02 07:58:35
 * @since JDK1.8
 */
public interface IManageSourceService extends BaseService<ManageSourceEntity> {
    /**
     * 保存
     *
     * @param vo ${@link ManageSourceVo} 数据源表保存
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-11-02 07:58:35
     */
    void save(ManageSourceVo vo) throws RuntimeException;


    /**
     * 通过id更新
     *
     * @param vo           ${@link ManageSourceVo} 数据源表更新
     * @param dataSourceId ${@link String} 数据源id
     * @param vo           ${@link ManageSourceVo} 数据源表更新
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-11-02 07:58:35
     */
    void updateById(String dataSourceId, ManageSourceVo vo) throws RuntimeException;


    /**
     * 分页查询
     *
     * @param vo ${@link ManageSourcePageVo} 数据源表分页查询Vo
     * @return PageDto<ManageSourcePageDto> ${@link PageDto<ManageSourcePageDto>} 分页查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-11-02 07:58:35
     */
    PageDto<ManageSourcePageDto> pageByModel(ManageSourcePageVo vo) throws RuntimeException;


    /**
     * 分页查询
     *
     * @param vo ${@link ManageSourcePageVo} 数据源表分页查询Vo
     * @return PageDto<ManageSourcePageDto> ${@link PageDto<ManageSourcePageDto>} 分页查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-11-02 07:58:35
     */
    PageDto<ManageSourcePageDto> page(ManageSourcePageVo vo) throws RuntimeException;


    /**
     * 条件查询多条
     *
     * @param vo ${@link ManageSourceQueryVo} 数据源表条件查询Vo
     * @return List<ManageSourceDto> ${@link List<ManageSourceDto>} 查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-11-02 07:58:35
     */
    List<ManageSourceDto> selectListByModel(ManageSourceQueryVo vo) throws RuntimeException;


    /**
     * 通过id查询详情
     *
     * @param dataSourceId ${@link String} 数据源id
     * @return ManageSourceDto ${@link ManageSourceDto} 查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-11-02 07:58:35
     */
    ManageSourceDto getById(String dataSourceId) throws RuntimeException;


    /**
     * 通过dataSourceId删除
     *
     * @param dataSourceId ${@link String} 数据源id
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-08-28 12:20
     */
    void deleteById(String dataSourceId) throws RuntimeException;


    /**
     * 文件批量删除
     *
     * @param dataSourceIds ${@link List<String>}
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-10-24 22:59
     */
    void deleteBatch(List<String> dataSourceIds) throws RuntimeException;
}