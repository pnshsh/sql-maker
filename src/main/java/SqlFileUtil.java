import java.io.*;

public class SqlFileUtil {

    /**
     * 输入文件全路径
     */
    private static String filename;

    /**
     * 原sql
     */
    private static String orginSQL;

    private static final String suffix = ".sql";

    public static void read(File file) {
        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        filename = file.getAbsolutePath();
        orginSQL = sb.toString();

    }

    /**
     * 将原SQL 根据不不同的数据库序号 替换为相应的SQL
     * @param source 原SQL
     * @param datebaseSeq 数据库序号
     * @return
     */
    private static String paseSQL(String source, String datebaseSeq) {
        String databaseName = "rights_account" + datebaseSeq + ".";
        String result = source.replace("rights_account.", databaseName);
        return result;
    }

    public static void write() throws IOException {
        Object[] cells = DatabaseUtil.cells();

        for (int i = 0; i < cells.length; i++) {
            String targetName = filename.replace(suffix, cells[i] + suffix);
            File targetFile = new File(targetName);
            FileWriter fileWriter = new FileWriter(targetFile);

            Object[] databases = DatabaseUtil.databases((String) cells[i]);
            for (int j = 0; j < databases.length; j++) {
                String databaseSeq = (String) databases[j];
                String resultSql = paseSQL(orginSQL, databaseSeq);
                fileWriter.write(resultSql);
            }

            fileWriter.flush();
            fileWriter.close();
        }
    }

    /**
     * 使用 use语句  SQL无需做改动
     * @throws IOException
     */
    public static void write2() throws IOException {
        Object[] cells = DatabaseUtil.cells();

        for (int i = 0; i < cells.length; i++) {
            String targetName = filename.replace(suffix, cells[i] + suffix);
            File targetFile = new File(targetName);
            FileWriter fileWriter = new FileWriter(targetFile);

            Object[] databases = DatabaseUtil.databases((String) cells[i]);
            for (int j = 0; j < databases.length; j++) {
                String databaseSeq = (String) databases[j];
                fileWriter.write("use rights_account" + databaseSeq + "\n");
                fileWriter.write(orginSQL);
                fileWriter.write("\n");
            }

            fileWriter.flush();
            fileWriter.close();
        }
    }


}
