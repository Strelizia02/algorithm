import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 输入
 * 两个4位数字字符，分别表示初始密码与解锁密码，以单个空格分隔。
 *
 * 输出
 * 一个整数，表示开锁的最少改变次数；无法解锁则输出-1。
 *
 * 样例
 * 输入样例 1 复制
 *
 * 0023 0059
 * 输出样例 1
 *
 * 2
 * 提示样例 1
 * 0023->0059，存在两种开锁方式：0023->0029->0059，或 0023->0053->0059，操作次数都是2
 */
public class UnlockPassWord {

    /**
     * 暴力解法：
     * 直接循环出来从初始到密码的所有组合步长，组合相同的取步长最小，最后直接hash.get
     */
    public static Set<String> suShu = new HashSet<>();
    public static void main(String[] args) {
        String initState = "0023";
        String dstState = "0059";
        System.out.println(unlock(initState, dstState));
    }

    private static int unlock(String initState, String dstState) {
        isSuShu();
        Map<String, Integer> target = new HashMap<>();
        getPwMap(target, initState, 0);
        return target.getOrDefault(dstState, 0);
    }

    private static void getPwMap(Map<String, Integer> target, String initState, int step) {
        if (target.containsKey(initState)) {
            if (target.get(initState) > step) {
                target.put(initState, step);
            }
        } else {
            target.put(initState, step);
        }

        //循环变换四个位置的数字
        for (int i = 0; i < 4; i++) {
            StringBuilder sb = new StringBuilder(initState);
            //循环十个数字
            for (int j = 0; j < 10; j++) {
                //不是原先的数字才开始做替换
                if (j != Character.getNumericValue(sb.charAt(i))) {
                    //替换
                    sb.replace(i, i + 1, "" + j);

                    if (suShu.contains(sb.toString()) && !target.containsKey(sb.toString())) {
                        //如果是素数且合集里不存在，直接加入
                        target.put(sb.toString(), step + 1);
                        getPwMap(target, sb.toString(), step + 1);
                    } else if (suShu.contains(sb.toString())) {
                        //是素数且合集里已存在，对比替换为更小的值
                        if (target.get(sb.toString()) > step + 1) {
                            target.put(sb.toString(), step + 1);
                            getPwMap(target, sb.toString(), step + 1);
                        }
                    }
                }
            }
        }
    }

    public static void isSuShu() {
        for (int i = 0; i < 10000; i++) {
            boolean num = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    num = false;
                    break;
                }
            }
            if (num) {
                suShu.add(String.format("%04d", i));
            }
        }
    }
}
