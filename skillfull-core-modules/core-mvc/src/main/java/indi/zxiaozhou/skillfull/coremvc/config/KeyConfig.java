// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.coremvc.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import indi.zxiaozhou.skillfull.coremvc.constant.CoreMvcConstant;
import indi.zxiaozhou.skillfull.coremvc.utils.CoreMvcStringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * key配置
 *
 * @author zxiaozhou
 * @date 2020-08-28 10:23
 * @since JDK11
 */
@Configuration
public class KeyConfig {
    /**
     * 雪花生成器
     *
     * @return Snowflake ${@link Snowflake}
     * @author zxiaozhou
     * @date 2020-06-22 14:42
     */
    @Bean
    public Snowflake snowflake() {
        return IdUtil.getSnowflake(CoreMvcConstant.WORKER_ID, CoreMvcConstant.DATACENTER_ID);
    }


    /**
     * 自定义mybatis plus id生成器
     *
     * @return IdentifierGenerator ${@link IdentifierGenerator}
     * @author zxiaozhou
     * @date 2020-08-29 01:34
     */
    @Bean
    public IdentifierGenerator idGenerator() {
        return new CustomIdGenerator(snowflake());
    }

    static class CustomIdGenerator implements IdentifierGenerator {
        private final Snowflake snowflake;

        public CustomIdGenerator(Snowflake snowflake) {
            this.snowflake = snowflake;
        }

        @Override
        public Number nextId(Object entity) {
            return snowflake.nextId();
        }

        @Override
        public String nextUUID(Object entity) {
            return CoreMvcStringUtils.get32UUId();
        }
    }

}
