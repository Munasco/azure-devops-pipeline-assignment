package com.multinationals.visa.api.model.dto;

import lombok.Data;

@Data
public class VisaDTO {
    private long visaId;
    private String name;
    private int processTimeInDays;
    private double fees;
    private String countryCode;
    private String region;
    private int gdpRank;
    private boolean hasPerks;
    private boolean hasRoadToCitizenship;
}
