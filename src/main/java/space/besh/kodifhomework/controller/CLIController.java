package space.besh.kodifhomework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.besh.kodifhomework.model.CommandRequest;
import space.besh.kodifhomework.model.CommandResponse;
import space.besh.kodifhomework.model.enums.Commands;
import space.besh.kodifhomework.service.CLIService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
@Slf4j
public class CLIController {

    final CLIService cliService;

    public CLIController(CLIService cliService) {
        this.cliService = cliService;
    }

    @PostMapping(value = "/execute", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CommandResponse> executeCommand(@RequestBody CommandRequest request) {
        String command = request.getCommand().split(" ")[0];
        String payload = request.getPayload();

        switch (Commands.fromCode(command)) {
            case CD -> {
                return ResponseEntity.ok(cliService.cd(payload));
            }
            case LS -> {
                return ResponseEntity.ok(cliService.ls());
            }
            case RM, MKDIR, RMDIR, TOUCH -> {
                return ResponseEntity.ok(new CommandResponse("this command is not ready yet"));
            }
            case PWD -> {
                return ResponseEntity.ok(cliService.pwd());
            }
            default -> {
                return ResponseEntity.ok(new CommandResponse("bash: " + command + ": command not found"));
            }
        }
    }

}
