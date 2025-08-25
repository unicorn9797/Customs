package daO.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.util.List;

import model.UserHolding;

class UserHoldingDaoImplTest {

    private UserHoldingDaoImpl dao;
    private final String testUsername = "testuser001";

    @BeforeEach
    void setUp() {
        dao = new UserHoldingDaoImpl();
        // 測試前先刪除同 username 的資料
        List<UserHolding> existing = dao.selectByUsername(testUsername);
        for (UserHolding uh : existing) {
            dao.delete(uh);
        }
    }

    @AfterEach
    void tearDown() {
        // 測試後清理資料
        List<UserHolding> existing = dao.selectByUsername(testUsername);
        for (UserHolding uh : existing) {
            dao.delete(uh);
        }
    }

    @Test
    void testAddSelectUpdateDelete() {
        UserHolding userHolding = new UserHolding();
        userHolding.setUsername(testUsername);
        userHolding.setDeclarationId("D001");
        userHolding.setRole("viewer");

        // 1. 測試新增
        dao.add(userHolding);

        // 2. 測試 selectByUsername
        List<UserHolding> selectedList = dao.selectByUsername(testUsername);
        assertFalse(selectedList.isEmpty(), "查詢結果不應為空");
        UserHolding selected = selectedList.get(0);
        assertNotNull(selected.getId(), "新增後資料應該有 id");
        assertEquals("D001", selected.getDeclarationId());
        assertEquals("viewer", selected.getRole());

        // 3. 測試 selectById
        UserHolding byId = dao.selectById(selected.getId());
        assertNotNull(byId, "應該能用 id 查到資料");
        assertEquals(testUsername, byId.getUsername());

        // 4. 測試更新
        selected.setDeclarationId("D002");
        selected.setRole("editor");
        dao.update(selected);

        UserHolding updated = dao.selectById(selected.getId());
        assertEquals("D002", updated.getDeclarationId());
        assertEquals("editor", updated.getRole());

        // 5. 測試刪除
        dao.delete(updated);
        UserHolding deleted = dao.selectById(updated.getId());
        assertNull(deleted, "刪除後應該查不到資料");
    }
}
