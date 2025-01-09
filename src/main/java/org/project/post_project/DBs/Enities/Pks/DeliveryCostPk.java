package org.project.post_project.DBs.Enities.Pks;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter @NoArgsConstructor
public class DeliveryCostPk implements Serializable {

    @Column(name = "type")
    private String packageType;

    @Column(name = "size_category")
    private Integer size;

    @Column(name = "weight_category")
    private Integer weight;

    @Column(name = "delivery_type")
    private String deliveryType;

    @Column(name = "kilometers")
    private Integer kilometers;

    private DeliveryCostPk(String packageType, Integer size, Integer weight, String deliveryType, Integer kilometers) {
        this.packageType = packageType;
        this.size = size;
        this.weight = weight;
        this.deliveryType = deliveryType;
        this.kilometers = kilometers;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
