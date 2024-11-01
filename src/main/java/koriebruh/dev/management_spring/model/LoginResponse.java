package koriebruh.dev.management_spring.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class LoginResponse {

    private int UserId;

    private String token;

    private String message;
}
