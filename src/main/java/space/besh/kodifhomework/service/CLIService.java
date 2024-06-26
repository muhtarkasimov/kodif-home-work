package space.besh.kodifhomework.service;

import space.besh.kodifhomework.model.CommandResponse;

public interface CLIService {

    CommandResponse cd(String value);

    CommandResponse rm(String value);

    CommandResponse ls();

    CommandResponse pwd();

    CommandResponse mkdir(String value);

    CommandResponse rmdir(String value);

    CommandResponse touch(String value);

}
