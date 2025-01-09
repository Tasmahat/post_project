package org.project.post_project.DBs.Repositories;

import org.project.post_project.DBs.Enities.DeliveryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryInfoRepository extends JpaRepository<DeliveryInfo, Long> {
}