package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop = new Properties();

    static{
        try{
            FileInputStream fileInput = new FileInputStream("config.properties");
            prop.load(fileInput);
            fileInput.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static String getProperty(String key){
        return prop.getProperty(key);
    }
}
