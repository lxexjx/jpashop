# API 개발 고급 정리

### 엔티티 조회
1. 엔티티를 조회해서 그대로 반환: V1
2. 엔티티 조회 후 DTO로 변환: V2
3. 페치 조인으로 쿼리 수 최적화: V3
4. 컬렉션 페이징과 한계 돌파: V3.1
      컬렉션은 페치 조인시 페이징이 불가능
      ToOne 관계는 페치 조인으로 쿼리 수 최적화
      컬렉션은 페치 조인 대신에 지연 로딩을 유지하고, hibernate.default_batch_fetch_size ,
      @BatchSize 로 최적화

### DTO 직접 조회
1. JPA에서 DTO를 직접 조회: V4
2. 컬렉션 조회 최적화 - 일대다 관계인 컬렉션은 IN 절을 활용해서 메모리에 미리 조회해서 최적화: V5
3. 플랫 데이터 최적화 - JOIN 결과를 그대로 조회 후 애플리케이션에서 원하는 모양으로 직접 변환: V6
