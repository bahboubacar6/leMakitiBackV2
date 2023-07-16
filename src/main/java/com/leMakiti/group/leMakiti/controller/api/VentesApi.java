package com.leMakiti.group.leMakiti.controller.api;

import com.leMakiti.group.leMakiti.dto.VentesDto;
import com.leMakiti.group.leMakiti.dto.VentesDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.leMakiti.group.leMakiti.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/ventes")
public interface VentesApi {

    @PostMapping(value = APP_ROOT + "/ventes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une vente", notes = "Cette methode permet d'enregistrer ou de modifier une vente", response = VentesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet vente Cree / Modifier"),
            @ApiResponse(code = 400, message = "L'objet vente n'est pas valide")
    })
    VentesDto save(@RequestBody VentesDto ventesDto);

    @GetMapping(value = APP_ROOT + "/ventes/{idVentes}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une vente par ID", notes = "Cette methode permet de chercher une vente par son ID", response = VentesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La vente a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune vente n'a ete trouve dans la BDD avec l'ID fourni")
    })
    VentesDto findById(@PathVariable("idVentes") Integer id);

    @GetMapping(value = APP_ROOT + "/ventes/all", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des ventes", notes = "Cette methode permet de chercher et renvoyer la liste des ventes qui existent dans la BDD", responseContainer = "List<VentesDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des ventes / une liste vide")
    })
    List<VentesDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/ventes/delete/{idVentes}")
    @ApiOperation(value = "Supprimer une vente", notes = "Cette methode permet de supprimer une vente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La vente a ete supprimer dans la BDD"),
    })
    void delete(@PathVariable("idVentes") Integer id);
}
