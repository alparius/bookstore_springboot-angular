package edu.mpp.bookstore2.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserDto extends BaseDto {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String role;
}