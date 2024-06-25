package space.besh.kodifhomework.service.impl;

import org.springframework.stereotype.Service;
import space.besh.kodifhomework.model.filesystem.Directory;
import space.besh.kodifhomework.model.filesystem.FileStructureObject;
import space.besh.kodifhomework.service.CLIService;

import java.util.Stack;
import java.util.stream.Collectors;

@Service
public class CLIServiceImpl implements CLIService {

    private final Stack<FileStructureObject> fileStack;
    private final Directory currentDirectory;

    public CLIServiceImpl() {
        fileStack = new Stack<>();
        fileStack.add(new Directory("/"));
        currentDirectory = (Directory) fileStack.getFirst();
        currentDirectory.getContent().add(new Directory("var"));
        currentDirectory.getContent().add(new Directory("home"));
    }

    @Override
    public void cd() {

    }

    @Override
    public void rm() {

    }

    @Override
    public String ls() {
        return currentDirectory.getContent().stream()
                .map(FileStructureObject::getName)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public void pwd() {

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
}
