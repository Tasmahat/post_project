package org.project.post_project.DBs.Enities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.project.post_project.DBs.Enities.Pks.AdditionalServiceCostPk;

@Entity
@Table(name = "additional_service")
@Getter @Setter
public class AdditionalServiceCost {

    @EmbeddedId
    private AdditionalServiceCostPk additionalServiceCostPk;

    @Column(name = "cost_of_additional_service")
    private Integer cost;
}
