package space.besh.kodifhomework.model.filesystem;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.security.Permissions;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"parent", "name"})
public abstract class FileStructureObject {

    String name;
    DirectoryObject parent;
    Permissions ownerPermissions;
    Permissions groupPermissions;
    Permissions otherPermissions;
    Long size;

    public FileStructureObject(String name, DirectoryObject parent) {
        this.name = name;
        this.parent = parent;
    }

    public String pwd() {
        if (parent == null) {
            return name;
        } else {
            return parent.pwd() + (parent.getName().equals("/") ? "" : "/") + name;
        }
    }

    public FileStructureObject getByName(String name) {
        if (name.equals(this.name)) {
            return this;
        }
        return null;
    }

}
