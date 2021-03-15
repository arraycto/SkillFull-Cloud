// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.coremvc.base.service.dto;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页结果
 *
 * @author zxiaozhou
 * @date 2020-06-23 15:36
 * @since JDK11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PageDto<T> implements Serializable {
    private static final long serialVersionUID = -460269613252565409L;

    /**
     * 数据列表
     */
    private List<T> records = Collections.emptyList();

    /**
     * 总数
     */
    private long total = 0L;

    /**
     * 每页显示条数
     */
    private int size = 10;

    /**
     * 当前页
     */
    private int current = 1;


    public PageDto(IPage<T> page) {
        this.total = page.getTotal();
        this.current = (int) page.getCurrent();
        this.size = (int) page.getSize();
        if (CollectionUtil.isNotEmpty(page.getRecords())) {
            this.records = page.getRecords();
        }
    }


    public PageDto(long total, int current, int size, List<T> records) {
        this.total = total;
        this.current = current;
        this.size = size;
        if (CollectionUtil.isNotEmpty(records)) {
            this.records = records;
        }

    }


    public PageDto(IPage<?> page, List<T> records) {
        this.total = page.getTotal();
        this.current = (int) page.getCurrent();
        this.size = (int) page.getSize();
        if (CollectionUtil.isNotEmpty(records)) {
            this.records = records;
        }
    }

}
