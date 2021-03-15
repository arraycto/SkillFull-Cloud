// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.message.utils;

import cn.hutool.core.collection.CollectionUtil;
import indi.zxiaozhou.skillfull.corecommon.base.model.UserAndAuthModel;
import indi.zxiaozhou.skillfull.corecommon.constant.SysBaseConstant;
import indi.zxiaozhou.skillfull.corewebflux.utils.ContextHolderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * websocket操作redis工具类
 *
 * @author zxiaozhou
 * @date 2021-01-14 12:13
 * @since JDK11
 */
@Component
@RequiredArgsConstructor
public class WebSocketRedisUtil {
    private static WebSocketRedisUtil util;
    private final RedisTemplate<String, Object> redisTemplate;


    @PostConstruct
    private void init() {
        util = this;
    }


    /**
     * 添加链接信息到redis
     *
     * @param token ${@link String} token
     * @author zxiaozhou
     * @date 2021-01-25 16:17
     */
    public static void addChartConnect(String token) {
        String systemId = ContextHolderUtils.getUserTokenSubInfoModel(token).getSystemId();
        UserAndAuthModel userAndAuthModel = ContextHolderUtils.getUserAndAuthModel(token);
        long expires = ContextHolderUtils.getExpires(token) / 1000 + 120;
        util.redisTemplate.opsForValue().set(SysBaseConstant.WEBSOCKET_MSG_CONNECT_PREFIX + userAndAuthModel.getUserId() + "_" + systemId, SysBaseConstant.USER_LOGIN_PREFIX + userAndAuthModel.getUserId() + "_" + systemId, expires, TimeUnit.SECONDS);
    }


    /**
     * 添加链接信息到redis
     *
     * @param token ${@link String} token
     * @author zxiaozhou
     * @date 2021-01-25 16:17
     */
    public static void addMsgConnect(String token) {
        String systemId = ContextHolderUtils.getUserTokenSubInfoModel(token).getSystemId();
        UserAndAuthModel userAndAuthModel = ContextHolderUtils.getUserAndAuthModel(token);
        long expires = ContextHolderUtils.getExpires(token) / 1000 + 120;
        util.redisTemplate.opsForValue().set(SysBaseConstant.WEBSOCKET_MSG_CONNECT_PREFIX + userAndAuthModel.getUserId() + "_" + systemId, SysBaseConstant.USER_LOGIN_PREFIX + userAndAuthModel.getUserId() + "_" + systemId, expires, TimeUnit.SECONDS);
    }


    /**
     * 获取当前用户的所有连接信息
     *
     * @param userId ${@link String} 用户id
     * @return List<UserAndAuthModel>  ${@link List<UserAndAuthModel> }
     * @author zxiaozhou
     * @date 2021-01-25 16:17
     */
    public static List<UserAndAuthModel> getChartUser(String userId) {
        Set<String> keys = util.redisTemplate.keys(SysBaseConstant.WEBSOCKET_CHART_CONNECT_PREFIX + userId + "_*");
        List<UserAndAuthModel> userAndAuthModels = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(keys)) {
            keys.forEach(v -> {
                String loginKey = v.replaceFirst(SysBaseConstant.WEBSOCKET_CHART_CONNECT_PREFIX, SysBaseConstant.USER_LOGIN_PREFIX);
                Object o = util.redisTemplate.opsForValue().get(loginKey);
                if (Objects.nonNull(o)) {
                    UserAndAuthModel model = (UserAndAuthModel) o;
                    userAndAuthModels.add(model);
                }
            });
            if (CollectionUtil.isNotEmpty(userAndAuthModels)) {
                return userAndAuthModels;
            }
        }
        return Collections.emptyList();
    }


    /**
     * 获取当前用户的所有连接信息
     *
     * @param userId ${@link String} 用户id
     * @return List<UserAndAuthModel>  ${@link List<UserAndAuthModel> }
     * @author zxiaozhou
     * @date 2021-01-25 16:17
     */
    public static List<UserAndAuthModel> getMsgUser(String userId) {
        Set<String> keys = util.redisTemplate.keys(SysBaseConstant.WEBSOCKET_MSG_CONNECT_PREFIX + userId + "_*");
        List<UserAndAuthModel> userAndAuthModels = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(keys)) {
            keys.forEach(v -> {
                String loginKey = v.replaceFirst(SysBaseConstant.WEBSOCKET_MSG_CONNECT_PREFIX, SysBaseConstant.USER_LOGIN_PREFIX);
                Object o = util.redisTemplate.opsForValue().get(loginKey);
                if (Objects.nonNull(o)) {
                    UserAndAuthModel model = (UserAndAuthModel) o;
                    userAndAuthModels.add(model);
                }
            });
            if (CollectionUtil.isNotEmpty(userAndAuthModels)) {
                return userAndAuthModels;
            }
        }
        return Collections.emptyList();
    }


    /**
     * 删除用户链接信息
     *
     * @param userId   ${@link String} 用户id
     * @param systemId ${@link String} 登录系统唯一id
     * @author zxiaozhou
     * @date 2021-01-25 16:18
     */
    public static void deleteCharUser(String userId, String systemId) {
        util.redisTemplate.delete(SysBaseConstant.WEBSOCKET_CHART_CONNECT_PREFIX + userId + "_" + systemId);
    }


    /**
     * 删除用户链接信息
     *
     * @param userId   ${@link String} 用户id
     * @param systemId ${@link String} 登录系统唯一id
     * @author zxiaozhou
     * @date 2021-01-25 16:18
     */
    public static void deleteMsgUser(String userId, String systemId) {
        util.redisTemplate.delete(SysBaseConstant.WEBSOCKET_MSG_CONNECT_PREFIX + userId + "_" + systemId);
    }
}
