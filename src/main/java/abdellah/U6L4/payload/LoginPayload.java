package abdellah.U6L4.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginPayload {

    String email;
    String password;

}
