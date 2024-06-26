package space.besh.kodifhomework.service.impl;

import org.springframework.stereotype.Service;
import space.besh.kodifhomework.exceptions.InvalidCDCommandException;
import space.besh.kodifhomework.exceptions.InvalidInputException;
import space.besh.kodifhomework.model.CommandResponse;
import space.besh.kodifhomework.model.filesystem.DirectoryObject;
import space.besh.kodifhomework.model.filesystem.FileObject;
import space.besh.kodifhomework.model.filesystem.FileStructureObject;
import space.besh.kodifhomework.service.CLIService;

import java.util.stream.Collectors;

@Service
public class CLIServiceImpl implements CLIService {

    private final DirectoryObject root;
    private DirectoryObject currentDirectory;

    //MOCK DATA
    private final String user = "MUH";
    private final String device = "MacBook Pro";

    public CLIServiceImpl() {
        root = new DirectoryObject("/", null);
        DirectoryObject home = new DirectoryObject("home", root);
        DirectoryObject users = new DirectoryObject("users", root);
        DirectoryObject muh = new DirectoryObject("muh", users);
        DirectoryObject puh = new DirectoryObject("puh", users);
        DirectoryObject downloads = new DirectoryObject("downloads", muh);
        root.addChild(home);
        root.addChild(users);
        users.addChild(muh);
        users.addChild(puh);
        muh.addChild(downloads);
        currentDirectory = root;
    }

    @Override
    public CommandResponse cd(String value) {
        try {
            if (isCommandStartsFromRoot(value)) {
                //TODO complete
                return new CommandResponse("this command is not completed yet");
            } else {
                FileStructureObject tempDir = new DirectoryObject(value, currentDirectory);
                if (currentDirectory.getChildren().contains(tempDir)) {
                    if (currentDirectory.getChild(value) instanceof DirectoryObject) {
                        currentDirectory = (DirectoryObject) currentDirectory.getChild(value);
                        return new CommandResponse(null);
                    } else {
                        return new CommandResponse(new InvalidCDCommandException(value).getMessage());
                    }
                } else {
                    return new CommandResponse(new InvalidCDCommandException(value).getMessage());
                }
            }
        } catch (InvalidInputException e) {
            return new CommandResponse(new InvalidCDCommandException(e.getMessage()).getMessage());
        }
    }

    @Override
    public CommandResponse rm(String value) { //rm is for files
        //TODO test
        //todo check if it is file
        if (isCommandStartsFromRoot(value)) {
            return null; //TODO handle
        } else {
            FileStructureObject child = currentDirectory.getChild(value);
            if (child != null) {
                currentDirectory.removeChild(child);
                return new CommandResponse(null);
            } else {
                return new CommandResponse("rm: cannot remove '" + value + "': No such file or directory");
            }
        }
    }

    @Override
    public CommandResponse ls() {
        return new CommandResponse(currentDirectory.getChildren().stream()
                .map(FileStructureObject::getName)
                .collect(Collectors.joining("\n")));
    }

    @Override
    public CommandResponse pwd() {
        return new CommandResponse(currentDirectory.pwd());
    }

    @Override
    public CommandResponse mkdir(String value) {
        //TODO test
        //TODO check if it starts with /
        currentDirectory.addChild(new DirectoryObject(value, currentDirectory));

        //TODO finish
        return null;
    }

    @Override
    public CommandResponse rmdir(String value) { // rmdir is only for directories
        if (isCommandStartsFromRoot(value)) {
            return null; //TODO handle
        } else {
            FileStructureObject child = currentDirectory.getChild(value);
            if (child != null) {
                currentDirectory.removeChild(child);
                return new CommandResponse(null);
            } else {
                return new CommandResponse("rmdir: failed to remove '" + value + "': No such file or directory");
            }
        }
    }

    @Override
    public CommandResponse touch(String name) { // to create file
        //TODO test
        currentDirectory.addChild(new FileObject(name, currentDirectory));
        //TODO finish
        return null;
    }

    private boolean isCommandStartsFromRoot(String command) {
        return command.startsWith("/");
    }

    private String generateHeader() { //todo move to frontend
        return user + "@" + device + " " + currentDirectory.pwd();
    }
}
