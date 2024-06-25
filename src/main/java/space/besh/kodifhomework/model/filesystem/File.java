package space.besh.kodifhomework.model.filesystem;

public class File extends FileStructureObject {

    byte[] content;

    public File(String name, byte[] content) {
        super.setName(name);
        this.content = content;
    }
}
