# 주문의 미학 (팀 프로젝트)
<img src="https://user-images.githubusercontent.com/53210680/196109460-cbe2fb60-0e15-42e3-90c9-9f79a91649a0.png" width="150"></img><br>
QR코드로 이뤄지는 웹 간편결제 서비스<br>
### 개발 블로그 참조 (개인 블로그)
📝 [개발 블로그 링크](https://velog.io/@daryu519/series/%EC%9B%B9%ED%82%A4%EC%98%A4%EC%8A%A4%ED%81%AC-%ED%8C%80%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8)

## [ 프로젝트 개요 ]
### miro 를 활용한 마인드맵
<img src="https://user-images.githubusercontent.com/90627763/224014632-9efcbf1c-e0c2-4b00-83cc-79e549e9b7f5.png" width="800"></img>

### 제작 기간 & 참여 인원
- 2022.08.01 ~ 2022.09.30
- 참여 인원 : 4명


## [ ERD ]
![image](https://user-images.githubusercontent.com/90627763/224008067-3fc8b1ff-8bec-451c-b0bd-5e131384bdc6.png)

## [ 패키지 구조 ]
<img width="231" alt="image" src="https://user-images.githubusercontent.com/90627763/224011874-ed50898d-45c9-411f-92c4-c95844844240.png">

- 도메인 별 패키지 분리
- controller, service, repository, dto, entity, exception 패키지로 구분
- 글로벌 설정 파일은 global 패키지에서 관리
  - AWS S3, Spring Security, Base Entity

## [ 협업 과정 소개 ]
### 브랜치 생성 양식
- {커밋 분류 태그}#{이슈번호}-{담당자}    ex) **feat#1-minseong**
- API 당 브랜치 하나씩 파서 진행

### JIRA
- JIRA 를 활용한 백로그 생성
- 총 5번의 스프린트로 개발
- 데일리 스크럼을 통해 개발 진행도 확인, 문제 상황에 대해 빠른 

### Github Issues Template
```
### 목적
> 로그인 API 구현
- [] 아이디, 패스워드 폼 구현
- [] 아이디 틀림 예외 사항 처리
- [] 비밀 번호 틀림 예외 사항 처리
- [] 로그인 후 redirect 처리
```

- 이슈 제목 : {구현 내용} API - {API url}  ex) 로그인 API 구현 - GET /admin/login
- 작업상세사항 : 세부 요구사항 자세히 적기
- Assigeness, Label, project 설정하기

### 커밋 메세지 규칙
```
{태그} : 작업내용(#{이슈번호})

- 세부 작업 내용1
- 세부 작업 내용2
```

ex)

```
Feat : 관신지역 알림 ON/OFF 기능 추가(#1)

- 시군구의 알림을 각각 ON/OFF 할 수 있도록 기능을 추가함
```

### Pull Request (PR)
- 2명이상의 코드 확인 이후 approave 받으면 머지하기
- 이후 브랜치를 만든 담당자가 머지 버튼을 누르고 브랜치 삭제까지 담당


## [ 기술 스택 ]
<img width="1450" alt="image" src="https://user-images.githubusercontent.com/90627763/224014164-e5955c58-0770-4994-b2e8-05f8ff664fed.png">


## [ 아키텍처 ]
<img width="1151" alt="image" src="https://user-images.githubusercontent.com/90627763/224014280-7adfc47c-4348-4ae7-bd84-18bca53e0534.png">


## [ 고민 사항 && 트러블 슈팅 ]
### 유저를 식별할 수 있는 방법에 대한 고민
문제 상황 
- 웹 간편 결제이기 때문에, 소비자는 로그인을 하지 않고, QR 코드를 통해서 서비스를 이용해야 한다.
- 로그인 없이 유저를 식별할 수 있어야 함

해결
- 소비자가 닉네임을 입력했을 때 세션 ID 와 쿠키 값에 UUID.toString() 을 보내준다.

### '장바구니 담기' 부터 '주문하기' 까지의 과정이 비효율적이다.
문제 상황
- 장바구니 담기 클릭 -> 장바구니 테이블에 해당 주문 저장 -> 소비자가 '주문하기' 클릭 -> 장바구니 테이블 내 주문 삭제 -> 다시 주문 테이블에 해당 주문 내역 저장
- 위 과정이 CREATE -> DELETE -> CREATE 과정이 비효율적이라고 판단

해결
- 이를 개선하기 위해 Redis 적용 (추가 성능 개선 부분에서 ConcurrentHashMap 으로 변경함)

### 옵션그룹이 걸려 있는 메뉴 삭제 시 오류 발생.
문제 상황
- 손님 페이지에서 장바구니에 메뉴 담았을 때, 메뉴를 외래키로 갖고 있어서 관리자페이지에서 메뉴 삭제가 불가했다.

해결
- 손님이 메뉴를 담았을 때는 관리자 페이지에서 삭제가 불가능하도록 구현

## [ 추가 성능 개선을 통한 프로젝트 고도화 ]
### '장바구니 담기' 부터 '주문하기' 까지의 비효율적 과정 개선 
문제 상황
- 기존 Redis 는 인메모리 방식으로, 캐시로 사용할 수 있지만 네트워크를 거쳐야 함.

해결
- ConcurrentHashMap 으로 변경. 
- 로컬 메모리인 ConcurrentHashMap 을 사용하면 멀티쓰레드 환경에서 독립적 캐시 공간으로 처리가 가능함.
- 그러나 서버 다중화 되었을 때는 문제가 발생할 수 있다. (로컬 메모리의 한계)

### 실시간 처리 부분의 개선
문제 상황
- 기존에는 Ajax 를 통해 시간초 단위(2초)로 새로고침을 하며 화면을 보여줬다.
- 매번 계속해서 새로고침을 통해 화면을 보여주는 방식이 비효율적이라 판단.

해결 (예정)
- Server-Sent-Event 방식으로 서버에서만 데이터 변경 발생 시 클라이언트에 알려주면 될 것 같다.

### 메서드 리팩터링
문제 상황
- 협업 과정에서 통일 되지 않은 메서드명 발생

해결
- 메서드명 전체 통일

### 페치 조인
문제 상황
- JPA 를 사용하면서 성능 저하의 대부분을 차지하는 N+1 문제 발생

해결 (진행 중)
- fetch join 적용(다대일)
- batch size 적용(일대다, 컬렉션 페치 조인)

