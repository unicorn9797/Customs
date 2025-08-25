package util;

import java.security.*;

public class RSABenchmark {
    public static void main(String[] args) throws Exception {
        int[] keySizes = {512, 1024, 2048};
        String msg = "這是一個模擬測試訊息，用來測RSA效能";

        for (int size : keySizes) {
            System.out.println("=== 測試 RSA-" + size + " ===");

            // 測試金鑰生成
            long start = System.currentTimeMillis();
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(size);
            KeyPair keyPair = keyGen.generateKeyPair();
            long end = System.currentTimeMillis();
            System.out.println("KeyPair生成時間: " + (end - start) + " ms");

            // 測試簽章
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(keyPair.getPrivate());
            signature.update(msg.getBytes());

            start = System.currentTimeMillis();
            byte[] sign = signature.sign();
            end = System.currentTimeMillis();
            System.out.println("簽章時間: " + (end - start) + " ms");

            // 測試驗章
            signature.initVerify(keyPair.getPublic());
            signature.update(msg.getBytes());

            start = System.currentTimeMillis();
            boolean result = signature.verify(sign);
            end = System.currentTimeMillis();
            System.out.println("驗章時間: " + (end - start) + " ms, 驗證結果=" + result);

            System.out.println();
        }
    }
}
