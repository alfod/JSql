package me.alfod.basedao;

import java.sql.ResultSet;

/**
 * @author Yang Dong
 * @createTime 2018/3/21  16:26
 * @lastUpdater Yang Dong
 * @lastUpdateTime 2018/3/21  16:26
 * @note
 */
public interface HandleChain<BO> {

    /**
     * 增强的处理函数
     * @param rs  数据库访问对象
     * @param bo  传入的bo
     */
    void handle(ResultSet rs, BO bo);

}
