# 프로젝트 진행상황 (7주차)
<br>


## 팀 구성원, 개인 별 역할

### 기능별 개발 역할분담 및 개발진행 ➡️ 스프린트4 종료, 스프린트5 수립
> 팀 전원 참여 
1. <u> 스프린트4 종료 </u> 2. <u>JIRA로 스프린트5 수립 </u> 3. <u>스프린트5는 스프린트4에서 남은 기능을 구현</u> <br>

김성현 <br> 
강민성 <br> 
유동안 <br>
조홍식 <br>


## 팀 내부 회의 진행 회차 및 일자

---
<img width="1147" alt="image" src="https://user-images.githubusercontent.com/53210680/191037964-57a78aba-dbfc-45bc-b696-ebb37dbe5207.png">

31회차(2022.09.13) 디스코드회의 (전원 참석) <br>
32회차(2022.09.14) 디스코드회의 (전원 참석) <br>
33회차(2022.09.15) 디스코드회의 (전원 참석) <br>
34회차(2022.09.16) 디스코드회의 (전원 참석) <br>
35회차(2022.09.19) 디스코드회의 (전원 참석) <br>

## 현재까지 개발 과정 요약 (최소 500자 이상)
### 1주차
1. 와이어프레임 제작 완료. <br>
2. API명세 초안 완성 <br>
### 2주차
- [x] DB모델링 초안 완성 <br> - 요구사항 명세서 작성 - 초안 작성 중 멘토님께 질문사항 정리
- [x] 1차 중간점검 완료 <br>
- [x] 중간점검 바탕으로 DB 및 API 개선 수정 완료 <br>
### 3주차
- [x] JPA 엔티티 작성 후 팀원 모두 git clone<br>
- [x] GIT 협업 규칙 협의 및 개발팀 편성 | 협업규칙: https://www.notion.so/45f70d08996e486487712b939cb96079 | 개발팀: https://www.notion.so/idea_4-27a2b029531b48049f05d25337bcc372?p=1a7c0ef5a7074faaaba0643a37666653&pm=s <br>
- [x] 브랜치 나누기 및 담당기능 issue생성 <br>
<img width="354" alt="image" src="https://user-images.githubusercontent.com/53210680/185876096-a2fb2207-c9ae-4af3-b8bf-bfc43888329e.png">
<img width="1000" alt="image" src="https://user-images.githubusercontent.com/53210680/185876352-db93a25d-0ab3-4985-945f-b9b28fb3b659.png">

### 4주차
- [x] 역할별 개발 진행 <br>
- 1) 로그인, 회원가입, 6) 유저 닉네임설정 + 메뉴판 조회기능 - 1팀 (민성, 성현) (2주)<br>
- 2) 카테고리 crud, 2) 메뉴판 crud - (관리자) - 1팀 (동안, 홍식)<br>
- [x] 기능개발 후 pullrequest - 팀원 두명이상의 feedback 후 merge <br>
- [x] 구현이 우선적으로 필요한 기능이 있을 경우 주석 및 팀원과 협의 후 issue생성하기 <br>

### 5주차
- [x] 2주로 계획했던 스프린트1&2 종료, 현재까지 구현완료된 기능을 API항목별로 체크 <br>
- [x] 스프린트3을 시작하기 위한 기능 분담 및 개발, 전 스프린트에서 해결 못한 내용도 합침 <br>
<img width="298" alt="image" src="https://user-images.githubusercontent.com/53210680/188453458-1658c452-89cb-4676-98ce-f1b0ab06b179.png">

### 6주차
- [x] 스프린트3 종료 후 개선&구현 못한 기능에 대한 스프린트4 진행
- [ ] 5주차에 진행한 코드리뷰 피드백을 반영한 코드리펙토링(DB구조변경과 2차중간점검으로 진행X)
- [x] DB구조 변경(중간테이블 추가와 OptionGroup엔티티에 store필드 추가 등)
- [x] 기능 60% 이상 구현완료 
- [x] 2차 중간점검

### 7주차
- [x] redis를 적용한 주문 기능 개발
- [x] 장바구니 기능 담기, 장바구니 조회, 옵션그룹과 옵션의 CRUD, 주문내역 조회 등 기능 개발
- [x] 이미지 업로드, 메뉴에서 옵션그룹 버그 수정
- [x] 기능 90% 이상 구현완료


### 8주차(예정)
- [ ] 프론트 ui 수정 보완
- [ ] 결제 모듈 구현
- [ ] 배포

## 개발 과정에서 나왔던 질문 (최소 200자 이상) 
---
### ❓전 주 개발이슈 정리
#### 버그 및 이슈
- getDescription()메서드를 가지고 있는 enum 클래스의 description이 Null이면 에러가 난다. -> 만약 결제거부가 있다면 decription에 '결제정보없음'이라는 값을 자동으로 추가시키는 메서드가 필요할 것 같다.
- thymeleaf 사용 시 form태그 안에 form태그로 감쌀 수는 없음. (메뉴 생성이나 수정 시 옵션그룹을 선택하는 과정에서 [옵션그룹추가]와 [메뉴생성] 두 가지 폼을 한꺼번에 쓸 수는 없었음.
- DB의 CRUD 수행 시 @Transactional은 권장되는 것임.

#### 현재 기능 구현에서 이슈
- 사용자 상세 주문내역 + 해당 사용자의 주문 상태 한페이지로 보여주기.
- 관리자의 주문조회를 위해 usernickname 필드를 통해 (order->cart->menu->optionGroup->option)을 조회해야함.
- 빌더를 쓰려면 생성자 allargument, 매개변수가 4개 이상이면 빌더 패턴이 권장된다.
### 그 외 회의내용
- thymeleaf에서 javascript사용과 ajax사용법
- 젠킨스, 도커 CI/CD에 대한 회의
- 프로젝트의 아키텍처 도안 작성을 위한 레퍼런스 공유


초안 ERD
![image](https://user-images.githubusercontent.com/53210680/184819347-e8d59b35-9806-430f-91b8-017718639341.png)

개선 ERD
<img width="978" alt="image" src="https://user-images.githubusercontent.com/53210680/184819454-0d43493d-ef5b-4aeb-a6df-170e0ea72910.png">

개선 ERD(0906 중간테이블인 메뉴메뉴옵션테이블 삭제, 메뉴&옵션그룹&옵션 간의 다대다관계)
<img width="712" alt="image" src="https://user-images.githubusercontent.com/53210680/188460892-f4c791e2-0a60-4686-a3fc-4a04dae3c3c7.png">


Github Repository URL: 
https://github.com/likelion-backendschool/Jumunui_Mihak

ERD Cloud URL:
https://www.erdcloud.com/team/nPF8jJKc9FKe2QxHu


- 필수) 팀원들과 함께 찍은 인증샷(온라인 만남시 스크린 캡쳐)도 함께 업로드 해주세요 🙂
<img width="667" alt="image" src="https://user-images.githubusercontent.com/53210680/185877544-701bd5a4-bb3f-43f7-baec-ffc05c0b38bd.png">





