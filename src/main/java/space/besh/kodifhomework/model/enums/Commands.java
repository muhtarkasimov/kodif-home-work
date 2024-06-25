package space.besh.kodifhomework.model.enums;

public enum Commands {

    CD("cd"),       // change directory
    RM("rm"),       // remove
    LS("ls"),       // list objects
    PWD("pwd"),     // print working directory
    MKDIR("mkdir"), // make directory
    RMDIR("rmdir"), // remove directory
    TOUCH("touch"); // create file

    Commands(String code) {}
}
