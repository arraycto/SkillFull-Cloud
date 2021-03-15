// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.coremvc.base.controller.vo;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import indi.zxiaozhou.skillfull.corecommon.utils.CoreCommonUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


/**
 * 分页基类vo
 *
 * @author zxiaozhou
 * @date 2020-06-22 15:27
 * @since JDK11
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public class BasePageVo implements Serializable {
    private static final long serialVersionUID = 6447362475456626689L;

    @ApiModelProperty(name = "current", value = "当前页", example = "0")
    private int current = 0;

    @ApiModelProperty(name = "startTime", value = "开始时间", example = "2020-11-12 11:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;

    @ApiModelProperty(name = "endTime", value = "结束时间", example = "2020-12-12 11:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;

    @ApiModelProperty(name = "size", value = "每页条数", example = "10")
    private int size = 10;

    @ApiModelProperty(name = "descs", value = "降序字段列表")
    private Set<String> descs;

    @ApiModelProperty(name = "ascs", value = "升序字段列表")
    private Set<String> ascs;

    @ApiModelProperty(name = "getPage", value = "myBatisPlus分页信息", hidden = true)
    public <T> Page<T> getPage() {
        Page<T> page = new Page<>(this.current, this.size);
        int aesLength = CollectionUtil.isNotEmpty(ascs) ? ascs.size() : 0;
        // 默认排序字段
        String createTime = "create_time";
        if (aesLength > 0) {
            Set<String> middleAscs = new HashSet<>();
            ascs.forEach(v -> middleAscs.add(CoreCommonUtils.humpToUnderline(v)));
            // 时间排序最近到最后,避免排序不稳定
            middleAscs.add(createTime);
            page.addOrder(OrderItem.ascs(middleAscs.toArray(new String[0])));
        } else {
            Set<String> middleDesc = new HashSet<>();
            if (CollectionUtil.isNotEmpty(descs)) {
                descs.forEach(v -> middleDesc.add(CoreCommonUtils.humpToUnderline(v)));
            }
            // 时间排序最近到最后,避免排序不稳定
            middleDesc.add(createTime);
            page.addOrder(OrderItem.descs(middleDesc.toArray(new String[0])));
        }
        return page;
    }
}
