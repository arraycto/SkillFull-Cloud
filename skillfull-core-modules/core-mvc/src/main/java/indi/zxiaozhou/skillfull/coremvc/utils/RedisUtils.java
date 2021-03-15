// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.coremvc.utils;

import com.google.common.collect.Lists;
import indi.zxiaozhou.skillfull.corecommon.constant.Status;
import indi.zxiaozhou.skillfull.corecommon.exception.ResponseException;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * redis处理工具类
 *
 * @author zxiaozhou
 * @date 2021-02-25 17:01
 * @since JDK1.8
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class RedisUtils {
    private static RedisUtils util;
    private final RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    private void init() {
        util = this;
    }


    /**
     * 分页获取指定格式key，使用 scan 命令代替 keys 命令，在大数据量的情况下可以提高查询效率
     *
     * @param patternKey  key格式
     * @param currentPage 当前页码
     * @param pageSize    每页条数
     * @return 分页获取指定格式key
     */
    public static PageDto<String> findKeysForPage(String patternKey, int currentPage, int pageSize) {
        ScanOptions options = ScanOptions.scanOptions().match(patternKey).build();
        RedisConnectionFactory factory = util.redisTemplate.getConnectionFactory();
        if (Objects.isNull(factory)) {
            log.error("------------RedisUtil------------>findKeysForPage--->\n参数:{},异常消息:{}", patternKey, "获取redis链接工厂失败");
            throw new ResponseException(Status.ERROR, "获取redis链接工厂失败");
        }
        RedisConnection rc = factory.getConnection();
        Cursor<byte[]> cursor = rc.scan(options);
        List<String> result = Lists.newArrayList();
        long tmpIndex = 0L;
        int startIndex = (currentPage - 1) * pageSize;
        int end = currentPage * pageSize;
        while (cursor.hasNext()) {
            String key = new String(cursor.next());
            if (tmpIndex >= startIndex && tmpIndex < end) {
                result.add(key);
            }
            tmpIndex++;
        }
        try {
            cursor.close();
            RedisConnectionUtils.releaseConnection(rc, factory, false);
        } catch (Exception e) {
            log.error("------------RedisUtil-------关闭redis异常----->findKeysForPage--->\n参数:{},异常消息:{}", patternKey, e.getMessage());
            log.warn("Redis连接关闭异常，", e);
        }
        return new PageDto<>(tmpIndex, currentPage, pageSize, result);
    }


    /**
     * 删除 Redis 中的某个key
     *
     * @param key ${@link String} 待删除的key
     * @author zxiaozhou
     * @date 2021-02-25 17:04
     */
    public static void delete(String key) {
        util.redisTemplate.delete(key);
    }


    /**
     * 批量删除 Redis 中的某些key
     *
     * @param keys ${@link Collection<String>} 待删除的key集合
     * @author zxiaozhou
     * @date 2021-02-25 17:04
     */
    public static void delete(Collection<String> keys) {
        util.redisTemplate.delete(keys);
    }
}
