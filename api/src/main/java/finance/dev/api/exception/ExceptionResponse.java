package finance.dev.api.exception;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionResponse {
    private final LocalDateTime localDateTime;
    private final int status;
    private final String error;
    private final String message;

    @Builder(access = AccessLevel.PRIVATE)
    public ExceptionResponse(final int status, final String error, final String message) {
        this.localDateTime = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
    }

    @Builder
    public static ExceptionResponse of(
            final HttpStatus httpStatus, final String message, final Throwable throwable) {
        return ExceptionResponse.builder()
                .status(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .message(message + "(" + throwable.getMessage() + ")")
                .build();
    }
}
