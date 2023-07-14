package com.leMakiti.group.leMakiti.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "roles")
public class Roles extends AbstractEntity{

    @Column(name = "rolename")
    private String roleName;

    @Column(name = "identreprise")
    private Integer idEntreprise;
    @ManyToOne
    @JoinColumn(name = "idutilisateur")
    private Utilisateur utilisateur;
}
