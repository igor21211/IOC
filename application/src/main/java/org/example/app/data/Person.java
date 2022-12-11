package org.example.app.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.container.annotations.Bean;
import org.example.container.annotations.Component;
import org.example.container.annotations.Configuration;
@Setter
@Getter
@NoArgsConstructor
public class Person {

    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private Long phoneNumber;




    public Person(String firstName, String lastName, String email, String country, Long phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
