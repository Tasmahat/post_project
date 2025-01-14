package org.project.post_project.DBs.Controller;

import org.project.post_project.DBs.Enities.Pks.SenderInfoPk;
import org.project.post_project.DBs.Enities.SenderInfo;
import org.project.post_project.DBs.Services.SenderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/sender")
public class SenderInfoController {

    private final SenderInfoService senderInfoService;

    @Autowired
    public SenderInfoController(SenderInfoService senderInfoService) {
        this.senderInfoService = senderInfoService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public SenderInfo createSenderInfo(
            @RequestParam String fullName,
            @RequestParam String passport,
            @RequestParam String phone,
            @RequestParam String address
    ) {
        return senderInfoService.createSenderInfo(fullName, passport, phone, address);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public Page<SenderInfo> getAllSenderInfo() {
        return senderInfoService.getAllSenderInfo();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{passport}/{phone}")
    public SenderInfo getSenderInfo(@PathVariable String passport, @PathVariable String phone) {
        SenderInfoPk senderInfoPk = new SenderInfoPk(passport, phone);
        return senderInfoService.getSenderInfoByPk(senderInfoPk);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public SenderInfo updateSenderInfo(
            @RequestParam Optional<String> fullName,
            @RequestParam String passport,
            @RequestParam String phone,
            @RequestParam Optional<String> address
    ) {
        return senderInfoService.updateSenderInfo(fullName, passport, phone, address);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete")
    public void deleteSenderInfo(@RequestParam String passport, @RequestParam String phone) {
        senderInfoService.deleteSenderInfo(passport, phone);
    }
}
