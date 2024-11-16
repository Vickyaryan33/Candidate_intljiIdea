package com.crm.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeDto {
    private Long id;
    @NotBlank(message = "Name is not null")
    @Size( min=2 , max = 30 , message=" name should be between 2 to 30 characters")
    private String name;
    @Email(message = "email should be valid")
    private String emailId;
    @Size(min=10,max=10, message="mobile number should be of 10 digits")
    private String mobile;
  //  private Date startDate;

}
