package me.alfod.basedao;

import java.util.List;

/**
 * @author Huchuan Huang
 * @createTime 2017/6/1 下午6:25
 * @lastUpdater Huchuan Huang
 * @lastUpdateTime 2017/6/1 下午6:25
 * @note BaseRepo
 */
@Deprecated
public interface BaseRepo<PO, CO, BO> {

    /**
     * 插入
     * @param po
     * @return
     */
    int insertAndGetKey(final PO po);

    /**
     * 批量插入
     * @param poList
     * @return
     */
    int[] batchInsert(List<PO> poList);

    /**
     * 根据id更新
     * @param po
     * @return
     */
    int updateById(final PO po);

    /**
     * 根据id批量更新
     * @param poList
     * @return
     */
    int batchUpdateById(final List<PO> poList);

    /**
     * 删除
     * @param id
     * @param operatorId
     * @return
     */
    int deleteById(final Integer id, final Integer operatorId);

    /**
     * 删除
     * @param ids
     * @param operatorId
     * @return
     */
    int batchDeleteById(final List<Integer> ids, final Integer operatorId);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    BO getById(Integer id);

    /**
     * 根据idList查询
     * @param ids
     * @return
     */
    List<BO> listByIds(List<Integer> ids);

    /**
     * 根据ids数组查询
     * @param ids
     * @return
     */
    List<BO> listByIds(Integer... ids);

    /**
     * 多条件查询
     * @param co
     * @return
     */
    List<BO> listByCondition(CO co, PageParam pageParam);

    /**
     * 多条件查询数量
     * @param co
     * @return
     */
    int countByCondition(CO co);
}
