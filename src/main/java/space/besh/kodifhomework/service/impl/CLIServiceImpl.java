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
        if (value == null) {
            return new CommandResponse(null, currentDirectory.pwd());
        }
        try {
            if (isCommandStartsFromRoot(value)) {
                //TODO complete
                return getCommandNotFoundResponse(value);
            } else {
                if ("..".equals(value) && currentDirectory == root) {
                    return new CommandResponse(null, currentDirectory.pwd());
                } else if ("..".equals(value)) {
                    currentDirectory = currentDirectory.getParent();
                    return new CommandResponse(null, currentDirectory.pwd());
                } else {
                    FileStructureObject tempDir = new DirectoryObject(value, currentDirectory);
                    if (currentDirectory.getChildren().contains(tempDir)) {
                        if (currentDirectory.getChild(value) instanceof DirectoryObject) {
                            currentDirectory = (DirectoryObject) currentDirectory.getChild(value);
                            return new CommandResponse(null, currentDirectory.pwd());
                        } else {
                            return new CommandResponse("cd: not a directory: " + value, currentDirectory.pwd());
                        }
                    } else {
                        return new CommandResponse(new InvalidCDCommandException(value).getMessage(), currentDirectory.pwd());
                    }
                }
            }
        } catch (InvalidInputException e) {
            return new CommandResponse(new InvalidCDCommandException(e.getMessage()).getMessage(), currentDirectory.pwd());
        }
    }

    @Override
    public CommandResponse rm(String value) { // The rm command removes complete directories, including subdirectories and files.
        //TODO test
        //todo check if it is file
        if (isCommandStartsFromRoot(value)) {
            return null; //TODO handle
        } else {
            FileStructureObject child = currentDirectory.getChild(value);
            if (child != null) {
                currentDirectory.removeChild(child);
                return new CommandResponse(null, currentDirectory.pwd());
            } else {
                return new CommandResponse("rm: cannot remove '" + value + "': No such file or directory", currentDirectory.pwd());
            }
        }
    }

    @Override
    public CommandResponse ls() {
        return new CommandResponse(currentDirectory.getChildren().stream()
                .map(FileStructureObject::getName)
                .collect(Collectors.joining("\n")), currentDirectory.pwd());
    }

    @Override
    public CommandResponse pwd() {
        return new CommandResponse(currentDirectory.pwd(), currentDirectory.pwd());
    }

    @Override
    public CommandResponse mkdir(String value) {
        if (isCommandStartsFromRoot(value)) {
            try {
                String[] split = value.split("/");
                DirectoryObject node = root;
                for (int i = 1; i < split.length; i++) {
                    FileStructureObject child = node.getChild(split[i]);
                    if (child == null && i == split.length - 1) {
                        node.addChild(child = new DirectoryObject(split[i], node));
                        return new CommandResponse(null, currentDirectory.pwd());
                    }
                    node = (DirectoryObject) child;
                }
                return new CommandResponse(null, currentDirectory.pwd());
            } catch (Exception e) {
                return new CommandResponse("mkdir: not a directory: " + value, currentDirectory.pwd());
            }
        } else {
            currentDirectory.addChild(new DirectoryObject(value, currentDirectory));
            return new CommandResponse(null, currentDirectory.pwd());
        }
    }

    @Override
    public CommandResponse rmdir(String value) { // The rmdir command removes empty directories
        if (isCommandStartsFromRoot(value)) {
            return null; //TODO handle
        } else {
            FileStructureObject child = currentDirectory.getChild(value);
            if (child != null) {
                currentDirectory.removeChild(child);
                return new CommandResponse(null, currentDirectory.pwd());
            } else {
                return new CommandResponse("rmdir: failed to remove '" + value + "': No such file or directory", currentDirectory.pwd());
            }
        }
    }

    @Override
    public CommandResponse touch(String name) { // to create file
        currentDirectory.addChild(new FileObject(name, currentDirectory));
        return new CommandResponse(null, currentDirectory.pwd());
    }

    private boolean isCommandStartsFromRoot(String command) {
        return command.startsWith("/");
    }

    private String generateHeader() { //todo move to frontend
        return user + "@" + device + " " + currentDirectory.pwd();
    }

    public CommandResponse getCommandNotFoundResponse(String payload) {
        return new CommandResponse("bash: " + payload + ": command not found", currentDirectory.pwd());
    }
}
