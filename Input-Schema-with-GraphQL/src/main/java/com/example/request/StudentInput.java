package com.example.request;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInput {

    private String firstName;
    private String lastName;
    private String email;
    private AddressInput address;
    private List<SubjectInput> learningSubjects;


    @Getter
    @Setter
    public static class AddressInput {
        private String street;
        private String city;
        private String state;
        private String zip;

        // getters and setters
    }

    @Getter
    @Setter
    public static class SubjectInput {
        private String subjectName;
        private Double marksObtained;

    }
}

