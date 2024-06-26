package space.besh.kodifhomework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.besh.kodifhomework.model.CommandRequest;
import space.besh.kodifhomework.model.CommandResponse;
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

    @PostMapping(value = "/cd", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CommandResponse> executeCommand(@RequestBody CommandRequest request) {

        validateInputOrElseThrowException(request.getCommand());

        return ResponseEntity.ok(cliService.cd(request.getCommand()));
    }

    @GetMapping(value = "/ls", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CommandResponse> ls() {
        return ResponseEntity.ok(cliService.ls());
    }

    @GetMapping("/pwd")
    public ResponseEntity<CommandResponse> pwd() {
        return ResponseEntity.ok(cliService.pwd());
    }

    private void validateInputOrElseThrowException(String command) {
        //TODO complete
//        if () {
//            throw new EmptyInputException();
//        }
//        if (command)
    }

}
