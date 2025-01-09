package org.project.post_project.DBs.Services;

import org.project.post_project.DBs.Enities.DeliveryCost;
import org.project.post_project.DBs.Repositories.DeliveryCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DeliveryCostService {

    private final DeliveryCostRepository deliveryCostRepository;

    @Autowired
    public DeliveryCostService(DeliveryCostRepository deliveryCostRepository) {
        this.deliveryCostRepository = deliveryCostRepository;
    }

    public Page<DeliveryCost> getAllDeliveryCost() {
        Pageable pageable = Pageable.unpaged();
        return deliveryCostRepository.findAll(pageable);
    }
}
