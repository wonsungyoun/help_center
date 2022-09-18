# help_center
help_center

프로젝트 명 : 상담 시스템
프로젝트 구성 환경 : 
|제목|기술
|------|---|
|백엔드|JAVA 8|
|프론트엔드|ES6, 타임리프|
|RDBMS|h2 database|
|프레임워크|스프링부트(스프링 시큐리티, 스프링 데이타 JPA) 활용|
|빌드 툴|gradle|

백엔드 구성 :
- 화면을 제어하는 뷰컨트롤러, ajax로 api 호출시 응답하는 api 컨트롤러로 컨트롤러단 구성
- 서비스는 도메인별로 나누었으며 도메인의 repository, mapper만 가지고 있음.
- repository는 해당 도메인의 데이터만 가져오는 곳으로 jpa로 구성하였고 mapper는 join이 필요 할 시 마이바티스를 활용하기 위해 구성함.
- entity는 db본연의 값과 관련 로직을 가지고 있기에 setter를 사용하지 않도록 하였고 디비관련 기능에서만 사용하도록 함.
- entity는 디비 관련 기능용이기 때문에, 서비스에서 별도로 기능수정이나 도메인끼리 결합을 할때에는 dto 객체를 사용하도록 dto객체를 만듬.
- dto는 서버간, 서비스에서 별도의 로직, 요청 및 응답등에 사용하기 위해 쓰임.

실행방법 :
(필수적으로 로컬에 java와 git이 설치되있어야한다)
```
   $ git clone https://github.com/wonsungyoun/help_center.git
   $ cd help_center
   $ ./gradlew clean build
   $ java -jar build/libs/help_center-0.0.1-SNAPSHOT.jar
```

url :
- http://localhost:8080 - 기본 페이지
- http://localhost:8080/customer/counsel/list - 고객용 상담관리페이지
- http://localhost:8080/counselor/counsel/list - 상담사용 상담관리페이지 (로그인필요)

로그인 계정 : import.sql 참고

문제해결 전략 :
1. 상담사의 상담페이지 같은 경우 스프링시큐리티를 활용하여 로그인 세션이 존재할때만 접근이 가능하게 하였고 고객의 상담페이지는 로그인세션이 없어도 별도로 접근가능하게 만듬
2. 상담사가 지정할 데이터와 상담사가 지정한 데이터를 화면에서 나누었으며 상담사가 지정버튼을 누르면 ajax 방식으로 api를 호출하여 디비에 업데이트 하고 최신화된 데이터를 받아 
   화면을 재구성 하도록 하게 만들었음.   
3. 글 작성은 별도의 페이지를 만들어서 구현함.
4. 상담사 상담화면이나 고객의 상담화면 둘다 프론트단에서 10초마다 api를 호출하여 최신화된 목록을 받아와 화면을 재구성하게 구현함.
5. 고객의 상담화면은 고객 계정이 별도로 요구사항에 없으므로 전체 데이터를 볼 수 있고 상담사의 상담화면은 미지정된 데이터와 자신이 지정한 데이터를 볼 수 있게 해놨음.
