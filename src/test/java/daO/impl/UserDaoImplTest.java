package daO.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import model.User;

class UserDaoImplTest {

    private UserDaoImpl dao;
    private final String testUsername = "testuser001";

    @BeforeEach
    void setUp() {
        dao = new UserDaoImpl();
        // 測試前先刪除同 username 的所有資料
        User temp = new User();
        temp.setUsername(testUsername);
        dao.delete(temp);
    }

    @AfterEach
    void tearDown() {
        // 測試後清理資料
        User temp = new User();
        temp.setUsername(testUsername);
        dao.delete(temp);
    }

    @Test
    void testAddSelectUpdateDelete() {
        User user = new User();
        user.setName("Test User");
        user.setUsername(testUsername);
        user.setPassword("pass123");
        user.setRole("viewer");

        // 1. 測試新增
        dao.add(user);

        // 2. 測試 selectByUsername
        User selected = dao.selectbyUsername(testUsername);
        assertNotNull(selected, "查詢結果不應該是 null");
        assertEquals("Test User", selected.getName());
        assertEquals("viewer", selected.getRole());

        // 3. 測試 selectForLogin
        User loginUser = dao.selectForLogin(testUsername, "pass123");
        assertNotNull(loginUser, "登入應成功");
        assertEquals("Test User", loginUser.getName());

        // 4. 測試更新
        selected.setName("Updated User");
        selected.setPassword("newpass");
        selected.setRole("admin");
        dao.update(selected);

        User updated = dao.selectbyUsername(testUsername);
        assertEquals("Updated User", updated.getName());
        assertEquals("admin", updated.getRole());
        assertEquals("newpass", updated.getPassword());

        // 5. 測試刪除
        dao.delete(updated);
        User deleted = dao.selectbyUsername(testUsername);
        assertNull(deleted, "刪除後應該查不到資料");
    }
}
