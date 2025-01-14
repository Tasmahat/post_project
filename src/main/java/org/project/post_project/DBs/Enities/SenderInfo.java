package org.project.post_project.DBs.Enities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.project.post_project.DBs.Enities.Pks.SenderInfoPk;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sender_info")
@Getter @Setter
public class SenderInfo {

    @Column(name = "full_name_sender")
    private String fullName;

    @EmbeddedId
    private SenderInfoPk senderInfoPk;

    @Column(name = "address_sender")
    private String address;

    @Getter(AccessLevel.NONE)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sender", cascade = CascadeType.REMOVE)
    private Set<DeliveryInfo> deliveryInfos = new HashSet<>();
}
