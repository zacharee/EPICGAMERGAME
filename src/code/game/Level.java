import java.io.*;

public class Level {

    File levelFile;
    public Level(String levelFile) {
        this.levelFile = new File(levelFile);
    }


    public static void parseLevel(File levelFile) {
        String line= "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(levelFile));
            while((line = br.readLine())!=null) {
                String[] values = line.split(",");
                //Add values
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
