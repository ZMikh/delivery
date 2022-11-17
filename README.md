### Бизнес-процесс. Сервис доставки

Для запуска проекта выполнить команды в директории проекта

```
mvn package 
docker compose up
```

**Конфигурация**

- Переменные окружения для настройки проекта:
    - ```DATABASE_URL``` - url для подключения к БД
    - ```DATABASE_USERNAME``` - username БД
    - ```DATABASE_PASSWORD``` - password для входа в БД
    - ```KAFKA_GROUP_ID``` - идентификатор группы для подключения consumers
    - ```KAFKA_SERVER``` - имя host

- Приложение запускается под портом ```8080```


- Использование Swagger для тестирования endpoints

```
http://localhost:8080/swagger-ui.html#/
```

**Особенности работы с endpoints**

Согласно Camunda BPM в проекте определены следующие Event ID:

- ```newState```
    - стартовое событие

- ```inProcessingState```
    - заказ принят в обработку
    - устанавливается после отправки POST запроса по endpoint "Создание доставки"
    - при отправке GET запроса по id значение поля "state" соответствует **IN_PROCESSING**

- ```registeredState```
    - заказ оформлен
    - устанавливается после создания доставки и отправки POST запроса по endpoint "Подтверждение заказа"
        - в параметре "confirmationState" указывается значение на выбор
            - ```CONFIRMED```  - продолжить работу с заказом
            - ```CANCELLED``` - отменить заказ

- ```paymentErrorState```
    - неудачная попытка оплаты
    - устанавливается после подтверждения заказа и наличия в поле "description" значения, содержащего "error"
    - при отправке GET запроса по id значение поля "state" соответствует **PAYMENT_ERROR**

- ```paidState```
    - заказ оплачен
    - устанавливается после подтверждения и оплаты заказа
        - в параметре "confirmationState" указывается значение ```CONFIRMED```
    - при отправке GET запроса по id значение поля "state" соответствует **PAID**

- ```finishedState```
    - заказ выполнен
    - устанавливается после оплаты и завершения доставки
        - доставка может завершаться двумя способами
            1. доставкой курьером с выполнением условий ниже
                - _прим.: на момент отправки/получения message заказ по отправляемому id должен быть подтвержден и в поле "isPickUp" записано false_
                - **отправлена информация** о доставке курьером
                    - для чтения message из topic выполняется команда в терминале
                      ```docker exec --interactive --tty broker_delivery kafka-console-consumer --bootstrap-server broker_delivery:9092 --topic deliveryInformation --from-beginning```
                        - message передается в формате JSON, e.g. ```{"id":3}```
                - **получена информация** о завершении доставки по message "delivery_finish_message"
                    - для записи message - 
                      ```docker exec --interactive --tty broker_delivery kafka-console-producer --bootstrap-server broker_delivery:9092 --topic deliveryFinishMessage```
                        - message передается в формате JSON, e.g. ```{"id":3}```
                    - для чтения message - 
                      ```docker exec --interactive --tty broker_delivery kafka-console-consumer --bootstrap-server broker_delivery:9092 --topic deliveryFinishMessage --from-beginning```

            2. самовывозом заказа
                - при подтверждении заказа в поле "isPickUp" записывается true и отправляется POST запрос по endpoint "Выдача заказа клиенту самовывозом"
    - при отправке GET запроса по id значение поля "state" соответствует **FINISHED**

- ```clientCancellationState```
    - клиент отменил заказ
    - устанавливается на этапе подтверждения в случае отмены заказа клиентом
        - в параметре "confirmationState" указывается значение ```CANCELLED```
    - при отправке GET запроса по id значение поля "state" соответствует **CLIENT_CANCELLATION**

- ```techErrorState```
    - заказ отменен по техническим причинам
    - устанавливается после получения "cancel_message"
        - для записи message -
          ```docker exec --interactive --tty broker_delivery kafka-console-producer --bootstrap-server broker_delivery:9092 --topic cancelMessage```
            - message передается в формате JSON, e.g. ```{"id":3}```
    - при отправке GET запроса по id значение поля "state" соответствует **TECH_ERROR**

**Используемые в проекте технологии**

- ```Spring Data Jpa```
- ```Flyway```
- ```ModelMapper```
- ```Camunda BPM```
- ```Apache Kafka```
- ```Swagger```
- ```Docker```
