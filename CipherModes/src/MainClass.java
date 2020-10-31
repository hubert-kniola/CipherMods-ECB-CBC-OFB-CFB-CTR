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

        //MakeFile.GenerateFile(66666, "1MBfile", "Iam1MBsizefile ");
        //MakeFile.GenerateFile(5882353, "100MBfile", "Iam100MBsizefile ");
        //MakeFile.GenerateFile(29411765, "200MBfile", "Iam200MBsizefile ");

        String mode = "ECB", outputName = "resultECB", fileName="200MBfile";
        System.out.println("[ECB] Encrypted: ");
        InputOutput.encMode(mode, outputName, fileName);
        System.out.println("[ECB] Decrypted: ");
        InputOutput.decMode(mode, outputName);

        mode = "CBC";
        outputName = "resultCBC";
        System.out.println("[CBC] Encrypted: ");
        InputOutput.encMode(mode, outputName, fileName);
        System.out.println("[CBC] Decrypted: ");
        InputOutput.decMode(mode, outputName);

        mode = "OFB";
        outputName = "resultOFB";
        System.out.println("[OFB] Encrypted: ");
        InputOutput.encMode(mode, outputName, fileName);
        System.out.println("[OFB] Decrypted: ");
        InputOutput.decMode(mode, outputName);

        mode = "CFB";
        outputName = "resultCFB";
        System.out.println("[CFB] Encrypted: ");
        InputOutput.encMode(mode, outputName, fileName);
        System.out.println("[CFB]Decrypted: ");
        InputOutput.decMode(mode, outputName);

        mode = "CTR";
        outputName = "resultCTR";
        System.out.println("[CTR] Encrypted: ");
        InputOutput.encMode(mode, outputName, fileName);
        System.out.println("[CTR] Decrypted: ");
        InputOutput.decMode(mode, outputName);
        //MakeFile.GenerateFile(11764705, "200MBfile", "Iam200MBsizefile ");
        //Tests.Test();
    }
}