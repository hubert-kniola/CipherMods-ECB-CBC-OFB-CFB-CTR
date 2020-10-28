import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class Encrypting {
    public static byte[] Encrypt(byte[] keyBytes, byte[] ivBytes, byte[] input, String mode) throws Exception
    {
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        Cipher cipher;

        //System.out.println("input : " + new String(input));
        switch (mode){
            case "ECB":
                cipher = Cipher.getInstance("AES/ECB/NoPadding");
                cipher.init(Cipher.ENCRYPT_MODE, key);
                break;
            case "CBC":
                cipher = Cipher.getInstance("AES/CBC/NoPadding");
                cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
                break;
            case "OFB":
                cipher = Cipher.getInstance("AES/OFB/NoPadding");
                cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
                break;
            case "CFB":
                cipher= Cipher.getInstance("AES/CFB/NoPadding");
                cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
                break;
            case "CTR":
                cipher = Cipher.getInstance("AES/CTR/NoPadding");
                cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
                break;
            default:
                System.out.println("No such mode, default mode: ECB!");
                cipher = Cipher.getInstance("AES/ECB/NoPadding");
                cipher.init(Cipher.ENCRYPT_MODE, key);
                break;
        }


//CFB, OFB, CTR, CBC/PKCS5Padding,

        ByteArrayInputStream bIn = new ByteArrayInputStream(input);
        CipherInputStream cIn = new CipherInputStream(bIn, cipher);
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();

        //int ch;
        //while ((ch = cIn.read()) >= 0) {
        //    bOut.write(ch);
        //}


        return bOut.toByteArray();
    }
}
