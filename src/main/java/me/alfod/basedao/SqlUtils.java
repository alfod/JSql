package me.alfod.basedao;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Yang Dong
 * @createTime 2018/3/29  19:10
 * @lastUpdater Yang Dong
 * @lastUpdateTime 2018/3/29  19:10
 * @note
 */
public class SqlUtils {

    public static String getPlaceHolders(Integer size) {
        if (size == null || size == 0) {
            return "";
        }
        char questionMark = '?';
        char comma = ',';
        StringBuilder stringBuilder = new StringBuilder(size * 3);
        stringBuilder.append(questionMark);
        for (int i = 0; i < size - 1; i++) {
            stringBuilder.append(comma).append(questionMark);
        }
        return stringBuilder.toString();
    }

    public static  Timestamp getCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }
}
