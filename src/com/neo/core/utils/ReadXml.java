package com.neo.core.utils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;

/**
 * @author ManhKM on 3/25/2022
 * @project readfilexml
 */
public class ReadXml {
    private String fileName;
    Document doc;

    public ReadXml(){}

    public ReadXml(String fileName){
        this.fileName = fileName;
        init();
    }

    /**
     * DocumentBuilderFactory
     * DocumentBuilder
     * Document
     * Change flag.
     * Puts all Text nodes in the full depth of the sub-tree underneath this Node...
     * @return
     */
    public boolean init() {
        boolean flag = false;
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            doc = docBuilder.parse(new File(this.fileName));
            doc.normalize();
            flag = true;
            return flag;

        } catch (Exception e){
            System.out.println(String.valueOf(String.valueOf((new StringBuffer("Error in read config file ")).append(fileName).append(e))));
        }
        return flag;
    }

    /**
     * Mục đích: Path of file in project
     * @param _name --> fileName -> xml.
     * @return
     */
    public String findPath(String _name){
        String path = "";
        URL url = null;

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            if(classLoader != null){
                url = classLoader.getResource(_name);
            } else {
                url = ClassLoader.getSystemResource(_name);
            }
            if(url != null){
                path = url.getFile();
                path = path.replaceAll("%20", " ");
            } else {
                path = _name;
            }
        } catch (Exception e){
            path = _name;
            e.printStackTrace();
        }
        return path;
    }

    /**
     * Mục đích convert thành một Ma trận dùng cho project.
     * @param parentNode
     * @param childNode
     * @param value
     * @return
     */
    public Vector<Object> getMatrix(String parentNode, String childNode, String value){

        Vector<Object> matrix = null;
        try {
            String[] attributes = value.split(",");
            NodeList parent = doc.getElementsByTagName(parentNode);
            NodeList childs = parent.item(0).getChildNodes();

            int j = 0;
            matrix = new Vector<Object>();
            for (int i = 0; i < childs.getLength(); i++){
                Node childLine = childs.item(i);
                if(!childLine.getNodeName().equalsIgnoreCase(childNode)){
                    continue;
                }
                Properties line = new Properties();
                for(j = 0; j < attributes.length; j++){
                    String key = attributes[j];
                    if(key == null){
                        key = "";
                    } else{
                        key = key.trim();
                    }

                    if(childLine.getAttributes().getNamedItem(key) != null){
                        line.setProperty(key, childLine.getAttributes().getNamedItem(key).getNodeValue());
                    } else {
                        line.setProperty(key, "");
                    }
                }
                matrix.add(line);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return matrix;
    }

    /**
     * GetHashObjectWithKey
     *      Lấy ra một đối tượng HashObject với key truyền vào ban đầu.
     *      Trả về một HashMap các HashMap.
     *
     * Chuyển config từ file xml thành Object HashMap -> dùng cho các việc config khác.
     * @param parentNode
     * @param childNode
     * @param key
     * @return
     */
    public HashMap<String, HashMap<String, Object>> getHashObjWithKey(String parentNode, String childNode, String key){
        HashMap<String, HashMap<String, Object>> result = new HashMap<>();
        try {
            NodeList parent = doc.getElementsByTagName(parentNode);
            NodeList childs = parent.item(0).getChildNodes();
            int j = 0;
            String keyValue = key;
            for(int i = 0; i < childs.getLength(); i++){
                Node childLine = childs.item(i);
                if(!childLine.getNodeName().equalsIgnoreCase(childNode)){
                    continue;
                }
                int num_attribute = childLine.getAttributes().getLength();
                HashMap<String, Object> line = new HashMap<>();
                keyValue = "";
                for(j = 0; j < num_attribute; j++){
                    Node one = childLine.getAttributes().item(j);
                    if(one.getNodeName().equals(key)){
                        keyValue=one.getNodeValue();
                    }
                    System.out.println("-------> " + one.getNodeName() + "=" + one.getNodeValue());
                    line.put(one.getNodeName(), one.getNodeValue());
                }
                result.put(keyValue, line);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
