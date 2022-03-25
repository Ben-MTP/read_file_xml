package com.neo.core.app;

import com.neo.core.utils.ReadXml;

import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;

/**
 * @author ManhKM on 3/25/2022
 * @project readfilexml
 */
public class ReadXmlApp {
    public static void main(String[] args) {
        ReadXml reader = new ReadXml();
        String path_user = reader.findPath("config/module.xml");
        System.out.println("-------> Start load file module: " + path_user);
        reader.setFileName(path_user);
        reader.init();

        HashMap<String, HashMap<String, Object>> hConfig = reader.getH
    }
}
