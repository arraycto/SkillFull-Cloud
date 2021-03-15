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
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageRoutePageVo;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageRouteVo;
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageRouteEntity;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageRouteDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageRoutePageDto;

import java.util.List;

/**
 * 路由(ManageRoute)业务层接口
 *
 * @author zxiaozhou
 * @date 2020-09-12 16:33:36
 * @since JDK1.8
 */
public interface IManageRouteService extends BaseService<ManageRouteEntity> {
    /**
     * 保存
     *
     * @param vo ${@link ManageRouteVo} 路由保存
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-12 16:33:36
     */
    void save(ManageRouteVo vo) throws RuntimeException;


    /**
     * 通过id更新
     *
     * @param vo      ${@link ManageRouteVo} 路由更新
     * @param routeId ${@link String} 路由id
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-12 16:33:36
     */
    void updateById(String routeId, ManageRouteVo vo) throws RuntimeException;


    /**
     * 分页查询
     *
     * @param vo ${@link ManageRoutePageVo} 路由分页查询Vo
     * @return PageDto<ManageRoutePageDto> ${@link PageDto< ManageRoutePageDto >} 分页查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-12 16:33:36
     */
    PageDto<ManageRoutePageDto> pageByModel(ManageRoutePageVo vo) throws RuntimeException;


    /**
     * 通过id查询详情
     *
     * @param routeId ${@link String} 路由id
     * @return ManageRouteDto ${@link ManageRouteDto} 查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-12 16:33:36
     */
    ManageRouteDto getById(String routeId) throws RuntimeException;


    /**
     * 通过routeId删除
     *
     * @param routeId ${@link String} 路由id
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-08-28 12:20
     */
    void deleteById(String routeId) throws RuntimeException;


    /**
     * 通过serviceId删除
     *
     * @param serviceId ${@link String} 服务id
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-08-28 12:20
     */
    void deleteByServiceId(String serviceId) throws RuntimeException;


    /**
     * 更新路由状态
     *
     * @param routeId ${@link String} 路由id
     * @param state   ${@link Integer} 操作状态:0-禁用,1-启用
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-13 00:00
     */
    void updateRouteState(String routeId, Integer state) throws RuntimeException;


    /**
     * 刷新路由
     *
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-13 00:39
     */
    void refreshRoute() throws RuntimeException;


    /**
     * 更具服务名获取所有路由信息
     *
     * @param serviceName ${@link String} 服务名
     * @return List<ManageRouteDto> ${@link List<ManageRouteDto>}
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-13 18:07
     */
    List<ManageRouteDto> getRouteAllInfoByServiceName(String serviceName) throws RuntimeException;
}