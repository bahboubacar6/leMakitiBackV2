package com.leMakiti.group.leMakiti.controller.api;

import com.leMakiti.group.leMakiti.dto.CommandeClientDto;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.leMakiti.group.leMakiti.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/cmdClients")
public interface CommandeClientApi {

    @PostMapping(APP_ROOT + "/cmdClients/create")
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto commandeClientDto);

    @GetMapping(value = APP_ROOT + "/cmdClients/{idCmdClient}")
    ResponseEntity<CommandeClientDto> findById(@PathVariable("idCmdClient") Integer id);

    @GetMapping(APP_ROOT + "/cmdClients/{codeCmdClient}")
    ResponseEntity<CommandeClientDto> findByCodeCmdClient(@PathVariable("codeCmdClient") String codeCmdClient);

    @GetMapping(APP_ROOT + "/cmdClients/all")
    ResponseEntity<List<CommandeClientDto>> findAll();

    @DeleteMapping(APP_ROOT + "/cmdClients/delete/{idCmdClient}")
    ResponseEntity delete(@PathVariable("idCmdClient") Integer id);
}
