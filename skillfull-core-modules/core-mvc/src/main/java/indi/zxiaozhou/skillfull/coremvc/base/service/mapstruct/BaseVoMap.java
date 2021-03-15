// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.coremvc.base.service.mapstruct;

import java.util.List;

/**
 * Vo与Entity相互转换基类
 *
 * @author zxiaozhou
 * @date 2020-06-22 17:19
 * @since JDK11
 */
public interface BaseVoMap<V, E> {
    /**
     * Vo转Entity
     *
     * @param vo /
     * @return /
     */
    E toEntity(V vo);


    /**
     * Entity转Vo
     *
     * @param entity /
     * @return /
     */
    V toVo(E entity);


    /**
     * Vo集合转Entity集合
     *
     * @param voList /
     * @return /
     */
    List<E> toEntity(List<V> voList);


    /**
     * Entity集合转Vo集合
     *
     * @param entityList /
     * @return /
     */
    List<V> toVo(List<E> entityList);
}
