package Utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;

public class ReadPropertiesUtility {

    Properties properties;


    public Properties readPropertyFiles(String propertyFilePath){
        try{
            InputStream input = new FileInputStream(propertyFilePath);
            properties = new Properties();
            properties.load(input);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
