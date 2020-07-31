package search;

/**
 * 二分查找
 *
 * @author xulei
 * @date 2020/7/30 8:52 下午
 */
public class BinarySearch {

    /**
     * 非递归实现，假设array数组有序，且不包含重复元素
     */
    public static int search(int[] array, int value) {
        int min = 0;
        int max = array.length - 1;

        while (min <= max) {
            int middle = (min + max) / 2;
            if (array[middle] == value) {
                return middle;
            }
            // 已经比中间位小了，所以索引减1
            if (array[middle] > value) {
                max = middle - 1;
            } else {
                min = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 递归实现，假设array数组有序，且不包含重复元素
     */
    public static int search2(int[] array, int value) {
        return searchInternally(array, 0, array.length - 1, value);
    }

    private static int searchInternally(int[] array, int min, int max, int value) {
        if (min > max) {
            return -1;
        }
        int middle = min + ((max - min) >> 1);
        if (array[middle] == value) {
            return middle;
        }
        if (array[middle] > value) {
            return searchInternally(array, min, middle -1, value);
        } else {
            return searchInternally(array, middle + 1, max, value);
        }
    }

    /**
     * 查找第一个值等于给定值的元素，array有序且包含重复元素
     */
    public static int search3(int[] array, int value) {
        int min = 0;
        int max = array.length - 1;

        while (min <= max) {
            int middle = min + (((max - min)) >> 1);
            if (array[middle] > value) {
                max = middle - 1;
            } else if (array[middle] < value) {
                min = middle + 1;
            } else {
                if (middle == 0 || array[middle - 1] != value) {
                    return middle;
                } else {
                    max = middle - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找最后一个值等于给定值的元素，array有序且包含重复元素
     */
    public static int search4(int[] array, int value) {
        int min = 0;
        int max = array.length - 1;

        while (min <= max) {
            int middle = min + (((max - min)) >> 1);
            if (array[middle] > value) {
                max = middle - 1;
            } else if (array[middle] < value) {
                min = middle + 1;
            } else {
                if (middle == array.length - 1 || array[middle + 1] != value) {
                    return middle;
                } else {
                    min = middle + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找第一个大于等于给定值的元素，array有序且包含重复元素
     */
    public static int search5(int[] array, int value) {
        int min = 0;
        int max = array.length - 1;

        while (min <= max) {
            int middle = min + (((max - min)) >> 1);
            if (array[middle] >= value) {
                if (middle == 0 || array[middle - 1] < value) {
                    return middle;
                } else {
                    max = middle - 1;
                }
            } else {
                min = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后个小于等于给定值的元素，array有序且包含重复元素
     */
    public static int search6(int[] array, int value) {
        int min = 0;
        int max = array.length - 1;

        while (min <= max) {
            int middle = min + (((max - min)) >> 1);
            if (array[middle] > value) {
                max = middle - 1;
            } else {
                if (middle == array.length - 1 || array[middle + 1] >= value) {
                    return middle;
                } else {
                    min = middle + 1;
                }
            }
        }
        return -1;
    }
}
