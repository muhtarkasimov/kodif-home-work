package space.besh.kodifhomework.service.impl;

import org.springframework.stereotype.Service;
import space.besh.kodifhomework.exceptions.InvalidCDCommandException;
import space.besh.kodifhomework.exceptions.InvalidInputException;
import space.besh.kodifhomework.model.CommandResponse;
import space.besh.kodifhomework.model.filesystem.DirectoryObject;
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
    public CommandResponse cd(String path) {
        try {
            if (isCommandStartsFromRoot(path)) {
                //TODO complete
                return new CommandResponse(generateHeader(), "this command is not completed yet");
            } else {
                FileStructureObject tempDir = new DirectoryObject(path, currentDirectory);
                if (currentDirectory.getChildren().contains(tempDir)) {
                    if (currentDirectory.getChild(path) instanceof DirectoryObject) {
                        currentDirectory = (DirectoryObject) currentDirectory.getChild(path);
                        return new CommandResponse(generateHeader(), "");
                    } else {
                        return new CommandResponse(generateHeader(), new InvalidCDCommandException(path).getMessage());
                    }
                } else {
                    return new CommandResponse(generateHeader(), new InvalidCDCommandException(path).getMessage());
                }
            }
        } catch (InvalidInputException e) {
            return new CommandResponse(generateHeader(), new InvalidCDCommandException(e.getMessage()).getMessage());
        }
    }

    @Override
    public CommandResponse rm() {
        return null;
    }

    @Override
    public CommandResponse ls() {
        return new CommandResponse(generateHeader(), currentDirectory.getChildren().stream()
                .map(FileStructureObject::getName)
                .collect(Collectors.joining("\n")));
    }

    @Override
    public CommandResponse pwd() {
        return new CommandResponse(generateHeader(), currentDirectory.pwd());
    }

    @Override
    public CommandResponse mkdir() {
        return null;
    }

    @Override
    public CommandResponse rmdir() {
        return null;
    }

    @Override
    public CommandResponse touch() {
        return null;
    }

    private boolean isCommandStartsFromRoot(String command) {
        return command.startsWith("/");
    }

    private String generateHeader() { //todo move to frontend
        return user + "@" + device + " " + currentDirectory.pwd();
    }
}
