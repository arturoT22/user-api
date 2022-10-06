package cl.ionix.user.util;

import cl.ionix.user.controller.dto.base.MessageResponseDto;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MessageSourceUtilities {

    public static final String DESCRIPCION_DEL_ERROR_NO_ENCONTRADA = "descripción del error no encontrada";
    public static final String CODIGO_DE_ERROR_NO_ENCONTRADO = "código de error no encontrado";

    private MessageSourceUtilities() {
    }

    private static final Locale defaultLocale = new Locale("es", "cl");
    private static ResourceBundle messagesResourceBundle = ResourceBundle.getBundle("message", defaultLocale);
    private static final ResourceBundle codesResourceBundle = ResourceBundle.getBundle("code");


    public static MessageResponseDto getValue(String key){
        String message = getValue(key, null, null);
        String code = getCode(key);
        return new MessageResponseDto(message, code);
    }

    public static MessageResponseDto getValue(String key, Object[] params){
        String message = getValue(key, params, null);
        String code = getCode(key);
        return new MessageResponseDto(message, code);
    }

    private static String getValue(String key, Object[] params, Locale locale){
        try {
            if (locale != null) {
                messagesResourceBundle = ResourceBundle.getBundle("messages", locale);
            } else {
                locale = defaultLocale;
            }

            key = formatKey(key);
            var pattern = messagesResourceBundle.getString(key);
            pattern = new String(pattern.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            if (params != null && params.length > 0) {
                var formatter = new MessageFormat(pattern, locale);
                return formatter.format(params);
            }
            return pattern;
        }catch (MissingResourceException e){
            return DESCRIPCION_DEL_ERROR_NO_ENCONTRADA;
        }
    }
    private static String getCode(String key){
        try {
            return codesResourceBundle.getString(formatKey(key));
        }catch (MissingResourceException e){
            return CODIGO_DE_ERROR_NO_ENCONTRADO;
        }
    }

    private static String formatKey(String key) {
        return key.toLowerCase().replace("_",".");
    }

}
