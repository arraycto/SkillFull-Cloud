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
 * Dto与Entity相互转换基类
 *
 * @author zxiaozhou
 * @date 2020-06-22 17:19
 * @since JDK11
 */
public interface BaseDtoMap<D, E> {
    /**
     * Dto转Entity
     *
     * @param dto /
     * @return /
     */
    E toEntity(D dto);


    /**
     * Entity转Dto
     *
     * @param entity /
     * @return /
     */
    D toDto(E entity);


    /**
     * Dto集合转Entity集合
     *
     * @param dtoList /
     * @return /
     */
    List<E> toEntity(List<D> dtoList);


    /**
     * Entity集合转Dto集合
     *
     * @param entityList /
     * @return /
     */
    List<D> toDto(List<E> entityList);
}
