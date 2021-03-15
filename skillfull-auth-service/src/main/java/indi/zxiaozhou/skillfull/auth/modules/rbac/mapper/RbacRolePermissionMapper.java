// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.rbac.mapper;

import indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo.RbacRolePermissionQueryVo;
import indi.zxiaozhou.skillfull.auth.modules.rbac.entity.RbacRolePermissionEntity;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacRolePermissionDto;
import indi.zxiaozhou.skillfull.coremvc.base.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色-权限表(RbacRolePermission)持久层
 *
 * @author zxiaozhou
 * @date 2020-10-08 13:29:15
 * @since JDK11
 */
@Repository
public interface RbacRolePermissionMapper extends BaseMapper<RbacRolePermissionEntity> {
    
    /**
     * 条件查询多条
     *
     * @param vo ${@link RbacRolePermissionQueryVo} 查询条件
     * @return List<RbacRolePermissionDto> ${@link List<RbacRolePermissionDto>} 结果
     * @author zxiaozhou
     * @date 2020-10-08 13:29:15
     */
    List<RbacRolePermissionDto> selectListByModel(@Param("query") RbacRolePermissionQueryVo vo);


    /**
     * 通过权限角色id物理删除
     *
     * @param roleId ${@link String} 角色id
     * @return int ${@link Integer} 成功状态:0-失败,1-成功
     * @author zxiaozhou
     * @date 2020-08-28 11:36
     */
    int physicalDeleteById(@Param("id") String roleId);
}