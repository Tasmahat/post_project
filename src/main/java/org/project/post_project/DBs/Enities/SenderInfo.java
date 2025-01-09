package org.project.post_project.DBs.Enities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.project.post_project.DBs.Enities.Pks.SenderInfoPk;

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
}
