package org.project.post_project.DBs.Controller;

import org.project.post_project.DBs.Enities.PackageInfo;
import org.project.post_project.DBs.Enities.PostDept;
import org.project.post_project.DBs.Services.PackageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/package")
public class PackageInfoController {

    private final PackageInfoService packageInfoService;

    @Autowired
    public PackageInfoController(PackageInfoService packageInfoService) {
        this.packageInfoService = packageInfoService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/create")
    public PackageInfo createPackageInfo(
            @RequestParam Long id,
            @RequestParam String packageType,
            @RequestParam Byte size,
            @RequestParam Byte weight,
            @RequestParam String deliveryType,
            @RequestParam Integer kilometers,
            @RequestParam Boolean isToDoor,
            @RequestParam Boolean isFragile,
            @RequestParam Boolean hasInsurance,
            @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateOfSending
    ) {
        return packageInfoService.createPackageInfo(
                id,
                packageType,
                size,
                weight,
                deliveryType,
                kilometers,
                isToDoor,
                isFragile,
                hasInsurance,
                dateOfSending
        );
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public Page<PackageInfo> getAllPackageInfo() {
        return packageInfoService.getAllPackageInfo();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/update")
    private PackageInfo updatePackageInfo(
            @RequestParam Long id,
            @RequestParam Optional<String> packageType,
            @RequestParam Optional<Byte> size,
            @RequestParam Optional<Byte> weight,
            @RequestParam Optional<String> deliveryType,
            @RequestParam Optional<Integer> kilometers,
            @RequestParam Optional<Boolean> isToDoor,
            @RequestParam Optional<Boolean> isFragile,
            @RequestParam Optional<Boolean> hasInsurance,
            @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") Optional<LocalDate> dateOfSending
    ) {
        return packageInfoService.updatePackageInfo(
                id,
                packageType,
                size,
                weight,
                deliveryType,
                kilometers,
                isToDoor,
                isFragile,
                hasInsurance,
                dateOfSending
        );
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete")
    public void deletePackageInfo(
            @RequestParam Long id
    ) {
        packageInfoService.deletePackageInfo(id);
    }
}
