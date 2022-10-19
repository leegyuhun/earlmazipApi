package api.earlmazip.earlmazipApi.controller;

import api.earlmazip.earlmazipApi.domain.dto.AptPriceDto;
import api.earlmazip.earlmazipApi.domain.dto.SearchCondition;
import api.earlmazip.earlmazipApi.service.TradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TradeController {

    private final TradeService tradeService;

    @GetMapping("/api/v1/tradelistMonthly")
    public @ResponseBody Map<String, Object> getTradeListMonthlyV1(
                                                        @RequestParam(value = "sigunguCode", defaultValue = "") String sigunguCode,
                                                        @RequestParam(value = "yyyymm", defaultValue = "") String yyyymm) {
        Map<String, Object> returnData = new HashMap<>();
        List<AptPriceDto> tradeList = new ArrayList<>();

        String tmp1 = yyyymm.substring(0, 4);
        String tmp2 = yyyymm.substring(4, 6);

        tradeList = tradeService.getTradeListMonthlyBySigunguCode(sigunguCode, yyyymm.substring(0, 4), yyyymm.substring(4, 6));

        returnData.put("code", "0000");
        returnData.put("msg", "success");
        returnData.put("dtl", "");
        returnData.put("count", tradeList.size());
        returnData.put("list", tradeList);

        return returnData;
    }

    @GetMapping("/api/v2/tradelistMonthly")
    public @ResponseBody Map<String, Object> getTradeListMonthlyV2(
            @RequestParam(value = "sigunguCode", defaultValue = "") String sigunguCode,
            @RequestParam(value = "year", defaultValue = "") String year,
            @RequestParam(value = "mon", defaultValue = "") String mon,
            @RequestParam(value = "landDong", defaultValue = "") String landDong,
            @RequestParam(value = "uaType", defaultValue = "UA01") String uaType) {
        Map<String, Object> returnData = new HashMap<>();
        SearchCondition con = new SearchCondition();

        List<AptPriceDto> tradeList = new ArrayList<>();

        if (sigunguCode.isEmpty() || year.isEmpty()) {
            returnData.put("code", "9999");
            returnData.put("msg", "parameter is empty.");
            returnData.put("dtl", "sigunguCode and year");
            returnData.put("count", tradeList.size());
            returnData.put("list", tradeList);
        } else {
            con.setSigunguCode(sigunguCode);
            con.setDealYear(year);
            con.setDealMon(mon);
            con.setLandDong(landDong);
            con.setUseAreaType(uaType);

            tradeList = tradeService.getTradeListMonthlyV2(con);

            returnData.put("code", "0000");
            returnData.put("msg", "success");
            returnData.put("dtl", "");
            returnData.put("count", tradeList.size());
            returnData.put("list", tradeList);
        }
        return returnData;
    }


    /**
     * 구코드로 최근거래 200 조회
     * @param sigunguCode
     * @return
     */
    @GetMapping("/api/v1/tradelist")
    public @ResponseBody Map<String, Object> getTradeListMonthly(
            @RequestParam(value = "sigunguCode", defaultValue = "") String sigunguCode
            ) {
        Map<String, Object> returnData = new HashMap<>();
        List<AptPriceDto> tradeList = new ArrayList<>();

//        String tmp1 = yyyymm.substring(0, 4);
//        String tmp2 = yyyymm.substring(4, 6);
        if (sigunguCode.isEmpty()) {
            returnData.put("code", "9999");
            returnData.put("msg", "parameter is empty.");
            returnData.put("dtl", "sigunguCode");
            returnData.put("count", tradeList.size());
            returnData.put("list", tradeList);
//            throw new RuntimeException("parameter is empty. [sigunguCode]");
        } else {
            tradeList = tradeService.getTradeListBySigunguCode(sigunguCode);

            returnData.put("code", "0000");
            returnData.put("msg", "success");
            returnData.put("dtl", "");
            returnData.put("count", tradeList.size());
            returnData.put("list", tradeList);
        }
        return returnData;
    }

    /**
     * 거래일자와 가격 조건으로 매매내역 조회
     * @param dealDate
     * @param dealAmt
     * @return
     */
    @GetMapping("/api/v1/tradelistByDealDateAndDealAmt")
    public @ResponseBody Map<String, Object> getTradeListByDealDateAndDealAmtV1(
            @RequestParam(value = "dealDate", defaultValue = "") String dealDate,
            @RequestParam(value = "dealAmt", defaultValue = "0") int dealAmt) {
        Map<String, Object> returnData = new HashMap<>();
        List<AptPriceDto> tradeList = new ArrayList<>();

//        String tmp1 = yyyymm.substring(0, 4);
//        String tmp2 = yyyymm.substring(4, 6);
        if (dealDate.isEmpty() || dealAmt == 0) {
            returnData.put("code", "9999");
            returnData.put("msg", "parameter is empty.");
            returnData.put("dtl", "sigunguCode");
            returnData.put("count", tradeList.size());
            returnData.put("list", tradeList);
//            throw new RuntimeException("parameter is empty. [sigunguCode]");
        } else {
            tradeList = tradeService.getTradeListByDealDateAndDealAmt(dealDate, dealAmt);

            returnData.put("code", "0000");
            returnData.put("msg", "success");
            returnData.put("dtl", "");
            returnData.put("count", tradeList.size());
            returnData.put("list", tradeList);
        }
        return returnData;
    }
}
