package br.com.ifsp.StockApp.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDataCreation(
    @NotBlank String name,
    byte[] userPhoto,
    @NotBlank @Email String email,
    @NotBlank String password
) { }
