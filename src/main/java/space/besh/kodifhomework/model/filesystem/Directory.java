package space.besh.kodifhomework.model.filesystem;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Directory extends FileStructureObject {

    List<FileStructureObject> content;

    public Directory(String name) {
        super.setName(name);
        content = new ArrayList<>();
    }

    public Directory(String name, List<FileStructureObject> content) {
        super.setName(name);
        this.content = content;
    }
}
