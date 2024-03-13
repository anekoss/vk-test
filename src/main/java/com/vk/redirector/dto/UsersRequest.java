package com.vk.redirector.dto;

public record UsersRequest(Long id, String name, String username, String email, Address address,
                           String phone,
                           String website, Company company) {
    record Address(String street, String suite, String city, String zipcode, UsersResponse.Address.Geo geo) {

        record Geo(String lat, String lng) {

        }
    }

    record Company(String name, String catchPhrase, String bs) {

    }
}
