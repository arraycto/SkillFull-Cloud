// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageRouteFilterEntity;
import indi.zxiaozhou.skillfull.system.modules.manage.mapper.ManageRouteFilterMapper;
import indi.zxiaozhou.skillfull.system.modules.manage.service.IManageRouteFilterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 路由过滤器(ManageRouteFilter)业务层实现
 *
 * @author zxiaozhou
 * @date 2020-09-12 16:33:38
 * @since JDK1.8
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class, Error.class})
public class ManageRouteFilterServiceImpl extends ServiceImpl<ManageRouteFilterMapper, ManageRouteFilterEntity> implements IManageRouteFilterService {
    private final ManageRouteFilterMapper mapper;
}