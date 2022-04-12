import java.util.ArrayList;
import java.util.List;

/**
 * 数组删除一个有序
 * 有一个大致有序的数组，你需要判断其是否有序，数组元素范围1-10000
 * 如果有序，返回数组最小值
 * 如果无序，判断是否能够通过删掉某一个值，达到数组有序
 * 返回这个值，如果有多种结果，返回每个结果的最小值
 * 如果删掉任意一个值均不能达到有序，返回-1
 */
public class SortByDeleteOne {
    public static void main(String[] args) {
        System.out.println(sortByDeleteOne(new int[]{1,2}));
//        System.out.println(sortByDeleteOne(new int[]{1, 4, 2, 3, 3}));
    }

    private static int sortByDeleteOne(int[] nums) {
        List<Integer> large = new ArrayList<>();
        List<Integer> small = new ArrayList<>();

        for (int i = 0; i < nums.length - 1; i++) {
            if ((nums[i + 1] - nums[i]) > 0) {
                large.add(i);
            } else if ((nums[i + 1] - nums[i]) < 0){
                small.add(i);
            }
        }

        int result = -1;

        if (large.size() == 0) {
            result = nums[nums.length - 1];
        } else if(small.size() == 0) {
            result = nums[0];
        } else if (large.size() == 1 && small.size() == 1){
            int a = nums[large.get(0) + 1];
            int b = nums[small.get(0) + 1];
            result = Math.min(a, b);
        }else if (large.size() == 1){
            result = nums[large.get(0) + 1];
        } else if (small.size() == 1) {
            result = nums[small.get(0)];
        }

        if (isSort(nums, result)) {
            return result;
        } else {
            return -1;
        }
    }

    public static boolean isSort(int[] nums, int n) {
        List<Integer> sort = new ArrayList<>();
        for (int i: nums) {
            if (i != n) {
                sort.add(i);
            }
        }

        if (sort.size() <= 1) {
            return true;
        }else {
            boolean b = sort.get(0) - sort.get(1) > 0;
            for (int i = 1; i < sort.size(); i++) {
                if (sort.get(i - 1) - sort.get(i) > 0 != b) {
                    return false;
                }
            }
        }
        return true;
    }
}
