package com.leMakiti.group.leMakiti.controller.api;

import com.leMakiti.group.leMakiti.dto.CategoryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.leMakiti.group.leMakiti.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/categories")
public interface CategoryApi {

    @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une categorie", notes = "Cette methode permet d'enregistrer ou de modifier une categorie", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet Categorie Cree / Modifier"),
            @ApiResponse(code = 400, message = "L'objet Categorie n'est pas valide")
    })
    CategoryDto save(@RequestBody CategoryDto categoryDto);

    @GetMapping(value = APP_ROOT + "/categories/{idCategory}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Categorie par ID", notes = "Cette methode permet de chercher une Categorie par son ID", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Categorie a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune Categorie n'a ete trouve dans la BDD avec l'ID fourni")
    })
    CategoryDto findById(@PathVariable("idCategory") Integer id);

    @GetMapping(value = APP_ROOT + "/categories/{codeCategory}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Categorie par CODE", notes = "Cette methode permet de chercher une Categorie par son CODE", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Category a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune Categorie n'a ete trouve dans la BDD avec le CODE fourni")
    })
    CategoryDto findByCodeCategory(@PathVariable("codeCategory") String codeCategory);

    @GetMapping(value = APP_ROOT + "/categories/all", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Categories", notes = "Cette methode permet de chercher et renvoyer la liste des categories qui existent dans la BDD", responseContainer = "List<CategoryDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des categories / une liste vide")
    })
    List<CategoryDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/categories/delete/{idCategory}")
    @ApiOperation(value = "Supprimer une categorie", notes = "Cette methode permet de supprimer une categorie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La categorie a ete supprimer dans la BDD"),
    })
    void delete(@PathVariable("idCategory") Integer id);
}
