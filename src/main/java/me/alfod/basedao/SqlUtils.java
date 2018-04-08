package me.alfod.basedao;

import javax.persistence.Column;
import java.lang.reflect.Field;
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


    public static  String camelToUnderLine(String s) {
        final String UNDER_LINE = "_";
        StringBuilder camelCase = new StringBuilder("");
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                if (i != 0) {
                    camelCase.append(UNDER_LINE);
                }
                camelCase.append(Character.toLowerCase(s.charAt(i)));
            } else {
                camelCase.append(s.charAt(i));
            }
        }
        return camelCase.toString();
    }


    public static  String getFieldColumnName(Field field) {
        field.setAccessible(true);
        if (field.isAnnotationPresent(Column.class)) {
            //init columns name of po
            Column column = field.getAnnotation(Column.class);
            if (column.name().length() > 0) {
                return column.name();
            } else {
                //init columns name of po
                return camelToUnderLine(field.getName());
            }
        }
        //init columns name of po
        return camelToUnderLine(field.getName());
    }
}
