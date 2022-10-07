package api.earlmazip.earlmazipApi.controller;

import api.earlmazip.earlmazipApi.domain.dto.AptInfoDto;
import api.earlmazip.earlmazipApi.domain.dto.AptPriceDto;
import api.earlmazip.earlmazipApi.domain.dto.TradeSearchCondition;
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

        returnData.put("code", "1000");
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
            @RequestParam(value = "landDong", defaultValue = "") String landDong) {
        Map<String, Object> returnData = new HashMap<>();
        TradeSearchCondition con = new TradeSearchCondition();

        List<AptPriceDto> tradeList = new ArrayList<>();

//        String tmp1 = yyyymm.substring(0, 4);
//        String tmp2 = yyyymm.substring(4, 6);

        con.setSigunguCode(sigunguCode);
        con.setDealYear(year);
        con.setDealMon(mon);
        con.setLandDong(landDong);

        tradeList = tradeService.getTradeListMonthlyV2(con);

        returnData.put("code", "1000");
        returnData.put("msg", "success");
        returnData.put("dtl", "");
        returnData.put("count", tradeList.size());
        returnData.put("list", tradeList);

        return returnData;
    }

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

            returnData.put("code", "1000");
            returnData.put("msg", "success");
            returnData.put("dtl", "");
            returnData.put("count", tradeList.size());
            returnData.put("list", tradeList);
        }
        return returnData;
    }
}
