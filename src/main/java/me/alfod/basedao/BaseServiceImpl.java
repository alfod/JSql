package me.alfod.basedao;

import com.aixuexi.thor.util.Page;
import com.gaosi.api.common.to.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * @author Yang Dong
 * @createTime 2017/8/3  10:20
 * @lastUpdater Yang Dong
 * @lastUpdateTime 2017/8/3  10:20
 * @note
 */
@Deprecated
public abstract class BaseServiceImpl<PO, CO extends PO, BO extends PO> implements com.gaosi.api.common.template.BaseService<PO, CO, BO>, ApplicationContextAware {

    @Autowired
    private ApplicationContext applicationContext;

    private BaseDao<PO, CO, BO> baseDao;

    @Override
   public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext=applicationContext;
    }
    @SuppressWarnings("unchecked")
    public BaseServiceImpl() {
        String daoName = this.getClass().getSimpleName().replace("Service", "DAO");
        baseDao = (BaseDao<PO, CO, BO>) applicationContext.getBeansWithAnnotation(Repository.class).get(daoName);
    }

    @Override
    public ApiResponse<Integer> save(BO bo) {
        return ApiResponse.success(baseDao.save(bo));
    }

    @Override
    public ApiResponse<int[]> batchSave(List<BO> bos) {
        return ApiResponse.success(baseDao.batchSave(bos));
    }

    @Override
    public ApiResponse<Integer> updateById(BO bo) {
        return ApiResponse.success(baseDao.updateById(bo));
    }

    @Override
    public ApiResponse<Integer> batchUpdateById(List<BO> bos) {
        return ApiResponse.success(baseDao.batchUpdateById(bos));
    }

    @Override
    public ApiResponse<Integer> deleteById(Integer id, Integer operatorId) {
        return ApiResponse.success(baseDao.deleteById(id,operatorId));
    }

    @Override
    public ApiResponse<Integer> batchDeleteById(List<Integer> ids, Integer operatorId) {
        return ApiResponse.success(baseDao.batchDeleteById(ids,operatorId));
    }

    @Override
    public ApiResponse<BO> getById(Integer id) {
        return ApiResponse.success(baseDao.getById(id));
    }

    @Override
    public ApiResponse<List<BO>> getListByIds(List<Integer> ids) {
        return ApiResponse.success(baseDao.getListByIds(ids));
    }

    @Override
    public ApiResponse<List<BO>> getListByIds(Integer... ids) {
        return ApiResponse.success(baseDao.getListByIds(Arrays.asList(ids)));
    }

    @Override
    public ApiResponse<Integer> countByCondition(CO co) {
        return ApiResponse.success(baseDao.countByCondition(co));
    }

    @Override
    public ApiResponse<Page<BO>> getPageByCondition(CO co, PageParam pageParam) {
        return ApiResponse.success(baseDao.getPageByCondition(co,pageParam));
    }
}
