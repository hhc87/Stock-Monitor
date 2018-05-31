package com.stock.monitor.util;

import java.util.Arrays;
import java.util.List;

/**
 * @Author Andrew He
 * @Date: Created in 16:04 2018/4/26
 * @Description:
 * @Modified by:
 */
public class SplitUtil {

    public static List<String> splitString(String mainString, String splitKey) {

        String[] splitResult = mainString.split(splitKey);
        List<String> stringList = Arrays.asList(splitResult);

        return stringList;
    }
}
