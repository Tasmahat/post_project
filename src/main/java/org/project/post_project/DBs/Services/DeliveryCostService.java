package org.project.post_project.DBs.Services;

import lombok.extern.slf4j.Slf4j;
import org.project.post_project.DBs.Enities.DeliveryCost;
import org.project.post_project.DBs.Enities.Pks.DeliveryCostPk;
import org.project.post_project.DBs.Repositories.DeliveryCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeliveryCostService {

    private final DeliveryCostRepository deliveryCostRepository;

    @Autowired
    public DeliveryCostService(DeliveryCostRepository deliveryCostRepository) {
        this.deliveryCostRepository = deliveryCostRepository;
    }

    public DeliveryCost createDeliveryCost(
            String packageType,
            Byte size,
            Byte weight,
            String deliveryType,
            Integer kilometers
    ) {
        DeliveryCostPk deliveryCostPk = new DeliveryCostPk(packageType, size, weight, deliveryType, kilometers);
        return createDeliveryCost(deliveryCostPk);
    }

    public DeliveryCost createDeliveryCost(DeliveryCostPk deliveryCostPk) {
        if (getDeliveryCost(deliveryCostPk) != null) {
            log.warn("Такая стоимость доставки уже существует!");
            return null;
        }
        DeliveryCost deliveryCost = new DeliveryCost();
        deliveryCost.setDeliveryCostPk(deliveryCostPk);
        deliveryCost.setCost(calculateCost(deliveryCostPk));
        return deliveryCostRepository.save(deliveryCost);
    }

    public Page<DeliveryCost> getAllDeliveryCost() {
        Pageable pageable = Pageable.unpaged();
        return deliveryCostRepository.findAll(pageable);
    }

    public DeliveryCost getDeliveryCost(DeliveryCostPk deliveryCostPk) {
        return deliveryCostRepository.findByDeliveryCostPk(deliveryCostPk).orElse(null);
    }

    public void deleteDeliveryCost(
            String packageType,
            Byte size,
            Byte weight,
            String deliveryType,
            Integer kilometers
    ) {
        DeliveryCostPk deliveryCostPk = new DeliveryCostPk(packageType, size, weight, deliveryType, kilometers);
        DeliveryCost deliveryCost = getDeliveryCost(deliveryCostPk);
        if (getDeliveryCost(deliveryCostPk) == null) {
            log.warn("Такая стоимость доставки не существует!");
            return;
        }
        deliveryCostRepository.delete(deliveryCost);
    }

    private Integer calculateCost(DeliveryCostPk deliveryCostPk) {
        Integer cost = 0;
        String packageType = deliveryCostPk.getPackageType();
        Byte size = deliveryCostPk.getSize();
        Byte weight = deliveryCostPk.getWeight();
        String deliveryType = deliveryCostPk.getDeliveryType();
        Integer kilometers = deliveryCostPk.getKilometers();

        switch (packageType) {
            case "Письмо" -> {
                switch (deliveryType) {
                    case "Обычная" ->
                        cost += ((size * weight) - 1) * 10 + 20;
                    case "Первый класс" ->
                        cost += ((size * weight) - 1) * 20 + 40;
                    case "Экспресс" ->
                        cost += ((size * weight) - 1) * 30 + 60;
                    default -> {
                        log.warn("Нет такого типа доставки!");
                        cost += ((size * weight) - 1) * 10 + 20;
                    }
                }
            }
            case "Бандероль" -> {
                switch (deliveryType) {
                    case "Обычная" ->
                        cost += ((size * weight) - 1) * 15 + 50;
                    case "Первый класс" ->
                        cost += ((size * weight) - 1) * 30 + 100;
                    case "Экспресс" ->
                        cost += ((size * weight) - 1) * 45 + 150;
                    default -> {
                        log.warn("Нет такого типа доставки!");
                        cost += ((size * weight) - 1) * 10 + 20;
                    }
                }
            }
            case "Посылка" -> {
                switch (deliveryType) {
                    case "Обычная" ->
                        cost += ((size * weight) - 1) * 25 + 100;
                    case "Первый класс" ->
                        cost += ((size * weight) - 1) * 50 + 200;
                    case "Экспресс" ->
                        cost += ((size * weight) - 1) * 75 + 300;
                    default -> {
                        log.warn("Нет такого типа доставки!");
                        cost += ((size * weight) - 1) * 10 + 20;
                    }
                }
            }
            default -> {
                log.warn("Нет такого вида почтового отправения!");
                cost += ((size * weight) - 1) * 10 + 20;
            }
        }

        if (kilometers < 500) {
            return cost;
        } else if (kilometers < 1000) {
            return (int) Math.round(cost * 1.5);
        } else if (kilometers < 2000) {
            return cost * 2;
        } else if (kilometers < 3000) {
            return (int) Math.round(cost * 2.5);
        } else {
            return cost * 3;
        }
    }
}
