package space.besh.kodifhomework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        cliService.cd();

        return ResponseEntity.ok(null);
    }

    private void validateInputOrElseThrowException(String command) {
//        if () {
//            throw new EmptyInputException();
//        }
//        if (command)
    }

}
