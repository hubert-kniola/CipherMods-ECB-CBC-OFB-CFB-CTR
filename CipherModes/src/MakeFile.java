import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MakeFile {

    public static void textSource() throws FileNotFoundException {
        GenerateFile(66666, "1MBfile", "Iam1MBsizefile ");
        GenerateFile(5882353, "100MBfile", "Iam100MBsizefile ");
        GenerateFile(11764705, "200MBfile", "Iam200MBsizefile ");
    }

    public static void GenerateFile(Integer size, String output, String whatWrite) throws FileNotFoundException {
        PrintWriter printer = new PrintWriter("C:\\Users\\Dell\\Desktop\\" + output + ".txt");
        for(int i=0;i<size; i++)
        {
            printer.print(whatWrite);
        }
    }
}