package com.example.springassignmentone;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonBody {
    @Valid
    @NotNull(message = "The course code is required.")
    private int code;

    @NotNull(message = "The course name is required.")
    @Size(max = 3, min = 3, message = "The course name must have exactly 3 characters.")
    private String name;

    @NotNull(message = "The course name is required.")
    private String category;

    @NotNull(message = "The course title is required.")
    @Size(message = "The title name must be provided.")
    private String title;

}

