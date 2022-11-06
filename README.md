### Бизнес-процесс. Сервис доставки

**Конфигурация**

- Переменные окружения для настройки проекта:
    - ```DATABASE_URL``` - url для подключения к БД
    - ```DATABASE_USERNAME``` - username БД
    - ```DATABASE_PASSWORD``` - password для входа в БД


- Приложение запускается под портом ```8080```, изменение порта можно выполнить в файле ```docker-compose.yml```


- Использование Swagger для тестирования endpoints
```
http://localhost:8080/swagger-ui.html#/
```

**Используемые в проекте технологии**
- ```Spring Data Jpa```
- ```Flyway```
- ```ModelMapper```
- ```Camunda BPM```
- ```Apache Kafka```
- ```Spring Boot Actuator```
- ```Swagger```
- ```Docker```
