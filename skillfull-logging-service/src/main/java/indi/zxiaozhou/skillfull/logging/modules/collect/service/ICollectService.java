// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.logging.modules.collect.service;


import indi.zxiaozhou.skillfull.logging.modules.manage.controller.vo.DataVo;
import indi.zxiaozhou.skillfull.logging.modules.manage.controller.vo.OperateVo;

import java.util.List;

/**
 * 数据收集服务层接口
 *
 * @author zxiaozhou
 * @date 2021-01-12 15:51
 * @since JDK11
 */
public interface ICollectService {
    /**
     * 数据日志收集
     *
     * @param vos ${@link List<DataVo>} 待收集数据
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-01-12 15:58
     */
    void dataCollectBatch(List<DataVo> vos) throws RuntimeException;


    /**
     * 操作日志收集
     *
     * @param vos ${@link List<OperateVo>} 待收集数据
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-01-12 15:59
     */
    void operateCollectBatch(List<OperateVo> vos) throws RuntimeException;
}
