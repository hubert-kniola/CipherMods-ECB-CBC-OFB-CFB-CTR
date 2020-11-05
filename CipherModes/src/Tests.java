import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Tests {
    private static final String key = "aesEncryptionKey";
    private static final String initVector = "encryptionIntVec";

    public static void CorruptionTest()
    {
        String originalString = "This is an example sentence that checks the corruption of each mode of the AES cipher";
        System.out.println("Original: " + originalString);
        String encryptedString = encrypt(originalString);
        System.out.println("Encrypted: " + encryptedString);
        Tests.Test(1, originalString);
        Tests.Test(10, originalString);
        Tests.Test(30, originalString);
        Tests.Test(45, originalString);

    }
    public static void Test(int count, String originalString)
    {
        String encryptedString = encrypt(originalString);
        var encryptedStringNew = Base64.getDecoder().decode(encryptedString);
        for(int i=1 ;i<=count; i++)
            encryptedStringNew[i] = 2;
        var changedString = Base64.getEncoder().encodeToString(encryptedStringNew);
        String decryptedString = decrypt(changedString);
        System.out.println("["+ count+ " bits changed] After: " + decryptedString);
        System.out.println(" ");
    }

    public static String encrypt(String value) {
        try {
            //CBC dziala z PKCS5PADDING
            //ECB dziala bez IV
            //OFB dziala z PKCS5PADDING
            //CFB dziala z PKCS5PADDING
            //CTR dziala z NoPadding
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
