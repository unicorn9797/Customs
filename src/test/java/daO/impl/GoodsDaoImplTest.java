package daO.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import model.Goods;

class GoodsDaoImplTest {

    private GoodsDaoImpl dao;
    private final String testGoodsId = "G001";

    @BeforeEach
    void setUp() {
        dao = new GoodsDaoImpl();
        // 測試前先刪除可能存在的資料
        Goods goods = new Goods();
        goods.setGoodsId(testGoodsId);
        dao.delete(goods);
    }

    @AfterEach
    void tearDown() {
        // 測試後清理資料
        Goods goods = new Goods();
        goods.setGoodsId(testGoodsId);
        dao.delete(goods);
    }

    @Test
    void testAddSelectUpdateDelete() {
        Goods goods = new Goods();
        goods.setGoodsId(testGoodsId);
        goods.setDeclarationId("D001");
        goods.setItemName("ItemA");
        goods.setHsCode("HS123");
        goods.setOriginCountry("TW");
        goods.setQuantity(10);
        goods.setUnitPrice(100.0);
        goods.setTotalPrice(1000.0);
        goods.setGrossWeight(50.0);
        goods.setNetWeight(45.0);

        // 1. 新增
        dao.add(goods);

        // 2. 查詢
        Goods selected = dao.select(goods);
        assertNotNull(selected);
        assertEquals("ItemA", selected.getItemName());

        // 3. 更新
        goods.setItemName("ItemB");
        dao.update(goods);
        Goods updated = dao.select(goods);
        assertEquals("ItemB", updated.getItemName());

        // 4. 刪除
        dao.delete(goods);
        Goods deleted = dao.select(goods);
        assertNull(deleted);
    }
}