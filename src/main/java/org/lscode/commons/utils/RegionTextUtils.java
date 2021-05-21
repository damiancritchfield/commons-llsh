package org.lscode.commons.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class RegionTextUtils {
    public static String getRegionTextLanguage(String key, String language, Object... parameters) throws UnsupportedEncodingException {
        Locale locale = new Locale(language, "");
        ResourceBundle messages = ResourceBundle.getBundle("translation", locale);

        MessageFormat formatter = new MessageFormat("");
        formatter.setLocale(locale);

        String tmp = messages.getString(key);
        String text = new String(tmp.getBytes(), StandardCharsets.UTF_8);

        formatter.applyPattern(text);
        String output = formatter.format(parameters);

        return output;
    }
}
