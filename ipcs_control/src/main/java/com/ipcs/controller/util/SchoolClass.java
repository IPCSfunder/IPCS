package com.ipcs.controller.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen Chao
 */
public class SchoolClass {
    private static List<String> classes = new ArrayList<String>();


    private static String[] classList= new String[]{
            "Math",
            "Chemical",
            "Physical",
            "English",
            "Chinese"
    };

    private SchoolClass(){}

    public static List<String> getClassList(){
        if(classes.isEmpty())
            for(int i =0;i<classList.length;i++)
                classes.add(classList[i]);
        return classes;
    }
}
