package org.project.post_project.DBs.Enities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.project.post_project.DBs.Enities.Pks.DeliveryCostPk;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "delivery")
@Getter @Setter
public class DeliveryCost {

    @EmbeddedId
    private DeliveryCostPk deliveryCostPk;

    @Column(name = "cost_of_delivery")
    private Integer cost;

    @Getter(AccessLevel.NONE)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "deliveryCost", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<PackageInfo> packageInfos = new HashSet<>();
}
