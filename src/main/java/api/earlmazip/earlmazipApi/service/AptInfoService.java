package api.earlmazip.earlmazipApi.service;

import api.earlmazip.earlmazipApi.domain.dto.AptInfoDto;
import api.earlmazip.earlmazipApi.repository.AptInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AptInfoService {

    private final AptInfoRepository aptInfoRepository;

    public List<AptInfoDto> findBySigunguCode(String sigunguCode) {
        return aptInfoRepository.findBySigunguCode(sigunguCode).stream().map(AptInfoDto::new).collect(Collectors.toList());
    }
}
