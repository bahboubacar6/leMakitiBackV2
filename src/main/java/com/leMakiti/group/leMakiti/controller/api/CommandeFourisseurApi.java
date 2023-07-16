package com.leMakiti.group.leMakiti.controller.api;

import com.leMakiti.group.leMakiti.dto.CommandeFournisseurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.leMakiti.group.leMakiti.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/cmdFournisseurs")
public interface CommandeFourisseurApi {

    @PostMapping(value = APP_ROOT + "/cmdFournisseurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une commande fournisseur", notes = "Cette methode permet d'enregistrer ou de modifier une commande fournisseur", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet Commande fournisseur Cree / Modifier"),
            @ApiResponse(code = 400, message = "L'objet Commande fournisseur n'est pas valide")
    })
    CommandeFournisseurDto save(@RequestBody CommandeFournisseurDto commandeFournisseurDto);

    @GetMapping(value = APP_ROOT + "/cmdFournisseurs/{idCmdFournisseur}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une commande fournisseur par ID", notes = "Cette methode permet de chercher une commande fournisseur par son ID", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande fournisseur a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune commande fournisseur n'a ete trouve dans la BDD avec l'ID fourni")
    })
    CommandeFournisseurDto findById(@PathVariable("idCmdFournisseur") Integer id);

    @GetMapping(value = APP_ROOT + "/cmdFournisseurs/{codeCmdFournisseur}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une commande fournisseur par CODE", notes = "Cette methode permet de chercher une commande fournisseur par son CODE", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande fournisseur a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune Commande fournisseur n'a ete trouve dans la BDD avec le CODE fourni")
    })
    CommandeFournisseurDto findByCodeCmdFournisseur(@PathVariable("codeCmdFournisseur") String codeCmdFournisseur);

    @GetMapping(value = APP_ROOT + "/cmdFournisseurs/all", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des commandes fournisseur", notes = "Cette methode permet de chercher et renvoyer la liste des commandes fournisseur qui existent dans la BDD", responseContainer = "List<CommandeFournisseurDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Commandes fournisseur / une liste vide")
    })
    List<CommandeFournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/cmdFournisseurs/delete/{idCmdFournisseur}")
    @ApiOperation(value = "Supprimer une commande fournisseur", notes = "Cette methode permet de supprimer une commande fournisseur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande fournisseur a ete supprimer dans la BDD"),
    })
    void delete(@PathVariable("idCmdFournisseur") Integer id);
}
