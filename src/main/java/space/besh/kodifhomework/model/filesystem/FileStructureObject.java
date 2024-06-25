package space.besh.kodifhomework.model.filesystem;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.security.Permissions;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class FileStructureObject {

    String name;
    Permissions ownerPermissions;
    Permissions groupPermissions;
    Permissions otherPermissions;
    Long size;

}
