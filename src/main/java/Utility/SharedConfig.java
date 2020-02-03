package Utility;

import java.io.IOException;
import java.util.Map;

public class SharedConfig {
    public static Map<String,String> config;

    static {
        try {
            config = ReadProperties.readProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
