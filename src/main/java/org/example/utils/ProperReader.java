package org.example.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class ProperReader {

    static FileInputStream fileInputStream = null;  // we assign null here to make sure static(which loads first) var doesn't take up any memory

    static Properties p = null;

    static Map<String, String> PROP_KEYS = new HashMap<>();

    static {
        try {
            fileInputStream = new FileInputStream(new File(System.getProperty("user.dir")+"/"+"src/test/resources/config.properties"));
            p = new Properties();
            p.load(fileInputStream);

            for(Object keys: p.keySet()){
                PROP_KEYS.put(String.valueOf(keys),p.getProperty(String.valueOf(keys)));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            try {
                if(Objects.nonNull(fileInputStream)){
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String readkey(String key) throws Exception {
        if(key == null && p.getProperty(key) == null){
            throw new Exception(p.getProperty(key)+" not found !!");
        } else {
            return PROP_KEYS.get(key);
        }
    }
}
