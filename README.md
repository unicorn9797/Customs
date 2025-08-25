# 海關報單簽章模擬系統 (Customs Simulation)

這是一個使用 **Java Swing** 製作的視窗化模擬系統，模擬報關過程中不同角色的操作流程，並結合 **RSA 公私鑰機制 (PKI)** 與 **數位簽章**。

## ✨ 功能特色
- 多角色登入系統
  - **使用者(可以進行報關流程)**  
  -   
  - **海關人員 (負責簽章)**
- 報關資料填寫與傳送
- 使用 RSA 金鑰對資料進行簽署與驗證
- 數位簽章驗證機制，確保資料不可竄改
- Swing 視窗化介面，簡單操作

## 🛠 技術架構
- **語言**：Java 11+
- **介面**：Java Swing (WindowBuilder)
- **安全性**：
  - `PublicKeyInfrastructure.java` 產生與轉換 RSA 金鑰
  - `DigitalSignature.java` 負責簽署與驗證
- **檔案處理**：模擬資料與金鑰存檔 (txt)

## 📂 專案架構

src/
├─ model/ # 各角色與報關資料模型
  │ ├─ User.java
  │ ├─ UserHolding.java
  │ ├─ Declaration.java
  │ ├─ Goods.java
  │ ├─ Tax.java
  │ └─ Transport.java
├─ controller/ #視窗介面
│ ├─ LoginUI.java
│ └─ RegisterUI.java
│ ├─User
  | ├─ UserUI.java
  | ├─ AddDeclarationUI.java
  | └─ SelectDeclarationUI.java 
│ ├─officer 
  | └─ CustomsOfficerUI.java
├─ util/ # 工具類別
│ ├─ PublicKeyInfrastructure.java # 金鑰產生 / 轉換
│ ├─ DigitalSignature.java # 簽署與驗證
│ ├─ HsCodeXlsImporter.java # 處理國際貨物碼存入資料庫的流程
│ ├─ DataBaseConnectionPool.java # 處理存取資料庫Driver
│ ├─ IdGenerator.java # 處理各表單Id的生成
│ └─ IOTool.java # 檔案處理
├─ daO
  │ ├─ UserDao.java
  │ ├─ UserHoldingDao.java
  │ ├─ DeclarationDao.java
  │ ├─ GoodsDao.java
  │ ├─ TaxDao.java
  │ ├─ TransportDao.java
  | └─ impl
    │ ├─ UserDaoImpl.java
    │ ├─ UserHoldingDaoImpl.java
    │ ├─ DeclarationDaoImpl.java
    │ ├─ GoodsDaoImpl.java
    │ ├─ TaxDaoImpl.java
    │ └─ TransportDaoImpl.java
├─ service
  │ ├─ UserService.java
  │ ├─ UserHoldingService.java
  │ ├─ DeclarationService.java
  │ ├─ GoodsService.java
  │ ├─ TaxService.java
  │ ├─ TransportService.java
  | └─ impl
    │ ├─ UserService.java
  │ ├─ UserHoldingServiceImpl.java
  │ ├─ DeclarationServiceImpl.java
  │ ├─ GoodsServiceImpl.java
  │ ├─ TaxServiceImpl.java
  │ └─ TransportServiceImpl.java
## 🚀 執行方式
1. 使用 Eclipse 或 IntelliJ 匯入專案
2. 確保 Java 版本為 **Java 11 或以上**
3. 執行 `Main.java`
4. 依照帳號/角色登入，開始模擬報關流程

## 🔒 數位簽章流程
1. 海關人員註冊後會產生 **RSA 公私鑰**  
2. 使用者填寫報關資料 → 海關人員審核過後決定是否使用私鑰簽署  
3. 使用者收到資料後，可以使用對應的公鑰驗證  
4. 驗證成功則通過，否則拒絕  
---

## 📌 備註
此專案為教學模擬用途，非真實報關系統。  
