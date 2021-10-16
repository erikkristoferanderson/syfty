package net.syfty.app.form;

import lombok.Data;
import net.syfty.app.form.validation.TwoFieldsAreEqual;
import net.syfty.app.form.validation.UsernameUnique;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@TwoFieldsAreEqual(fieldOneName = "confirmPassword", fieldTwoName = "password", message = "Password and Conform Password must be the same.")
public class SignUpForm {


    @NotEmpty
    @Email
    @UsernameUnique(message = "email already exists in database")
    private String email;

    @NotEmpty(message = "Password can not be empty.")
    @Size(min = 10, max = 25, message = "Password must be between 10 and 25 characters")
    private String password;

    @NotEmpty(message = "Confirm Password is required.")
    private String confirmPassword;
}




