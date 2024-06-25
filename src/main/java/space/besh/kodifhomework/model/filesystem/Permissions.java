package space.besh.kodifhomework.model.filesystem;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Permissions {

    boolean read;
    boolean write;
    boolean execute;

}
