package org.project.post_project.DBs.Enities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "post_workers")
@Getter @Setter
public class PostWorkers {

    @Id
    @Column(name = "service_number_worker")
    private Long serviceNumber;

    @Getter(AccessLevel.NONE)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "postWorkers", cascade = CascadeType.ALL)
    private Set<DeliveryInfo> deliverySet = new HashSet<>();

    @Column(name = "position_worker")
    private String position;

    @Column(name = "full_name_worker")
    private String fullName;
}
