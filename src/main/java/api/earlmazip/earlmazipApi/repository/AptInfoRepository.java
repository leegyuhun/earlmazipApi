package api.earlmazip.earlmazipApi.repository;

import api.earlmazip.earlmazipApi.domain.AptInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AptInfoRepository extends JpaRepository<AptInfo, Long> {

    List<AptInfo> findBySigunguCode(String sigunguCode);
}
