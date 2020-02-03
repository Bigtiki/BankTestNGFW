package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ReadProperties {

    public static FileInputStream readFile(String file) throws FileNotFoundException {
     return new FileInputStream(new File(file));
    }
    public static Map<String, String>readProperties() throws IOException {
        Properties prop = new Properties();
        prop.load(readFile("C:\\Users\\Mufazzal\\IdeaProjects\\NewM\\config.properties"));

        Map<String,String>properties = new HashMap<>();
        Enumeration<Object>Keys = prop.keys();
        while(Keys.hasMoreElements()){
            String Key = Keys.nextElement().toString();
            String Value= prop.getProperty(Key);
            properties.put(Key,System.getProperty(Key,Value));

        }
        return properties;
    }
}
