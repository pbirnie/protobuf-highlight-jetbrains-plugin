package com.jumpnotzerosoftware.jetbrains.plugin;

public class ProtoHighStringUtil {

    public static String extractPackage(String fullClassName){

        String result = "";

        int posn = fullClassName.lastIndexOf(".");

        if (posn != -1){

            result = fullClassName.substring(0, posn);
        }

        return result;
    }
}
