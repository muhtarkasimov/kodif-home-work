package space.besh.kodifhomework.model.enums;

import lombok.Getter;

import java.lang.annotation.Annotation;

@Getter
public enum Commands {

    CD("cd"),       // change directory
    RM("rm"),       // remove
    LS("ls"),       // list objects
    PWD("pwd"),     // print working directory
    MKDIR("mkdir"), // make directory
    RMDIR("rmdir"), // remove directory
    TOUCH("touch"); // create file

    private final String code;

    Commands(String code) {
        this.code = code;
    }

    public static Commands fromCode(String code) {
        for (Commands command : Commands.values()) {
            if (command.getCode().equalsIgnoreCase(code)) {
                return command;
            }
        }
        throw new IllegalArgumentException("Unknown command: " + code);
    }
}