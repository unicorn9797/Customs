package daO.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Tax;

class TaxDaoImplTest {

    private TaxDaoImpl taxDao;

    @BeforeEach
    void setUp() {
        taxDao = new TaxDaoImpl();
    }

    @Test
    void testAddSelectUpdateDelete() {
        // 建立測試用 Tax 物件
        Tax tax = new Tax();
        tax.setTaxId("T001");
        tax.setDeclarationId("D001");
        tax.setCustomsDuty(100.5);
        tax.setVat(50.2);
        tax.setOtherTax(10.3);
        tax.setTotalTax(161.0);

        // ======== 測試新增 ========
        taxDao.add(tax);
        Tax selected = taxDao.select(tax);
        assertNotNull(selected);
        assertEquals("D001", selected.getDeclarationId());
        assertEquals(100.5, selected.getCustomsDuty());
        assertEquals(50.2, selected.getVat());
        assertEquals(10.3, selected.getOtherTax());
        assertEquals(161.0, selected.getTotalTax());

        // ======== 測試更新 ========
        tax.setDeclarationId("D002");
        tax.setCustomsDuty(200.0);
        tax.setVat(100.0);
        tax.setOtherTax(20.0);
        tax.setTotalTax(320.0);
        taxDao.update(tax);

        Tax updated = taxDao.select(tax);
        assertNotNull(updated);
        assertEquals("D002", updated.getDeclarationId());
        assertEquals(200.0, updated.getCustomsDuty());
        assertEquals(100.0, updated.getVat());
        assertEquals(20.0, updated.getOtherTax());
        assertEquals(320.0, updated.getTotalTax());

        // ======== 測試刪除 ========
        taxDao.delete(tax);
        Tax deleted = taxDao.select(tax);
        assertNull(deleted);
    }
}