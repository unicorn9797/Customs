package util;

import org.junit.jupiter.api.Test;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DataBaseConnectionTest {

    @Test
    void testConnectionNotNull() {
        // 呼叫你原本的 DataBaseConnection 類別
        Connection conn = DataBaseConnectionPool.getDataBaseConnection();

        // 驗證回傳不是 null
        assertNotNull(conn, "連線不應該是 null");
    }

    @Test
    void testConnectionIsOpen() throws Exception {
        Connection conn = DataBaseConnectionPool.getDataBaseConnection();

        // 驗證連線有成功開啟
        assertNotNull(conn, "連線不應該是 null");
        assertFalse(conn.isClosed(), "連線應該是開啟狀態");
    }
}