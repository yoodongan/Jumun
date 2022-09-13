# 프로젝트 진행상황 (6주차)
<br>


## 팀 구성원, 개인 별 역할

### 기능별 개발 역할분담 및 개발진행 ➡️ 스프린트3 종료, 스프린트4 수립
> 팀 전원 참여 
1. <u> 스프린트3 종료 </u> 2. <u>JIRA로 스프린트4 수립 </u> 3. <u>2차 중간점검 진행</u> <br>

김성현 <br> 
강민성 <br> 
유동안 <br>
조홍식 <br>


## 팀 내부 회의 진행 회차 및 일자

---
<img width="1052" alt="image" src="https://user-images.githubusercontent.com/53210680/189826543-fdf1b091-c137-4b6d-b4ae-0d1d67e60cd5.png">


27회차(2022.09.05) 디스코드회의 (전원 참석) <br>
28회차(2022.09.06) 디스코드회의 (전원 참석) <br>
29회차(2022.09.07) 디스코드회의 (전원 참석) <br>
30회차(2022.09.12) 디스코드회의 (전원 참석) <br>

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

## 개발 과정에서 나왔던 질문 (최소 200자 이상) 
---
### 지난 주 질문
###### 메뉴옵션 구현
1. 메뉴마다 수량을 요구하는 경우 이를 어떻게 해결할 건지.
- 예로 카페업체의 커피 샷추가일 경우 1샷, 2샷..., 하지만 분식 업체의 메뉴인 라볶이이의 경우 '면사리'는 수량이 필요가 없다.
###### 대안1
- 처음엔 다대다 관계의 중간테이블인 메뉴-메뉴옵션테이블에 '메뉴수량' 컬럼을 추가하고 이를 NULL을 허용하는 방식으로 진행하려고 했음
- 하지만 메뉴수량은 '메뉴의 고유특성이'지, 이를 중간테이블에 새로운 컬럼에 추가하는 것은 맞지 않다고 봄
###### 대안2
- 배달의 민족의 경우, 메뉴옵션의 수량이 있을 경우 이를 항목으로 ([ ]1샷추가 [ ]2샷추가 [ ]3샷추가)로 나눈 후 이를 체크타입으로 포함시키고 있었음.
- 즉 사용자 입력을 따로 요구하지 않음. 대신 수량의 메뉴옵션인 경우 '중복이 불가능'하도록 제한하고 있었음.
- 메뉴테이블에 '중복가능'컬럼을 두고, 여러 항목을 체크하는 기능을 토글로 선택할 수 있도록 구현하려고 함.

2. 메뉴옵션테이블만으로는 중복되는 데이터가 많을 것이라는 문제점. 그래서 추가하기로 했던 옵션그룹테이블과 메뉴옵션테이블간의 관계(다대다?1대다?)
- 중간회의 때, 옵션이 메뉴를 관리하도록(hot-아메리카노, 라떼, 에스프레소//why?-배달의 민족을 참고했을 때, 이런 방식으로 운영하고 있었고 데이터중복을 줄여주기 때문에) '메뉴옵션그룹'테이블을 추가하기로 함.
- 예로 메뉴옵션테이블에는 '옵션이름과 옵션추가에 따른 요금 2가지의 데이터', 외래키로 '옵션그룹일련번호'가 들어간다. 전자의 이름과 가격의 중복데이터가 많을 것이라는 예측을 했다.
#### 대안 '{}는 다대다관계에서 생기는 중간테이블'
- 기존 메뉴 <->메뉴메뉴옵션<->메뉴옵션
- 현재 메뉴 <->{메뉴메뉴옵션그룹}<->메뉴옵션그룹<->{메뉴옵션메뉴옵션그룹}<->메뉴옵션
- 만약 1대다로 상정한다면 (hot,ice)가 한 그룹이라면, (hot,ice,medium)이라는 새로운 그룹이 생길 때, 또 다시 중복 데이터가 생긴다. 이럴 경우는 드물지만, 결국 메뉴옵션그룹과 메뉴옵션 간의 관계를 다대다로 결정.
---
### 결론
1. 대안2번으로 옵션그룹 생성시 사용자는 (1)중복가능 OR (2)중복불가능을 선택해야함. 사용자가 메뉴를 고를 때는 폼에 입력하지 않고, 체크만 가능하도록 구현.
###### 옵션그룹 생성
<img width="1052" alt="image" src="https://user-images.githubusercontent.com/53210680/189827429-02307f9c-28e0-42ac-a0e6-2c020d1fd509.png">
###### 사용자 장바구니
<img width="1052" alt="image" src="https://user-images.githubusercontent.com/53210680/189827773-35694dc0-7905-41ef-b007-4da8e5273c96.png">


2. 
- 2차 중간점검 당시 대안에 대한 멘토님 피드백 -> DB구조변경 완료. 
- MenuOption* 의 복잡한 엔티티명을 Option, OptionGroup으로 변경, 중간테이블도 이와 같이 이름변경
<img width="791" alt="image" src="https://user-images.githubusercontent.com/53210680/189830713-fd7363a2-7beb-481e-a78f-9f2bf383bb32.png">


---
### 전 주 개발이슈 정리
#### 의도치 않은 테이블 생성이슈
- 문제상황: 작성한대로라면 n개가 생성되어야할 테이블이 하나 더 추가되어 n+1개가 만들어짐
- 원인: 엔티티이름인 option이 JPA에서 예약어였음.
- 해결방법: 엔티티 name을 options라고 붙여줬음. 중간테이블에서 참조하는 테이블이름은 복수형이면 혼란의 여지가 있으므로 option으로 설정

#### OptionGroup생성 시 데이터가 넘어가지 않는 이슈
- 문제상황: 중간테이블에 값이 추가되어야 하는데 추가되지 않는 문제, OptionGroupController의 옵션 그룹 상세페이지에서 옵션 추가하는 부분
- 원인: 뷰단에서 form 태그를 붙여주지 않아서 컨트롤러에 정보가 전달 되지 않았음.
- 해결방법: 기존 코드에 form태그를 추가.
```html
<form th:action th:object="${OptionGroupDetailDto}" method="post">
    <label class="form-label">옵션 선택</label>
    <select th:field="*{optionId}" class="form-select">
        <option value="">==옵션 선택==</option>
        <option th:each="option : ${options}" th:value="${option.id}" th:text="${option.name}">카테고리옵션</option>
    </select>
    <input onclick="if ( !confirm('옵션을 추가하시겠습니까?') ) return false;" type="submit" value="옵션 추가" class="btn btn-primary my-2">
</form>
```

### 그 외 회의내용
- 카테고리 crud 빠진부분 구현 풀리퀘
- 장바구니 crud 구현 풀리퀘와 양방향 맵핑
- 옵션그룹과 옵션 crud 풀리퀘


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





