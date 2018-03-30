package me.alfod.util;

/**
 * @author Yang Dong
 * @createTime 2017/12/1  10:16
 * @lastUpdater Yang Dong
 * @lastUpdateTime 2017/12/1  10:16
 * @note
 */
public class MathUtils {

    private MathUtils(){}
    /**
     * @param comparable  判断是正数
     * @return null fanle;
     */
    public static boolean isPositive(Comparable<Number> comparable) {
        return comparable != null && comparable.compareTo(0) > 0;
    }


    /**
     * @param comparable 判断是负数
     * @return null false
     */
    public static boolean isNegative(Comparable<Number> comparable) {
        return comparable != null && comparable.compareTo(0) < 0;
    }

    /**自然数, 大于等于 0 的正数, 0 ,1, 2, 3,,, ,,,
     * @param integer 判断是否自然数, 多用于校验id是否合法
     * @return
     */
    public static boolean isNatural(Integer integer) {
        if (integer == null) {
            return false;
        }
        return integer >= 0;
    }

    public static boolean isNotNatural(Integer integer) {
        return !isNatural(integer);
    }

    /**
     * 自然数, 大于等于 0 的正数, 0 ,1, 2, 3,,, ,,,
     * @param aLong 判断是否自然数, 多用于校验id是否合法
     * @return
     */
    public static boolean isNatural(Long aLong) {
        if (aLong == null) {
            return false;
        }
        return aLong >= 0;
    }

    public static boolean isNotNatural(Long aLong) {
        return !isNatural(aLong);
    }

    /**
     * @param numberComparable 数字
     * @param min              最小值
     * @param max              最大值
     * @return 是否在区间内, [min.max],闭区间
     */
    public static <T extends Comparable<K>, K extends Number> boolean isBetween(T numberComparable, K min, K max) {
        if (numberComparable == null || min == null || max == null) {
            return false;
        }
        return numberComparable.compareTo(min) >= 0 && numberComparable.compareTo(max) <= 0;
    }

    public static <T extends Comparable<K>, K extends Number> boolean isNotBetween(T numberComparable, K min, K max) {
        return !isBetween(numberComparable, min, max);
    }

    /**
     * @param numberComparable 要进行判断的数
     * @param value            被比较的数字
     * @param <T>              泛型, 要判断的数必须继承 Comparable<?>
     * @param <K>              泛型, 被判断的数字需要继承Number
     * @return 任意一方为null, 返回false;
     * false  不大于
     * true   大于
     */
    public static <T extends Comparable<K>, K extends Number> boolean isGreater(T numberComparable, K value) {
        if (numberComparable == null || value == null) {
            return false;
        }
        return numberComparable.compareTo(value) > 0;
    }

    public static <T extends Comparable<K>, K extends Number> boolean isNotGreater(T numberComparable, K value) {
        return !isGreater(numberComparable, value);
    }

    /**
     * @param numberComparable 要进行判断的数
     * @param value            被比较的数字
     * @param <T>              泛型, 要判断的数必须继承 Comparable<?>
     * @param <K>              泛型, 被判断的数字需要继承Number
     * @return 任意一方为null, 返回false;
     * false  不小于
     * true   小于
     */
    public static <T extends Comparable<K>, K extends Number> boolean isLesser(T numberComparable, K value) {
        if (numberComparable == null || value == null) {
            return false;
        }
        return numberComparable.compareTo(value) < 0;
    }

    public static <T extends Comparable<K>, K extends Number> boolean isNotLesser(T numberComparable, K value) {
        return !isLesser(numberComparable, value);
    }

    /**
     * @param numberComparable 要进行判断的数
     * @param value            被比较的数字
     * @param <T>              泛型, 要判断的数必须继承 Comparable<?>
     * @param <K>              泛型, 被判断的数字需要继承Number
     * @return 任意一方为null, 返回false;
     * false  不等于
     * true   等于
     */
    public static <T extends Comparable<K>, K extends Number> boolean isEquals(T numberComparable, K value) {
        if (numberComparable == null || value == null) {
            return false;
        }
        return numberComparable.compareTo(value) == 0;
    }

    public static <T extends Comparable<K>, K extends Number> boolean isNotEquals(T numberComparable, K value) {
        return !isEquals(numberComparable, value);
    }


}
