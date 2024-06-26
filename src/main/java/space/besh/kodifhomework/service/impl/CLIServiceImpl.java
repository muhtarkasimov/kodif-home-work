package space.besh.kodifhomework.service.impl;

import org.springframework.stereotype.Service;
import space.besh.kodifhomework.exceptions.InvalidCDCommandException;
import space.besh.kodifhomework.model.filesystem.DirectoryObject;
import space.besh.kodifhomework.model.filesystem.FileStructureObject;
import space.besh.kodifhomework.service.CLIService;

import java.nio.file.InvalidPathException;
import java.util.stream.Collectors;

@Service
public class CLIServiceImpl implements CLIService {

    private final DirectoryObject root;
    private final DirectoryObject currentDirectory;

    public CLIServiceImpl() {
        root = new DirectoryObject("/", null);
        DirectoryObject home = new DirectoryObject("home", root);
        DirectoryObject user = new DirectoryObject("user", root);
        DirectoryObject muh = new DirectoryObject("muh", user);
        DirectoryObject puh = new DirectoryObject("puh", user);
        DirectoryObject downloads = new DirectoryObject("downloads", muh);
        root.addChild(home);
        root.addChild(user);
        user.addChild(muh);
        user.addChild(puh);
        muh.addChild(downloads);
        currentDirectory = root;
    }

    @Override
    public void cd(String path) {
        if (isCommandStartsFromRoot(path)) {

        } else {
            FileStructureObject tempDir = new DirectoryObject(path, currentDirectory);
            if (currentDirectory.getChildren().contains(tempDir)) {
//                currentDirectory = currentDirectory.getChildren().stream().filter(
//                        (child) -> child.equals(tempDir)
//                );
                //TODO complete
            } else {
                throw new InvalidCDCommandException(path);
            }

        }
    }

    @Override
    public void rm() {

    }

    @Override
    public String ls() {
        return currentDirectory.getChildren().stream()
                .map(FileStructureObject::getName)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String pwd() {
        return currentDirectory.pwd();
    }

    @Override
    public void mkdir() {

    }

    @Override
    public void rmdir() {

    }

    @Override
    public void touch() {

    }

    private boolean isCommandStartsFromRoot(String command) {
        return command.startsWith("/");
    }
}
