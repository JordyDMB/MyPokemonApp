package com.hey.mypokemonapp.core.utils;

public class StringUtils {

    public static boolean isValidString(String text) {
        return text != null && !text.isEmpty();
    }

    public static String capitalize(String line) {
        if (line.toCharArray().length > 1) {
            line = line.toLowerCase();
            return Character.toUpperCase(line.charAt(0)) + line.substring(1);
        }
        return line.toUpperCase();
    }

    public static String formatName(String name) {
        if (isValidString(name)) {
            if (name.contains("-")) {
                name = name.replace("-", " ");
            }
            StringBuilder mName = new StringBuilder();
            String[] texts = name.split(" ");
            for (String text : texts) {
                mName.append(capitalize(text)).append(" ");
            }
            return mName.toString().trim();
        }
        return "";
    }
}
