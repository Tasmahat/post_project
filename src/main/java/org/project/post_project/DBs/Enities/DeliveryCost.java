package org.project.post_project.DBs.Enities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.project.post_project.DBs.Enities.Pks.DeliveryCostPk;

@Entity
@Table(name = "delivery")
@Getter @Setter
public class DeliveryCost {

    @EmbeddedId
    private DeliveryCostPk deliveryCostPk;

    @Column(name = "cost_of_delivery")
    private Integer cost;
}
