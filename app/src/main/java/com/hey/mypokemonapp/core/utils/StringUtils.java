package com.hey.mypokemonapp.core.utils;

public class StringUtils {

    public static String capitalize(String line){
        line = line.toLowerCase();
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    public static String formatName(String name){
        if (name.contains("-")){
            name = name.replace("-", " ");
        }
        StringBuilder mName = new StringBuilder();
        String[] texts = name.split(" ");
        for (String text : texts) {
            text  = text.toLowerCase();
            mName.append(capitalize(text)).append(" ");
        }
        return mName.toString().trim();
    }
}
