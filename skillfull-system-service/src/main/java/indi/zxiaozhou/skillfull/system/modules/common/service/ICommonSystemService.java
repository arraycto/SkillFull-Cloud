// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.common.service;

import indi.zxiaozhou.skillfull.coremvc.base.service.BaseService;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.system.modules.common.controller.vo.CommonSystemPageVo;
import indi.zxiaozhou.skillfull.system.modules.common.controller.vo.CommonSystemVo;
import indi.zxiaozhou.skillfull.system.modules.common.entity.CommonSystemEntity;
import indi.zxiaozhou.skillfull.system.modules.common.service.dto.CommonSystemDto;
import indi.zxiaozhou.skillfull.system.modules.common.service.dto.CommonSystemPageDto;

import java.util.List;

/**
 * 系统表(CommonSystem)业务层接口
 *
 * @author zxiaozhou
 * @date 2020-11-02 09:25:34
 * @since JDK11
 */
public interface ICommonSystemService extends BaseService<CommonSystemEntity> {
    /**
     * 保存
     *
     * @param vo ${@link CommonSystemVo} 系统表保存
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-11-02 09:25:34
     */
    void save(CommonSystemVo vo) throws RuntimeException;


    /**
     * 通过id更新
     *
     * @param vo       ${@link CommonSystemVo} 系统表更新
     * @param systemId ${@link String} 系统id
     * @param vo       ${@link CommonSystemVo} 系统表更新
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-11-02 09:25:34
     */
    void updateById(String systemId, CommonSystemVo vo) throws RuntimeException;


    /**
     * 分页查询
     *
     * @param vo ${@link CommonSystemPageVo} 系统表分页查询Vo
     * @return PageDto<CommonSystemPageDto> ${@link PageDto<CommonSystemPageDto>} 分页查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-11-02 09:25:34
     */
    PageDto<CommonSystemPageDto> pageByModel(CommonSystemPageVo vo) throws RuntimeException;


    /**
     * 查询所有系统
     *
     * @return List<CommonSystemDto> ${@link List<CommonSystemDto>} 查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-11-02 09:25:34
     */
    List<CommonSystemDto> selectListAll() throws RuntimeException;


    /**
     * 通过id查询详情
     *
     * @param systemId ${@link String} 系统id
     * @return CommonSystemDto ${@link CommonSystemDto} 查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-11-02 09:25:34
     */
    CommonSystemDto getById(String systemId) throws RuntimeException;


    /**
     * 通过systemId删除
     *
     * @param systemId ${@link String} 系统id
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-08-28 12:20
     */
    void deleteById(String systemId) throws RuntimeException;
}