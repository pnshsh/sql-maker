import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("当前路径：" + System.getProperty("user.dir"));

        if (args.length == 0) {
            System.err.println("参数不能为空，应当把SQL全路径名当作参数");
            return;
        }


        String fileName = args[0];
        File file = new File(fileName);

        SqlFileUtil.read(file);

        SqlFileUtil.write2();

    }
}
