package api.earlmazip.earlmazipApi.controller;

import api.earlmazip.earlmazipApi.domain.dto.AptInfoDto;
import api.earlmazip.earlmazipApi.service.AptInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AptInfoController {

    private final AptInfoService aptInfoService;

    @GetMapping("/api/v1/aptinfo")
    public @ResponseBody Map<String, Object> findAptInfoBySigunguCode(@RequestParam(value = "sigunguCode", defaultValue = "") String sigunguCode) {
        Map<String, Object> returnData = new HashMap<>();
        List<AptInfoDto> aptInfos = new ArrayList<>();

        if (sigunguCode.isEmpty()) {
            returnData.put("code", "9999");
            returnData.put("msg", "parameter is empty.");
            returnData.put("dtl", "sigunguCode");
            returnData.put("count", aptInfos.size());
            returnData.put("list", aptInfos);
//            throw new RuntimeException("parameter is empty. [sigunguCode]");
        } else {
            aptInfos = aptInfoService.findBySigunguCode(sigunguCode);

            if (aptInfos.size() == 0) {
                returnData.put("code", "9000");
                returnData.put("msg", "response empty.");
                returnData.put("dtl", "");
                returnData.put("count", aptInfos.size());
                returnData.put("list", aptInfos);
            } else {
                returnData.put("code", "1000");
                returnData.put("msg", "success");
                returnData.put("dtl", "");
                returnData.put("count", aptInfos.size());
                returnData.put("list", aptInfos);
            }
        }
        return returnData;
    }
}
