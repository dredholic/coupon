## Coupon Project
### Project 설명
사용자로부터 이메일 주소를 입력으로 받아서 16자리의 알파벳과 숫자로 이루어진 중복없는 쿠폰 번호를 발급하고 발급된 쿠폰 정보를 같은 페이지에 리스팅하는 웹어플리케이션

### 문제 해결 전략
* RNG(Random Number Generator) 사용하여 0~61 범위의 난수를 생성 후 대응하는 문자로 치환하여 쿠폰번호 생성

## 프로젝트 빌드 및 실행
### 빌드
```
./gradlew build
```

### 실행
```
java -jar build/libs/coupon-0.0.1-SNAPSHOT.jar
```
* [link](http://localhost:8080/)