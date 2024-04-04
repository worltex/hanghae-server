## 마일스톤
![마일스톤.png](..%2F%EB%A7%88%EC%9D%BC%EC%8A%A4%ED%86%A4.png)

## 시퀀스 다이어그램
![콘서트 시퀀스 다이어그램.drawio.png](..%2F%EC%BD%98%EC%84%9C%ED%8A%B8%20%EC%8B%9C%ED%80%80%EC%8A%A4%20%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8.drawio.png)


## ERD
![concert_erd.drawio.png](..%2Fconcert_erd.drawio.png)

## API 명세

## 토큰 발급 API

HTTP METHOD  URL

POST /api/v1/users/{userId}/token

Response
``` json
{

“token”: “asdfasdfasdfs…”

}
``` 

## 예약 가능한 날짜 조회 API

HTTP METHOD  URL

GET /api/v1/concerts/{concertId}/shows

HEADER

Authorization : Bearer sadfsdfsdf
``` json
Response

[

{

“showId”: “1”,

“showDate”: “ 2024-01-01”

},

{

“showId”: “2”,

“showDate”: “ 2024-01-02”

}

]
```
## 예약 가능한 좌석 조회 API

HTTP METHOD  URL

GET /api/v1/shows/{showId}/seats

HEADER

Authorization : Bearer sadfsdfsdf

Response
``` json
[

{

“seatId”: “1”,

“seatNumber”: “ 1”

},

{

“seatId”: “2”,

“seatNumber: “ 2”

}

]
```
## 좌석 예약 조회 API

HTTP METHOD  URL

POST /api/v1/shows/{showId}/seats/{seatId}

HEADER

Authorization : Bearer sadfsdfsdf

Response
``` json
{

200 OK

}
```

## 잔액 조회 API

HTTP METHOD  URL

GET /api/v1/users/{userId}/balance

HEADER

Authorization : Bearer sadfsdfsdf

Response
``` json
{

“amount” : “10000”

}
```
## 잔액 충전 API

HTTP METHOD  URL

POST /api/v1/users/{userId}/balance

HEADER

Authorization : Bearer sadfsdfsdf
``` json
Response

{

“amount” : “10000”

}
```
## 결제  API

HTTP METHOD  URL

POST /api/v1/users/{userId}/payments

HEADER

Authorization : Bearer sadfsdfsdf

Response
``` json
{

“amount” : “10000”

}
```