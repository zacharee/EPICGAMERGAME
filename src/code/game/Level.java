import java.io.*;

public class Level {

    File levelFile;
    Handler handler;
    
    public Level(String levelFile, Handler handler) {
        this.levelFile = new File(levelFile);
        this.handler = handler;
        parseLevel(this.levelFile);
    }


    public static void parseLevel(File levelFile) {
        String line= "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(levelFile));
            while((line = br.readLine())!=null) {
                String[] values = line.split(",");
                for(int i=0;i<values.length;i++) {

                }
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
