import java.util.*;

/**
 * 有一个小镇中心坐标点是(0,0)
 * 现有一个二维数组 positions，代表了小镇中可以放置设备的位置，现在需要放置num个设备
 * 为了做到安装成本最低，请你选择距离最近的几个位置，返回位置在positions里的下标
 * 下标按照升序排列，返回结果的下标数组
 */
public class ClosestPositions {
    public static void main(String[] args) {
        ClosestPositions main11 = new ClosestPositions();
        int[][] a = new int[][] {{0, 0}, {-4, -5}, {3, -2}, {-1, 2}, {5, 6}};
        System.out.println(Arrays.toString(main11.getClosestPositions(a, 5)));
    }

    class Position {
        int num;
        int len;

        public Position(int i, int[] position) {
            this.num = i;
            this.len = position[0] * position[0] + position[1] * position[1];
        }

        public int getNum() {
            return num;
        }

        public int getLen() {
            return len;
        }
    }

    public int[] getClosestPositions(int[][] positions, int num) {
        List<Integer> result = new ArrayList<>(num);
        List<Position> list = new ArrayList<>(positions.length);
        for (int i = 0; i < positions.length; i++) {
            list.add(new Position(i, positions[i]));
        }
        list.sort(Comparator.comparingInt(Position::getLen));
        for (int i = 0; i < num; i++) {
            result.add(list.get(i).getNum());
        }
        result.sort(null);
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = result.get(i);
        }
        return arr;
    }
}
