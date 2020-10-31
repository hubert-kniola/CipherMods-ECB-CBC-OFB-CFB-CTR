import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MakeFile {

    public static void GenerateFile(Integer size, String output, String whatWrite) throws FileNotFoundException {
        PrintWriter printer = new PrintWriter("C:\\Users\\Dell\\Desktop\\" + output + ".txt");
        for(int i=0;i<size; i++)
        {
            printer.print(whatWrite);
        }
    }
}
