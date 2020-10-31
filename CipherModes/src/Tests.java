import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Tests {
    private static final String key = "aesEncryptionKey";
    private static final String initVector = "encryptionIntVec";
    public static void Test()
    {
        String originalString = "This is an example sentence that checks the corruption of each mode of the AES cipher";
        System.out.println("Original: " + originalString);
        String encryptedString = encrypt(originalString);
        System.out.println("Encrypted: " + encryptedString);
        String decryptedString = decrypt(encryptedString);
        System.out.println("[0 bits changed] After: " + decryptedString);
        System.out.println(" ");

        //System.out.println("Original: " + originalString);
        String encryptedString1 = encrypt(originalString);
        var encryptedString2 = Base64.getDecoder().decode(encryptedString1);
        encryptedString2[0] = 2;
        var changedString2 = Base64.getEncoder().encodeToString(encryptedString2);
        //System.out.println("Encrypted: " + changedString2);
        String decryptedString2 = decrypt(changedString2);
        System.out.println("[1 bit changed] After: " + decryptedString2);
        System.out.println(" ");

        //System.out.println("Original: " + originalString);
        String encryptedString3 = encrypt(originalString);
        var encryptedString4 = Base64.getDecoder().decode(encryptedString3);
        for(int i=0 ;i<10; i++)
            encryptedString4[i] = 2;
        var changedString3 = Base64.getEncoder().encodeToString(encryptedString4);
        //System.out.println("Encrypted: " + changedString3);
        String decryptedString3 = decrypt(changedString3);
        System.out.println("[10 bits changed] After: " + decryptedString3);
        System.out.println(" ");

        //System.out.println("Original: " + originalString);
        String encryptedString5 = encrypt(originalString);
        var encryptedString6 = Base64.getDecoder().decode(encryptedString5);
        for(int i=0 ;i<30; i++)
            encryptedString6[i] = 2;
        var changedString4 = Base64.getEncoder().encodeToString(encryptedString6);
        //System.out.println("Encrypted: " + changedString4);
        String decryptedString4 = decrypt(changedString4);
        System.out.println("[30 bits changed] After: " + decryptedString4);
        System.out.println(" ");

        //System.out.println("Original: " + originalString);
        String encryptedString7 = encrypt(originalString);
        var encryptedString8 = Base64.getDecoder().decode(encryptedString7);
        for(int i=0 ;i<45; i++)
            encryptedString8[i] = 2;
        var changedString5 = Base64.getEncoder().encodeToString(encryptedString8);
        //System.out.println("Encrypted: " + changedString5);
        String decryptedString5 = decrypt(changedString5);
        System.out.println("[45 bits changed] After: " + decryptedString5);
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
