# Optimization with Redis
This project aims to demonstrate optimization capabilities using Redis. The project uses Java Spring and the PostgreSQL database and as a Redis cache.  
  
![chart](./assets/chart.png)  
### Results optimization ms
Java: 21  
Service: Java Spring  
Database: PostgreSQL ~15 ms  
Cache: Redis ~4 ms  
  
### Without a cache. PostgreSQL queries

![without_cache](./assets/without_cache.jpeg)  
Requests/second: 62.79  
Avg. response: 15 ms

### With a cache. Redis queries

![with_cache](./assets/with_cache.jpeg)  
Requests/second: 63.40  
Avg. response: 4 ms

### Warming up the cache with Redis

![warming_up_cache](./assets/warming_up_cache.jpeg)
![warming_up_cache2](./assets/warming_up_cache_2.jpeg)  
  
Time warming up cache: 2 min  
Count users: 100  
Requests/second: 76.16  
The request time has been lowered: from 15 to 4 ms

### How to Install

Run databases from docker-compose file:  
`docker-compose up -d --build`  
  
Build project with command:  
`.\gradlew build`  

Run `jar` file:  
`java -jar name.jar`
  
The collected files are stored in build/libs.

### API

**[POST] Create news:**

Link: `http://localhost:8080/api/v1/news/create`  
Body:
```json
{
    "title": "This is title",
    "content": "This is content"
}
```
Response:
```json
{
  "id": 4218,
  "title": "This is title",
  "content": "This is content",
  "rating": 0
}
```
  
**[GET] Get random news:**  

Link: `http://localhost:8080/api/v1/news/random`  
Response:
```json
{
    "id": 1525,
    "title": "This is title 1525",
    "content": "This is content 1525",
    "rating": 0
}
```
  
**[DELETE] Remove news by id:**  
  
Link: `http://localhost:8080/api/v1/news/delete`  
Body:
```json
{
    "id": 4217
}
```
Response:
```json
{
    "id": 4217,
    "title": null,
    "content": null,
    "rating": null
}
```