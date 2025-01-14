package org.project.post_project.DBs.Enities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipient_info")
@Getter @Setter
public class RecipientInfo {

    @Column(name = "full_name_recipient")
    private String fullName;

    @Id
    @Column(name = "phone_recipient")
    private String phone;

    @Getter(AccessLevel.NONE)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "recipient", cascade = CascadeType.REMOVE)
    private Set<DeliveryInfo> deliverySet = new HashSet<>();

    @Column(name = "address_recipient")
    private String address;
}
