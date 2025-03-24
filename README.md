# 🚛 [five-io] logistics: MSA 기반 물류 관리 시스템

## 📌 프로젝트 소개
**logistics**는 MSA 기반의 B2B 물류 및 배송 관리 플랫폼으로, 물류 운영을 효율적으로 관리하고 자동화하는 것을 목표로 합니다.

### 🎯 기술적 목표
- **RBAC(Role-Based Access Control)**을 통한 사용자 및 관리자 권한 관리
- **JWT 기반 인증 및 데이터 암호화** 적용
- **MSA 구조**를 활용하여 각 서비스 간 원활한 통신
- **도메인 주도 개발(DDD)**을 기반으로 의존성을 낮춘 모듈 설계

### 🚀 구현 목표
- **우선순위 기반 API 개발** 및 테스트 시나리오 작성으로 `사용자 → 허브 → 주문 → 배송` 흐름을 명확하게 검증
- **FeignClient**를 활용하여 모듈 간 필요한 부분만 통신하도록 구현
- **공통 라이브러리 모듈**을 만들어 서브모듈에서 공통적으로 사용하는 라이브러리를 관리
- **Docker Compose 환경 구축**으로 서비스 실행 과정 단순화
- **Swagger 적용**을 통한 API 문서 자동화 및 효율적인 관리
- **주문 시 Slack API 연동**으로 실시간 배송 알림 전송

---

## 💻 개발 환경
| 분류             | 상세                        |
|----------------|---------------------------|
| **IDE**        | IntelliJ                    |
| **Language**   | Java 17                     |
| **Framework**  | Spring Boot, Spring Cloud   |
| **Repository** | PostgreSQL                  |
| **Build Tool** | Gradle 8.8                   |
| **Version Control** | Git, GitHub         |
| **Containerization** | Docker              |
| **API Documentation** | Swagger          |
| **External API** | Gemini API, Slack API, KakaoMobility, Google API |
| **Testing Tool** | IntelliJ HTTPClient |

---

## 🛠 실행 방법
### 1️⃣ .env 파일 설정
`.env` 파일을 프로젝트 루트 디렉토리에 생성하고, 아래 형식에 맞춰 환경 변수를 설정합니다.

```ini
services:
  postgres:
    image: postgres:16
    container_name: postgresql
    restart: always
    ports:
      - "5432:5432"
    environment:
      POSTGRES_USER: fiveio
      POSTGRES_PASSWORD: 1234
    volumes:
      - postgres_data:/var/lib/postgresql/data
      - ./init.sql:/docker-entrypoint-initdb.d/init.sql
volumes:
  postgres_data:
```

### 2️⃣ Docker Compose 실행
```sh
docker-compose up -d
```

### 3️⃣ API 문서 확인 (Swagger)
Swagger UI를 사용하여 API를 확인할 수 있습니다.
```sh
http://localhost:19090
```

---
## Skill


### [인프라 설계서](https://github.com/five-io/logistics/wiki/%EC%9D%B8%ED%94%84%EB%9D%BC-%EC%84%A4%EA%B3%84%EC%84%9C)

### [도메인 다이어그램 및 역할분담](https://github.com/five-io/logistics/wiki/%EB%8F%84%EB%A9%94%EC%9D%B8-%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8-&-%EC%97%AD%ED%95%A0-%EB%B6%84%EB%8B%B4)

### [애그리거트 구조도](https://github.com/five-io/logistics/wiki/%EC%97%90%EA%B7%B8%EB%A6%AC%EA%B1%B0%ED%8A%B8-%EA%B5%AC%EC%A1%B0%EB%8F%84)

### [ERD](https://www.erdcloud.com/d/ZeDtDoF2WsviKEhgS)

### [테이블 명세서](https://www.erdcloud.com/d/ZeDtDoF2WsviKEhgS)

### [API명세서](https://github.com/five-io/logistics/wiki/API-%EB%AA%85%EC%84%B8%EC%84%9C)

### [컨벤션](https://github.com/five-io/logistics/wiki/%EC%BB%A8%EB%B2%A4%EC%85%98)

### [Flow Chart](https://github.com/five-io/logistics/wiki/Flow-Chart)

## 도메인별 전략
<br>
<details>
<summary><h3>사용자 및 배송담담자</h3></summary>
<img src="https://github.com/user-attachments/assets/f12da2fd-9608-498c-8ced-22f0c0509ea9" width="100%">
</details>
<details>
<summary><h3>인증 및 인가</h3></summary>
   <img src="https://github.com/user-attachments/assets/e3005893-93e7-4283-a556-7542a8c39114" width="100%">
</details>
<br>
<details>
<summary><h3>허브</h3></summary>
     <img src="https://github.com/user-attachments/assets/751f2d88-4719-43ec-bab1-868656a0aaac" width="100%">
</details>
<br>
<details>
<summary><h3>업체 및 상품</h3></summary>  
</details>
<br>
<details>
<summary><h3>주문 및 배송</h3></summary>
<img src="https://github.com/user-attachments/assets/d0e937ed-6a2d-4bf2-ad96-96df9640c293" width="100%">
</details>
<br>
<details>
<summary><h3>AI 활용 Slack 알림</h3></summary>
<img src="https://github.com/user-attachments/assets/7485fae0-cbf0-4d03-9608-95e89b1e6d0f" width="100%">
</details>
<br>

## 프로젝트 회고

### 프로젝트 일정 및 진행

### 공통 관심사항

### 🛠 트러블슈팅 (Troubleshooting)

개발 과정에서 발생한 주요 문제와 해결 방법을 정리하였습니다.  
이전보다 더 안정적이고 확장 가능한 시스템을 구축하는 데 기여한 경험을 공유합니다.

---

<details>
<summary>⚙️ Config Server 설정 문제</summary>

### 📌 문제 상황
- MSA의 각 마이크로서비스들은 개별 설정 파일을 갖고 있음.
- 공통 설정값 변경 시 **애플리케이션을 재배포해야 하는 문제** 발생.
- 이를 해결하기 위해 **Spring Cloud Config Server**를 도입하여 중앙에서 설정을 관리하려 함.

### ❌ 발생한 문제
- Spring Cloud Config Server는 기본적으로 **Git을 통한 설정 관리**가 필요하지만, 우선 로컬 테스트를 위해 파일 시스템을 활용.
- Spring Cloud Bus + RabbitMQ를 이용하여 **서버를 재시작하지 않고 설정 변경을 반영**하려 했으나, 환경 변수가 Config Server에 있어도 제대로 반영되지 않음.
- **Gateway의 route 설정 및 테스트 과정에서 변경된 설정이 적용되지 않는 문제** 발생.
- 매번 **Config Server를 재부팅하거나 설정을 개별 애플리케이션으로 옮겨 테스트**하는 과정이 비효율적이었음.

### ✅ 해결 방법
- **Config Server 적용을 보류**하고, 프로젝트 진행을 위해 **각 애플리케이션에서 설정을 관리**하도록 변경.
- 추후 **리팩토링 시 Config Server를 다시 도입**하여 문제를 해결할 계획.

</details>

---

<details>
<summary>🔗 Swagger 통합 시 엔드포인트 설정 오류</summary>

### 📌 문제 상황
- 각 마이크로서비스별로 Swagger를 제공하지만, **각 서비스마다 개별적으로 접근해야 하는 불편함**이 존재.
- 이를 개선하기 위해 **Gateway에서 모든 서비스의 API 문서를 통합하여 한 곳에서 제공**하도록 설정.

### ❌ 발생한 문제
- 각 서비스에서는 `Swagger UI`와 `docs`가 정상적으로 제공되었으나, **Gateway에서는 문서가 정상적으로 표시되지 않는 문제 발생**.

### 🔍 원인 분석
- 각 서비스의 **Swagger URL 설정이 잘못되어 Gateway에서 문서를 가져오지 못함**.
- Gateway에서 라우팅 시 Swagger 관련 엔드포인트도 **기존 API 라우팅 방식과 동일하게 적용**하여 문제가 발생.

### ✅ 해결 방법
- **각 서비스의 Swagger URL을 정정**하여 올바르게 매핑.
- 기존의 API 라우팅 설정과 **Swagger 엔드포인트 라우팅을 분리**하여 설정.
- **RewritePath**를 활용하여 불필요한 경로를 제거하고 올바르게 라우팅되도록 수정.

</details>

---

<details>
<summary>🐘 PostgreSQL 로그인 오류</summary>

### 📌 문제 상황
- PostgreSQL에 연결 시 아래와 같은 오류 발생:

```log
org.springframework.beans.factory.BeanCreationException: Error creating bean with
name 'entityManagerFactory' defined in class path resource 
[org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: 
[PersistenceUnit: default] Unable to build Hibernate SessionFactory; 
nested exception is org.hibernate.exception.GenericJDBCException: 
Unable to open JDBC Connection for DDL execution
```

### 🔍 원인 분석
- **Docker Compose로 실행한 PostgreSQL과 로컬에 설치된 PostgreSQL이 충돌**한 것으로 추정.
- Docker Compose로 실행 중인데, **로컬 PostgreSQL이 동시에 실행되어 예상치 못한 충돌 발생**.

### ✅ 해결 방법
- **로컬 PostgreSQL 서비스를 중지**한 후 다시 실행.

```sh
sudo systemctl stop postgresql  # Linux
brew services stop postgresql   # MacOS (Homebrew 사용 시)
```

- 이후 **Docker Compose로 실행한 PostgreSQL을 정상적으로 연결**하여 해결.

</details>

---

<details>
<summary>🔒 권한 설정 오류 (AOP 기반)</summary>

### 📌 문제 상황
- 권한 체크 후 API를 호출하면 아래와 같은 에러가 발생:

```json
{
  "code": 999,
  "message": "권한이 만료되었습니다."
}
```

### 🔍 원인 분석
- **사용자의 AccessToken을 Spring Security가 아니라 Common Library에서 관리**하는 방식으로 구현됨.
- 그러나 Security 설정에서 **기본적으로 AccessToken을 검증**하려고 시도하여 충돌이 발생.

### ✅ 해결 방법
- **Spring Security 관련 설정을 제거**하고, Common Library에서 직접 권한을 검증하도록 변경.
- `build.gradle`에서 Security 관련 의존성을 삭제하여 문제 해결.

</details>

---

<details>
<summary>🔄 Feign Client 사용 시 HTTP 매핑 이슈</summary>

### 📌 문제 발생
- `@PatchMapping`을 사용했으나 요청이 제대로 전달되지 않음.
- `@GetMapping`을 사용했는데, 서버에서는 `@PostMapping`으로 요청을 받음.

### 🔍 원인 분석
- **Feign Client 기본 설정 문제**
  - Feign Client는 기본적으로 `HttpURLConnection`을 사용.
  - `HttpURLConnection`은 **PATCH 요청을 지원하지 않음**.
  
- **`@GetMapping` + `@RequestBody` 사용 오류**
  - `@GetMapping`은 **HTTP GET 요청이므로 바디를 포함할 수 없음**.
  - 요청을 보내더라도 서버에서 제대로 인식되지 않음.

### ✅ 해결 방법
- **PATCH 요청 문제**
  - `@PatchMapping` 대신 **`@PutMapping`으로 임시 변경하여 해결**.
  - 추후 **OkHttp 등 외부 라이브러리를 사용하여 PATCH 요청을 정상 처리**할 예정.

- **GET 요청 문제**
  - `@GetMapping`에서 데이터를 전달할 때는 **`@RequestParam`을 사용하도록 변경**.

### 🔧 코드 예시
```java
// ❌ 잘못된 코드: GET 요청에 @RequestBody 사용
@GetMapping("/example")
ResponseEntity<String> getExample(@RequestBody ExampleDto dto); 

// ✅ 수정된 코드: @RequestParam 사용
@GetMapping("/example")
ResponseEntity<String> getExample(@RequestParam String param);
```
</details>

---

## 협업 진행

### ✅ 협업 시 우리 조가 잘한 점


### ❌ 협업 시 아쉬웠던 부분

## 팀원 소개


## 💁‍♂️ 프로젝트 팀원


| <img src="https://github.com/user-attachments/assets/d7101f4b-94fe-482d-971c-cd24ce05bf9c" width="100%"> |
|:--------------------------------------------------------------------------------------------------:|

| 👑발표리더 | 👑테크리더 | 👑노션리더 | 👑팀리더 | 👑위키리더 |
|:---:|:---:|:---:|:---:|:---:|
| **이태훈** | **김민경** | **문고은** | **양수영** | **신예나** |
| 사용자/담당자 담당 | 허브, 인증/인가 담당 | 배송, 주문 담당 | Slack, AI 담당 | 업체, 상품 담당 |

도움을 주신 분: 👏🏻 **최용석 튜터님** 👏🏻


## 추후 리팩토링

- 현재 시스템의 문제점 인지 -> 개선을 위한 기술적 계획 및 접근

### • 인증 및 인가

blacklist관리
accesstoken refreshtoken
resolver와 context 관리

### • 허브

캐시관리

### • 업체 및 상품

### •주문 및 배송

### • AI 활용 Slack 알림

### • 사용자 및 배송담담자

