package api.earlmazip.earlmazipApi.repository;

import api.earlmazip.earlmazipApi.domain.AptPriceRaw;
import api.earlmazip.earlmazipApi.domain.QAptInfo;
import api.earlmazip.earlmazipApi.domain.QAptPriceRaw;
import api.earlmazip.earlmazipApi.domain.dto.AptPriceDto;
import api.earlmazip.earlmazipApi.domain.dto.TradeSearchCondition;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Repository
public class TradeJpaRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    QAptPriceRaw qAptPriceRaw = QAptPriceRaw.aptPriceRaw;

    public TradeJpaRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<AptPriceRaw> findBySigungu_Qerydsl(String sigunguCode) {
        return queryFactory
                .selectFrom(qAptPriceRaw)
                .where(qAptPriceRaw.sigunguCode.eq(sigunguCode),
                        qAptPriceRaw.dealYear.eq("2022"))
                .orderBy(qAptPriceRaw.dealDate.desc())
                .offset(0)
                .limit(100)
                .fetch();
    }

    public List<AptPriceRaw> findByBuilder(TradeSearchCondition condition) {
        BooleanBuilder builder = new BooleanBuilder();
        if (hasText(condition.getSigunguCode())) {
            builder.and(qAptPriceRaw.sigunguCode.eq(condition.getSigunguCode()));
        }
        if (hasText(condition.getDealYear())) {
            builder.and(qAptPriceRaw.dealYear.eq(condition.getDealYear()));
            if (hasText(condition.getDealMon())) {
                builder.and(qAptPriceRaw.dealMon.eq(condition.getDealMon()));
            }
        }
        if (hasText(condition.getLandDong())) {
            builder.and(qAptPriceRaw.landDong.eq(condition.getLandDong()));
        }

        return queryFactory
                .selectFrom(qAptPriceRaw)
                .where(builder)
                .orderBy(qAptPriceRaw.dealDate.desc())
                .fetch();
    }
}
