### Проект с тестами по REST API с Allure

---

### Запуск тестов
```sh
gradle clean test
```

#### Запуск Allure отчета
```sh
allure serve build/allure-results
```

---
### Библиотека JsonUnit

Для сравнения двух JSON (один возвращается в ответе, другой подготовлен в виде ожидаемого 
ответа) используется библиотека JsonUnit. Интегрирована в Allure.

* [GitHub](https://github.com/lukas-krecan/JsonUnit)
* [Статья](https://viclovsky.github.io/%D0%B0%D0%B2%D1%82%D0%BE%D1%82%D0%B5%D1%81%D1%82%D1%8B%20%D0%BD%D0%B0%20api/%D0%B0%D0%B2%D1%82%D0%BE%D1%82%D0%B5%D1%81%D1%82%D1%8B%20%D0%BD%D0%B0%20web/2018/08/07/jsonunit/)
* [Allure JsonUnit](https://github.com/allure-framework/allure-java#allure-jsonunit)

Для подключения необходимо указать зависимость в ``build.gradle``
```groovy
"io.qameta.allure:allure-jsonunit:2.17.2"
```
В Gradle ядро почему-то не подтянулось, пришлось дополнительно указать на него зависимость
```groovy
"net.javacrumbs.json-unit:json-unit-core:2.28.0"
```
Примеры использования библиотеки JsonUnit в тестах. 

---
### Библиотека Swagger-coverage

Используется для визуализации тестового покрытия REST API.

* [GitHub](https://github.com/viclovsky/swagger-coverage)

Для подключения необходимо указать зависимость в ``build.gradle``
```groovy
"com.github.viclovsky:swagger-coverage-rest-assured:1.4.4"
```
Добавить фильтр в RestAssured
```
RestAssured.given().filter(new SwaggerCoverageRestAssured())
```
Для настройки добавить файл coverage.json в resources.

###Настройка в Jenkins

1. Т.к. директория swagger-coverage-output располагается в корне проекта (жестко задано 
в коде), необходимо перед выполнением сборки добавить команду на удаление директории 
для очистки старых результатов
```sh
#delete directory swagger-coverage-output
rm -rf swagger-coverage-output
```
2. После выполнение сборки добавить команду на скачивание swagger-coverage-commandline
```sh
cd ..

# Download swagger-coverage-commandline
FILE=./swagger-coverage-1.4.4.zip
if [ ! -f "$FILE" ]; then
   wget https://github.com/viclovsky/swagger-coverage/releases/download/1.4.4/swagger-coverage-1.4.4.zip
   unzip swagger-coverage-1.4.4.zip
fi
```
3. Добавить команду на скачивание документации Swagger тестируемого сервиса и запуск swagger-coverage-commandline
```sh
cd build

# Download Swagger specification from the test service
wget -O swagger.json https://petstore.swagger.io/v2/swagger.json

#Run swagger-coverage report
/home/jenkins/workspace/swagger-coverage-commandline-1.4.4/bin/swagger-coverage-commandline -c resources/test/coverage.json -s swagger.json -i ../swagger-coverage-output
```
Где последовательно:
* Путь к swagger-coverage-commandline
* Путь к конфигурации (ключ ``-c``)
* Путь к скаченной Swagger документации (ключ ``-s``)
* Путь к сгенерированным файлам (ключ ``-i``)

4. Добавить создание HTML отчета, например, через плагин Publish HTML. В нем указать

* HTML directory to archive - ``build``
* Index page[s] - ``swagger-coverage-report.html`` (название из файла coverage.json)
* Report title - ``Swagger-coverage``

5. После выполнения сборки появится HTML отчет.
Если в отчете в Jenkins не отображаются стили CSS, то решение описано по [ссылке](https://stackoverflow.com/questions/35783964/jenkins-html-publisher-plugin-no-css-is-displayed-when-report-is-viewed-in-j/35785788#35785788).