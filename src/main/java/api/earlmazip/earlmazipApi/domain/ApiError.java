package api.earlmazip.earlmazipApi.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class ApiError implements Serializable {
    private String message;

    private HttpStatus status;
}
