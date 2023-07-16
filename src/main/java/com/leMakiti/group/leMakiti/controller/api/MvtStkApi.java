package com.leMakiti.group.leMakiti.controller.api;

import com.leMakiti.group.leMakiti.dto.MvtStkDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.leMakiti.group.leMakiti.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/mvtstks")
public interface MvtStkApi {

    @PostMapping(value = APP_ROOT + "/mvtstks/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un mouvement de stock", notes = "Cette methode permet d'enregistrer ou de modifier un mouvement de stock", response = MvtStkDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet mouvement de stock Cree / Modifier"),
            @ApiResponse(code = 400, message = "L'objet mouvement de stock n'est pas valide")
    })
    MvtStkDto save(@RequestBody MvtStkDto mvtStkDto);

    @GetMapping(value = APP_ROOT + "/mvtstks/{idmvtStk}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un mouvement de stock par ID", notes = "Cette methode permet de chercher un mouvement de stock par son ID", response = MvtStkDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le mouvement de stock a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun mouvement de stock n'a ete trouve dans la BDD avec l'ID fourni")
    })
    MvtStkDto findById(@PathVariable("idmvtStk") Integer id);

    @GetMapping(value = APP_ROOT + "/mvtstks/all", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des mouvements de stock", notes = "Cette methode permet de chercher et renvoyer la liste des des mouvements de stock qui existent dans la BDD", responseContainer = "List<MvtStkDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des mouvements de stock / une liste vide")
    })
    List<MvtStkDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/mvtstks/delete/{idmvtStk}")
    @ApiOperation(value = "Supprimer un client", notes = "Cette methode permet de supprimer un mouvement de stock")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le mouvement de stock a ete supprimer dans la BDD"),
    })
    void delete(@PathVariable("idmvtStk") Integer id);
}
