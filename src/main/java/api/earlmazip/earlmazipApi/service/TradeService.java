package api.earlmazip.earlmazipApi.service;

import api.earlmazip.earlmazipApi.domain.dto.AptPriceDto;
import api.earlmazip.earlmazipApi.domain.dto.SearchCondition;
import api.earlmazip.earlmazipApi.repository.TradeJpaRepository;
import api.earlmazip.earlmazipApi.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TradeService {

    private final TradeRepository tradeRepository;

    private final TradeJpaRepository tradeJpaRepository;

    public List<AptPriceDto> getTradeListMonthlyBySigunguCode(String sigunguCode, String dealYear, String dealMon) {
        return tradeRepository.findBySigunguCodeAndDealYearAndDealMon(sigunguCode, dealYear, dealMon).stream().map(AptPriceDto::new).collect(Collectors.toList());
    }

    public List<AptPriceDto> getTradeListBySigunguCode(String sigunguCode) {
        return tradeJpaRepository.findBySigungu_Qerydsl(sigunguCode).stream().map(AptPriceDto::new).collect(Collectors.toList());
    }

    public List<AptPriceDto> getTradeListMonthlyV2(SearchCondition con) {
        return tradeJpaRepository.findByBuilder(con).stream().map(AptPriceDto::new).collect(Collectors.toList());
    }

    public List<AptPriceDto> getTradeListByDealDateAndDealAmt(String dealDate, int dealAmt) {
        return tradeRepository.findByDealDateAndDealAmt(dealDate, dealAmt).stream().map(AptPriceDto::new).collect(Collectors.toList());
    }
}
