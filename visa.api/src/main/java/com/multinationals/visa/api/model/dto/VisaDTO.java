package com.multinationals.visa.api.model.dto;



public class VisaDTO {
    private long visaId;
    private String name;
    private int processTimeInDays;
    private double feesLow;
    private double feesHigh;
    private String countryCode;
    private String region;
    private int gdpRank;
    private boolean hasPerks;
    private boolean hasRoadToCitizenship;

    public long getVisaId() {
        return visaId;
    }

    public void setVisaId(long visaId) {
        this.visaId = visaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProcessTimeInDays() {
        return processTimeInDays;
    }

    public void setProcessTimeInDays(int processTimeInDays) {
        this.processTimeInDays = processTimeInDays;
    }

    public double getFeesLow() {
        return feesLow;
    }

    public void setFeesLow(double feesLow) {
        this.feesLow = feesLow;
    }

    public double getFeesHigh() {
        return feesHigh;
    }

    public void setFeesHigh(double feesHigh) {
        this.feesHigh = feesHigh;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getGdpRank() {
        return gdpRank;
    }

    public void setGdpRank(int gdpRank) {
        this.gdpRank = gdpRank;
    }

    public boolean isHasPerks() {
        return hasPerks;
    }

    public void setHasPerks(boolean hasPerks) {
        this.hasPerks = hasPerks;
    }

    public boolean isHasRoadToCitizenship() {
        return hasRoadToCitizenship;
    }

    public void setHasRoadToCitizenship(boolean hasRoadToCitizenship) {
        this.hasRoadToCitizenship = hasRoadToCitizenship;
    }
}
