package space.besh.kodifhomework.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import space.besh.kodifhomework.model.enums.NodeType;

import java.security.Permissions;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class Node {

    NodeType nodeType;
    Permissions ownerPermissions;
    Permissions groupPermissions;
    Permissions otherPermissions;
    Long size;

}
