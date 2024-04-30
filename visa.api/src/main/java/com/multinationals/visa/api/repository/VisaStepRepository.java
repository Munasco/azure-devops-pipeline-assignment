package com.multinationals.visa.api.repository;

import com.multinationals.visa.api.model.Visa;
import com.multinationals.visa.api.model.VisaStep;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VisaStepRepository extends JpaRepository<VisaStep, Long> {
    List<VisaStep> findVisaStepsByVisa(Visa visa);
}
