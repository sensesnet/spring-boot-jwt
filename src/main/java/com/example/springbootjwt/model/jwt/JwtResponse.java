package com.example.springbootjwt.model.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * This is class is required for creating a response containing the JWT to be returned to the user.
 */

@Data
@AllArgsConstructor
public class JwtResponse implements Serializable {

    private final String jwtToken;

}
