package com.neo.core.utils;

/**
 * @author ManhKM on 3/25/2022
 * @project readfilexml
 */
public class FuncHelper {
    /**
     * Trả về môt kiểu int từ một giá trị String cho trước.
     * Nếu "   1212   " -> 1212
     * Nếu "ads"  -> 0
     * @param v
     * @return
     */
    public static int getInt(String v){
        try {
            return Integer.parseInt(v.trim());
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
