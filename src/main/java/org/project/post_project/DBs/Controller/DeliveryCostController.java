package org.project.post_project.DBs.Controller;

import org.project.post_project.DBs.Enities.DeliveryCost;
import org.project.post_project.DBs.Services.DeliveryCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("deliveryCost")
public class DeliveryCostController {

    private final DeliveryCostService deliveryCostService;

    @Autowired
    public DeliveryCostController(DeliveryCostService deliveryCostService) {
        this.deliveryCostService = deliveryCostService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public DeliveryCost createDeliveryCost(
            @RequestParam String packageType,
            @RequestParam Byte size,
            @RequestParam Byte weight,
            @RequestParam String deliveryType,
            @RequestParam Integer kilometers
    ) {
        return deliveryCostService.createDeliveryCost(packageType, size, weight, deliveryType, kilometers);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public Page<DeliveryCost> getAllDeliveryCost() {
        return deliveryCostService.getAllDeliveryCost();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete")
    public void updateDeliveryCost(
            @RequestParam String packageType,
            @RequestParam Byte size,
            @RequestParam Byte weight,
            @RequestParam String deliveryType,
            @RequestParam Integer kilometers
    ) {
       deliveryCostService.deleteDeliveryCost(packageType, size, weight, deliveryType, kilometers);
    }
}
