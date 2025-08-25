package daO.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import model.Declaration;

public class DeclarationDaoImplTest {

    private DeclarationDaoImpl dao = new DeclarationDaoImpl();
    private final String testId = "D001";

    // 每次測試前先刪除可能存在的測試資料
    @BeforeEach
    void cleanBefore() {
        Declaration declaration = new Declaration();
        declaration.setDeclarationId(testId);
        dao.delete(declaration);
    }

    // 每次測試後清理資料
    @AfterEach
    void cleanAfter() {
        Declaration declaration = new Declaration();
        declaration.setDeclarationId(testId);
        dao.delete(declaration);
    }

    @Test
    void testAddAndSelect() {
        Declaration declaration = new Declaration();
        declaration.setDeclarationId(testId);
        declaration.setDeclarationDate(LocalDateTime.now());
        declaration.setDeclarationType("Import");
        declaration.setImporterName("ABC Corp");
        declaration.setImporterId("12345678");
        declaration.setExporterName("XYZ Ltd");
        declaration.setExporterId("87654321");

        // 測試新增
        dao.add(declaration);

        // 測試查詢
        Declaration result = dao.select(declaration);
        assertNotNull(result, "查詢結果不應該是 null");
        assertEquals(testId, result.getDeclarationId());
        assertEquals("Import", result.getDeclarationType());
        assertEquals("ABC Corp", result.getImporterName());
    }

    @Test
    void testUpdate() {
        // 先新增一筆資料
        Declaration declaration = new Declaration();
        declaration.setDeclarationId(testId);
        declaration.setDeclarationDate(LocalDateTime.now());
        declaration.setDeclarationType("Import");
        declaration.setImporterName("ABC Corp");
        declaration.setImporterId("12345678");
        declaration.setExporterName("XYZ Ltd");
        declaration.setExporterId("87654321");
        dao.add(declaration);

        // 再更新資料
        declaration.setDeclarationType("Export");
        declaration.setImporterName("Updated Corp");
        declaration.setImporterId("99999999");
        declaration.setExporterName("Updated Ltd");
        declaration.setExporterId("11111111");
        declaration.setTransportMode("Air");
        declaration.setStatus("Approved");
        declaration.setDigitalSignature("abc123");
        declaration.setPublicKey("pubkey");

        dao.update(declaration);

        Declaration updated = dao.select(declaration);
        assertNotNull(updated, "更新後應該能查到資料");
        assertEquals("Updated Corp", updated.getImporterName());
        assertEquals("Export", updated.getDeclarationType());
        assertEquals("Air", updated.getTransportMode());
    }

    @Test
    void testDelete() {
        // 先新增一筆資料
        Declaration declaration = new Declaration();
        declaration.setDeclarationId(testId);
        declaration.setDeclarationDate(LocalDateTime.now());
        declaration.setDeclarationType("Import");
        declaration.setImporterName("ABC Corp");
        declaration.setImporterId("12345678");
        declaration.setExporterName("XYZ Ltd");
        declaration.setExporterId("87654321");
        dao.add(declaration);

        // 刪除
        dao.delete(declaration);

        Declaration deleted = dao.select(declaration);
        assertNull(deleted, "刪除後應該查不到資料");
    }
}