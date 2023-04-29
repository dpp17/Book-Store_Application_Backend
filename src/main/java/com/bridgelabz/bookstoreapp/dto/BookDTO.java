package com.bridgelabz.bookstoreapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class BookDTO {

    @NotBlank(message = "bookName Cannot be empty")
    @Pattern(regexp = "^([A-Z]{1}[a-z]{1,}){1,}$", message = "First letter of bookName must start with capital")
    public String bookName;

    @NotBlank(message = "bookName Cannot be empty")
    @Pattern(regexp = "^([A-Z]{1}[a-z]{1,}){1,}$", message = "First letter of bookName must start with capital")
    public String bookAuthor;

    @NotBlank(message = "bookDescription Cannot be empty")
    public String bookDescription;

    @NotBlank(message = "bookPrice Cannot be empty")
    public double bookPrice;

    @NotBlank(message = "bookQuantity Cannot be empty")
    public int bookQuantity;
}
