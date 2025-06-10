package com.joshuapok.buynowdotcom.Controller;


import com.joshuapok.buynowdotcom.Requests.LoginRequest;
import com.joshuapok.buynowdotcom.Security.User.ShopUserDetailsService;
import com.joshuapok.buynowdotcom.Security.jwt.JwtUtils;
import com.joshuapok.buynowdotcom.utils.CookieUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JwtUtils jwtUtils;
    private final CookieUtils cookieUtils;
    private ShopUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    @Value("${auth.token.refreshExpirationInMils}")
    private Long refreshTokenExpirationTime;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest request, HttpServletResponse response){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String accessToken = jwtUtils.generateAccessTokenForUser(authentication);
        String refreshToken = jwtUtils.generateRefreshToken(request.getEmail());
        cookieUtils.addRefreshTokenCookie(response, refreshToken, refreshTokenExpirationTime);
        Map<String, String> token = new HashMap<>();
        token.put("accessToken", accessToken);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request){
        String refreshToken = cookieUtils.getRefreshTokenFromCookies(request);
        if(refreshToken != null){
            boolean isValid = jwtUtils.validateToken(refreshToken);
            if(isValid){
                String username = jwtUtils.getUsernameFromToken(refreshToken);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                String newAccessToken = jwtUtils
                        .generateAccessTokenForUser(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
                if(newAccessToken != null){
                    Map<String, String> token = new HashMap<>();
                    token.put("accessToken", newAccessToken);
                    return ResponseEntity.ok(token);
                }else{
                    return ResponseEntity.status(500).body("Error generating access token");
                }
            }
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("invalid refresh token!");

    }

}
