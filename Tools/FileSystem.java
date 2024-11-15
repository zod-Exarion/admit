package Tools;

import java.util.HashMap;
import java.util.Scanner;
import java.io.File; 
import java.io.IOException;
import java.io.Serializable;
import java.io.FileWriter; 

public class FileSystem implements Serializable{
  private File file;
    private Scanner reader;
    private FileWriter writer;

    public FileSystem(String name){
        try{
            file = new File(name);
            file.createNewFile();
        }
        catch(IOException e){
            System.out.println("An error has occured");
            e.printStackTrace();
        }
    }

      public FileSystem(String name, boolean flag){
        file = new File(name);
        if (!file.exists()){
           file.mkdirs();
        }
    }

    public void write(String content, boolean append){
        try {
            writer = new FileWriter(file,append);
            writer.write(content);
            writer.close();
        }
        catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }

      void flush(){
        try {
            writer = new FileWriter(file,false);
            writer.write("");
            writer.close();
        }
        catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
      }

     public void addUser(String email,String password){
        write("\n"+email + ":" + password,true);
      }

      public HashMap<String,String> fetchDatabase(){
        HashMap<String,String> map = new HashMap<String, String>();
        try{
          reader = new Scanner(file);
          while(reader.hasNextLine()){
            String s = reader.nextLine();
            int x = s.indexOf(':');
            map.put(s.substring(0,x),s.substring(x+1,s.length()));
          }
        }
        catch(Exception e){
          reader.close();
        }
        return map;
    }

      public String[] readAll(){
        if(!file.isDirectory()) return new String[0];

        File[] listOfFiles = file.listFiles();
          assert listOfFiles != null;
          String[] fileNameList = new String[listOfFiles.length];

        int i = 0;
        for (File file : listOfFiles)
          if (file.isFile()){
            fileNameList[i] = "Students/"+file.getName();
            i++;
        }

        return fileNameList;
    }
}
