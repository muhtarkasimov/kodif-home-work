package space.besh.kodifhomework.service;

import space.besh.kodifhomework.model.CommandResponse;

public interface CLIService {

    CommandResponse cd(String path);

    CommandResponse rm();

    CommandResponse ls();

    CommandResponse pwd();

    CommandResponse mkdir();

    CommandResponse rmdir();

    CommandResponse touch();

}
