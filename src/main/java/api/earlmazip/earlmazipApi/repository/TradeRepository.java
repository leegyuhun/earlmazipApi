package api.earlmazip.earlmazipApi.repository;

import api.earlmazip.earlmazipApi.domain.AptPriceRaw;
import api.earlmazip.earlmazipApi.domain.dto.TradeSearchCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeRepository extends JpaRepository<AptPriceRaw, Long> {

    List<AptPriceRaw> findBySigunguCodeAndDealYearAndDealMon(String sigunguCode, String dealYear, String dealMon);
}
