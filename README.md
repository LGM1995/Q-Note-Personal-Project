## Q-Note (ToyProject)
>##### * Synology NAS 서버와 Docker를 사용하여 외부 접속이 가능합니다.
[Q-Note 바로가기](http://gunmok.i234.me:28000/login)

<img width="1426" alt="Q-Note 메인" src="https://user-images.githubusercontent.com/110650972/193794517-41637423-b69c-44a4-ad39-0b589e58420f.png">

## 목차
* [소개](#소개)
  *  개발 목적
  *  프로젝트 소개
  *  프로젝트 기능

  
* [동작 및 구조](#동작-및-구조) 
  * 사용 기술
    * Back-End
    * Front-End
  * UI (사용자 화면)
    * 로그인 및 회원가입
    * 단어장 및 단어 CRUD
    * 단어 테스트
  * 구조
    * 패키지
    * 데이터베이스


* [결론](#결론)
  * 보완점
  * 후기

## 소개
### 1.개발목적

 본 프로젝트는 웹 프로그래밍 학습과 포트폴리오 제작 목적으로 개발되었으며, 단어장 웹 애플리케이션으로 평소에 공부에 사용할 수 있도록 제작되었습니다.

### 2.프로젝트 소개

 나만의 단어학습 Q-Note는 자유로운 형식으로 단어장을 제작하여 랜덤으로 출제되는 문제를 풀어가는 학습노트입니다.

### 3.프로젝트 기능

 프로젝트 기능

* 로그인 - Spring Security를 활용한 회원가입 및 로그인이 가능하며, OAuth 2.0 구글, 페이스북, 네이버 소셜 로그인이 가능합니다.


* 단어장 - 기본적인 CRUD 기능을 통해 자유로운 단어장과 단어를 등록 및 수정 등을 할 수 있습니다.


* 테스트 - 학습 기능을 통해 랜덤으로 출제되는 단어 리스트들이 학습가능하며 단어장 학습횟수와 단어들의 성공 및 틀린 횟수를 제공하여 집중 학습이 가능합니다.

## 동작 및 구조
### 1.사용기술

### Back-End
* OpenJDK 8
* SpringBoot 2.7.3
* JPA
* Spring Security
* OAuth 2.0
* Lombok
* Synology NAS
* Docker
#### Build Tool
* maven 4.0.0
#### DataBase
* mysql
### Front-End
* html/css
* javascript
* mustache
* swiper8
* jquery3.6.1
* bootstrap 5.2.1
* SweetAlert2


### 2. UI (사용자 화면)
<details>
<summary>로그인 및 회원가입 <span style="color: #0000ff">(클릭시 이미지가 나옵니다)</span></summary>

#### 1. 로그인 화면
<img width="1426" alt="Q-Note 로그인" src="https://user-images.githubusercontent.com/110650972/193794625-bddb2e30-89a4-4a46-8ac5-3fe65e63d0b7.png">


#### 2. 회원가입
<img width="1436" alt="Q-Note 회원가입" src="https://user-images.githubusercontent.com/110650972/193794674-5e931e88-43b6-4632-a54c-27c94ef41b39.png">

</details>
<br>
<details>
<summary>단어장 및 단어 CRUD <span style="color: #0000ff">(클릭시 이미지가 나옵니다)</span></summary>

#### 1. 메인 페이지
<img width="1426" alt="Q-Note 메인" src="https://user-images.githubusercontent.com/110650972/193794758-f0c838d2-e0e2-465c-8bf2-115252ef0c9b.png">
 
#### 2. 단어장 등록
<img width="1424" alt="단어장 생성 modal" src="https://user-images.githubusercontent.com/110650972/193794805-db6f053a-0ec3-44ec-8d18-02d553e1be1c.png">
 
#### 3. 단어장 Details (학습하기)
<img width="1425" alt="학습횟수,틀린횟수,성공횟수 업데이트" src="https://user-images.githubusercontent.com/110650972/193794833-41006d0e-3f6d-4d39-ad4d-db7f6d177d39.png">
 
#### 4. 단어 등록
<img width="1426" alt="단어 등록 modal" src="https://user-images.githubusercontent.com/110650972/193795193-c7422327-0051-494d-8005-b41de7bd972e.png">
 
#### 5. 단어 수정
<img width="1427" alt="단어수정 modal" src="https://user-images.githubusercontent.com/110650972/193795230-cfd106b0-06ba-4dbf-8ee7-05d4fa8d54d7.png">
 
</details>
<br>
<details>
<summary>단어 테스트 <span style="color: #0000ff">(클릭시 이미지가 나옵니다)</span></summary>

#### 1. 테스트 화면
<img width="1426" alt="단어 테스트 swiper" src="https://user-images.githubusercontent.com/110650972/193795322-0d712fcf-da2a-40e1-af57-cff31e4475bb.png">
 
#### 2. 테스트 완료 화면
<img width="1426" alt="단어 테스트 완료" src="https://user-images.githubusercontent.com/110650972/193795376-307d344f-ae97-460e-a30d-1d3d507078a0.png">
 
#### 3. 틀린 문제 확인 (arlet)
<img width="450" alt="틀린문제 arlet" src="https://user-images.githubusercontent.com/110650972/193795420-c88a663d-edcf-4e80-a826-6f3f2a171be7.png">
 
</details>

### 3. 구조

<details>
<summary>패키지 <span style="color: #0000ff">(클릭시 이미지가 나옵니다)</span></summary>
<img alt="패키지 구조" src="https://user-images.githubusercontent.com/110650972/193796428-abbe941e-e425-427f-9536-8487d94a5581.png">


 
</details>
<br>
<details>
<summary>DB <span style="color: #0000ff">(클릭시 이미지가 나옵니다)</span></summary>
<img width="649" alt="DB스키마" src="https://user-images.githubusercontent.com/110650972/193795618-e3644fd2-bd21-4a25-9f19-9856243b246d.png">
 
</details>

## 결론

### 1. 보완점 
* facebook http 로그인 불가능
* ~~모든 알림 (alert) 커스터마이징~~ => 해결 22.10.21(금)
  * ~~sweetalert2 사용 예정~~
* user 프로필 수정 기능
* 등록된 단어 없을때 학습 불가능하게 하기
* 단어 테스트 객관식, 주관식 선택기능
* 단어 테스트 시간제한 기능
* ~~DTO 사용~~ => 해결 22.10.20(목)
* ~~ID중복처리~~ => 해결 22.10.20(목)
* 각종 예외 처리
### 2. 후기

Spring Boot의 기본 강의를 학습하며 CRUD 기능이 있으며 실제로 사용할 수 있을 만한 프로젝트를 설계하였습니다. 
비교적 문법이 쉬운 Mustache를 활용하여 feach API를 통한 REST API의 호출로 HTTP 요청 메소드를 통해 JSON을 넘기는 통신 규칙을 실습할 수 있었습니다.
또한 Spring Security와 OAth2을 활용한 로그인 기능을 통해 Session 안에서 Spring Security Session 영역과 그 안에 Authentication을 어떻게 활용하는지 알게 되었습니다. 
다만 기초부터 학습해서 활용하느라 여러 강의를 정주행하였습니다. 비록 시간은 오래 걸렸지만 뜻깊은 학습 시간이 되었습니다. 
추가적으로 Synology NAS에 Docker를 활용하여 배포하여 Docker에 대한 이해와 전반적인 서비스 배포 방법에 대하여 공부할 수 있었습니다.

이상으로 저의 Git에 방문해 주셔서 감사합니다.



  
