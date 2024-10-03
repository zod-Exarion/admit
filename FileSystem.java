import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileSystem{
    private File file;
    private Scanner reader;
    private FileWriter writer;
    private FileWriter writerOverride;

    public FileSystem(String pathname){
        try {
            file = new File(pathname);
            if(!file.exists()){file.createNewFile();}
            reader = new Scanner(file);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean append(String content){
        try {
            writer = new FileWriter(file, true);
            writer.write(content);
            writer.close();
        }
        catch(Exception e){
            return false;
        }
        return true;
    }

    public boolean override(String content) {
        try {
            writerOverride = new FileWriter(file, false);
            writerOverride.write(content);
            writerOverride.close();
        }
        catch(Exception e){
            return false;
        }
        return true;
    }

    public boolean exists(){
        return file.exists();
    }

    public String readContent(){
        StringBuilder content = new StringBuilder();
        while(reader.hasNextLine()){
            content.append(reader.nextLine()).append("\n");
        }
        return content.toString();
    }
}
