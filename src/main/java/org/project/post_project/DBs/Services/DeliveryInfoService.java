package org.project.post_project.DBs.Services;

import org.project.post_project.DBs.Enities.DeliveryInfo;
import org.project.post_project.DBs.Repositories.DeliveryInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DeliveryInfoService {

    private final DeliveryInfoRepository deliveryInfoRepository;

    @Autowired
    public DeliveryInfoService(DeliveryInfoRepository deliveryInfoRepository) {
        this.deliveryInfoRepository = deliveryInfoRepository;
    }

    public Page<DeliveryInfo> getAllDeliveryInfo() {
        Pageable pageable = Pageable.unpaged();
        return deliveryInfoRepository.findAll(pageable);
    }
}
