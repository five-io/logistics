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

| 분류             | 상세                     |
|----------------|---------------------------|
| **IDE**        | IntelliJ                    |
| **Language**   | Java 17                     |
| **Framework**  | Spring Boot 3.3.1, Spring Cloud 2003.0.3 |
| **Repository** | PostgreSQL               	            |
| **ORM** | JPA (Jakarta Persistence API)         	    |
| **Query Builder** | QueryDSL 5.0.0             	    |
| **Build Tool** | Gradle 8.8                   	    |
| **Version Control** | Git, GitHub              	    |
| **Containerization** | Docker           		    |
| **API Documentation** | Swagger        		    |
| **External API** | Gemini API, Slack API, KakaoMobility, Google API |
| **Testing Tool** | IntelliJ HTTPClient |

<br>

---

## 🛠 실행 방법
<br>

### 1️⃣ docker-compose 파일 설정
`docker-compose.yml` 파일을 프로젝트 루트 디렉토리에 생성하고, 아래 형식에 맞춰 환경 변수를 설정합니다.

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
http://localhost:19090/swagger-ui.html
```

<br>

---

## 설계 산출물
<br>

설계 산출물은 시스템의 핵심 구조와 흐름을 정리하고 문서화한 결과물입니다.<br> 
이를 통해 개발 팀은 시스템을 더 명확하게 이해하고, 향후 확장 및 유지보수를 용이하게 할 수 있습니다.
<br>

### S.A Links

<details>
  <summary>에그리거트 구조도</summary>
<image>[https://github.com/user-attachments/assets/dba70451-994f-4a68-a24b-7c898307d713](https://github-production-user-asset-6210df.s3.amazonaws.com/64643668/426111345-dba70451-994f-4a68-a24b-7c898307d713.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAVCODYLSA53PQK4ZA%2F20250324%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250324T140436Z&X-Amz-Expires=300&X-Amz-Signature=7364bea9ad692304645ff3cbef6d414e1953c9d842ded4f72932d8cd8bf4e888&X-Amz-SignedHeaders=host)</image>

</details>

<details>
  <summary>도메인 다이어그램 & 역할분담</summary>
 ![a2](https://github.com/user-attachments/assets/522b919b-00e3-4c5c-8717-9bbea4b0dbbc)


|   담당자   |  신예나  |  문고은  |  김민경  |  이태훈  |  양수영  |
|:----------:|:--------:|:--------:|:--------:|:--------:|:--------:|
|    역할    | 업체 및 상품 | 배송관리, 주문 | 허브, 배송경로, 인증/인가 | 사용자 및 담당자 관리 | 슬랙, AI |

</details>

<details>
  <summary>ERD 명세서</summary>
 ![a3](https://github.com/user-attachments/assets/e469e4ec-5bab-4ef8-819a-96016d7a57bc)
</details>

<details>
  <summary>테이블 명세서</summary>
	
<h3>1. 사용자 (p_users)</h3>

필드 이름 | 데이터 타입 | 설명 | 제약사항
-- | -- | -- | --
user_id | BIGINT | 사용자 ID | NotNull, PK
slack_id | VARCHAR(255) | 슬랙 ID | NotNull
role | ENUM | 사용자 역할<br/>(master,<br/> delivery_manager,<br/> company_manager,<br/> customer) | NotNull
username | VARCHAR(100) | 유저 이름 | NotNull
password | VARCHAR(100) | 사용자 비밀번호 | NotNull
hub_id | UUID | 허브 ID | NotNull

<h3>2. 배송담당자 (p_delivery_managers)</h3>

필드 이름 | 데이터 타입 | 설명 | 제약사항
-- | -- | -- | --
user_id | BIGINT | 사용자 ID | FK
managers_type | ENUM | 배송담당자 타입<br/> (허브, 업체) | NotNull
sequence | VARCHAR(100) | 배송순번 | 
is_working | BOOLEAN | 현재상태 | NotNull


<h3>3. 슬랙 (p_slacks)</h3>

필드 이름 | 데이터 타입 | 설명 | 제약사항
-- | -- | -- | -- 
slack_id | UUID | 슬랙 ID | NotNull, PK
order_id | UUID | 주문 ID | NotNull, Unique
depart_hub_name | VARCHAR(100) | 출발 허브명 | NotNull
transit_point | VARCHAR(100) | 경유지 | NotNull
arrive_hub_name | VARCHAR(100) | 도착 허브명 | NotNull
delivery_address | VARCHAR(255) | 배송지 | NotNull
recipient_name | VARCHAR(100) | 수령인 | NotNull
recipient_slack_id | VARCHAR(100) | 수신 슬랙 ID | NotNull
company_delivery_manager | VARCHAR(100) | 업체 배송 담당자 | NotNull
product_name | VARCHAR(100) | 상품명 | NotNull
product_quantity | BIGINT | 상품 수량 | NotNull
request_notes | VARCHAR(255) | 요청 사항 | NotNull
message | TEXT | 메시지 |  
send_status | ENUM | 발신 상태 | NotNull

<h3>4. 업체 (p_companys)</h3>

필드 이름 | 데이터 타입 | 설명 | 제약사항
-- | -- | -- | --
company_id | UUID | 업체 ID | NotNull, PK
hub_id | UUID | 허브 ID | NotNull
company_name | VARCHAR(255) | 업체 이름 | NotNull
company_type | ENUM | 업체 종류<br/> (생산, 수령) | NotNull
company_address | VARCHAR(255) | 업체 주소 | NotNull

<h3>5. 상품 (p_products)</h3>

필드 이름 | 데이터 타입 | 설명 | 제약사항
-- | -- | -- | --
product_id | UUID | 상품 ID | NotNull, PK
hub_id | UUID | 허브 ID | NotNull
company_id | UUID | 업체 ID | NotNull
product_name | VARCHAR(255) | 상품 이름 | NotNull
product_detail | VARCHAR(255) | 상품 설명 |  
product_price | DOUBLE | 상품가격 | NotNull
product_type | ENUM | 상품타입 | NotNull
stock_id | UUID | 재고 ID | NotNull, FK

<h3>6. 재고관리 (p_stocks)</h3>

필드 이름 | 데이터 타입 | 설명 | 제약사항
-- | -- | -- | --
stock_id | UUID | 재고 ID | NotNull, PK
quantity | BIGINT | 수량 | NotNull

<h3>7. 배송경로 (p_delivery_routes)</h3>

필드 이름 | 데이터 타입 | 설명 | 제약사항
-- | -- | -- | --
delivery_routes_id | UUID | 배송경로 ID | NotNull, PK
delivery_id | UUID | 배송 ID | NotNull, FK
delivery_manager_id | BIGINT | 배송담당자 ID | NotNull, Unique
sequence | INT | 시퀀스<br/> (배송 경로 상 허브의 순번) |
depart_hub_id | UUID | 출발허브 ID |
arrive_hub_id | UUID | 도착허브 ID |
estimated_distance_km | DOUBLE | 예상 거리 |
estimated_duration_min | BIGINT | 예상 소요시간 |
actual_distance_km | DOUBLE | 실제 거리 |
actual_duration_min | BIGINT | 실제 소요시간 |
delivery_status | ENUM | 현재 상태 | NotNull

<h3>8. 이동정보관리 (p_locations)</h3>

필드 이름 | 데이터 타입 | 설명 | 제약사항
-- | -- | -- | --
loacation_id | UUID | 이동정보 ID | NotNull, PK
depart_id | UUID | 출발허브 ID  | NotNull
arrive_id | UUID | 도착허브 ID  | NotNull
taken_time | BIGINT | 소요시간
distance | BIGINT | 이동거리

<h3>9. 허브 (p_hubs)</h3>

필드 이름 | 데이터 타입 | 설명 | 제약사항
-- | -- | -- | --
hub_id | UUID | 이동정보 ID | NotNull, PK
hub_name | VARCHAR(100) | 출발허브 ID | NotNull, Unique
address | VARCHAR(100) | 도착허브 ID
latitude | DOUBLE | 위도 | NotNull
longitude | DOUBLE | 경도 | NotNull

<h3>10. 주문 (p_orders)</h3>

필드 이름 | 데이터 타입 | 설명 | 제약사항
-- | -- | -- | --
order_id | UUID | 주문 ID | NotNull, PK
requester_company_id | UUID | 요청업체 ID | NotNull
receiver_company_id | UUID | 수령업체 ID | NotNull
product_id | UUID | 상품 ID | NotNull
quantity | BIGINT | 수량  | NotNull
request_notes | VARCHAR(100) | 요청사항 |
total_price | DOUBLE | 총 금액  | NotNull

<h3>11. 배송 (p_deliveries)</h3>

필드 이름 | 데이터 타입 | 설명 | 제약사항
-- | -- | -- | --
delivery_id | UUID | 배송 ID | NotNull, PK
order_id | UUID | 주문 ID | NotNull
depart_hub_id | UUID | 출발허브 ID | NotNull
arrive_hub_id | UUID | 목적지허브 ID | NotNull
delivery_status | VARCHAR(255) | 배송상태 | NotNull
delivery_address | VARCHAR(255) | 배송지주소 | NotNull
recipient_name | VARCHAR(255) | 수령인 | NotNull
recipient_slack_id | VARCHAR(255) | 수신 슬랙 ID | NotNull
company_delivery_manager_id | BIGINT | 업체배송 담당자 | NotNull

</details>

<details>
  <summary>API 명세서</summary>

<h3>사용자 API</h3>

| 기능 | Method | URL | 접근권한 | Request | Response  |
|--|--|--|----|--|--|
| 회원가입       | POST   | /api/users/signUp        | 마스터관리자, <br/>허브관리자, <br/>배송담당자, <br/>업체관리자     | `{` <br/>`"username":"test0311",` <br/>`"password":"Test0311@", `<br/>`"name":"tester", `<br/>`"slackId":"123445", `<br/>`"role":"ROLE_MASTER", `<br/>`"hubId":"3fa85f64-5717-4562-b3fc-2c963f66afa6"`<br/>`}` | `"회원가입 완료"` |
| 로그인        | POST   | /api/users/signIn           | 마스터관리자, <br/>허브관리자, <br/>배송담당자, <br/>업체관리자     | `{`<br/>` "username":"test4", `<br/>`"password":"Abcd1234@" `<br/>`}` | `jwt`     |
| 로그아웃      | POST   | /api/users/logout          | 마스터관리자, <br/>허브관리자, <br/>배송담당자, <br/>업체관리자     | 없음                                                                                                                                                                                                                   | `"로그아웃 완료"` |
| 회원정보 수정  | PATCH  | /api/users/{user_id}       | 마스터관리자, <br/>허브관리자, <br/>배송담당자, <br/>업체관리자     | `{`<br/>` "username":"test0311", `<br/>`"password":"", `<br/>`"slackId":"", `<br/>`"hubId":"" `<br/>`}`                                                                                                          | `"수정 완료"` |
| 회원 탈퇴     | DELETE | /api/users/resign/{user_id}       | 마스터관리자, <br/>허브관리자, <br/>배송담당자, <br/>업체관리자     |`{ `<br/>`"slackId": "" `<br/>`}`                                                                                                                                                                                | `"회원 탈퇴 완료"` |
| 회원정보 조회  | GET    | /api/users/{user_id}       | 마스터관리자, <br/>허브관리자, <br/>배송담당자, <br/>업체관리자     | `jwt`                                                                                                                                                                                                                | `{ `<br/>`"username": "test4", `<br/>`"userId": 1, `<br/>`"slackId": "010-4234-5678", `<br/>`"hubId": "d0913844-3fe3-4527-bde1-c4d588b946f4", `<br/>`"role": "ROLE_DELIVERY_MANAGER", `<br/>`"createDate": "2025-03-24T11:34:43.432353" `<br/>`}` |
| 회원정보 정렬 조회  | GET    | /api/users?page=1<br>&size=1<br>&sortBy=createAt<br>&isAsc=true            | 마스터관리자                                         | 없음                                                                                                                                                                                                                   | 조회된 회원 정보 목록 |

---

<h3>배송담당자 API</h3>

기능 | Method | URL | 접근권한 | Request | Response
-- | -- | -- | -- | -- | --
배송담당자 지정 | POST | /api/users/delivery-managers | 허브관리자, <br/>배송담당자 | {<br/>"managersType":"Hub" <br/>} | {<br/>"is_working": "0",  <br/>"user_id": "배송담당자 ID", <br/>"sequence":"",<br/>"is_working": },<br/>"hub_id":""}
배송담당자 조회 | GET | /api/users/delivery-managers | 허브관리자,<br/>업체관리자,<br/>배송담당자 | jwt | {  <br/>"hubId": "d0913844-3fe3-4527-bde1-c4d588b946f4",  <br/>"sequence": null,  <br/>"isWorking": false,  <br/>"managerType": "Company"<br/>}
배송담당자 수정 | PATCH | /api/users/delivery-managers | 허브관리자,<br/>업체관리자,<br/>배송담당자 | {<br/>"managersType":"Hub" <br/>} | 수정된내용
배송담당자 전체조회 | GET | /api/users/page=1<br/>&size=8<br/>&sortBy=createAt<br/>&isAsc=true | 허브관리자,<br/>업체관리자,<br/>배송담당자 | { <br/>"hub_id"=,"managers_<br/>type"=,"sequence":"",<br/>"is_working": <br/>} | { <br/>"hub_id"=,<br/>"managers_type"=,<br/>"sequence":"",<br/>"is_working": <br/>}
배송담당자 삭제 | DELETE | /api/users/delivery-managers | 허브관리자,<br/>업체담당자 | {<br/>"hub_id":" ", <br/>"user_id":" ", <br/>} | 삭제 내용

<h3>슬랙 API</h3>

기능 | Method | URL | 접근권한 | Request | Response
-- | -- | -- | -- | -- | --
슬랙 등록 | POST | /api/slacks | 마스터관리자, <br/>허브관리자, <br/>배송담당자, <br/>업체담당자 | {  <br/>"order-id": "12345678-9e53-48c4-bbc7-2425ec691888",  <br/>"depart-hub-name":"수원 허브",  <br/>"transit-point":"천안 허브",  <br/>"arrive-hub-name":"대전 허브",  <br/>"delivery-address":"대전광역시 중구",  <br/>"recipient-name": "문고은",  <br/>"recipient-slack-id":"01012345678",  <br/>"company-delivery-manager":"김민경",  <br/>"product-name": "랍스터 3kg",  <br/>"product-quantity": 3,  <br/>"request-notes": "긴급! 빠른배송 부탁드립니다."<br/>} | 작성된 슬랙메시지 번호
슬랙 조회 | GET | /api/slacks/page={page}<br/>&size={size}<br/>&sort={sort}<br/>&orderby={orderby} | 마스터관리자 | {<br/>"page": "0", <br/>"size": "10", <br/>"sort": "CREATED", <br/>"orderby": "DESC" <br/>} | 슬랙(단건) 조회
슬랙 검색 | GET | /api/slacks/search?<br/>query={query}<br/>&page={page}<br/>&size={size}<br/>&sort={sort}<br/>&orderby={orderby} | 마스터관리자 | {<br/>"page": "0",<br/> "size": "10", <br/>"sort": "CREATED", <br/>"orderby": "DESC" <br/>"transit-point": "천안 허브",<br/>"company-delivery-manager": "김민경"<br/>} | 슬랙메시지 검색 리스트
슬랙 상태 변경 | PUT | /api/slacks/status | 마스터관리자 | { <br/> "order-id": "12345678-9e53-48c4-bbc7-2425ec691710",  <br/>"send-status": "SEND_FAILURE<br/>} | 수정된 슬랙메시지 정보
슬랙 삭제 | DELETE | /api/slacks/{id} | 마스터관리자 |   | 삭제된 슬랙메시지 번호


<h3>허브 API</h3>

기능 | Method | URL | 접근권한 | request | response
-- | -- | -- | -- | -- | --
허브 등록 | POST | /api/hubs | 마스터관리자 | {<br/>"hub_name":"화성시 센터",<br/>"address":"경기도 화성시 21",<br/>"latitude”:"37.0895",<br/>"longitude":"37.9102"<br/>} | 작성된 허브 정보
허브 단 건 조회 | GET | /api/hubs | 마스터관리자,<br/>허브관리자, <br/>업체담당자, <br/>배송담당자 | {<br/>"hub_id": 1<br/>} | 허브 단 건 내용
허브 수정 | PATCH | /api/hubs/{id} | 마스터관리자 | {<br/>“hub_name”:“화성물류 센터“<br/>} | 수정된 허브 내용
허브 전체 조회 | GET | /api/hubs/search | 마스터관리자,<br/>허브관리자, <br/>업체담당자, <br/>배송담당자 | {<br/>“searchText”:“화성”,<br/>“sort”:“createdAt,updatedAt”,<br/>“page”:1,<br/>“size”:10<br/>} | 허브 리스트
허브 삭제 | DELETE | /api/hubs/{id} | 마스터관리자 | {<br/>"hub_id": 1<br/>} | 허브 삭제 결과

<h3>이동정보관리 API</h3>

기능 | Method | URL | 접근권한 | request | response
-- | -- | -- | -- | -- | --
이동정보 생성 | POST | /api/routes | 마스터관리자 | {<br/>“depart_id”:2,<br/>“taken_time”:600000,<br/>“distance”:10<br/>} | 작성된 이동정보 내용
이동정보 단 건 조회 | GET | /api/routes/{id} | 마스터관리자, <br/>업체중앙담당자, <br/>배송팀장 | {<br/>“location_id”:3<br/>} | 수령인 이동정보 내용
이동정보 수정 | PATCH | /api/routes/{id} | 마스터관리자, <br/>업체중앙담당자 | {<br/>“taken_time”:900000<br/>} | 수정된 이동정보 내용
이동정보 삭제 | DELETE | /api/routes/{id} | 마스터관리자 | {} | 이동정보 삭제 결과
이동정보 전체 조회 | GET | /api/routes/search | 마스터관리자, <br/>업체중앙담당자, <br/>배송팀장 | {<br/>“depart_id”:2,<br/>“page”:1,<br/>“size”:10<br/>} | 이동정보 전체 내용

<h3>주문 API</h3>

기능 | Method | URL | 접근권한 | request | response
-- | -- | -- | -- | -- | --
주문 등록 | POST | /api/orders | 마스터관리자, <br/>허브관리자, <br/>업체중앙담당자, <br/>배송팀장 | { <br/>"receiver-company-id": "수령 업체 ID",  <br/>"product-id": "상품 ID",  <br/>"quantity": 개수,  <br/>"request-notes": "요청 사항",  <br/>"recipient-name": "수령인 이름",  <br/>"recipient-slack-id": "수령인 슬랙 ID"<br/>} | {<br/>"orderId": "생성된 주문 ID"<br/>}
주문 단 건 조회 | GET | /api/orders/{id} | 마스터관리자, <br/>허브관리자, <br/>업체중앙담당자, <br/>배송팀장 | id": “주문ID” | {<br/>"order-id": "주문 ID",  <br/>"requester-company-id": "공급 업체 ID",  <br/>"receiver-company-id": "수령 업체 ID",  <br/>"product-id": "상품 ID",  <br/>"quantity": 개수,  <br/>"request-notes": "요청 사항"<br/>}
주문 수정 | PATCH | /api/orders/{id} | 마스터관리자, <br/>허브관리자, <br/>업체중앙담당자, <br/>배송팀장 | { <br/>"quantity": 개수,  <br/>"request-notes": "요청사항"<br>} | {<br/>"order-id": "주문 ID",  <br/>"requester-company-id": "공급 업체 ID",  <br/>"receiver-company-id": "수령 업체 ID",  <br/>"product-id": "상품 ID",  <br/>"quantity": 개수,  <br/>"request-notes": "요청 사항"<br/>}
주문 삭제 | DELETE | /api/orders/{id} | 마스터관리자, <br/>허브관리자, <br/>업체중앙담당자, <br/>배송팀장 | "id": “주문ID” |  
주문 전체 조회(검색) | GET | /api/orders?<br/>page=1&size=10<br/>&sortBy=createdAt<br/>&isAsc=true | 마스터관리자, <br/>허브관리자, <br/>업체중앙담당자, <br/>배송팀장 | {  <br/>"requester-company-id": "공급 업체 ID",  <br/>"receiver-company-id": "수령 업체 ID",  <br/>"product-id": "상품 ID"<br/>} | {<br/>"content": [    <br/>{      <br/>"order-id": "주문 ID",      <br/>"requester-company-id": "공급 업체 ID",      <br/>"receiver-company-id": "수령 업체 ID",      <br/>"product-id": "상품 ID",      <br/>"quantity": 개수,      <br/>"request-notes": "요청 사항"    <br/>}  <br/>],<br/>  ...<br/>}
주문 취소 | DELETE | /api/orders/{id}/cancel | 마스터관리자, 허브관리자 | "id": “주문ID” |  

<h3>배송 API</h3>

기능 | Method | URL | 접근권한 | request | response
-- | -- | -- | -- | -- | --
배송 생성 | POST | /api/deliveries | 마스터관리자 | {  <br/>"order-id": "주문ID",  <br/>"depart-hub-id": "출발허브ID",  <br/>"arrive-hub-id": "도착허브ID",  <br/>"delivery-address": "배송지",  <br/>"recipient-name": "수령인이름",  <br/>"recipient-slack-id": "수령인슬랙ID",  <br/>"quantity": 개수,  <br/>"request-notes": "요청사항",  <br/>"product-name": "상품명"<br/>} |  
배송 단건 조회 | GET | /api/deliveries/{id} | 마스터관리자, <br/>허브관리자, <br/>업체중앙담당자, <br/>배송팀장 | "id": “배송ID” | {<br/>"deliveryId": "배송ID",  <br/>"orderId": "주문ID",  <br/>"deliveryStatus": "배송상태",  <br/>"departHubId": "출발허브ID",  <br/>"arriveHubId": "도착허브ID",  <br/>"deliveryAddress": "배송지",  <br/>"companyDeliveryManagerId": "업체배송담당자ID",  <br/>"recipient": "수령인"<br/>}
배송 전체 조회(검색) | GET | /api/deliveries?<br/>page=1&size=10<br/>&sortBy=createdAt<br/>&isAsc=true | 마스터관리자, <br/>허브관리자, <br/>업체중앙담당자, <br/>배송팀장 | {  <br/>“delivery-status”: "배송상태",  <br/>“depart-hub-id”: "출발허브ID",  <br/>“arrive-hub-id”: "목적지허브ID",  <br/>“delivery-address”: 배송지주소,  <br/>“recipient-name”: "수령인이름",  <br/>“recipient-slack-id”: "수령인슬랙ID",  <br/>“company-delivery-manager”: "업체배송담당자ID",  <br/>“order-id”: "주문ID"<br/>} | {<br/>  ...  <br/>"content": [   <br/> {      <br/>"deliveryId": "배송ID",      <br/>"orderId": "주문ID",      <br/>"deliveryStatus": "배송상태",      <br/>"departHubId": "출발허브ID",      <br/>"arriveHubId": "도착허브ID",      <br/>"deliveryAddress": "배송지",      <br/>"companyDeliveryManagerId": "업체배송담당자ID",      <br/>"recipient": "수령인"    <br/>}  ],<br/>  ...<br/>}
배송 삭제 | DELETE | /api/deliveries/{id} | 마스터관리자, <br/>허브관리자, <br/>업체중앙담당자, <br/>배송팀장 | "id": “배송ID” |  
배송 상태 변경 | PATCH | /api/deliveries/{id}/status?<br/>deliveryStatus= | 마스터관리자,<br/> 허브관리자,<br/> 배송팀장 | "id": “배송ID”“delivery_status”:배송상태 |  
배송 상태 조회 | GET | /api/deliveries/{id}/status | 마스터관리자, <br/>허브관리자, <br/>배송팀장 | "id": “배송ID” | 배송상태
배송 취소 | DELETE | /api/deliveries/{id}/cancel | 마스터관리자, <br/>허브관리자 | "id": “배송ID”<br/>”user-id”: “사용자ID” |  

<h3>배송 경로 API</h3>

기능 | Method | URL | 접근권한 | request | response
-- | -- | -- | -- | -- | --
배송 경로 조회(검색) | GET | /api/delivery-routes/{deliveryId}?<br/>page=1<br/>&size=8<br/>&sortBy=createAt<br/>&isAsc=true | 마스터관리자, <br/>허브관리자, <br/>업체중앙담당자, <br/>배송팀장 | {<br/>“delivery-status”: 현재상태,<br/>“delivery-manager”: 배송담당자ID<br/>} | 배송 경로 관련 경로 리스트
배송 경로 상세 | GET | /api/delivery-routes/{deliveryRouteId} | 마스터관리자, <br/>허브관리자, <br/>업체중앙담당자, <br/>배송팀장 | {} | 배송 경로 상세 내용
배송 경로 생성 | POST | /api/delivery-routes | 마스터관리자, <br/>허브관리자, <br/>업체중앙담당자, <br/>배송팀장 | {<br/>“deliveryResoponseDto”:“배송정보“,<br/>”deliveryManager”:“11”,<br/>“routeResponseDto”:“허브이동정보경로“<br/>} | 작성된 배송 경로 내용
배송 경로 수정 | PATCH | /api/delivery-routes/{deliveryRouteId} | 마스터관리자, <br/>허브관리자, <br/>업체중앙담당자, <br/>배송팀장 | {<br/>“actual_distance_km”: 실제거리,<br/>“actual_duration_min”: 실제소요시간,<br/>“delivery-manager”: 배송담당자 ID<br/>} | 수정된 배송 경로 정보
배송 경로 삭제 | DELETE | /api/delivery-routes/{deliveryRouteId} | 마스터관리자, <br/>허브관리자, <br/>업체중앙담당자, <br/>배송팀장 | {} | 배송 경로 삭제 결과
배송 경로 상태 변경 | PUT | /api/delivery-routes/ | 마스터관리자, <br/>허브관리자, <br/>업체중앙담당자, <br/>배송팀장 | {<br/>“deliveryId": "1d2edfdssdfd",<br/>"arrivedHubId":"sdfhubId"<br/>} |  

<h3>AI API</h3>

기능 | Method | URL | 접근권한 | Request | Response
-- | -- | -- | -- | -- | --
AI 등록 | POST | /api/ais | 배송담당자 | { <br/>"order-id": "order-id",<br/>"depart-id":"출발허브 ID",<br/>"transitPoint":"경유지",<br/>"arrive-id":"도착허브 ID",<br/>"delivery_status":"배송시작",<br/>"delivery_address":"배송주소",<br/>"recipient-id": "수령인 ID",<br/>"recipient_slack_id":"수령인 slack ID",<br/>"company_delivery_manager":"배송 담당자",<br/>"product-name": "소고기 3kg",<br/>"product-quantity": "수량",<br/>"request-notes": "요청사항" <br/>} | 생성된 AI 메세지와 AI ID


<h3>업체 API</h3>

기능 | Method | URL | 접근권한 | Request | Response
-- | -- | -- | -- | -- | --
업체등록 | POST | /api/companys | 마스터관리자,<br/> 허브관리자 | {<br/> "company_name": "업체명",<br/> "company_type": "업체타입",<br/> "hub_id": "허브id", <br/>"company_adress": "업체주소"<br/> } | 작성된 업체 정보
업체 검색 | GET | /api/companys/search | 마스터관리자,<br/> 허브관리자,<br/> 배송담당자 |{<br/> "query": "검색키워드",<br/> "page": "페이지번호",<br/> "size": "데이터수",<br/> "sortBy": "정렬기준",<br/> "isAsc": "정렬순서"<br/> } | 업체검색 리스트
업체 단건 조회 | GET | /api/companys/{companyId} | 마스터관리자,<br/> 허브관리자,<br/> 배송담당자 |   | 업체(단건) 조회
업체 수정 | PATCH | /api/companys/{companyId} | 마스터관리자,<br/> 허브관리자 | {<br/> "companyId": "업체id",<br/>company_type": "업체타입"<br/> } | 수정된 업체 내용
업체 삭제 | DELETE | /api/companys/{companyId} | 마스터관리자,<br/> 허브관리자 |   |
주문상품업체조회 | GET | /api/companys/{companyId}/products | 마스터관리자,<br/> 허브관리자,<br/> 업체담당자 |   | {<br/>”arriveHubId”: “허브ID”,<br/>"deliveryAddress": "배송주소"<br/>}
상품ID조회 | GET | /api/companys/{companyId}/products/hub-id | 마스터관리자,<br/> 허브관리자,<br/> 업체담당자 |   | “hubID”

<h3>상품 API</h3>

기능 | Method | URL | 접근권한 | Request | Response
-- | -- | -- | -- | -- | --
상품등록 | POST | /api/products | 마스터관리자,<br/> 허브관리자,<br/> 업체담당자 | { <br/>"hub_id": "허브ID",<br/> "product_name": "상품명",<br/> "product_detail": "상품내용",<br/> "company_id": "업체ID" "product_price": 3000 <br/>} | 작성된 상품 정보
상품 검색 | GET | /api/products/search | 마스터관리자,<br/> 허브관리자,<br/> 배송담당자,<br/> 업체담당자 | {<br/> "query": "검색키워드",<br/> "page": "페이지번호",<br/> "size": "데이터수",<br/> "sortBy": "정렬기준",<br/> "isAsc": "정렬순서"<br/> } | 상품검색 리스트
상품 단건 조회 | GET | /api/products/{productId} | 마스터관리자,<br/> 허브관리자,<br/> 배송담당자,<br/> 업체담당자 |   | 상품(단건) 조회
상품 수정 | PATCH | /api/products/{productId} | 마스터관리자,<br/> 허브관리자,<br/> 업체담당자 | {<br/> "productId": "상품ID",<br/>"product_name": "상품명",<br/> } | 수정된 상품 내용
상품 삭제 | DELETE | /api/products/{productId} | 마스터관리자,<br/> 허브관리자 |   |
주문상품조회 및 재고변경 | get | /api/products/order?<br/>productId={상품id}<br/>&receiverCompanyId={업체id}<br/>&quantity=100 | 마스터관리자,<br/> 허브관리자,<br/> 업체담당자 |   | {<br/>"deliveryAddress": "배송주소",<br/>"requesterCompanyId": "상품업체ID",<br/> "departHubId": "출발허브ID",<br/>"arriveHubId": "도착허브ID","productName": "상품명",<br/>"productPrice": 3000,"productType": ON_SALE,<br/>"isOrderable": true<br/>}
주문취소시 재고 수량 롤백 | put | /api/products/{producId}/rollback?quantity=300 | 마스터관리자,<br/> 허브관리자,<br/> 업체담당자 |   |

<h3>재고 API</h3>

기능 | Method | URL | 접근권한 | Request | Response
-- | -- | -- | -- | -- | --
재고 등록 | POST | 상품등록과 합침 | 마스터관리자, 허브관리자, 업체담당자 |   |  
재고 조회 | GET | /api/stocks/{stokId} | 마스터관리자, 허브관리자, 업체담당자, 배송담당자 | { "product_id": 3 } | 재고 단 건 내용
재고 수정 | PUT | /api/stocks/{stokId} | 마스터관리자, 허브관리자, 업체담당자 | { "quantity": 50 } | 수정된 재고 내용
재고 삭제 | DELETE | /api/stocks/{stokId} | 마스터관리자, 허브관리자 |   |  
재고 전체 조회 | GET | /api/stocks | 마스터관리자, 허브관리자, 업체담당자, 배송담당자 |   | 재고 전체 내용


</details>

<details>
  <summary>인프라 설계서</summary>
<h3>초안</h3>
![a4](https://github.com/user-attachments/assets/c52245ec-ba0e-484f-8a0c-01d571474958)
<h3>리팩토링</h3>
![a5](https://github.com/user-attachments/assets/4a44c621-d20b-4cb7-bea0-face44caa6e9)

</details>

<details>
  <summary>컨벤션</summary>

***

<h2>GIT 컨벤션</h2>
<h3>이슈</h3>
 <img src="https://private-user-images.githubusercontent.com/64643668/421723637-fd8a0d72-c7bd-4821-abea-0eb324adb7c5.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NDIzNjY5MjQsIm5iZiI6MTc0MjM2NjYyNCwicGF0aCI6Ii82NDY0MzY2OC80MjE3MjM2MzctZmQ4YTBkNzItYzdiZC00ODIxLWFiZWEtMGViMzI0YWRiN2M1LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNTAzMTklMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwMzE5VDA2NDM0NFomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTc0ZjdjMDg3MDRiNjNjNDQwODkwODNlZTM5ZGFlNDdmYTU4MmFkOWQxYzE5NmQyMzc5YThlOTEwNTMzNjA2MDEmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.I98mLI2OrqQZbLwM4VAwTtAa1Bp6-A5ySzAEZcHCE1g" alt="image1">

 <img src="https://private-user-images.githubusercontent.com/64643668/421723705-b9819399-1852-49e5-acfe-b2c59a946bf5.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NDIzNjY5MjQsIm5iZiI6MTc0MjM2NjYyNCwicGF0aCI6Ii82NDY0MzY2OC80MjE3MjM3MDUtYjk4MTkzOTktMTg1Mi00OWU1LWFjZmUtYjJjNTlhOTQ2YmY1LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNTAzMTklMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwMzE5VDA2NDM0NFomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTcwOWQyMDM2ZGRiYzA5MTI3NmMxNTkyZjU2NmJiOTIxYzcyZmVkYjMxYmFmYWU0ZDkwOTQ5NGQ4NWE1OTA4N2YmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.prd6YYAQ_aPB2Mg-zEx22XMmeRBRAS6VKNWf5NcasTY" alt="image2">
<h3>Feature</h3>
 <img src="https://private-user-images.githubusercontent.com/64643668/421723754-65e1d623-35a6-4ba5-82e5-7233bbc9f30f.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NDIzNjY5MjQsIm5iZiI6MTc0MjM2NjYyNCwicGF0aCI6Ii82NDY0MzY2OC80MjE3MjM3NTQtNjVlMWQ2MjMtMzVhNi00YmE1LTgyZTUtNzIzM2JiYzlmMzBmLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNTAzMTklMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwMzE5VDA2NDM0NFomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPThjYjk4NWVlMjkwNDNmMWRiYzJiOTgyZDM1MmM3M2YzYjlhODMzN2UzMjAyYzdkMmQxNWYzOTQxZjM5NDQxZmEmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.eqhSlQAMZLgrcl5MdJv9w3jphzzWr8FOx7yCBI0S8IQ" alt="image3">
<h3>Bug</h3>
 <img src="https://private-user-images.githubusercontent.com/64643668/421723779-e07648ea-1aa3-4809-ad91-10f761fbf4ca.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NDIzNjY5MjQsIm5iZiI6MTc0MjM2NjYyNCwicGF0aCI6Ii82NDY0MzY2OC80MjE3MjM3NzktZTA3NjQ4ZWEtMWFhMy00ODA5LWFkOTEtMTBmNzYxZmJmNGNhLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNTAzMTklMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwMzE5VDA2NDM0NFomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTVkZDIxY2VjOTM0NTEzY2YwNjIzN2RlYmM4YjgwYmYxYTZlYmE1MzE1ZDRiMjJmN2VmNzIzODYyODQxZjkyY2EmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.zH6hQR7er0sAE2y0Iw6Zp9sNFtyvUK3s4EV3RMpA020" alt="image4">
<h3>PR</h3>
 <img src="https://private-user-images.githubusercontent.com/64643668/421723816-8766fa69-f8d6-49c5-8982-6feee6793dd1.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NDIzNjY5MjQsIm5iZiI6MTc0MjM2NjYyNCwicGF0aCI6Ii82NDY0MzY2OC80MjE3MjM4MTYtODc2NmZhNjktZjhkNi00OWM1LTg5ODItNmZlZWU2NzkzZGQxLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNTAzMTklMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwMzE5VDA2NDM0NFomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPWU4MDIzMTU4M2FmMmY4ZWIzNjY2MWRmZTIxMjUzNGFmODlhODMwODZmMjVhMGQwYTZmNjUzZTQzZjUwYjBjZDImWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.60ugbGmbhz4h-m_nlRgtWmb40mwNBMjhcPSASh0qGS0" alt="image5">

***

<h2>코드 컨벤션</h2>

<pre><code>
1. release : 배포용 (main)
2. develop: develop 브랜치 (개발)
3. feature: 기능 개발 브랜치 (feat/도메인명-기능) 
</code></pre>

구분 | JAVA
-- | --
PascalCase | 클래스,Exception
camelCase | 변수,함수,메소드
UPPER_CASE | 상수변수

<h3>mapper 사용시</h3>
<pre><code>
// ex)
public class slacksMapper {

	public static Slacks slacksCreateRequestDtoToEntity(
		SlacksCreateRequestDto slacksCreateRequestDto) {
		// Dto를 Entity로 변환시 // 어떤 Dto인지 써주기
	}

	public static SlacksCreateResponseDto entityToCreateResponseDto(Slacks slacks) {
		// Entity를 Dto로 변환시
	}
</code></pre>

---

### ERD & 데이터베이스 

Spring Boot Entity 생성 시
@Table(name = "명명된 테이블명")
@Column(name = "명명된 칼럼명")
@Table(name = "명명된 테이블명")
@Column(name = "명명된 칼럼명")
반드시 설정

---
</details>
<details>
  <summary>Flow Chart</summary>
<h3>통합 Flow Chart</h3>
![a6](https://github.com/user-attachments/assets/4ef24822-3b58-4c32-bf6b-81056ba2d0b7)
</details>

<details>
  <summary>우선순위</summary>
<br>
<br>
🔴 최우선 순위    🟡우선 순위     🟢하위 순위
<br>
<br>

양수영

## 슬랙 api

- [x] 🔴 **슬랙 생성 api**
- [x] 🟢 **슬랙 조회 api**
- [x] 🟢 **슬랙 삭제 api**
- [x] 🟢 **슬랙 검색 api**
- [x] 🟡 **슬랙 상태 변경 api**
- [x] 🟡 **슬랙 발송 api**

## **AI api**

- [x] 🔴 **AI 생성 api**

이태훈

## **사용자 api**

- [x] 🔴 **사용자 생성 api**
- [x] 🟡 **사용자 조회 api**
- [ ] 🟡 **사용자 전체 조회 api**
- [ ] 🟡 **사용자 수정 api**
- [ ] 🟡 **사용자 삭제 api**

## **배송담당자 api**

- [x] 🔴 **배송담당자 지정 api**
- [ ] 🟡 **배송담당자 전체 조회 api**
- [x] 🟡 **배송담당자 조회 api**
- [ ] 🟡 **배송담당자 수정 api**
- [ ] 🟡 **배송담당자 삭제 api**

김민경

## 인증인가 api

- [x] 🔴 **로그인 api**
- [ ] 🟢 **로그아웃  api**

## 허브 api

- [x] 🔴 **허브 생성 api**
- [x] 🟢 **허브 단 건 조회 api**
- [x] 🟢 **허브 전체 조회 api**
- [x] 🟢 **허브 수정 api**
- [ ] 🟢 **허브 삭제 api**

## 이동정보 관리api

- [x] 🔴 **허브 간 이동정보 생성 api**
- [ ] 🟡 **허브  간 이동정보 조회 api**
- [x] 🔴 **허브  간 이동정보 전체 조회 api**
- [ ] 🟡 **허브  간 이동정보수정 api**
- [ ] 🟢 **허브 간 이동정보 삭제 apiDe**

## 배송 경로 api

- [x] 🔴 **배송 경로 생성 api**
- [ ] 🟡 **배송 ID 기반 배송 경로 조회 api**
- [x] 🔴 **배송 경로 상태 변경 api**
- [ ] 🟢 **배송 경로 수정 api**
- [ ] 🟢 **배송 경로 삭제 api**

문고은

## 주문 api

- [x] 🔴 **주문 생성 api**
- [x] 🟡 **주문 리스트 조회 (검색) api**
- [x] 🟡 **주문 단건 조회 api**
- [x] 🟢 **주문 수정 api**
- [x] 🟢 **주문 삭제 api**
- [x] 🔴 **주문 취소**

## 배송 api

- [x] 🔴 **배송 생성 api**
- [x] 🟡 **배송 리스트 조회 (검색) api**
- [x] 🟡 **배송 단건 조회 api**
- [x] 🔴 **배송 상태 변경 api**
- [x] 🟢 **배송 삭제 api**
- [x] 🟡 **배송 상태 조회 api**
- [x] 🟢 **배송 취소 api**

신예나 

### 업체 api

- [x] 🔴 **업체 생성**
- [ ] 🟡 **업체 search**
- [x] 🟢 **업체 수정**
- [x] 🟢 **업체 삭제**
- [x] 🟡 **업체 단건조회**

### 상품 api

- [x] 🔴 **상품 생성**
- [ ] 🟡 **상품 search**
- [ ] 🟢 **상품수정**
- [ ] 🟢 **상품삭제**
- [ ] 🟡 **상품 단건조회**

### 재고 api

- [x] 🔴 **재고생성**
- [ ] 🟢 **재고 조회**
- [ ] 🔴 **재고확인**
- [ ] 🟢 **재고수정**
- [ ] 🟢 **재고삭제**
- [ ] 🟢 **재고 전체조회**


</details>


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

---

<br>

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
<summary>🔒 권한 오류 </summary>

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
<summary>✔️ 다른 서비스 호출 시 권한 에러 발생</summary>

### 📌 문제 발생
- 배송 생성 로직에서 허브,유저 등 여러 서비스에 요청을 하는데 권한에러 발생

### 🔍 원인 분석
- **Gateway 로그 확인**
  - Gateway에 에러로그 없음. 로그인에 대한 권한 에러 아님.
  
- **FeignClient 호출 후 요청받은 서비스에서 에러로그 발생**
  - 요청을 받았으나 권한이 없음을 알게됨

### ✅ 해결 방법
- **인증/인가 로직 점검**
  - Gateway에서 인가 후 토큰에 userId와 role 값을 헤더에 추가하여 넘겨줌.
  - 넘겨진 헤더값은 내부에서 다른 서비스로 또 요청이 넘겨질때 넘겨지지 않음.

- **RequestInterceptor 사용**
  - 헤더값을 넣어주어 넘겨주었더니 해결되었음.
  - 헤더에 넣어준 role은 권한검증에 사용되고, userId는 auditor에 사용될 수 있음.

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

