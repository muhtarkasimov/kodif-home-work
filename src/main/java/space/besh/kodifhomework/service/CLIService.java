package space.besh.kodifhomework.service;

public interface CLIService {

    void cd(String path);

    void rm();

    String ls();

    String pwd();

    void mkdir();

    void rmdir();

    void touch();

}
