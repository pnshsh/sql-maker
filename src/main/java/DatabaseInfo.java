/**
 * 数据库信息
 */
public class DatabaseInfo {
    public static final int CELL_SIZE = 8;
    public static final int DB_SIZE_PER_CELL = 5;


    public static String[] getCells() {
        String[] cells = new String[CELL_SIZE];
        for (int i = 0; i < CELL_SIZE; i++) {
            cells[i] = String.format("%02d", i+1);
        }
        return cells;
    }

    /**
     * 获取某天机器上所有的DB编号列表
     * @param cell 机器编号
     * @return 数据库编号列表
     */
    public static String[] getDatabaseList(String cell) {
        String[] databaseArray = new String[DB_SIZE_PER_CELL];
        int cellNum = Integer.parseInt(cell);

        for (int i = 0; i < DB_SIZE_PER_CELL; i++) {
            databaseArray[i] = String.format("%03d", cellNum);
            cellNum += CELL_SIZE;
        }

        return databaseArray;
    }

}
