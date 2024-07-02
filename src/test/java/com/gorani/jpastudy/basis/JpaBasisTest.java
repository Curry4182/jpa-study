package com.gorani.jpastudy.basis;

import com.gorani.jpastudy.basis.gorani.GoraniEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class JpaBasisTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @DisplayName("1차 캐시 테스트")
    @Transactional
    public void testFirstLevelCache() {
        GoraniEntity gorani1 = new GoraniEntity("gorani", 30);
        entityManager.persist(gorani1);
        entityManager.flush();

        // 1차 캐시 테스트: 동일 엔티티를 다시 조회
        GoraniEntity cachedGorani = entityManager.find(GoraniEntity.class, gorani1.getId());
        Assertions.assertSame(gorani1, cachedGorani);  // 같은 인스턴스를 참조해야 함
    }
}

