package com.leMakiti.group.leMakiti.controller.api;

import com.leMakiti.group.leMakiti.dto.EntrepriseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.leMakiti.group.leMakiti.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/entreprises")
public interface EntrepriseApi {

    @PostMapping(value = APP_ROOT + "/entreprises/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une entreprise", notes = "Cette methode permet d'enregistrer ou de modifier une entreprise", response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet entreprise Cree / Modifier"),
            @ApiResponse(code = 400, message = "L'objet entreprise n'est pas valide")
    })
    EntrepriseDto save(@RequestBody EntrepriseDto entrepriseDto);

    @GetMapping(value = APP_ROOT + "/entreprises/{idEntreprise}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une entreprise par ID", notes = "Cette methode permet de chercher une entreprise par son ID", response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'ntreprise a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune entreprise n'a ete trouve dans la BDD avec l'ID fourni")
    })
    EntrepriseDto findById(@PathVariable("idEntreprise") Integer id);

    @GetMapping(value = APP_ROOT + "/entreprises/all", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des entreprises", notes = "Cette methode permet de chercher et renvoyer la liste des entreprises qui existent dans la BDD", responseContainer = "List<EntrepriseDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des entreprises / une liste vide")
    })
    List<EntrepriseDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/entreprises/delete/{idEntreprise}")
    @ApiOperation(value = "Supprimer une entreprise", notes = "Cette methode permet de supprimer une entreprise")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'entreprise a ete supprimer dans la BDD"),
    })
    void delete(@PathVariable("idEntreprise") Integer id);
}
