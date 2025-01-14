package org.project.post_project.DBs.Controller;

import org.project.post_project.DBs.Enities.DeliveryInfo;
import org.project.post_project.DBs.Services.DeliveryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/delivery")
public class DeliveryInfoController {

    private final DeliveryInfoService deliveryInfoService;

    @Autowired
    public DeliveryInfoController(DeliveryInfoService deliveryInfoService) {
        this.deliveryInfoService = deliveryInfoService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public DeliveryInfo createDeliveryInfo(
            @RequestParam Long id,
            @RequestParam String postDeptIndexSend,
            @RequestParam String senderPassport,
            @RequestParam String senderPhone,
            @RequestParam Long workerNumber,
            @RequestParam Long packageId,
            @RequestParam String postDeptIndexReceive,
            @RequestParam String recipientPhone
    ) {
        return deliveryInfoService.createDeliveryInfo(
                id,
                postDeptIndexSend,
                senderPassport,
                senderPhone,
                workerNumber,
                packageId,
                postDeptIndexReceive,
                recipientPhone
        );
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public Page<DeliveryInfo> getAllDeliveryInfo() {
        return deliveryInfoService.getAllDeliveryInfo();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public DeliveryInfo updateDeliveryInfo(
            @RequestParam Long id,
            @RequestParam Optional<String> postDeptIndexSend,
            @RequestParam Optional<String> senderPassport,
            @RequestParam Optional<String> senderPhone,
            @RequestParam Optional<Long> workerNumber,
            @RequestParam Optional<Long> packageId,
            @RequestParam Optional<String> postDeptIndexReceive,
            @RequestParam Optional<String> recipientPhone
    ) {
        return deliveryInfoService.updateDeliveryInfo(
                id,
                postDeptIndexSend,
                senderPassport,
                senderPhone,
                workerNumber,
                packageId,
                postDeptIndexReceive,
                recipientPhone
        );
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete")
    public void deleteDeliveryInfo(@RequestParam Long id) {
        deliveryInfoService.deleteDeliveryInfo(id);
    }
}
