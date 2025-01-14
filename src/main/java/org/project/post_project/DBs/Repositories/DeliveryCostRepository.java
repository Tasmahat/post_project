package org.project.post_project.DBs.Repositories;

import org.project.post_project.DBs.Enities.DeliveryCost;
import org.project.post_project.DBs.Enities.Pks.DeliveryCostPk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryCostRepository extends JpaRepository<DeliveryCost, Long> {

    Optional<DeliveryCost> findByDeliveryCostPk(DeliveryCostPk deliveryCostPk);
}