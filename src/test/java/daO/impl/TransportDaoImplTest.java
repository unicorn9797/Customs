package daO.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.*;

import model.Transport;

class TransportDaoImplTest {

    private TransportDaoImpl dao;
    private final String testTransportId = "T001";

    @BeforeEach
    void setUp() {
        dao = new TransportDaoImpl();
        // 測試前先刪除可能存在的資料
        Transport transport = new Transport();
        transport.setTransportId(testTransportId);
        dao.delete(transport);
    }

    @AfterEach
    void tearDown() {
        // 測試後清理資料
        Transport transport = new Transport();
        transport.setTransportId(testTransportId);
        dao.delete(transport);
    }

    @Test
    void testAddSelectUpdateDelete() {
        Transport transport = new Transport();
        transport.setTransportId(testTransportId);
        transport.setDeclarationId("D001");
        transport.setVesselOrFlight("Flight123");
        transport.setPortOfLoading("PortA");
        transport.setPortOfDischarge("PortB");
        transport.setDepartureDate(LocalDateTime.now());
        transport.setArrivalDate(LocalDateTime.now().plusDays(2));

        // 1. 新增
        dao.add(transport);

        // 2. 查詢
        Transport selected = dao.select(transport);
        assertNotNull(selected);
        assertEquals("Flight123", selected.getVesselOrFlight());

        // 3. 更新
        transport.setVesselOrFlight("Flight456");
        dao.update(transport);
        Transport updated = dao.select(transport);
        assertEquals("Flight456", updated.getVesselOrFlight());

        // 4. 刪除
        dao.delete(transport);
        Transport deleted = dao.select(transport);
        assertNull(deleted);
    }
}