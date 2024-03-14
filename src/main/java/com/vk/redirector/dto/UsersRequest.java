package com.vk.redirector.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsersRequest(@JsonProperty("id") @NotNull Long id,
                           @JsonProperty("name") @NotBlank String name,
                           @JsonProperty("username") @NotBlank String username,
                           @JsonProperty("email") @NotBlank String email,
                           @JsonProperty("address") @NotNull Address address,
                           @JsonProperty("phone") @NotBlank String phone,
                           @JsonProperty("website") @NotBlank String website,
                           @JsonProperty("company") @NotNull Company company) {
    record Address(@JsonProperty("street") @NotBlank String street, @JsonProperty("suite") @NotBlank String suite,
                   @JsonProperty("city") @NotBlank String city, @JsonProperty("zipcode") @NotBlank String zipcode,
                   @JsonProperty("geo") @NotNull Geo geo) {
    }

    record Geo(@JsonProperty("lat") @NotBlank String lat, @JsonProperty("lng") @NotBlank String lng) {

    }


    record Company(@JsonProperty("name") @NotBlank String name,
                   @JsonProperty("catchPhrase") @NotBlank String catchPhrase,
                   @JsonProperty("bs") @NotBlank String bs) {

    }
}
