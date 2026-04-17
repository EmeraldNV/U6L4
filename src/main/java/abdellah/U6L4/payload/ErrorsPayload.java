package abdellah.U6L4.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;


@AllArgsConstructor
@Getter
public class ErrorsPayload {
    private String message;
    private LocalDateTime timestamp;
}