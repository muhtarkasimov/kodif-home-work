package space.besh.kodifhomework.model.filesystem;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileObject extends FileStructureObject {

    String extension;

    public FileObject(String name, DirectoryObject parent, String extension) {
        super(name, parent);
        this.extension = extension;
    }

}
