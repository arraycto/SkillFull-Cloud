// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.core.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 * 自动以processor
 *
 * @author zxiaozhou
 * @date 2020-09-13 02:01
 * @since JDK11
 */
@Component
public interface RouterUpdateProcessor {
    /**
     * 更新路由
     */
    String PROCESS_ROUTER_UPDATE = "process_router_update";


    /**
     * 更新路由
     *
     * @return MessageChannel ${@link MessageChannel}
     * @author zxiaozhou
     * @date 2020-09-13 02:06
     */
    @Output(PROCESS_ROUTER_UPDATE)
    MessageChannel updateRouteMessage();
}
