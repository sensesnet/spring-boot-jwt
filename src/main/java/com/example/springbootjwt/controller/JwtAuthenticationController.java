package com.example.springbootjwt.controller;

import com.example.springbootjwt.model.jwt.JwtRequest;
import com.example.springbootjwt.model.jwt.JwtResponse;
import com.example.springbootjwt.service.JwtUserDetailsService;
import com.example.springbootjwt.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Map;

/**
 * The POST API gets username and password in the body, Using Spring Authentication Manager
 * we authenticate the username and password.If the credentials are valid, a JWT token is
 * created using the JWTTokenUtil and provided to the client.
 */

@Slf4j
@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Operation(summary = "Authenticate user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully logged in",
                    content = { @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = JwtResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Incorrect user data supplied",
                    content = { @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Map.class))
                    })
    })
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            log.info("Start authentication for:" + username + " " + password);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            log.warn("Error: user disabled - [" + username + "]");
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            log.warn("Error: invalid credentials - [" + username + " | " + password + "]");
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
