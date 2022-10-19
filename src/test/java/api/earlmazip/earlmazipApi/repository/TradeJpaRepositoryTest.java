package api.earlmazip.earlmazipApi.repository;

import api.earlmazip.earlmazipApi.domain.AptPriceRaw;
import api.earlmazip.earlmazipApi.domain.dto.SearchCondition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TradeJpaRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    TradeJpaRepository tradeJpaRepository;

    @Test
    void findBySigungu_Qerydsl() {

        List<AptPriceRaw> bySigungu_qerydsl = tradeJpaRepository.findBySigungu_Qerydsl("11680");

        for (AptPriceRaw tmp : bySigungu_qerydsl) {
            System.out.println(tmp.getDealDate() + ", " + tmp.getAptName() + ", " + tmp.getDealAmt());
        }

        assertThat(bySigungu_qerydsl.size()).isGreaterThan(0);
    }

    @Test
    void findByBuilder() {
        SearchCondition con = new SearchCondition();
        con.setSigunguCode("11500");
        con.setDealYear("2022");
        con.setLandDong("방화동");
        List<AptPriceRaw> bySigungu_builder = tradeJpaRepository.findByBuilder(con);

        for (AptPriceRaw tmp : bySigungu_builder) {
            System.out.println(tmp.getDealDate() + ", " + tmp.getLandDong() + ", " + tmp.getAptName() + ", " + tmp.getDealAmt());
        }

        assertThat(bySigungu_builder.size()).isGreaterThan(0);
    }
}