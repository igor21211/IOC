package org.example.app.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer phoneNumber;
}
