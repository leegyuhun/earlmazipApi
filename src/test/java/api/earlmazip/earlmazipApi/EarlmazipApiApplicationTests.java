package api.earlmazip.earlmazipApi;

import api.earlmazip.earlmazipApi.domain.AptInfo;
import api.earlmazip.earlmazipApi.domain.QAptInfo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class EarlmazipApiApplicationTests {

	@Autowired
	EntityManager em;

	@Test
	void contextLoads() {

		AptInfo aptInfo = new AptInfo();

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QAptInfo qAptInfo = QAptInfo.aptInfo;

		long result = queryFactory
				.selectFrom(qAptInfo)
				.where(qAptInfo.sigunguCode.eq("11680"))
				.fetchCount();
		assertThat(result).isGreaterThan(0);
	}

}
