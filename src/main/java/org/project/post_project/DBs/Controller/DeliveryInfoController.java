package org.project.post_project.DBs.Controller;

import org.project.post_project.DBs.Enities.DeliveryInfo;
import org.project.post_project.DBs.Services.DeliveryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery")
public class DeliveryInfoController {

    private final DeliveryInfoService deliveryInfoService;

    @Autowired
    public DeliveryInfoController(DeliveryInfoService deliveryInfoService) {
        this.deliveryInfoService = deliveryInfoService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public Page<DeliveryInfo> getAllDeliveryInfo() {
        return deliveryInfoService.getAllDeliveryInfo();
    }
}
