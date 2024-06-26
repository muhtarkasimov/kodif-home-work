package space.besh.kodifhomework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<String> executeCommand(@RequestBody String command) {

        validateInputOrElseThrowException(command);
        cliService.cd(null); //TODO complete

        return ResponseEntity.ok(null);
    }

    @GetMapping("/ls")
    public ResponseEntity<String> ls() {
        return ResponseEntity.ok(cliService.ls());
    }

    @GetMapping("/pwd")
    public ResponseEntity<String> pwd() {
        return ResponseEntity.ok(cliService.pwd());
    }

    private void validateInputOrElseThrowException(String command) {
//        if () {
//            throw new EmptyInputException();
//        }
//        if (command)
    }

}
