import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class InputOutput {
    public static void encMode(byte[] keyBytes, byte[] ivBytes,  String mode, String output) throws Exception
    {
        //System.out.println("Get file name (String): ");
        //Scanner scan = new Scanner(System.in);
        //var fileName = scan.nextLine();
        var fileName = "macbeth";
        String pathToFile = "C:\\Users\\Dell\\Desktop\\" + fileName + ".txt";

        String line = "";
        byte[] test = new byte[0];
        long startTime = System.nanoTime();
        PrintWriter printer = new PrintWriter("C:\\Users\\Dell\\Desktop\\" + output + ".txt");
        Scanner scans = new Scanner(new File(pathToFile));
        while(scans.hasNextLine()) {
            line = scans.nextLine();
            //System.out.println(line);
            if((mode == "ECB" || mode == "CBC") && (line.length() % 16) != 0)
                getFull(line);
            test = Encrypting.Encrypt(keyBytes, ivBytes, line.getBytes(), mode);
            //System.out.println(test);
            printer.println(test);
        }
        long estimatedTime = System.nanoTime() - startTime;
        double estimatedTimeSec1 = (double) estimatedTime / 1_000_000_000;
        System.out.println("Esimated time:");
        System.out.println("[ns]: "+ estimatedTime);
        System.out.println("[s]: "+ estimatedTimeSec1);
    }

    public static void decMode(byte[] keyBytes, byte[] ivBytes,  String mode, String input) throws Exception
    {
        //System.out.println("[Decrypting]\nGet file name (String): ");
        //Scanner scan = new Scanner(System.in);
        //var fileName = scan.nextLine();
        //var fileName = "macbeth";
        String pathToFile = "C:\\Users\\Dell\\Desktop\\" + input + ".txt";

        String line = "";
        String test = "";
        long startTime = System.nanoTime();
        Scanner scans = new Scanner(new File(pathToFile));
        while(scans.hasNextLine()) {
            line = scans.nextLine();
            System.out.println(line);
            test = Decrypting.Decrypt(keyBytes, ivBytes, line.getBytes(), mode);
            System.out.println(test);
        }
        long estimatedTime = System.nanoTime() - startTime;
        double estimatedTimeSec1 = (double) estimatedTime / 1_000_000_000;
        System.out.println("Esimated time:");
        System.out.println("[ns]: "+ estimatedTime);
        System.out.println("[s]: "+ estimatedTimeSec1);
    }

    public static String getFull(String name) {
        var xdec = 16 - (name.length() % 16);
        for (int i = 0; i < xdec; i++) {
            name += " ";
        }
        return name;
    }
}
