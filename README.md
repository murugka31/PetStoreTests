<p align="left">
<img src="https://readme-typing-svg.herokuapp.com?color=32CD32&font=Sigmar&size=40&right=true&vCenter=true&lines=++Ваш+зоомагазин+!+++">
</p> 

![2023-04-23_18-46-17](https://user-images.githubusercontent.com/118747260/233849913-de578971-014f-468e-bc3b-568fab8eae84.png)

## Автоматизация API тестов на основе  [Swagger Petstore](https://petstore.swagger.io//)

## :dog: :cat: :rabbit: Языки и инструменты, которые были использованы

| Java | JUnit5 | Rest-assured | Gradle | Intelij IDEA | Allure Report | Allure Testops | Jenkins | Telegram |
|------|--------|--------------|--------|--------------|---------------|----------------|---------|----------|
|![](images/JAVA.svg)|![](images/Junit5.svg)|<img src="images/Rest-assured.png" width=70 height=70>|![](images/Gradle.svg)|![](images/IDEA.svg)|![](images/AllureReport.svg)|![](images/AllureTestops.svg)|![](images/Jenkins.svg)|![](images/Telegram.svg)|

## :dog: :cat: :rabbit: Интеграция с Jenkins
![2023-04-23_17-29-10](https://user-images.githubusercontent.com/118747260/233848633-2ec07b2e-7965-4dde-91c7-d0e2790a94a4.png)
В Jenkins запускается сборка с параметрами:
* Выбор, какие тесты нужно проходить
    * test - выполнение всех тестов
    * pet_tests - выполнение тестов для проверки функционала "pet"
    * store_tests - выполнение тестов для проверки функционала "store"
    * user_tests - выполнение тестов для проверки функционала "user"
* Выбор опции параллельного прохождения тест-кейсов
* Выбор количества тест-кейсов для параллельного запуска

Run all tests:
```bash
gradle clean test
```
Run certain test:
```bash
gradle clean pet_tests
gradle clean store_tests 
gradle clean user_tests
```

## :dog: :cat: :rabbit: Интеграция c Allure Report
В Allure Report отображается вся информация по пройденной сборке
![2023-04-23_18-25-01](https://user-images.githubusercontent.com/118747260/233848766-00fe4ed7-21f0-4caf-9a22-4ad8cbf7e50a.png)

В Allure Report отображается вся информация по тест-кейсам в пройденной сборке
![2023-04-23_17-33-10](https://user-images.githubusercontent.com/118747260/233848803-0f53fa05-e941-41f3-bc47-13c25be4fd51.png)

## :dog: :cat: :rabbit: Интеграция c Allure TestOps
В Allure TestOps хранится информация по всем ручным и автотестам проекта, по запускам сборок, а также отчет по пройденным сборкам.
![2023-04-23_17-38-55](https://user-images.githubusercontent.com/118747260/233849390-881074a9-bed9-4283-9b8f-1aafba2cb956.png)

![2023-04-23_18-39-07](https://user-images.githubusercontent.com/118747260/233849545-7793f64e-fd62-4004-95ae-bd4f2c5e178f.png)

![2023-04-23_17-41-06](https://user-images.githubusercontent.com/118747260/233849405-73d3d3d8-285a-4c04-8900-ea4fec980ab0.png)

## :dog: :cat: :rabbit: Интеграция c Теlegram
Уведомления о прохождении тестов приходят в телеграм канал.

https://user-images.githubusercontent.com/118747260/233849143-b49e4b18-11a4-43f7-a657-392169de194c.mp4



:heart: <a target="_blank" href="https://github.com/murugka31">github.com/murugka31</a><br/>
