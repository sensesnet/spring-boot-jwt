package com.example.springbootjwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest implements Serializable {

    private String username;
    private String password;

    /*
     * NoArgsConstructor for JSON Parsing
     */
}
