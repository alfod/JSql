package me.alfod.util;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author Yang Dong
 * @createTime 2017/11/29  11:22
 * @lastUpdater Yang Dong
 * @lastUpdateTime 2017/11/29  11:22
 * @note
 */
public class CollectionUtils {

    private CollectionUtils() {
    }

    public static int nullNumber(Iterable iterable) {
        if (iterable == null) {
            return 0;
        }
        Iterator iterator = iterable.iterator();
        int num = 0;
        while (iterator.hasNext()) {
            if (iterator.next() == null) {
                ++num;
            }
        }
        return num;
    }


    public static <T> int nullNumber(T[] objects) {
        if (objects == null || objects.length == 0) {
            return 0;
        }
        int num = 0;
        for (T o : objects) {
            if (o == null) {
                ++num;
            }
        }
        return num;
    }

    public static int blankNumber(CharSequence[] strings) {
        if (strings == null || strings.length == 0) {
            return 0;
        }
        int num = 0;
        for (CharSequence str : strings) {
            if (isBlank(str)) {
                ++num;
            }
        }
        return num;
    }

    /**
     * @param iterable 去除迭代器类中的所有null元素
     * @return  非null元素的数量
     */
    public static <T extends Iterable> T removeNull(T iterable) {
        if (iterable == null) {
            return null;
        }
        Iterator iterator = iterable.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == null) {
                iterator.remove();
            }
        }
        return iterable;
    }

    @SuppressWarnings("all")
    public static <T extends Object> T[] removeNull(T[] ts) {
        if (isEmpty(ts)) {
            return ts;
        }
        List<Object> list = new ArrayList<>(ts.length);
        for (T t : ts) {
            if (t != null) {
                list.add(t);
            }
        }
        return list.toArray((T[]) Array.newInstance(ts.getClass().getComponentType(), list.size()));
    }

    /**
     * @param ts 数组
     * @param value 被删除的元素
     * @param <T>
     * @return
     */
    @SuppressWarnings("all")
    public static <T extends Object> T[] remove(T[] ts, T value) {
        if (isEmpty(ts)) {
            return ts;
        }
        if (value == null) {
            return removeNull(ts);
        }
        List<Object> list = new ArrayList<>(ts.length);
        for (T t : ts) {
            if (!value.equals(t)) {
                list.add(t);
            }
        }
        return list.toArray((T[]) Array.newInstance(ts.getClass().getComponentType(), list.size()));
    }

    /**
     * 去掉可迭代序列中, 所有值为value的元素
     *
     * @param iterable
     * @param value
     * @param <T>
     * @return
     */
    public static <T extends Iterable> T remove(T iterable, Object value) {
        if (iterable == null) {
            return null;
        }
        if (value == null) {
            return removeNull(iterable);
        }
        Iterator iterator = iterable.iterator();
        Object object;
        while (iterator.hasNext()) {
            object = iterator.next();
            if (object != null && object.equals(value)) {
                iterator.remove();
            }
        }
        return iterable;
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.size() == 0;
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static <T> boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }

    public static <T> boolean isNotBlank(CharSequence str) {
        return !isBlank(str);
    }

    public static <T> boolean isBlank(CharSequence str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    public static boolean isAllNull(Object... objects) {
        for (Object object : objects) {
            if (object != null) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotAllNull(Object... objects) {
        return !isAllNull(objects);
    }

    public static boolean isAnyNull(Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotAnyNull(Object... objects) {
        return !isAnyNull(objects);
    }

}
