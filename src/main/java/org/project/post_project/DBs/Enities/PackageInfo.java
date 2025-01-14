package org.project.post_project.DBs.Enities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
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
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "packageInfo", cascade = CascadeType.REMOVE)
    private Set<DeliveryInfo> deliverySet = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private DeliveryCost deliveryCost;

    @ManyToOne(fetch = FetchType.EAGER)
    private AdditionalServiceCost additionalServiceCost;

    @Column(name = "date_of_sending")
    private LocalDate dateOfSending;

    @Column(name = "travel_time")
    private Integer travelTime;
}
