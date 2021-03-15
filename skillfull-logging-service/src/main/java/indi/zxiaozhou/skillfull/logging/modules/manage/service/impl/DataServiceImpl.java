// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.logging.modules.manage.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.zxiaozhou.skillfull.corecommon.base.model.DataLogModel;
import indi.zxiaozhou.skillfull.corecommon.constant.Status;
import indi.zxiaozhou.skillfull.corecommon.exception.ResponseException;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.logging.modules.manage.controller.vo.DataPageVo;
import indi.zxiaozhou.skillfull.logging.modules.manage.entity.DataEntity;
import indi.zxiaozhou.skillfull.logging.modules.manage.mapper.DataMapper;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.IDataService;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.dto.DataDto;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.dto.DataPageDto;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.mapstruct.dto.DataDtoMap;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.mapstruct.vo.DataModelMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 数据日志(Data)业务层实现
 *
 * @author zxiaozhou
 * @date 2021-01-12 14:38:51
 * @since JDK11
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DataServiceImpl extends ServiceImpl<DataMapper, DataEntity> implements IDataService {
    private final DataDtoMap dtoMap;
    private final DataMapper mapper;
    private final DataModelMap modelMap;

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void save(DataLogModel model) throws RuntimeException {
        DataEntity dataEntity = modelMap.toEntity(model);
        dataEntity.setCreateUserId(model.getUserId());
        dataEntity.setCreateUserName(model.getUserName());
        this.save(dataEntity);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public PageDto<DataPageDto> pageByModel(DataPageVo vo) throws RuntimeException {
        return new PageDto<>(mapper.pageByModel(vo.getPage(), vo));
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public DataDto getById(String dataLogId) throws RuntimeException {
        DataEntity byId = super.getById(dataLogId);
        if (Objects.isNull(byId)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "为找到符合条件数据");
        }
        return dtoMap.toDto(byId);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void deleteById(String dataLogId) throws RuntimeException {
        // 查询数据是否存在
        this.getById(dataLogId);
        boolean b = this.removeById(dataLogId);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "删除数据失败");
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void deleteBatch(List<String> dataLogIds) throws RuntimeException {
        List<DataEntity> entities = this.listByIds(dataLogIds);
        if (CollectionUtil.isEmpty(entities)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "数据不存在或已经被别人删除");
        }
        List<String> waitDeleteList = new ArrayList<>();
        entities.forEach(v -> waitDeleteList.add(v.getDataLogId()));
        boolean b = this.removeByIds(waitDeleteList);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "批量删除数据失败");
        }
    }
}