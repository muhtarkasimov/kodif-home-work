package space.besh.kodifhomework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CommandResponse {
    String header;
    String payload;
}
