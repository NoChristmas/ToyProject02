# Project Title

간단한 Android Studio 및 Kotlin을 사용한 Spring Boot API 통합 프로젝트 
MVVC 구조를 따라서 구현해본 kotlin Project

## 프로젝트 소개

본 프로젝트는 Android Studio에서 Kotlin을 사용하여 Spring Boot 애플리케이션과 통합하여 RESTful API를 호출하고 데이터를 표시하는 게시판 CRUD 입니다.

## 기술스텍
- 안드로이드 애플리케이션 개발
- Kotlin 프로그래밍 언어
- Android Jetpack 라이브러리 (ViewModel, LiveData, DataBinding 등)
- Retrofit 라이브러리를 사용한 RESTful API 통신
- OkHttp 클라이언트
- Gson 라이브러리를 사용한 JSON 데이터 직렬화 및 역직렬화
- RecyclerView 및 Adapter를 사용한 리스트 표시
- 상속을 활용한 코드 재사용 (Base 클래스 활용)
- LiveData 및 ViewModel을 사용한 데이터 관리 및 뷰모델 패턴 구현

## 주요 기능
- Token 인증 Filter 사용자는 로그인 기능을 통해 Spring Boot 서버에 인증
- 로그인 및 회원가입 : 사용자가 로그인하여 토큰을 받아내 인증을 사용, 회원가입이 가능합니다.
- Retrofit2을 이용한 게시판 CRUD
- 게시판 목록 표시: 서버에서 게시판 목록을 가져와서 앱에 표시합니다.
- 게시물 상세 보기: 게시물을 클릭하면 해당 게시물의 상세 내용을 볼 수 있습니다.
- 게시물 작성: 사용자가 새로운 게시물을 작성할 수 있습니다.
- 게시물 수정: 사용자가 작성한 게시물을 수정할 수 있습니다.
- 게시물 삭제: 사용자가 작성한 게시물을 삭제할 수 있습니다.
- 사용자 인증: 사용자의 로그인 및 로그아웃을 처리하여 인증을 관리합니다.
- 사용자는 인증 후 서버에서 제공하는 데이터를 가져와서 표시할 수 있습니다. (RESTAPI) 

## 요구 사항
- Android Studio 설치
- Kotlin 지원 플러그인 설치
- Spring Boot 서버 액세스 가능한 환경

## 로그인 구현 화면
- ![image](https://github.com/NoChristmas/ToyProject02/assets/127386254/8ee9b044-5af6-40fa-84e3-e9a78b59ac06)

## 회원가입
- ![image](https://github.com/NoChristmas/ToyProject02/assets/127386254/b859cb6b-b294-4c40-ac61-4db99a6e5555)

## 게시판 구현 
- ![image](https://github.com/NoChristmas/ToyProject02/assets/127386254/8939a8d5-5623-4775-9a1d-ccb5feb6c372)

## 게시판 상세
- ![image](https://github.com/NoChristmas/ToyProject02/assets/127386254/2473fea4-9689-4ea1-a51c-d0f4ad388d43)

## 게시판 수정
- ![image](https://github.com/NoChristmas/ToyProject02/assets/127386254/146dd523-c63e-4f64-a6b8-1872c47f4d88)

