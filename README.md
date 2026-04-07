# Ads Service Backend

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.15-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)
![REST API](https://img.shields.io/badge/API-REST-green)
![Security](https://img.shields.io/badge/Security-Spring%20Security-red)
![Docker](https://img.shields.io/badge/Docker-Ready-blue)

Backend-сервис для размещения объявлений в духе Avito / Юла.

Проект реализует полноценный REST API с авторизацией, загрузкой изображений, комментариями и управлением пользователями. Документация API доступна через Swagger UI.

## Table of Contents

- [Features](#features)
- [Технологии](#технологии)
- [Архитектура](#архитектура)
- [Project Structure](#project-structure)
- [API](#api)
- [Безопасность](#безопасность)
- [Работа с изображениями](#работа-с-изображениями)
- [База данных](#база-данных)
- [Быстрый старт через Docker](#быстрый-старт-через-docker)
- [Локальный запуск](#локальный-запуск)
- [Текущее состояние проекта](#текущее-состояние-проекта)

## Features

- Authentication & Authorization
- Ads management
- Comments system
- Image upload
- User profile
- Role-based access

## Что умеет сервис

- регистрация пользователя
- вход в систему
- просмотр всех объявлений без авторизации
- просмотр объявления по `id`
- создание, редактирование и удаление объявлений
- загрузка и обновление изображений объявлений
- получение и редактирование профиля пользователя
- смена пароля
- загрузка аватара пользователя
- просмотр, добавление, редактирование и удаление комментариев

## Технологии

- Java 17
- Spring Boot 2.7.15
- Spring Web
- Spring Security
- Spring Data JPA
- Hibernate
- PostgreSQL
- Springdoc OpenAPI
- Lombok
- Docker Compose

## Архитектура

Проект построен по классической многослойной схеме:

`Controller -> Service -> Repository -> Database`

Наглядно поток запроса выглядит так:

```text
[Client]
   ↓
[Controller]
   ↓
[Service]
   ↓
[Repository]
   ↓
[Database]
```

Основные пакеты:

- `config` - конфигурация Spring, CORS и безопасности
- `controller` - REST endpoints
- `dto` - объекты запросов и ответов
- `entity` - JPA-сущности
- `mapper` - преобразование `Entity <-> DTO`
- `repository` - доступ к данным
- `service` - бизнес-логика
- `exception` - централизованная обработка ошибок

## Project Structure

```text
src/main/java
 ├── config
 ├── controller
 ├── dto
 ├── entity
 ├── mapper
 ├── repository
 ├── service
 └── exception
```

## API

Основные группы endpoint-ов:

- `POST /register` - регистрация пользователя
- `POST /login` - вход в систему
- `GET /ads` - список всех объявлений
- `GET /ads/{id}` - объявление по идентификатору
- `GET /ads/{id}/comments` - комментарии объявления
- `GET /users/me` - профиль текущего пользователя
- `GET /images/{fileName}` - получение изображения

Endpoint-ы, требующие авторизации:

- `GET /ads/me`
- `POST /ads`
- `PATCH /ads/{id}`
- `DELETE /ads/{id}`
- `PATCH /ads/{id}/image`
- `POST /ads/{id}/comments`
- `PATCH /ads/{adId}/comments/{commentId}`
- `DELETE /ads/{adId}/comments/{commentId}`
- `PATCH /users/me`
- `PATCH /users/me/image`
- `POST /users/set_password`

Swagger UI:

`http://localhost:8080/swagger-ui/index.html`

## Безопасность

Используется Spring Security с HTTP Basic authentication.

Открыты без авторизации:

- `POST /login`
- `POST /register`
- `GET /ads/**`
- `GET /images/**`
- Swagger endpoints

Роли:

- `USER`
- `ADMIN`

Пользователь может изменять свои объявления и комментарии. Администратор имеет доступ к любым объявлениям и комментариям.

## Работа с изображениями

Изображения сохраняются в файловой системе, а в базе хранится путь к файлу.

Каталог задается свойством:

`app.images.dir`

По умолчанию изображения сохраняются в папку:

`images/`

## База данных

Используется PostgreSQL.

Основные сущности:

- `UserEntity`
- `AdEntity`
- `CommentEntity`

Связи:

- пользователь -> объявления (`OneToMany`)
- пользователь -> комментарии (`OneToMany`)
- объявление -> комментарии (`OneToMany`)

## Быстрый старт через Docker

Запуск проекта:

```bash
docker compose up --build
```

После старта будут доступны:

- backend: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- PostgreSQL: `localhost:5433`

Параметры базы данных:

- database: `ads`
- username: `ads_user`
- password: `ads_password`

## Локальный запуск

Требования:

- Java 17
- PostgreSQL

Приложение читает настройки из переменных окружения и имеет значения по умолчанию:

```properties
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5433/ads
SPRING_DATASOURCE_USERNAME=ads_user
SPRING_DATASOURCE_PASSWORD=ads_password
SERVER_PORT=8080
APP_IMAGES_DIR=images
```

1. Поднимите PostgreSQL с базой `ads`.
2. При необходимости задайте переменные окружения.
3. Запустите приложение:

```bash
./mvnw spring-boot:run
```

## Текущее состояние проекта

Сейчас в проекте есть базовое покрытие стартовым тестом Spring context, но полноценные unit и integration tests еще не добавлены.

Что логично развивать дальше:

- пагинация объявлений
- сортировка и фильтрация
- unit tests
- integration tests
- CI/CD pipeline

## Автор

Артур Старовойт  
Java Backend Developer  
GitHub: https://github.com/Arthur-Starovoyt
