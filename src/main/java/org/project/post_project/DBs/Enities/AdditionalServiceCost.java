package org.project.post_project.DBs.Enities;

import jakarta.persistence.*;
import lombok.*;
import org.project.post_project.DBs.Enities.Pks.AdditionalServiceCostPk;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "additional_service")
@Getter @Setter
public class AdditionalServiceCost {

    @EmbeddedId
    private AdditionalServiceCostPk additionalServiceCostPk;

    @Column(name = "cost_of_additional_service")
    private Integer cost;

    @Getter(AccessLevel.NONE)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "additionalServiceCost", cascade = CascadeType.REMOVE)
    private Set<PackageInfo> packageInfos = new HashSet<>();
}
