package space.besh.kodifhomework.model.filesystem;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DirectoryObject extends FileStructureObject {

    List<FileStructureObject> children = new ArrayList<>();

    public DirectoryObject(String name, DirectoryObject parent) {
        super(name, parent);
    }

    public void addChild(FileStructureObject child) {
        children.add(child);
    }

    public void removeChild(FileStructureObject child) {
        children.remove(child);
    }

    public String pwd() {
        if (getParent() == null) {
            return getName();
        } else {
            return getParent().pwd() + (getParent().getName().equals("/") ? "" : "/") + getName();
        }
    }

    public FileStructureObject getChild(String name) {
//        for (FileStructureObject child : children) {
//            if (child.getName().equals(name)) {
//                return child;
//            } else {
//
//            }
//        }
        return null;
    }
}
