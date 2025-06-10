package com.joshuapok.buynowdotcom.Dto;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
}
