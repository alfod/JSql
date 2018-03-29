package me.alfod.basedao;

/**
 * @author Yang Dong
 * @createTime 2018/3/29  19:10
 * @lastUpdater Yang Dong
 * @lastUpdateTime 2018/3/29  19:10
 * @note
 */
public class SqlUtils {

    public static String GetPlaceHolders(Integer size) {
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
}
