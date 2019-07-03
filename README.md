# springbootWebService

**Build & Excute**

​	`gradle build && java -jar build/libs/spring-webservice-0.0.1.jar`

**ROOT Page**
​	URL : http://localhost:8080/

## 기본 요구사항

### 1. 로그인

```
1. 사용자의 아이디와 비번으로 로그인을 할 수 있어야 한다.
2. 사용자 데이터는 애플리케이션 실행 시점에 생성.
    ID: testuser, testuser1, testuser2, testuser3
    password: qodzmrhkwp!@#
3. 비밀번호는 암호화해서 저장.
```
### 2. 장소 검색

```
1. 키워드를 통해 장소를 검색.
2. 검색 결과는 Pagination 형태로 제공해.
3. 검색 소스는 카카오 API의 키워드로 장소 검색
```

### 3. 검색된 장소의 상세 조회

```
1. 각 검색 결과의 상세 정보(지번, 도로명주소, 전화번호 등) 표시.
2. 상세 정보에는 Daum 지도 바로가기 URL 제공.
```

### 4. 내 검색 히스토리

```
1. 나의 검색 히스토리(키워드, 검색 일시) 최신순 제공.
```

### 5. 인기 키워드 목록

```
1. 사용자들이 많이 검색한 순서대로, 최대 10개의 검색 키워드를 제공.
2. 키워드 별로 검색된 횟수도 함께 표기.
```

## Skill Set
* Back-end

| 기술 |비고 |
|:-------------|:-------------|
| JAVA 8 |-|
| Spring Boot 2.1.6 | Java-Platform Framework |
| H2 Database | Spring boot Embedded 버전을 사용 (In-memory) |
| Spring security | 로그인 기능 처리 |
| Spring boot data JPA | Spring 기본 JPA를 사용 |

* Front-end

| 기술 |비고 |
|:-------------|:-------------|
| jquery 3.4 |-|
| Bootstrap 4.3.1 | Front-end Component Libarary |
| DataTables |-|

## Database Console
```
http://localhost:8080/h2-console
Driver Class : org.h2.Driver
JDBC URL : jdbc:h2:mem:yym
User Name : sa
password: 없음
```

