import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

public class DatabaseUtil {

    private static JSONObject json() {
        StringBuffer sb = new StringBuffer();

        File file = new File(DatabaseUtil.class.getClassLoader().getResource("databaseInfo.json").getPath());
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(sb.toString());
    }

    public static Object[] cells() {
        JSONArray cells = json().getJSONArray("cells");
        return cells.toArray();
    }

    public static Object[] databases(String cell) {
        JSONObject database = json().getJSONObject("database");
        JSONArray array = database.getJSONArray(cell);
        return array.toArray();
    }

}
