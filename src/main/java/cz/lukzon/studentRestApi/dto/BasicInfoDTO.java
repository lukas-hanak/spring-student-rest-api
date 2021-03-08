package cz.lukzon.studentRestApi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class BasicInfoDTO {

    private String message;
    private boolean success;
    private int status;
}
