# 🚛 [five-io] logistics: MSA 기반 물류 관리 시스템
<br>

### 🦖 프로젝트 소개
**logistics**는 MSA 기반의 B2B 물류 및 배송 관리 플랫폼으로,<br>
물류 운영을 효율적으로 관리하고 자동화하는 것을 목표로 합니다.

<br>

### 🧐 기술적 목표
- **RBAC(Role-Based Access Control)**을 통한 사용자 및 관리자 권한 관리
- **JWT 기반 인증 및 데이터 암호화** 적용
- **MSA 구조**를 활용하여 각 서비스 간 원활한 통신
- **도메인 주도 개발(DDD)**을 기반으로 의존성을 낮춘 모듈 설계
<br>

### 😎 구현 목표
- **우선순위 기반 API 개발** 및 테스트 시나리오 작성으로 `사용자 → 허브 → 주문 → 배송` 흐름을 명확하게 검증
- **FeignClient**를 활용하여 모듈 간 필요한 부분만 통신하도록 구현
- **공통 라이브러리 모듈**을 만들어 서브모듈에서 공통적으로 사용하는 라이브러리를 관리
- **Docker Compose 환경 구축**으로 서비스 실행 과정 단순화
- **Swagger 적용**을 통한 API 문서 자동화 및 효율적인 관리
- **주문 시 Slack API 연동**으로 실시간 배송 알림 전송
<br>

---

## 💁🏻 프로젝트 팀원
<br>

| <img src="https://github.com/user-attachments/assets/d7101f4b-94fe-482d-971c-cd24ce05bf9c" width="100%"> |
|:--------------------------------------------------------------------------------------------------:|

| 👑발표리더 | 👑테크리더 | 👑노션리더 | 👑팀리더 | 👑위키리더 |
|:---:|:---:|:---:|:---:|:---:|
| **이태훈** | **김민경** | **문고은** | **양수영** | **신예나** |
| 사용자/담당자 담당 | 허브, 인증/인가 담당 | 배송, 주문 담당 | Slack, AI 담당 | 업체, 상품 담당 |

<br>

도움을 주신 분: 👏🏻 **최용석 튜터님** 👏🏻

<br>

---

## 💻 개발 환경
<br>

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

<br>

---

## 🛠 실행 방법
<br>

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

<br>

### 2️⃣ Docker Compose 실행

```sh
docker-compose up -d
```

<br>

### 3️⃣ API 문서 확인 (Swagger)
Swagger UI를 사용하여 API를 확인할 수 있습니다.

```sh
http://localhost:19090
```

<br>

---

## 🪄 Skill
<br>

내용

---

## 설계 산출물
<br>

설계 산출물은 시스템의 핵심 구조와 흐름을 정리하고 문서화한 결과물입니다.<br> 
이를 통해 개발 팀은 시스템을 더 명확하게 이해하고, 향후 확장 및 유지보수를 용이하게 할 수 있습니다.
<br>


### [인프라 설계서](https://github.com/five-io/logistics/wiki/%EC%9D%B8%ED%94%84%EB%9D%BC-%EC%84%A4%EA%B3%84%EC%84%9C)

### [도메인 다이어그램 및 역할분담](https://github.com/five-io/logistics/wiki/%EB%8F%84%EB%A9%94%EC%9D%B8-%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8-&-%EC%97%AD%ED%95%A0-%EB%B6%84%EB%8B%B4)

### [애그리거트 구조도](https://github.com/five-io/logistics/wiki/%EC%97%90%EA%B7%B8%EB%A6%AC%EA%B1%B0%ED%8A%B8-%EA%B5%AC%EC%A1%B0%EB%8F%84)

### [ERD](https://www.erdcloud.com/d/ZeDtDoF2WsviKEhgS)

### [테이블 명세서](https://www.erdcloud.com/d/ZeDtDoF2WsviKEhgS)

### [API명세서](https://github.com/five-io/logistics/wiki/API-%EB%AA%85%EC%84%B8%EC%84%9C)

### [컨벤션](https://github.com/five-io/logistics/wiki/%EC%BB%A8%EB%B2%A4%EC%85%98)

### [Flow Chart](https://github.com/five-io/logistics/wiki/Flow-Chart)

<br>

---

## ⚔️ 도메인별 전략
<br>

도메인별 전략은 각 도메인에 대한 설계 및 접근 방식을 설명하며,<br>
시스템의 각 요소가 어떻게 상호작용하는지를 명확하게 보여줍니다.

---
<details>
<summary>사용자 및 배송담담자</summary>
<img src="https://github.com/user-attachments/assets/f12da2fd-9608-498c-8ced-22f0c0509ea9" width="100%">
</details>

---

<details>
<summary>인증 및 인가</summary>
   <img src="https://github.com/user-attachments/assets/e3005893-93e7-4283-a556-7542a8c39114" width="100%">
</details>

---

<details>
<summary>허브</summary>
     <img src="https://github.com/user-attachments/assets/751f2d88-4719-43ec-bab1-868656a0aaac" width="100%">
</details>

---

<details>
<summary>업체 및 상품</summary>  
  <img src="https://github.com/user-attachments/assets/13b4ca99-1264-4a42-9959-8ea9b6dd719d" width="100%">
</details>

---

<details>
<summary>주문 및 배송</summary>
<img src="https://github.com/user-attachments/assets/d0e937ed-6a2d-4bf2-ad96-96df9640c293" width="100%">
</details>

---

<details>
<summary>AI 활용 Slack 알림</summary>
<img src="https://github.com/user-attachments/assets/7485fae0-cbf0-4d03-9608-95e89b1e6d0f" width="100%">
</details>

<br>

---

## 🤔 프로젝트 회고
<br>

### 🗓️ 프로젝트 일정 및 진행

### 3.11 ~ 14
- 에그리거트 구조도, 도메인 다이어그램, ERD 명세서, 테이블 명세서, API 명세서 작성
- 인프라 설계서 및 컨벤션 작성
- 역할 분담
- 환경 세팅
  - Eureka Server, GateWay, ConfigServer 설정
  - Docker-compose 설정
  - 구글 자바코드 컨벤션 및 깃 컨벤션 적용
- Swagger 통합 적용

### 3.15 ~ 18
- 각 도메인 CRUD 개발
- HttpClient 적용
- Flow Chart 작성
- ConfigServer 비활성화, Common 모듈 구성
- 인증/인가 플로우 및 구현
- 권한 체크

### 3.19 ~ 24
- TODO List 및 우선순위 정리
- 테스트 시나리오 작성 및 테스트
- README 작성
- 발표자료 작성
<br>
  
### 🥰 공통 관심사항

#### ❗ Exception
- 시스템 전반에서 발생할 수 있는 예외 처리 전략을 세우고, 예외 발생 시 <br>일관된 오류 메시지와 로그를 출력하여 디버깅과 문제 해결을 용이하게 합니다.

#### 📝 Auditing
- 시스템의 중요한 데이터에 대한 생성일, 수정일, 수정자 등과 같은 변경 이력을 <br>자동으로 추적하여 데이터의 변동사항을 기록하고 관리합니다.

#### 🔄 Interceptor
- 요청과 응답을 가로채서 공통적인 처리를 할 수 있는 구조로, 로깅, 인증/인가 <br>등의 작업을 구현할 때 유용하게 사용됩니다.

#### 🔒 AOP (권한검증)
- 관점 지향 프로그래밍(AOP)을 사용하여, 특정 메서드 호출 시 권한 검증을 자동으로 <br>수행하여 코드의 중복을 줄이고 유지보수를 용이하게 합니다.

#### 📚 DDD (Domain-Driven Design)
- 비즈니스 도메인을 중심으로 시스템을 설계하고 구현하는 방법론으로, 도메인 모델을 <br>중심으로 복잡한 비즈니스 로직을 효과적으로 다룰 수 있습니다.

<br>
  
---

### 🚨 트러블슈팅 (Troubleshooting)
<br>

개발 과정에서 발생한 주요 문제와 해결 방법을 정리하였습니다.<br>
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

<br>

### 🚀 협업 진행 회고
<br>

팀워크와 협업이 원활하게 이루어졌고, 문제를 함께 해결하면서 모두가 성장하는 경험을 얻었습니다.<br>
커뮤니케이션과 협업 도구를 더 적극적으로 활용할 계획입니다.

---
<details>
<summary>✅ 우리 조가 잘한 점</summary>

### 🔹 원활한 커뮤니케이션 & 팀워크
- 각자 학습한 내용을 공유하며 함께 성장할 수 있도록 적극적으로 소통.
- **ZEP에 상시 접속**하여 즉각적인 피드백을 주고받으며 빠른 이슈 해결.
- 이슈 생성 및 커밋 시 **API별, 클래스별로 상세하게 기록**하여 확인이 용이함.
- **슬랙, 노션을 적극 활용**하여 작업 진행 상황과 의견을 원활하게 교환.

### 🔹 책임감 있는 역할 분배
- 모든 팀원이 **각자의 역할을 리더십 있게 수행**하며 프로젝트를 주도적으로 진행.
- 누가 시키지 않아도 **매일 늦은 시간까지 남아 함께 개발**하여 높은 팀워크를 보여줌.
- 한 파트의 담당자로서 **각자의 역할과 책임을 명확하게 수행**.

### 🔹 코드 품질 유지 및 자동화
- **Google Java Code Style**을 IntelliJ에 일괄 적용하여 코드 컨벤션을 통일.
- **Mapper 컨벤션 적용**으로 일관된 개발 방식 유지.
- **Git flow 전략 적용 & 코드 리뷰 진행**하여 코드 품질 유지.
- **자동화된 CI/CD 구축**을 통해 배포 프로세스를 효율적으로 개선.

### 🔹 철저한 문서화
- **API 명세서, ERD, 요구사항 정의서 등을 정리**하여 팀원 간 이해도를 높임.
- 프로젝트 진행 중 **문서화를 통해 변경 사항을 공유**하고 이슈를 관리.

### 🔹 신속한 문제 해결
- **트러블슈팅 시 빠른 공유 & 해결책 논의**를 통해 개발 속도를 유지.
- 예상치 못한 문제 발생 시 **즉시 공유하고 해결책을 논의하여 신속하게 대응**.

</details>

---

<br>

<details>
<summary>❌ 아쉬웠던 점 & 개선할 점</summary>

### 🔸 초기 설계 부족
- **설계가 계속 변경되며 플로우가 바뀌어** 초기 개발에 혼란이 있었음.
- **MSA + DDD 구조를 처음 도입하면서** 공통 모듈 및 설정 작업에 시행착오 발생.

### 🔸 일정 관리 부족
- 일정 관리가 미흡하여 **일부 작업이 예상보다 지연**됨.
- 요구사항 변경이 많아 **불필요한 수정이 발생**하는 경우가 있었음.

### 🔸 코드 리뷰 & 테스트 부족
- 일정이 촉박할 때 **코드 리뷰가 제대로 이루어지지 않아 코드 품질이 저하**된 경우가 있음.
- 기능 개발은 완료되었으나 **테스트가 부족하여 버그 발생** 사례가 있음.

### 🔸 워라벨 부족
- **너무 늦은 시간까지 개발하는 경우가 많아** 저녁 식사시간이 부족함.
- 개발에 대한 **열정이 넘쳤지만, 워라벨이 잘 지켜지지 않음**.

</details>

---

<details>
<summary>🛠 앞으로의 개선 방향</summary>

- **초기 설계를 더 철저히 준비**하여 불필요한 변경을 줄이기.
- **일정 관리 툴 적극 활용** (예: Jira, Notion 등)하여 업무 분배를 명확하게 하기.
- **코드 리뷰 프로세스 강화**하여 코드 품질 유지.
- **테스트 자동화 도입**으로 배포 전 이슈 최소화.
- **개발 시간 조정**하여 워라벨을 고려한 효율적인 협업 방식 도입.

</details>

---

<br>

### ♻️ **추후 리팩토링 계획**
<br>

추후 리팩토링 계획은 시스템의 성능과 유지보수성을 개선하기 위한 방향성을 제시하며,<br>
현재 시스템에서 발생할 수 있는 문제점들을 개선하기 위한 작업을 포함합니다.

---

<details>
<summary>🔐 인증 및 인가</summary>

- **Blacklist 관리**  
  - AccessToken 및 RefreshToken의 블랙리스트 관리 필요.
  - 보안 강화를 위해 **토큰 무효화 로직 개선**.
  
- **Token Resolver & Context 관리**  
  - 현재 인증 정보의 효율적인 관리 필요.
  - **인증 컨텍스트 관리 로직 개선**으로 인증 흐름 최적화.

</details>

---

<details>
<summary>🚏 허브 (Hub) 개선</summary>

- **캐시 관리 최적화**  
  - 주문 및 배송 관련 데이터의 **캐시 적중률을 높여 성능 개선**.
  - 불필요한 캐시 삭제 및 재사용 로직 정비.

</details>

---

<details>
<summary>🏬 업체 및 상품</summary>

- **다중 상품 주문 대응**  
  - 기존 **단일 상품 주문 → 다중 상품 주문**으로 변경 필요.
  - API 응답 구조 변경 및 **멀티 주문 처리 로직 추가**.

- **유효성 검증 강화**  
  - 주문 시 **데이터 유효성 검사 로직 개선**.
  - 필수 입력값 누락 방지 및 예외 처리 로직 추가.

</details>

---

<details>
<summary>📦 주문 및 배송</summary>

- **주문 처리 방식 개선**  
  - 기존 단일 상품 주문에서 **다중 상품 주문이 가능하도록 로직 리팩토링**.
  - 주문별 개별 배송이 아닌 **묶음 배송 기능 추가**.

</details>

---

<details>
<summary>🤖 AI 활용 Slack 알림</summary>

- **배송 담당자 자동 알림 기능 추가**  
  - **매일 아침 6시에 배송 담당자에게 자동 알림 전송**.
  - AI 분석을 활용하여 **우선순위 알림** 제공 가능.

- **알림 형식 리팩토링**  
  - 동일한 템플릿으로 일관된 형식의 알림 발송하도록 수정.

</details>

---

<details>
<summary>👤 사용자 및 배송 담당자 관리</summary>

- **사용자 권한 및 역할 분리**  
  - 일반 사용자 / 배송 담당자 / 관리자 권한 별도 관리.
  - **RBAC(Role-Based Access Control) 적용**으로 보안 강화.

- **배송 담당자 관리 시스템 개선**  
  - 배송 담당자 배정 최적화 및 **배정 로직 개선**.

</details>

---

<br>

