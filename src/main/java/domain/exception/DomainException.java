package domain.exception;

import com.google.gson.JsonObject;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {

    private String message;
    private int statusCode;

    public DomainException(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String toJson() {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("message", this.message);

        return jsonObject.toString();
    }

}
