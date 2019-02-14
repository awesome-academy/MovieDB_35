package com.framgia.moviedb_35.util;

public class StringUtils {
    public static String append(String... strings) {
        StringBuilder builder = new StringBuilder();
        for (String string : strings) {
            builder.append(string);
        }
        return builder.toString();
    }

    public static String getImageUrl(int size, String childUrl) {
        return StringUtils.append(Constants.IMAGE_LINK, String.valueOf(size), Constants.SLASH, childUrl);
    }
}
