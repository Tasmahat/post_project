package org.project.post_project.DBs.Enities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.project.post_project.DBs.Enities.Pks.AdditionalServiceCostPk;
import org.project.post_project.DBs.Enities.Pks.DeliveryCostPk;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "package_info")
@Getter @Setter
public class PackageInfo {

    @Id
    @Column(name = "id_package")
    private Long id;

    @Getter(AccessLevel.NONE)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "packageInfo", cascade = CascadeType.ALL)
    private Set<DeliveryInfo> deliverySet = new HashSet<>();

    @ManyToOne
    private DeliveryCost deliveryCost;

    @ManyToOne
    private AdditionalServiceCost additionalServiceCost;

    @Column(name = "date_of_sending")
    private LocalDate dateOfSending;

    @Column(name = "travel_time")
    private Integer travelTime;
}
