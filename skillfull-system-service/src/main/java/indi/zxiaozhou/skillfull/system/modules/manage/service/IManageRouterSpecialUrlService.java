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
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageRouterSpecialUrlPageVo;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageRouterSpecialUrlQueryVo;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageRouterSpecialUrlVo;
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageRouterSpecialUrlEntity;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageRouterSpecialUrlDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageRouterSpecialUrlPageDto;

import java.util.List;

/**
 * 路由特殊地址(ManageRouterSpecialUrl)业务层接口
 *
 * @author zxiaozhou
 * @date 2020-12-31 22:50:51
 * @since JDK1.8
 */
public interface IManageRouterSpecialUrlService extends BaseService<ManageRouterSpecialUrlEntity> {
    /**
     * 保存
     *
     * @param vo ${@link ManageRouterSpecialUrlVo} 路由特殊地址保存
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-12-31 22:50:51
     */
    void save(ManageRouterSpecialUrlVo vo) throws RuntimeException;


    /**
     * 通过id更新
     *
     * @param vo             ${@link ManageRouterSpecialUrlVo} 路由特殊地址更新
     * @param routeSpecialId ${@link String} 特殊路由id
     * @param vo             ${@link ManageRouterSpecialUrlVo} 路由特殊地址更新
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-12-31 22:50:51
     */
    void updateById(String routeSpecialId, ManageRouterSpecialUrlVo vo) throws RuntimeException;


    /**
     * 分页查询
     *
     * @param vo ${@link ManageRouterSpecialUrlPageVo} 路由特殊地址分页查询Vo
     * @return PageDto<ManageRouterSpecialUrlPageDto> ${@link PageDto<ManageRouterSpecialUrlPageDto>} 分页查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-12-31 22:50:51
     */
    PageDto<ManageRouterSpecialUrlPageDto> pageByModel(ManageRouterSpecialUrlPageVo vo) throws RuntimeException;


    /**
     * 分页查询
     *
     * @param vo ${@link ManageRouterSpecialUrlPageVo} 路由特殊地址分页查询Vo
     * @return PageDto<ManageRouterSpecialUrlPageDto> ${@link PageDto<ManageRouterSpecialUrlPageDto>} 分页查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-12-31 22:50:51
     */
    PageDto<ManageRouterSpecialUrlPageDto> page(ManageRouterSpecialUrlPageVo vo) throws RuntimeException;


    /**
     * 条件查询多条
     *
     * @param vo ${@link ManageRouterSpecialUrlQueryVo} 路由特殊地址条件查询Vo
     * @return List<ManageRouterSpecialUrlDto> ${@link List<ManageRouterSpecialUrlDto>} 查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-12-31 22:50:51
     */
    List<ManageRouterSpecialUrlDto> selectListByModel(ManageRouterSpecialUrlQueryVo vo) throws RuntimeException;


    /**
     * 通过id查询详情
     *
     * @param routeSpecialId ${@link String} 特殊路由id
     * @return ManageRouterSpecialUrlDto ${@link ManageRouterSpecialUrlDto} 查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-12-31 22:50:51
     */
    ManageRouterSpecialUrlDto getById(String routeSpecialId) throws RuntimeException;


    /**
     * 通过routeSpecialId删除
     *
     * @param routeSpecialId ${@link String} 特殊路由id
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-08-28 12:20
     */
    void deleteById(String routeSpecialId) throws RuntimeException;


    /**
     * 路由特殊地址批量删除
     *
     * @param routeSpecialIds ${@link List<String>}
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-10-24 22:59
     */
    void deleteBatch(List<String> routeSpecialIds) throws RuntimeException;
}