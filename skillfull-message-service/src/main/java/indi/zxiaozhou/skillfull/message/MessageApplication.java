// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.message;

import indi.zxiaozhou.skillfull.corecommon.utils.CoreCommonUtils;
import indi.zxiaozhou.skillfull.corewebflux.annotation.SkillfulCloudApplication;
import indi.zxiaozhou.skillfull.corewebflux.message.LogSendProcessor;
import indi.zxiaozhou.skillfull.corewebflux.message.MessageSendProcessor;
import indi.zxiaozhou.skillfull.message.core.message.ReceiveMsgProcessor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * start
 *
 * @author zxiaozhou
 * @date 2020-09-10 01:10
 * @since JDK11
 */
@Slf4j
@EnableBinding(value = {LogSendProcessor.class, MessageSendProcessor.class, ReceiveMsgProcessor.class})
@SkillfulCloudApplication
@MapperScan("indi.zxiaozhou.skillfull.*.modules.*.mapper")
@EnableTransactionManagement
public class MessageApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(MessageApplication.class, args);
        CoreCommonUtils.printSysInfo(application);
    }
}
