package org.project.post_project.DBs.Enities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "delivery_info")
@Getter @Setter
public class DeliveryInfo {

    @Id
    @Column (name = "id_delivery")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "index_dept_send")
    private PostDept postSendDept = new PostDept();

    @ManyToOne(fetch = FetchType.EAGER)
    private SenderInfo sender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_number_worker")
    private PostWorkers postWorkers = new PostWorkers();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_package")
    private PackageInfo packageInfo = new PackageInfo();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "index_dept_receive")
    private PostDept postReceiveDept = new PostDept();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "phone_recipient")
    private RecipientInfo recipient = new RecipientInfo();
}
