package com.leMakiti.group.leMakiti.validator;

import com.leMakiti.group.leMakiti.dto.EntrepriseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {

    public static List<String> validate(EntrepriseDto entrepriseDto){
        List<String> errors = new ArrayList<>();

        if (entrepriseDto == null) {
            errors.add("Veuillez renseigner le nom de l'entreprise");
            errors.add("Veuillez renseigner le Code Fiscal de l'entreprise");
            errors.add("Veuillez renseigner l'email de l'entreprise");
            errors.add("Veuillez renseigner le numero de telephone de l'entreprise");
            return errors;
        }

        if (!StringUtils.hasLength(entrepriseDto.getNom())){
            errors.add("Veuillez renseigner le nom de l'entreprise");
        }
        if (!StringUtils.hasLength(entrepriseDto.getCodeFiscal())){
            errors.add("Veuillez renseigner le Code Fiscal de l'entreprise");
        }
        if (!StringUtils.hasLength(entrepriseDto.getEmail())){
            errors.add("Veuillez renseigner l'email de l'entreprise");
        }
        if (!StringUtils.hasLength(entrepriseDto.getNumTel())){
            errors.add("Veuillez renseigner le numero de telephone de l'entreprise");
        }
        return errors;
    }
}
