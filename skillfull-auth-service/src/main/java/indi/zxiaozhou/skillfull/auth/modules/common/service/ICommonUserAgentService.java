// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.common.service;

import indi.zxiaozhou.skillfull.auth.modules.common.controller.vo.CommonUserAgentPageVo;
import indi.zxiaozhou.skillfull.auth.modules.common.controller.vo.CommonUserAgentQueryVo;
import indi.zxiaozhou.skillfull.auth.modules.common.controller.vo.CommonUserAgentVo;
import indi.zxiaozhou.skillfull.auth.modules.common.entity.CommonUserAgentEntity;
import indi.zxiaozhou.skillfull.auth.modules.common.service.dto.CommonUserAgentDto;
import indi.zxiaozhou.skillfull.auth.modules.common.service.dto.CommonUserAgentPageDto;
import indi.zxiaozhou.skillfull.coremvc.base.service.BaseService;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;

import java.util.List;

/**
 * 用户-代理人表(CommonUserAgent)业务层接口
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:40:43
 * @since JDK1.8
 */
public interface ICommonUserAgentService extends BaseService<CommonUserAgentEntity> {
    /**
     * 保存
     *
     * @param vo ${@link CommonUserAgentVo} 用户-代理人表保存
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-02-12 19:40:43
     */
    void save(CommonUserAgentVo vo) throws RuntimeException;


    /**
     * 通过id更新
     *
     * @param vo      ${@link CommonUserAgentVo} 用户-代理人表更新
     * @param agentId ${@link String} 代理id
     * @param vo      ${@link CommonUserAgentVo} 用户-代理人表更新
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-02-12 19:40:43
     */
    void updateById(String agentId, CommonUserAgentVo vo) throws RuntimeException;


    /**
     * 分页查询
     *
     * @param vo ${@link CommonUserAgentPageVo} 用户-代理人表分页查询Vo
     * @return PageDto<CommonUserAgentPageDto> ${@link PageDto<CommonUserAgentPageDto>} 分页查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-02-12 19:40:43
     */
    PageDto<CommonUserAgentPageDto> pageByModel(CommonUserAgentPageVo vo) throws RuntimeException;


    /**
     * 分页查询
     *
     * @param vo ${@link CommonUserAgentPageVo} 用户-代理人表分页查询Vo
     * @return PageDto<CommonUserAgentPageDto> ${@link PageDto<CommonUserAgentPageDto>} 分页查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-02-12 19:40:43
     */
    PageDto<CommonUserAgentPageDto> page(CommonUserAgentPageVo vo) throws RuntimeException;


    /**
     * 条件查询多条
     *
     * @param vo ${@link CommonUserAgentQueryVo} 用户-代理人表条件查询Vo
     * @return List<CommonUserAgentDto> ${@link List<CommonUserAgentDto>} 查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-02-12 19:40:43
     */
    List<CommonUserAgentDto> selectListByModel(CommonUserAgentQueryVo vo) throws RuntimeException;


    /**
     * 通过id查询详情
     *
     * @param agentId ${@link String} 代理id
     * @return CommonUserAgentDto ${@link CommonUserAgentDto} 查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-02-12 19:40:43
     */
    CommonUserAgentDto getById(String agentId) throws RuntimeException;


    /**
     * 通过agentId删除
     *
     * @param agentId ${@link String} 代理id
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-08-28 12:20
     */
    void deleteById(String agentId) throws RuntimeException;


    /**
     * 用户-代理人表批量删除
     *
     * @param agentIds ${@link List<String>}
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-10-24 22:59
     */
    void deleteBatch(List<String> agentIds) throws RuntimeException;
}