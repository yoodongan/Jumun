# 프로젝트 진행상황 (4주차)
<br>


## 팀 구성원, 개인 별 역할

### 기능별 개발 역할분담 및 개발진행 ➡️ 담당 기능별 구현
> 팀 전원 참여 
1. <u> 담당 기능 구현 </u> 2. <u>기능구현을 위한 이슈생성 </u> 3. <u>풀리퀘스트 및 코드리뷰 및 approve</u> <br>

김성현 <br> 
강민성 <br> 
유동안 <br>
조홍식 <br>


## 팀 내부 회의 진행 회차 및 일자

---
<img width="488" alt="image" src="https://user-images.githubusercontent.com/53210680/185877898-9894afb0-462c-45d3-8a8c-9a5b4860f33f.png">

17회차(2022.08.22) 디스코드회의 (전원 참석) <br>
18회차(2022.08.23) 디스코드회의 (전원 참석) <br>
19회차(2022.08.24) 디스코드회의 (전원 참석) <br>
20회차(2022.08.25) 디스코드회의 (전원 참석) <br>
21회차(2022.08.26) 디스코드회의 (전원 참석) <br>

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
    
    GET	/admin/login	로그인 페이지 → 민성님<br>
    POST /admin/login	로그인<br>
    GET	/admin/new	회원가입 페이지<br>
    POST /admin/new	회원가입<br>
    
    GET	/admin/store/new	매장 생성 페이지<br>
    POST /admin/store/new	매장 생성<br>
    GET	/admin/store	메뉴판페이지, 주문확인페에지, 매장관리페이지로 이동할 수 있는 페이지<br>
    
    POST /user	닉네임 설정(쿠키 저장) → 메뉴 페이지 Redirect → 성현님<br>
    GET	/menu	메뉴판 페이지 → 메뉴판 <br>
    GET	/menu/{menuId}/option	메뉴 상세보기<br>
    POST /menu/{menuId}/option 장바구니 메뉴 담기<br>
    
    GET	/menu?category={noodle}	카테고리별 보여주기<br>
    

- 2) 카테고리 crud, 2) 메뉴판 crud - (관리자) - 1팀 (동안, 홍식)<br>
    
    GET          /admin/store/menu/category 카테고리 목록<br>
    
    POST	/admin/store/menu/create/category	카테고리 생성  →  홍식님<br>
    DELETE	/admin/store/menu/category/{categoryId}	카테고리 삭제<br>
    PATCH	/admin/store/menu/category/{categoryId}	카테고리 변경<br>
    
    GET	/admin/store/menu	메뉴판 페이지 보기   <br>
    
    POST	/admin/store/menu	메뉴 추가 + 옵션과 함께?   → 동안님<br>
    GET	/admin/store/menu/{menuId}	메뉴 상세보기<br>
    PATCH	/admin/store/menu/{menuId}	메뉴 일부 수정<br>
    DELETE	/admin/store/menu/{menuId}	메뉴 삭제<br>
    PATCH	/admin/store/menu/{menuId}/status	메뉴 주문 상태 변경 - 주문가능/재고없음<br>
    
    POST /admin/store/menu/{menuId}/option	메뉴 옵션 생성<br>
    PATCH.   /admin/store/menu/{menuId}/option/{optionId}	메뉴 옵션 변경<br>
    DELETE	/admin/store/menu/{menuId}/option/{optionId}	메뉴 옵션 삭제<br>
- [x] 기능개발 후 pullrequest - 팀원 두명이상의 feedback 후 merge <br>
- [x] 구현이 우선적으로 필요한 기능이 있을 경우 주석 및 팀원과 협의 후 issue생성하기 <br>\

### 5주차(예정)
- [ ] 멘토님과 미팅(2차 중간점검) <br>
- [ ] 기능 분담 및 개발 <br>
## 개발 과정에서 나왔던 질문 (최소 200자 이상)

---
### 고객과 관리자 측의 menu 및 상세보기는 crud기능을 위한 버튼의 여부 정도만 차이가 있습니다. 그렇다면 이를 함께 처리할 수 있는 하나의 Contoller만 있으면 되지 않을까요?
- 가능할 것 같습니다. 같은 템플릿 쓰면서, input type="hidden" 방식으로 해결하려고 합니다.
- 추가적으로 관리자 메뉴판 및 상세 페이지, 고객 메뉴판 및 상세 페이지 구현,
- 그리고 메뉴판 페이지 카테고리 보여주는 부분 어떻게 구성할지 고민해야합니다.

### 이미지처리를 어떻게 해야할지 고민입니다.
- amazon s3로 이미지 업로드를 할 수 있는 Test코드를 작성했으며, 이를 다른 팀원이 작업한 menu CRUD 코드안에 합쳤습니다.
![image](https://user-images.githubusercontent.com/53210680/187178556-503f866f-bb14-49de-8541-099b3b0b5d8e.png)
 
## 담당기능별 개발 회고

### 그 외 회의내용

- layout 공통 템플릿에는 모든 템플릿이 공유하는 부분만 . -> 회의 때 다뤄보기
-- 현재까지는, nav bar 추가 부분과 bootstrap 및 공통 css(폰트) 까지만 적용하는걸로.
- 카테고리 서비스,리포지터리 타입(int -> Long) 수정

## 개발 결과물 공유

초안 ERD
![image](https://user-images.githubusercontent.com/53210680/184819347-e8d59b35-9806-430f-91b8-017718639341.png)

개선 ERD
<img width="978" alt="image" src="https://user-images.githubusercontent.com/53210680/184819454-0d43493d-ef5b-4aeb-a6df-170e0ea72910.png">

Github Repository URL: 
https://github.com/likelion-backendschool/Jumunui_Mihak

ERD Cloud URL:
https://www.erdcloud.com/team/nPF8jJKc9FKe2QxHu


- 필수) 팀원들과 함께 찍은 인증샷(온라인 만남시 스크린 캡쳐)도 함께 업로드 해주세요 🙂
<img width="667" alt="image" src="https://user-images.githubusercontent.com/53210680/185877544-701bd5a4-bb3f-43f7-baec-ffc05c0b38bd.png">





