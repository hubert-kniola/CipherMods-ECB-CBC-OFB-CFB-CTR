import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class InputOutput {
    private static final String key = "aesEncryptionKey";
    private static final String initVector = "encryptionIntVec";
    private static final String pathToFile = "C:\\Users\\Dell\\Desktop\\";

    public static String readFile(String fileName) throws FileNotFoundException {
        var path = pathToFile + fileName + ".txt";
        File file = new File(path);
        Scanner in = new Scanner(file);
        String readSting = in.nextLine();
        return readSting;
    }

    public static byte[] encMode(String mode, String output, String fileName) throws Exception
    {
        String line = "";
        String encResult = "";
        var readSting = readFile(fileName);
        long startTime = System.nanoTime();

        var encryptedMessage = Encrypting.Encrypt(readSting, mode);

        long estimatedTime = System.nanoTime() - startTime;
        double estimatedTimeSec = (double) estimatedTime / 1_000_000_000;
        double estimatedTimeMsec = (double) estimatedTime / 1_000_000;
        //System.out.println("[ms]: "+ estimatedTimeMsec);
        System.out.println("[s]: "+ estimatedTimeSec);
        return encryptedMessage;
    }

    public static void decMode(String mode, byte[] input) throws Exception
    {
        String line = "";
        String decResult = "";
        long startTime = System.nanoTime();

        var decryptedMessage = Decrypting.Decrypt(input, mode);

        long estimatedTime = System.nanoTime() - startTime;
        double estimatedTimeSec = (double) estimatedTime / 1_000_000_000;
        double estimatedTimeMsec = (double) estimatedTime / 1_000_000;

        //System.out.println("[ms]: "+ estimatedTimeMsec);
        System.out.println("[s]: "+ estimatedTimeSec);
    }

    public static String getFull(String name) {
        var xdec = 16 - (name.length() % 16);
        for (int i = 0; i < xdec; i++) {
            name += " ";
        }
        return name;
    }
}
