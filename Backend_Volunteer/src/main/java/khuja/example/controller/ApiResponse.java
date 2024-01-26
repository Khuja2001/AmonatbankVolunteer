package khuja.example.controller;

import lombok.Data;

@Data
public class ApiResponse {
    private String message;
    private Integer id;

    public ApiResponse(String message) {
        this.message = message;
    }

    public ApiResponse(String message, Integer id) {
        this.message = message;
        this.id = id;
    }
}
