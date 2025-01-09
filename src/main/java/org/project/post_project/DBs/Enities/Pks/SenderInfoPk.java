package org.project.post_project.DBs.Enities.Pks;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor
public class SenderInfoPk implements Serializable {

    @Column(name = "passport_data_sender")
    private String passport;

    @Column(name = "phone_sender")
    private String phone;

    public SenderInfoPk(String passport, String phone) {
        this.passport = passport;
        this.phone = phone;
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
