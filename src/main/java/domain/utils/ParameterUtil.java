package domain.utils;

import domain.exception.impl.BadRequestException;
import domain.exception.impl.InternalServerError;
import jakarta.servlet.http.HttpServletRequest;

public class ParameterUtil {

    public static String parseString(HttpServletRequest request, String parameterName) {
        var value = request.getParameter(parameterName);

        if (value == null || value.isBlank()) {
            throw new BadRequestException("Parâmetro " + parameterName + " não pode ser vazio");
        }

        return value;
    }

    public static double parseDouble(HttpServletRequest request, String parameterName) {
        var value = request.getParameter(parameterName);

        if (value == null || value.isBlank()) {
            throw new BadRequestException("Parâmetro " + parameterName + " não pode ser vazio");
        }

        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException exception) {
            throw new BadRequestException("Parâmetro " + parameterName + " deve ser um número decimal");
        }
    }

    public static int parseInt(HttpServletRequest request, String parameterName) {
        var value = request.getParameter(parameterName);

        if (value == null || value.isBlank()) {
            throw new BadRequestException("Parâmetro " + parameterName + " não pode ser vazio");
        }

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new BadRequestException("Parâmetro " + parameterName + " deve ser um número inteiro");
        }
    }

    public static boolean parseBoolean(HttpServletRequest request, String parameterName) {
        var value = request.getParameter(parameterName);

        if (value == null || value.isBlank()) {
            throw new BadRequestException("Parâmetro " + parameterName + " não pode ser vazio");
        }

        return Boolean.parseBoolean(value);
    }

    public static <T> T parseAttribute(HttpServletRequest request, String attributeName, Class<T> clazz) {
        var value = request.getAttribute(attributeName);

        if (value == null) {
            throw new InternalServerError();
        }

        if (!clazz.isInstance(value)) {
            throw new InternalServerError();
        }

        return clazz.cast(value);
    }

}
