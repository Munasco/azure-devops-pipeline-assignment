package com.multinationals.visa.api.repository;

import java.util.List;

import com.multinationals.visa.api.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import com.multinationals.visa.api.model.Visa;
import org.springframework.data.jpa.repository.Query;

public interface VisaRepository extends JpaRepository<Visa, Long>{
    // List<Visa>  
    List<Visa> findByFeesHighLessThanOrderByGdpRankAsc(double fees);
    List<Visa> findByCountry(Country country);

    List<Visa> findByGdpRankBetween(int minDdpRank, int maxGdpRank);
    List<Visa> findByGdpRankBetweenAndHasRoadToCitizenship(int minDdpRank, int maxGdpRank, boolean hasRoadToCitizenship);

    List<Visa> findByGdpRankBetweenAndHasPerks(int minDdpRank, int maxGdpRank, boolean hasPerks);
    List<Visa> findByGdpRankBetweenAndHasPerksAndHasRoadToCitizenship(int minDdpRank, int maxGdpRank, boolean hasPerks, boolean hasRoadToCitizenship);
    List<Visa> findByCountryAndFeesHighLessThanOrderByGdpRankAsc(Country country, double fees);

    List<Visa> findByRegionAndFeesHighLessThanOrderByGdpRankAsc(String region, double fees);

    @Query("SELECT visa\n" +
            "FROM Visa visa\n" +
            "WHERE visa.region = :region\n" +
            "AND visa.feesHigh < :feesHigh\n" +
            "AND visa.processTimeInDays < :processTimeInDays\n" +
            "AND visa.gdpRank BETWEEN :minGdpRank AND :maxGdpRank\n" +
            "AND visa.hasRoadToCitizenship = :hasRoadToCitizenship\n" +
            "AND visa.hasPerks = :hasPerks\n" +
            "ORDER BY visa.gdpRank ASC\n")
    List<Visa> findByAllParameters(String region, double feesHigh, int processTimeInDays, int minGdpRank, int maxGdpRank, boolean hasRoadToCitizenship, boolean hasPerks);

    @Query("SELECT visa\n" +
            "FROM Visa visa\n" +
            "WHERE visa.region = :region\n" +
            "AND visa.feesHigh < :feesHigh\n" +
            "AND visa.processTimeInDays < :processTimeInDays\n" +
            "AND visa.gdpRank BETWEEN :minGdpRank AND :maxGdpRank\n" +
            "AND visa.hasRoadToCitizenship = :hasRoadToCitizenship\n" +
            "ORDER BY visa.gdpRank ASC\n")
    List<Visa> findByAllParametersExceptPerks(String region, double feesHigh, int processTimeInDays, int minGdpRank, int maxGdpRank, boolean hasRoadToCitizenship);

    @Query("SELECT visa\n" +
            "FROM Visa visa\n" +
            "WHERE visa.region = :region\n" +
            "AND visa.feesHigh < :feesHigh\n" +
            "AND visa.processTimeInDays < :processTimeInDays\n" +
            "AND visa.gdpRank BETWEEN :minGdpRank AND :maxGdpRank\n" +
            "AND visa.hasPerks = :hasPerks\n" +
            "ORDER BY visa.gdpRank ASC\n")
    List<Visa> findByAllParametersExceptCitizenship(String region, double feesHigh, int processTimeInDays, int minGdpRank, int maxGdpRank, boolean hasPerks);

    @Query("SELECT visa\n" +
            "FROM Visa visa\n" +
            "WHERE visa.region = :region\n" +
            "AND visa.feesHigh < :feesHigh\n" +
            "AND visa.processTimeInDays < :processTimeInDays\n" +
            "AND visa.gdpRank BETWEEN :minGdpRank AND :maxGdpRank\n" +
            "ORDER BY visa.gdpRank ASC\n")
    List<Visa> findByAllParametersExceptCitizenshipAndPerks(String region, double feesHigh, int processTimeInDays, int minGdpRank, int maxGdpRank);

    @Query("SELECT visa\n" +
            "FROM Visa visa\n" +
            "WHERE visa.feesHigh < :feesHigh\n" +
            "AND visa.processTimeInDays < :processTimeInDays\n" +
            "AND visa.gdpRank BETWEEN :minGdpRank AND :maxGdpRank\n" +
            "AND visa.hasRoadToCitizenship = :hasRoadToCitizenship\n" +
            "AND visa.hasPerks = :hasPerks\n" +
            "ORDER BY visa.gdpRank ASC\n")
    List<Visa> findByAllParametersExceptRegion(double feesHigh, int processTimeInDays, int minGdpRank, int maxGdpRank, boolean hasRoadToCitizenship, boolean hasPerks);

    @Query("SELECT visa\n" +
            "FROM Visa visa\n" +
            "WHERE visa.feesHigh < :feesHigh\n" +
            "AND visa.processTimeInDays < :processTimeInDays\n" +
            "AND visa.gdpRank BETWEEN :minGdpRank AND :maxGdpRank\n" +
            "AND visa.hasRoadToCitizenship = :hasRoadToCitizenship\n" +
            "ORDER BY visa.gdpRank ASC\n")
    List<Visa> findByAllParametersExceptRegionAndPerks(double feesHigh, int processTimeInDays, int minGdpRank, int maxGdpRank, boolean hasRoadToCitizenship);

    @Query("SELECT visa\n" +
            "FROM Visa visa\n" +
            "WHERE visa.feesHigh < :feesHigh\n" +
            "AND visa.processTimeInDays < :processTimeInDays\n" +
            "AND visa.gdpRank BETWEEN :minGdpRank AND :maxGdpRank\n" +
            "AND visa.hasPerks = :hasPerks\n" +
            "ORDER BY visa.gdpRank ASC\n")
    List<Visa> findByAllParametersExceptRegionAndCitizenship(double feesHigh, int processTimeInDays, int minGdpRank, int maxGdpRank, boolean hasPerks);

    @Query("SELECT visa\n" +
            "FROM Visa visa\n" +
            "WHERE visa.feesHigh < :feesHigh\n" +
            "AND visa.processTimeInDays < :processTimeInDays\n" +
            "AND visa.gdpRank BETWEEN :minGdpRank AND :maxGdpRank\n" +
            "ORDER BY visa.gdpRank ASC\n")
    List<Visa> findByAllParametersExceptRegionAndCitizenshipAndPerks(double feesHigh, int processTimeInDays, int minGdpRank, int maxGdpRank);

    Visa findByVisaId(long visaId);
}
