package com.multinationals.visa.api.repository;

import com.multinationals.visa.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
}


// {
//    // List<Visa>
//    List<Visa> findByFeesLessThanOrderByGdpRankDesc(int fees);
//    List<Visa> findByCountry(String country);
//    List<Visa> findByRegion(String region);
//    List<Visa> findByVisaIdOrName(long visaId, String name);
//}
