// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.gateway.core.constant;

/**
 * 全局公共常量
 *
 * @author zxiaozhou
 * @date 2020-07-23 19:57
 * @since JDK11
 */
public interface CommonConstant {

    String TIME_ZONE_GMT8 = "GMT+8";

    /**
     * 测试缓存key
     */
    String TEST_DEMO_CACHE = "test:demo";

    /**
     * 雪花终端id
     */
    long WORKER_ID = 12L;

    /**
     * 雪花数据中心id
     */
    long DATACENTER_ID = 12L;

    /**
     * 线程池前缀
     */
    String TASK_EXECUTOR_PREFIX = "taskExecutor-";

    /**
     * 断言
     */
    String PREDICATE_TYPE = "PredicateType";

    /**
     * 过滤器
     */
    String FILTER_TYPE = "FilterType";

    /**
     * LB类型
     */
    String LB_TYPE = "LbType";


    /**
     * 协议类型
     */
    String PROTOCOL_TYPE = "ProtocolType";


    /**
     * 负载均衡类型前缀
     */
    String LB_PREFIX = "lb:";


}
