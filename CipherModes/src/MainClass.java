import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.KeyGenerator;

public class MainClass {
    public static void main(String[] args) throws Exception {
        //Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        byte[] input = "testtesttesttest".getBytes();


        var keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        var secretKey = keyGen.generateKey();

        byte[] keyBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09,
                0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17 };
        byte[] ivBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x00, 0x01, 0x02, 0x03, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x01 };

        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        String mode = "ECB", outputName = "resultECB";
        System.out.println("[ECB] Encrypted: "); //sprawdzic czy dzieli sie przez 16
        InputOutput.encMode(keyBytes, ivBytes, mode, outputName);
        System.out.println("[ECB] Decrypted: ");
        InputOutput.decMode(keyBytes, ivBytes, mode, outputName);

        mode = "CBC";
        outputName = "resultCBC";
        System.out.println("[CBC] Encrypted: "); //sprawdzic czy dzieli sie przez 16
        InputOutput.encMode(keyBytes, ivBytes, mode, outputName);
        System.out.println("[CBC] Decrypted: ");
        InputOutput.decMode(keyBytes, ivBytes, mode, outputName);

        mode = "OFB";
        outputName = "resultOFB";
        System.out.println("[OFB] Encrypted: ");
        InputOutput.encMode(keyBytes, ivBytes, mode, outputName);
        System.out.println("[OFB] Decrypted: ");
        InputOutput.decMode(keyBytes, ivBytes, mode, outputName);

        mode = "CFB";
        outputName = "resultCFB";
        System.out.println("[CFB] Encrypted: ");
        InputOutput.encMode(keyBytes, ivBytes, mode, outputName);
        System.out.println("[CFB]Decrypted: ");
        InputOutput.decMode(keyBytes, ivBytes, mode, outputName);

        mode = "CTR";
        outputName = "resultCTR";
        System.out.println("[CTR] Encrypted: ");
        InputOutput.encMode(keyBytes, ivBytes, mode, outputName);
        System.out.println("[CTR] Decrypted: ");
        InputOutput.decMode(keyBytes, ivBytes, mode, outputName);
    }
}