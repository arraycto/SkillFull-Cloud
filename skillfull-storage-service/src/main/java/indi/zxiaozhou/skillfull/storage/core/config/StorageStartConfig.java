// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.storage.core.config;

import indi.zxiaozhou.skillfull.storage.core.config.properties.LocalFileProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 存储启动处理
 *
 * @author zxiaozhou
 * @date 2020-10-23 13:21
 * @since JDK11
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class StorageStartConfig implements ApplicationRunner {
    private final LocalFileProperty property;

    @Override
    public void run(ApplicationArguments args) {
        this.createSaveFileFolder();
    }


    /**
     * 传本地文件存储文件夹
     *
     * @author zxiaozhou
     * @date 2020-10-23 13:22
     */
    private void createSaveFileFolder() {
        File file = new File(property.getUploadFolder());
        if (!file.exists() || !file.isDirectory()) {
            boolean mkdirs = file.mkdirs();
            if (mkdirs) {
                log.info("------------StorageStartConfig------------>createSaveFileFolder:{}", "创建文件保存路径成功" + property.getUploadFolder());
            }
        }
    }
}
