import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class InputOutput {
    public static void encMode(String mode, String output, String fileName) throws Exception
    {
        //var fileName = "100MBfile";
        String pathToFile = "C:\\Users\\Dell\\Desktop\\" + fileName + ".txt";

        String line = "";
        String encResult = "";
        long startTime = System.nanoTime();

        PrintWriter printer = new PrintWriter("C:\\Users\\Dell\\Desktop\\" + output + ".txt");
        Scanner scans = new Scanner(new File(pathToFile));
        StringBuilder sb = new StringBuilder();
        while(scans.hasNextLine()) {
            line = scans.nextLine();
            encResult = Encrypting.Encrypt(line, mode);
            //System.out.println(encResult);
            printer.println(encResult);
        }
        printer.close();
        long estimatedTime = System.nanoTime() - startTime;
        double estimatedTimeSec = (double) estimatedTime / 1_000_000_000;
        double estimatedTimeMsec = (double) estimatedTime / 1_000_000;

        //System.out.println("[ns]: "+ estimatedTime);
        //System.out.println("[ms]: "+ estimatedTimeMsec);
        System.out.println("[s]: "+ estimatedTimeSec);
    }

    public static void decMode(String mode, String input) throws Exception
    {
        String pathToFile = "C:\\Users\\Dell\\Desktop\\" + input + ".txt";

        String line = "";
        String decResult = "";

        long startTime = System.nanoTime();
        Scanner scans = new Scanner(new File(pathToFile));
        StringBuilder sb = new StringBuilder();
        while(scans.hasNextLine()) {
            line = scans.nextLine();
            decResult = Decrypting.Decrypt(line, mode);
            //System.out.println(line);
            //System.out.println(decResult);
        }
        //System.out.println(decResult);
        long estimatedTime = System.nanoTime() - startTime;
        double estimatedTimeSec = (double) estimatedTime / 1_000_000_000;
        double estimatedTimeMsec = (double) estimatedTime / 1_000_000;

        //System.out.println("[ns]: "+ estimatedTime);
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
