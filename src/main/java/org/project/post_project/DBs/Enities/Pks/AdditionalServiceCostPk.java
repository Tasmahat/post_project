package org.project.post_project.DBs.Enities.Pks;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter @NoArgsConstructor
public class AdditionalServiceCostPk implements Serializable {

    @Column(name = "is_to_door")
    private Boolean isToDoor;

    @Column(name = "is_fragile")
    private Boolean isFragile;

    @Column(name = "has_insurance")
    private Boolean hasInsurance;

    public AdditionalServiceCostPk(Boolean isToDoor, Boolean isFragile, Boolean hasInsurance) {
        this.isToDoor = isToDoor;
        this.isFragile = isFragile;
        this.hasInsurance = hasInsurance;
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
