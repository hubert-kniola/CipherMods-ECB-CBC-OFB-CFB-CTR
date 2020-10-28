import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class Decrypting {
    public static String Decrypt(byte[] keyBytes, byte[] ivBytes, byte[] cipherText, String mode) throws Exception{
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        Cipher cipher;

        switch (mode){
            case "CFB":
                cipher= Cipher.getInstance("AES/CFB/NoPadding");
                cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
                break;
            case "OFB":
                cipher = Cipher.getInstance("AES/OFB/NoPadding");
                cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
                break;
            case "CTR":
                cipher = Cipher.getInstance("AES/CTR/NoPadding");
                cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
                break;
            case "CBC":
                cipher = Cipher.getInstance("AES/CBC/NoPadding");
                cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
                break;
            case "ECB":
                cipher = Cipher.getInstance("AES/ECB/NoPadding");
                cipher.init(Cipher.DECRYPT_MODE, key);
                break;
            default:
                System.out.println("No such mode, default mode: ECB!");
                cipher = Cipher.getInstance("AES/ECB/NoPadding");
                cipher.init(Cipher.DECRYPT_MODE, key);
                break;
        }

        ByteArrayOutputStream bOut = new ByteArrayOutputStream();

        CipherOutputStream cOut = new CipherOutputStream(bOut, cipher);
        //cOut.write(cipherText);
        cOut.close();


        return new String(bOut.toByteArray());
    }
}
