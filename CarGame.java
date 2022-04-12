public class CarGame {
    /**
     * 输入命令，控制小车的方向，结果输出小车位置
     * 小车默认位置为(0,0)，方向是x正向
     * 输入G 前进一格
     * 输入R 原地右转90°
     * 输入L 原地左转90°
     */
    public static void main(String[] args) {
        System.out.println(execCommand("GG"));
    }

    // 待实现函数，在此函数中填入答题代码
    private static String execCommand(String commands) {
        int x = 0;
        int y = 0;
        int q = 1;
        for (int i = 0; i < commands.length(); i++) {
            switch (commands.charAt(i)) {
                case 'G':
                    switch (q) {
                        case 1:
                            y++;
                            break;
                        case 2:
                            x++;
                            break;
                        case 3:
                            y--;
                            break;
                        case 4:
                            x--;
                            break;
                        default:
                            break;
                    }
                    break;
                case 'L':
                    if (q == 1) {
                        q = 4;
                    } else {
                        q--;
                    }
                    break;
                case 'R':
                    if (q == 4) {
                        q = 1;
                    } else {
                        q++;
                    }
                    break;
                default:
                    break;
            }
        }
        return "(" + x + "," + y + ")";
    }
}
