// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.corecommon.utils.tree.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 树形基础类
 *
 * @author zxiaozhou
 * @date 2020-07-10 11:48
 * @since JDK11
 */
@Data
public class BaseTree<T> {

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private List<T> children;

    @ApiModelProperty(name = "isLeaf", value = "是否叶子节点,默认true")
    private boolean isLeaf = true;

    @ApiModelProperty(name = "hasChildren", value = "是否有子节点,默认false")
    private boolean hasChildren;

    public boolean getIsLeaf() {
        return this.isLeaf;
    }

    public void setIsLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }
}
