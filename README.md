<h1 align="center">Dogterest</h1>

<center>
  <img src="https://github.com/xxria17/Dogterest/assets/41279544/f1cdd9ad-f55d-42a8-9abe-7fed4763f254" width="300" height="300"/>
</center>




> 모든 개들을 보며 힐링할 수 있는 핀터레스트 개.ver 🐶

<br>


## ✨ 서비스 설명
```sh
‘Dogterest’는 미디어 검색 어플 Pinterest를 모티브로,
https://dog.ceo/dog-api/ API 를 사용하여 다양한 개의 사진을 보고, 또 검색할 수 있는 안드로이드 어플리케이션입니다. 
xml을 사용하지 않고 Jetpack Compose로만 UI 구현을 해보고 싶어 도전한 프로젝트입니다.
Compose로 구현을 위해 MVI 패턴을 적용하였으며 클린 아키텍쳐 또한 적용하여 멀티 모듈화하였습니다.
```

<br>


## 🔍 서비스 기능
- 전체 사진 목록으로 불러오기
- 랜덤 api를 이용한 단일 사진 불러오기 및 로컬에 저장하기
- 맘에 드는 사진 로컬 컬렉션에 담아두기
- 품종별로 검색하기

<br>


## 📱 실행 화면
<img src="https://github.com/xxria17/Dogterest/assets/41279544/4ce399ba-a55e-414f-adeb-e79a16d0efd2" width="300" height="600"/>
<img src="https://github.com/xxria17/Dogterest/assets/41279544/f9f2e3b6-f70e-4a0d-addf-f1ccb3875994" width="300" height="600"/>
<img src="https://github.com/xxria17/Dogterest/assets/41279544/5b108c23-d8b4-4113-86c7-21c45495f284" width="300" height="600"/>
<img src="https://github.com/xxria17/Dogterest/assets/41279544/0043b269-d86f-4513-84f6-4307e0b1972a" width="300" height="600"/>
<img src="https://github.com/xxria17/Dogterest/assets/41279544/db6a77b8-0ea7-4864-8e11-aed325533774" width="300" height="600"/>

<br>


## 📋 스킬
- Language : Kotlin
- minSdk : 24
- targetSdk : 33
- Jetpack Compose
- JetPack Navigation
- Retrofit2 + OkHttp3 + Gson
- Coroutine + Flow
- Hilt
- Room

<br>


## 🐶 사용 Api
https://dog.ceo/dog-api/


## 🗂 멀티 모듈
```sh
클린 아키텍쳐 적용
app (presentation) - domain - data
```

<br>


## 📝
```sh
Compose 구현을 위해 화면 이동은 Jetpack Navigation을 이용하였고,
데이터 관리는 MVI의 State를 이용하여 보여주는데 노력하였습니다.
```
