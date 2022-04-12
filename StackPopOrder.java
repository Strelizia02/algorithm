import java.util.ArrayList;
import java.util.List;

/**
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，
 * 序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 */
public class StackPopOrder {

    /**
     * 解题思路
     * 比如
     * 入栈顺序{1,2,3,4,5}
     * 出栈顺序{4,3,5,1,2}
     * 4出栈了，出栈顺序元素4后面的元素，必须都是按顺序倒叙的。
     * 因此我直接遍历出栈顺序，循环遍历每一个元素，该元素后面的，小于该元素的元素，必须都是入栈顺序的逆序排列。
     *
     * popStack()方法用于遍历出栈顺序，判断是否符合上述逆序规则。
     *
     * isDesc()方法判断某下标q后面是否符合逆序规则
     *
     * 取出栈顺序的下标q，查找该元素在入栈顺序的下标len。
     * 对入栈顺序从len开始向前遍历，查找出栈顺序q后面的，相等的元素，如果相等就将出栈下标加入List
     * 因为是向前遍历，所以正常情况下，list中应该是递增的。
     *
     * 如题目中出栈第0位是4，入栈队列向前遍历，顺序应该是321，对应出栈顺序下标list=[1,4,3]，并非顺序排列
     */
    public static void main(String[] args) {
        int[] stack1 = {1,2,3,4,5};
        int[] stack2 = {4,3,5,1,2};
        System.out.println(popStack(stack1, stack2));
    }

    public static boolean popStack(int[] stack1, int[] stack2) {
        for (int i = 0; i < stack2.length; i++) {
            if(!isDesc(stack1, stack2, i)){
                return false;
            }
        }
        return true;
    }

    private static boolean isDesc(int[] stack1, int[] stack2, int q) {
        // 出栈的第q个元素，在入栈的下标为len
        int len = 0;
        for (int i = 0; i < stack1.length; i++) {
            if (stack1[i] == stack2[q]){
                len = i;
            }
        }

        List<Integer> pop = new ArrayList<>();

        // 判断出栈的下标顺序
        for (int i = len; i >= 0; i--) { // 从len元素往前循环
            for (int j = q; j < stack2.length; j++) { // 查找stack2中的该元素下标
                if (stack1[i] == stack2[j]) {
                    pop.add(j);
                }
            }
        }

        if (!pop.isEmpty()){
            int max = pop.get(0);
            for (int i: pop) {
                if (i >= max){
                    max = i;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
