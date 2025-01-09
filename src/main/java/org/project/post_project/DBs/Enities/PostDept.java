package org.project.post_project.DBs.Enities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "post_dept")
@Getter @Setter
public class PostDept {

    @Id
    @Column(name = "index_dept")
    private String index;

    @Getter(AccessLevel.NONE)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "postSendDept", cascade = CascadeType.ALL)
    private Set<DeliveryInfo> deliverySenderSet = new HashSet<>();

    @Getter(AccessLevel.NONE)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "postReceiveDept", cascade = CascadeType.ALL)
    private Set<DeliveryInfo> deliveryReceiveSet = new HashSet<>();

    @Column(name = "region_send")
    private String region;

    @Column(name = "city_send")
    private String city;

    @Column(name = "address_send")
    private String address;
}
