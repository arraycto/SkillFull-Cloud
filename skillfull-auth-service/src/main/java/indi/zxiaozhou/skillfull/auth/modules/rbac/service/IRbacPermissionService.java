// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.rbac.service;

import indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo.RbacPermissionPageVo;
import indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo.RbacPermissionVo;
import indi.zxiaozhou.skillfull.auth.modules.rbac.entity.RbacPermissionEntity;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacPermissionDto;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacPermissionPageDto;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacPermissionTreeDto;
import indi.zxiaozhou.skillfull.coremvc.base.service.BaseService;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;

import java.util.List;

/**
 * 权限表(Permission)业务层接口
 *
 * @author zxiaozhou
 * @date 2020-09-26 17:16:15
 * @since JDK11
 */
public interface IRbacPermissionService extends BaseService<RbacPermissionEntity> {
    /**
     * 保存
     *
     * @param vo ${@link RbacPermissionVo} 权限表保存
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-26 17:16:15
     */
    void save(RbacPermissionVo vo) throws RuntimeException;


    /**
     * 通过id更新
     *
     * @param vo           ${@link RbacPermissionVo} 权限表更新
     * @param permissionId ${@link String} 权限id
     * @param vo           ${@link RbacPermissionVo} 权限表更新
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-26 17:16:15
     */
    void updateById(String permissionId, RbacPermissionVo vo) throws RuntimeException;

    /**
     * 通过id查询详情
     *
     * @param permissionId ${@link String} 权限id
     * @return RbacPermissionDto ${@link RbacPermissionDto} 查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-26 17:16:15
     */
    RbacPermissionDto getById(String permissionId) throws RuntimeException;

    /**
     * 通过权限id修改状态
     *
     * @param permissionId ${@link String} 权限id
     * @param type         ${@link Integer} 操作类型:0-禁用,1-启用
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-10-31 20:57
     */
    void updatePermissionState(String permissionId, Integer type) throws RuntimeException;

    /**
     * 通过permissionId删除
     *
     * @param permissionId ${@link String} 权限id
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-08-28 12:20
     */
    void deleteById(String permissionId) throws RuntimeException;


    /**
     * 分页查询
     *
     * @param vo ${@link RbacPermissionPageVo} 权限表分页查询Vo
     * @return PageDto<RbacPermissionPageDto> ${@link PageDto< RbacPermissionPageDto >} 分页查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-26 17:16:15
     */
    PageDto<RbacPermissionPageDto> pageByModel(RbacPermissionPageVo vo) throws RuntimeException;


    /**
     * 通过父级id查询下级信息
     *
     * @param parentId ${@link String} 父级id
     * @return List<RbacPermissionPageDto> ${@link List< RbacPermissionPageDto >} 结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-26 19:13
     */
    List<RbacPermissionPageDto> getChildren(String parentId) throws RuntimeException;


    /**
     * 获取权限树
     *
     * @param type   ${@link Integer} 类型:1-目录、菜单,2-目录、菜单、按钮权限
     * @param status ${@link Integer} 状态:1-有效、2-所有,默认2
     * @return List<RbacPermissionTreeDto> ${@link List< RbacPermissionTreeDto >}
     * @author zxiaozhou
     * @date 2020-10-07 20:23
     */
    List<RbacPermissionTreeDto> getPermissionTree(Integer type, Integer status);
}