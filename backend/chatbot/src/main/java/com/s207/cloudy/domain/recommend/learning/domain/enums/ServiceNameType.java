package com.s207.cloudy.domain.recommend.learning.domain.enums;

import com.s207.cloudy.global.error.exception.CustomValidationException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum ServiceNameType {
    DATABASE("Database", "Database"),
    STORAGE("Storage", "Storage"),
    MACHINE_LEARNING("Machine_Learning", "Machine Learning"),
    CLOUD_ESSENTIALS("Cloud_Essentials", "Cloud Essentials"),
    NETWORK_CONTENT_DELIVERY("Network_Content_Delivery", "Network & Content Delivery"),
    SERVERLESS("Serverless", "Serverless"),
    ETC("etc", "etc");

    private final String serviceName;
    private final String convertServiceName;
    private static final Map<String, String> SERVICENAME_MAP = new HashMap<>();

    static {
        for(ServiceNameType serviceNameType : values()) {
            SERVICENAME_MAP.put(serviceNameType.serviceName, serviceNameType.convertServiceName);
        }
    }

    ServiceNameType(String serviceName, String convertServiceName) {
        this.serviceName = serviceName;
        this.convertServiceName = convertServiceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getConvertServiceName() {
        return convertServiceName;
    }

    public static String getByServiceName(String serviceName) {
        return Optional.ofNullable(SERVICENAME_MAP.get(serviceName)).orElseThrow(CustomValidationException::new);
    }
}
