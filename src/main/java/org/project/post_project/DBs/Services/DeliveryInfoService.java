package org.project.post_project.DBs.Services;

import lombok.extern.slf4j.Slf4j;
import org.project.post_project.DBs.Enities.*;
import org.project.post_project.DBs.Enities.Pks.SenderInfoPk;
import org.project.post_project.DBs.Repositories.DeliveryInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DeliveryInfoService {

    private final DeliveryInfoRepository deliveryInfoRepository;
    private final PostDeptService postDeptService;
    private final SenderInfoService senderInfoService;
    private final PostWorkersService postWorkersService;
    private final PackageInfoService packageInfoService;
    private final RecipientInfoService recipientInfoService;

    @Autowired
    public DeliveryInfoService(
            DeliveryInfoRepository deliveryInfoRepository,
            PostDeptService postDeptService,
            SenderInfoService senderInfoService,
            PostWorkersService postWorkersService,
            PackageInfoService packageInfoService,
            RecipientInfoService recipientInfoService
            ) {
        this.deliveryInfoRepository = deliveryInfoRepository;
        this.postDeptService = postDeptService;
        this.senderInfoService = senderInfoService;
        this.postWorkersService = postWorkersService;
        this.packageInfoService = packageInfoService;
        this.recipientInfoService = recipientInfoService;
    }

    public DeliveryInfo createDeliveryInfo(
            Long id,
            String postDeptIndexSend,
            String senderPassport,
            String senderPhone,
            Long workerNumber,
            Long packageId,
            String postDeptIndexReceive,
            String recipientPhone
    ) {
        if (getDeliveryInfo(id) != null) {
            log.warn("Такая доставка уже существует!");
            return null;
        }
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setId(id);

        PostDept postDeptSend = postDeptService.getPostDeptByIndex(postDeptIndexSend);
        if (postDeptSend == null) {
            log.warn("Почтовое отделение отправки для добавления в информацию о доставке не существует!");
            return null;
        }
        deliveryInfo.setPostSendDept(postDeptSend);

        SenderInfoPk senderInfoPk = new SenderInfoPk(senderPassport, senderPhone);
        SenderInfo senderInfo = senderInfoService.getSenderInfoByPk(senderInfoPk);
        if (senderInfo == null) {
            log.warn("Информации об отправителе для добавления в информацию о доставке не существует!");
            return null;
        }
        deliveryInfo.setSender(senderInfo);

        PostWorkers postWorker = postWorkersService.getPostWorker(workerNumber);
        if (postWorker == null) {
            log.warn("Информации о работнике для добавления в информацию о доставке не существует!");
            return null;
        }
        deliveryInfo.setPostWorkers(postWorker);

        PackageInfo packageInfo = packageInfoService.getPackageInfo(packageId);
        if (packageInfo == null) {
            log.warn("Информации об отправителе для добавления в информацию о доставке не существует!");
            return null;
        }
        deliveryInfo.setPackageInfo(packageInfo);

        PostDept postDeptReceive = postDeptService.getPostDeptByIndex(postDeptIndexReceive);
        if (postDeptReceive == null) {
            log.warn("Почтовое отделение доставки для добавления в информацию о доставке не существует!");
            return null;
        }
        deliveryInfo.setPostReceiveDept(postDeptReceive);

        RecipientInfo recipientInfo = recipientInfoService.getRecipientInfo(recipientPhone);
        if (recipientInfo == null) {
            log.warn("Получателя для добавления в информацию о доставке не существует!");
            return null;
        }
        deliveryInfo.setRecipient(recipientInfo);

        return deliveryInfoRepository.save(deliveryInfo);
    }

    public Page<DeliveryInfo> getAllDeliveryInfo() {
        Pageable pageable = Pageable.unpaged();
        return deliveryInfoRepository.findAll(pageable);
    }

    public DeliveryInfo getDeliveryInfo(Long id) {
        return deliveryInfoRepository.findById(id).orElse(null);
    }

    public DeliveryInfo updateDeliveryInfo(
            Long id,
            Optional<String> postDeptIndexSend,
            Optional<String> senderPassport,
            Optional<String> senderPhone,
            Optional<Long> workerNumber,
            Optional<Long> packageId,
            Optional<String> postDeptIndexReceive,
            Optional<String> recipientPhone
    ) {
        DeliveryInfo deliveryInfo = getDeliveryInfo(id);
        if (deliveryInfo == null) {
            log.warn("Информации о доставке " + id + " не существует!");
            return null;
        }

        if (postDeptIndexSend.isPresent()) {
            PostDept postDeptSendNew = postDeptService.getPostDeptByIndex(postDeptIndexSend.get());
            if (postDeptSendNew == null) {
                log.warn(
                        "Почтовое отделение отправки для добавления в информацию о доставке не существует!" +
                        "Информация не была обновлена!"
                );
            } else {
                deliveryInfo.setPostSendDept(postDeptSendNew);
            }
        }

        if (senderPassport.isPresent() || senderPhone.isPresent()) {
            SenderInfoPk senderInfoPkOld = deliveryInfo.getSender().getSenderInfoPk();
            SenderInfoPk senderInfoPkNew = new SenderInfoPk(
                    senderPassport.orElse(senderInfoPkOld.getPassport()),
                    senderPhone.orElse(senderInfoPkOld.getPhone())
            );
            SenderInfo senderInfoNew = senderInfoService.getSenderInfoByPk(senderInfoPkNew);
            if (senderInfoNew == null) {
                log.warn(
                        "Информации об отправителе для добавления в информацию о доставке не существует!" +
                        "Информация не была обновлена!"
                );
            } else {
                deliveryInfo.setSender(senderInfoNew);
            }
        }

        if (workerNumber.isPresent()) {
            PostWorkers postWorkerNew = postWorkersService.getPostWorker(workerNumber.get());
            if (postWorkerNew == null) {
                log.warn("Информации о работнике для добавления в информацию о доставке не существует!" +
                        "Информация не была обновлена!"
                );
            } else {
                deliveryInfo.setPostWorkers(postWorkerNew);
            }
        }

        if (packageId.isPresent()) {
            PackageInfo packageInfoNew = packageInfoService.getPackageInfo(packageId.get());
            if (packageInfoNew == null) {
                log.warn("Информации об отправителе для добавления в информацию о доставке не существует!" +
                        "Информация не была обновлена!"
                );
            } else {
                deliveryInfo.setPackageInfo(packageInfoNew);
            }
        }

        if (postDeptIndexReceive.isPresent()) {
            PostDept postDeptReceiveNew = postDeptService.getPostDeptByIndex(postDeptIndexReceive.get());
            if (postDeptReceiveNew == null) {
                log.warn("Почтовое отделение доставки для добавления в информацию о доставке не существует!" +
                        "Информация не была обновлена!"
                );
            } else {
                deliveryInfo.setPostReceiveDept(postDeptReceiveNew);
            }
        }

        if (recipientPhone.isPresent()) {
            RecipientInfo recipientInfoNew = recipientInfoService.getRecipientInfo(recipientPhone.get());
            if (recipientInfoNew == null) {
                log.warn("Получателя для добавления в информацию о доставке не существует!" +
                        "Информация не была обновлена!"
                );
            } else {
                deliveryInfo.setRecipient(recipientInfoNew);
            }
        }

        return deliveryInfoRepository.save(deliveryInfo);
    }

    public void deleteDeliveryInfo(Long id) {
        DeliveryInfo deliveryInfo = getDeliveryInfo(id);
        if (deliveryInfo == null) {
            log.warn("Информации о доставке " + id + " не существует!");
            return;
        }
        deliveryInfoRepository.delete(deliveryInfo);
    }
}
