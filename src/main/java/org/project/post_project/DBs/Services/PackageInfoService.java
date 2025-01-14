package org.project.post_project.DBs.Services;

import lombok.extern.slf4j.Slf4j;
import org.project.post_project.DBs.Enities.AdditionalServiceCost;
import org.project.post_project.DBs.Enities.DeliveryCost;
import org.project.post_project.DBs.Enities.PackageInfo;
import org.project.post_project.DBs.Enities.Pks.AdditionalServiceCostPk;
import org.project.post_project.DBs.Enities.Pks.DeliveryCostPk;
import org.project.post_project.DBs.Repositories.PackageInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class PackageInfoService {

    private final PackageInfoRepository packageInfoRepository;
    private final DeliveryCostService deliveryCostService;
    private final AdditionalServiceCostService additionalServiceCostService;

    @Autowired
    public PackageInfoService(
            PackageInfoRepository packageInfoRepository,
            DeliveryCostService deliveryCostService,
            AdditionalServiceCostService additionalServiceCostService
    ) {
        this.packageInfoRepository = packageInfoRepository;
        this.deliveryCostService = deliveryCostService;
        this.additionalServiceCostService = additionalServiceCostService;
    }

    public PackageInfo createPackageInfo(
            Long id,
            String packageType,
            Byte size,
            Byte weight,
            String deliveryType,
            Integer kilometers,
            Boolean isToDoor,
            Boolean isFragile,
            Boolean hasInsurance,
            LocalDate dateOfSending
    ) {
        if(getPackageInfo(id) != null) {
            log.warn("Такое отправление уже существует!");
            return null;
        }
        PackageInfo packageInfo = new PackageInfo();
        DeliveryCostPk deliveryCostPk = new DeliveryCostPk(packageType, size, weight, deliveryType, kilometers);
        DeliveryCost deliveryCost = deliveryCostService.getDeliveryCost(deliveryCostPk);
        if (deliveryCost == null) {
            deliveryCost = deliveryCostService.createDeliveryCost(packageType, size, weight, deliveryType, kilometers);
        }
        AdditionalServiceCostPk additionalServiceCostPk
                = new AdditionalServiceCostPk(isToDoor, isFragile, hasInsurance);
        AdditionalServiceCost additionalServiceCost
                = additionalServiceCostService.getAdditionalServiceCost(additionalServiceCostPk);
        if (additionalServiceCost == null) {
            additionalServiceCost
                    = additionalServiceCostService.createAdditionalServiceCost(isToDoor, isFragile, hasInsurance);
        }
        packageInfo.setId(id);
        packageInfo.setDeliveryCost(deliveryCost);
        packageInfo.setAdditionalServiceCost(additionalServiceCost);
        packageInfo.setDateOfSending(dateOfSending);
        packageInfo.setTravelTime(calculateTravelTime(deliveryType, kilometers));
        return packageInfoRepository.save(packageInfo);
    }

    public Page<PackageInfo> getAllPackageInfo() {
        Pageable pageable = Pageable.unpaged();
        return packageInfoRepository.findAll(pageable);
    }

    public PackageInfo getPackageInfo(Long id) {
        return packageInfoRepository.findById(id).orElse(null);
    }

    public PackageInfo updatePackageInfo(
            Long id,
            Optional<String> packageType,
            Optional<Byte> size,
            Optional<Byte> weight,
            Optional<String> deliveryType,
            Optional<Integer> kilometers,
            Optional<Boolean> isToDoor,
            Optional<Boolean> isFragile,
            Optional<Boolean> hasInsurance,
            Optional<LocalDate> dateOfSending
    ) {
        PackageInfo packageInfo = getPackageInfo(id);
        if (packageInfo == null) {
            log.warn("Такого отправления не существует!");
            return null;
        }

        if (packageType.isPresent() || size.isPresent() || weight.isPresent() || deliveryType.isPresent() || kilometers.isPresent()) {
            DeliveryCostPk deliveryCostPkOld = packageInfo.getDeliveryCost().getDeliveryCostPk();

            String deliveryTypeNew = packageType.orElse(deliveryCostPkOld.getPackageType());
            Integer kilometersNew = kilometers.orElse(deliveryCostPkOld.getKilometers());

            DeliveryCostPk deliveryCostPkNew = new DeliveryCostPk(
                    deliveryTypeNew,
                    size.orElse(deliveryCostPkOld.getSize()),
                    weight.orElse(deliveryCostPkOld.getWeight()),
                    deliveryType.orElse(deliveryCostPkOld.getDeliveryType()),
                    kilometersNew
            );
            DeliveryCost deliveryCostNew = deliveryCostService.getDeliveryCost(deliveryCostPkNew);
            if (deliveryCostNew == null) {
                deliveryCostNew = deliveryCostService.createDeliveryCost(deliveryCostPkNew);
            }
            packageInfo.setDeliveryCost(deliveryCostNew);
            packageInfo.setTravelTime(calculateTravelTime(deliveryTypeNew, kilometersNew));
        }

        if (isToDoor.isPresent() || isFragile.isPresent() || hasInsurance.isPresent()) {
            AdditionalServiceCostPk additionalServiceCostPkOld
                    = packageInfo.getAdditionalServiceCost().getAdditionalServiceCostPk();
            AdditionalServiceCostPk additionalServiceCostPkNew = new AdditionalServiceCostPk(
                    isToDoor.orElse(additionalServiceCostPkOld.getIsToDoor()),
                    isFragile.orElse(additionalServiceCostPkOld.getIsFragile()),
                    hasInsurance.orElse(additionalServiceCostPkOld.getHasInsurance())
            );
            AdditionalServiceCost additionalServiceCostNew
                    = additionalServiceCostService.getAdditionalServiceCost(additionalServiceCostPkNew);
            if (additionalServiceCostNew == null) {
                additionalServiceCostNew
                        = additionalServiceCostService.createAdditionalServiceCost(additionalServiceCostPkNew);
            }
            packageInfo.setAdditionalServiceCost(additionalServiceCostNew);
        }

        dateOfSending.ifPresent(packageInfo::setDateOfSending);
        return packageInfoRepository.save(packageInfo);
    }

    public void deletePackageInfo(Long id) {
        PackageInfo packageInfo = getPackageInfo(id);
        if (packageInfo == null) {
            log.warn("Такого отправления не существует!");
            return;
        }
        packageInfoRepository.delete(packageInfo);
    }

    private Integer calculateTravelTime(String deliveryType, Integer kilometers) {
        switch (deliveryType) {
            case "Обычная" -> {
                if (kilometers < 500) {
                    return 2;
                } else if (kilometers < 1000) {
                    return 3;
                } else if (kilometers < 2000) {
                    return 6;
                } else if (kilometers < 3000) {
                    return 9;
                } else {
                    return 12;
                }
            }
            case "Первый класс" -> {
                if (kilometers < 500) {
                    return 1;
                } else if (kilometers < 1000) {
                    return 2;
                } else if (kilometers < 2000) {
                    return 4;
                } else if (kilometers < 3000) {
                    return 6;
                } else {
                    return 8;
                }
            }
            case "Экспресс" -> {
                if (kilometers < 500) {
                    return 0;
                } else if (kilometers < 1000) {
                    return 1;
                } else if (kilometers < 2000) {
                    return 2;
                } else if (kilometers < 3000) {
                    return 3;
                } else {
                    return 4;
                }
            }
            default -> {
                log.warn("Нет такого типа доставки!");
                return 12;
            }
        }
    }
}
