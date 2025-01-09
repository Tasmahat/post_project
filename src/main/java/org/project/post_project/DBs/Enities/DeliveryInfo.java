package org.project.post_project.DBs.Enities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "delivery_info")
@Getter @Setter
public class DeliveryInfo {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "id_delivery")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "index_dept_send")
    private PostDept postSendDept = new PostDept();

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "passport_data_sender"),
            @JoinColumn(name = "phone_sender")
    })
    private SenderInfo sender;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "service_number_worker")
    private PostWorkers postWorkers = new PostWorkers();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_package")
    private PackageInfo packageInfo = new PackageInfo();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "index_dept_receive")
    private PostDept postReceiveDept = new PostDept();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "phone_recipient")
    private RecipientInfo recipient = new RecipientInfo();
}
