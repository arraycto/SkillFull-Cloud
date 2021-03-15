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
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageServicePageVo;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageServiceVo;
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageServiceEntity;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageServiceDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageServicePageDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ServiceStatDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.SwaggerInfoDto;

import java.util.List;

/**
 * 服务管理(ManageService)业务层接口
 *
 * @author zxiaozhou
 * @date 2020-09-13 17:50:24
 * @since JDK1.8
 */
public interface IManageServiceService extends BaseService<ManageServiceEntity> {
    /**
     * 保存
     *
     * @param vo ${@link ManageServiceVo} 服务管理保存
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-13 17:50:24
     */
    void save(ManageServiceVo vo) throws RuntimeException;


    /**
     * 通过id更新
     *
     * @param vo        ${@link ManageServiceVo} 服务管理更新
     * @param serviceId ${@link String} 路由id
     * @param vo        ${@link ManageServiceVo} 服务管理更新
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-13 17:50:24
     */
    void updateById(String serviceId, ManageServiceVo vo) throws RuntimeException;


    /**
     * 分页查询
     *
     * @param vo ${@link ManageServicePageVo} 服务管理分页查询Vo
     * @return PageDto<ManageServicePageDto> ${@link PageDto<ManageServicePageDto>} 分页查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-13 17:50:24
     */
    PageDto<ManageServicePageDto> pageByModel(ManageServicePageVo vo) throws RuntimeException;


    /**
     * 通过id查询详情
     *
     * @param serviceId ${@link String} 路由id
     * @return ManageServiceDto ${@link ManageServiceDto} 查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-13 17:50:24
     */
    ManageServiceDto getById(String serviceId) throws RuntimeException;


    /**
     * 通过serviceId删除
     *
     * @param serviceId ${@link String} 路由id
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-08-28 12:20
     */
    void deleteById(String serviceId) throws RuntimeException;


    /**
     * 获取swagger信息
     *
     * @return List<SwaggerInfoDto> ${@link List<SwaggerInfoDto>}
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-14 03:19
     */
    List<SwaggerInfoDto> selectSwaggerInfo() throws RuntimeException;

    /**
     * 获取服务统计
     *
     * @return ServiceStatDto ${@link ServiceStatDto}
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-01-27 16:55
     */
    ServiceStatDto serviceStat() throws RuntimeException;


    /**
     * 更新服务状态
     *
     * @param serviceId ${@link String}
     * @param state     ${@link Integer} 0-禁用,1-启用
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-01-28 14:48
     */
    void updateServiceState(String serviceId, Integer state) throws RuntimeException;
}