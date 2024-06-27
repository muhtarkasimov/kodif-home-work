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
@CrossOrigin(origins = "*")
public class CLIController {

    final CLIService cliService;

    public CLIController(CLIService cliService) {
        this.cliService = cliService;
    }

    @PostMapping(value = "/execute", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CommandResponse> executeCommand(@RequestBody CommandRequest request) {
        String command = request.getPayload().trim().split(" ")[0];
        String payload = null;
        if (request.getPayload().trim().split(" ").length > 1) {
            payload = request.getPayload().trim().split(" ")[1];
        }

        try {
            switch (Commands.fromCode(command)) {
                case CD -> {
                    return ResponseEntity.ok(cliService.cd(payload));
                }
                case LS -> {
                    return ResponseEntity.ok(cliService.ls());
                }
                case RM -> {
                    return ResponseEntity.ok(cliService.rm(payload));
                }
                case PWD -> {
                    return ResponseEntity.ok(cliService.pwd());
                }
                case MKDIR -> {
                    return ResponseEntity.ok(cliService.mkdir(payload));
                }
                case RMDIR -> {
                    return ResponseEntity.ok(cliService.rmdir(payload));
                }
                case TOUCH -> {
                    return ResponseEntity.ok(cliService.touch(payload));
                }
                default -> {
                    return ResponseEntity.ok(cliService.getCommandNotFoundResponse(payload));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.ok(cliService.getCommandNotFoundResponse(payload));
        }
    }

}
