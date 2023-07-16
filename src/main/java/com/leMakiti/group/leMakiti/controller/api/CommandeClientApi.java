package com.leMakiti.group.leMakiti.controller.api;

import com.leMakiti.group.leMakiti.dto.CommandeClientDto;
import com.leMakiti.group.leMakiti.dto.CommandeClientDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.leMakiti.group.leMakiti.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/cmdClients")
public interface CommandeClientApi {

    @PostMapping(value = APP_ROOT + "/cmdClients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une commande client", notes = "Cette methode permet d'enregistrer ou de modifier une commande client", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet Commande Client Cree / Modifier"),
            @ApiResponse(code = 400, message = "L'objet Commande Client n'est pas valide")
    })
    CommandeClientDto save(@RequestBody CommandeClientDto commandeClientDto);

    @GetMapping(value = APP_ROOT + "/cmdClients/{idCmdClient}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une commande Client par ID", notes = "Cette methode permet de chercher une commande client par son ID", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande client a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune commande client n'a ete trouve dans la BDD avec l'ID fourni")
    })
    CommandeClientDto findById(@PathVariable("idCmdClient") Integer id);

    @GetMapping(value = APP_ROOT + "/cmdClients/{codeCmdClient}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une commande client par CODE", notes = "Cette methode permet de chercher une commande client par son CODE", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande client a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune Commande client n'a ete trouve dans la BDD avec le CODE fourni")
    })
    CommandeClientDto findByCodeCmdClient(@PathVariable("codeCmdClient") String codeCmdClient);

    @GetMapping(value = APP_ROOT + "/cmdClients/all", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des commandes client", notes = "Cette methode permet de chercher et renvoyer la liste des commandes client qui existent dans la BDD", responseContainer = "List<CommandeClientDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Commandes client / une liste vide")
    })
    List<CommandeClientDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/cmdClients/delete/{idCmdClient}")
    @ApiOperation(value = "Supprimer une commande client", notes = "Cette methode permet de supprimer une commande client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande client a ete supprimer dans la BDD"),
    })
    void delete(@PathVariable("idCmdClient") Integer id);
}
