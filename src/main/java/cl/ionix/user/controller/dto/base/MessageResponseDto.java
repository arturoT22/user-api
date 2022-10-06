package cl.ionix.user.controller.dto.base;

public class MessageResponseDto {
    private String message;
    private String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MessageResponseDto(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public MessageResponseDto(MessageResponseDto messageResponseDto) {
        this.message = messageResponseDto.getMessage();
        this.code = messageResponseDto.getCode();
    }

    public MessageResponseDto(String message) {
        this.message = message;
    }
}
