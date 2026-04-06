# Ads Service Backend

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Docker](https://img.shields.io/badge/Docker-Ready-blue)
![Build](https://img.shields.io/badge/build-passing-brightgreen)

Backend сервис для размещения объявлений (аналог Avito / Юла).

Проект реализует REST API для работы с пользователями, объявлениями, комментариями и изображениями.

---

# Возможности

- Регистрация пользователя
- Авторизация пользователя
- Получение профиля пользователя
- Обновление профиля
- Смена пароля
- Просмотр объявлений без авторизации
- Создание объявления
- Редактирование объявления
- Удаление объявления
- Загрузка изображения объявления
- Добавление комментариев
- Редактирование комментариев
- Удаление комментариев
- Загрузка аватарки пользователя

---

# Технологии

- Java 17
- Spring Boot
- Spring Web
- Spring Security
- Spring Data JPA
- Hibernate
- PostgreSQL
- Lombok
- Swagger / OpenAPI
- Docker

---

# Архитектура

Проект реализован по многослойной архитектуре:

Controller → Service → Repository → Database

### Слои

- Controller — обработка HTTP запросов
- Service — бизнес логика
- Repository — работа с БД
- Entity — сущности базы данных
- DTO — объекты передачи данных
- Mapper — преобразование Entity ↔ DTO

---

# Структура проекта

```text
src/main/java/ru/skypro/homework
├── config
│   ├── WebConfig
│   └── WebSecurityConfig
├── controller
│   ├── AdsController
│   ├── AuthController
│   ├── CommentController
│   ├── ImageController
│   └── UserController
├── service
│   ├── AdService
│   ├── AuthService
│   ├── CommentService
│   ├── ImageService
│   ├── MyUserDetailsService
│   └── UserService
├── repository
│   ├── AdRepository
│   ├── CommentRepository
│   └── UserRepository
├── entity
│   ├── AdEntity
│   ├── CommentEntity
│   └── UserEntity
├── mapper
│   ├── AdMapper
│   ├── CommentMapper
│   └── UserMapper
└── exception
    └── GlobalExceptionHandler
```

---

# Безопасность

Используется Spring Security.

Реализована ролевая модель:

- USER
- ADMIN

Права пользователей:

USER:
- редактирование своих объявлений
- удаление своих объявлений
- редактирование своих комментариев

ADMIN:
- редактирование любых объявлений
- удаление любых объявлений
- редактирование любых комментариев

---

# Работа с изображениями

Изображения сохраняются в файловой системе.

В базе данных хранится путь к изображению.

Получение изображения:

GET /images/{fileName}

---

# База данных

Используется PostgreSQL.

Сущности:

- UserEntity
- AdEntity
- CommentEntity

Связи:

- пользователь → объявления (OneToMany)
- пользователь → комментарии (OneToMany)
- объявление → комментарии (OneToMany)

---

# Запуск через Docker

Запуск проекта одной командой:

```bash
docker compose up --build
```

После запуска приложение будет доступно:

Backend:  
http://localhost:8080

Swagger:  
http://localhost:8080/swagger-ui/index.html

PostgreSQL:  
localhost:5433

Database:  
ads_db

Username:  
ads_user

Password:  
ads_password

---

# Запуск проекта локально

### 1. Запустить PostgreSQL

### 2. Настроить application.properties

### 3. Запустить приложение

```bash
mvn spring-boot:run
```

Swagger:

http://localhost:8080/swagger-ui/index.html

---

# Возможные улучшения

- Pagination для объявлений
- Сортировка объявлений
- Фильтрация объявлений
- Unit тесты
- Интеграционные тесты
- CI/CD
- Docker Registry
- Kubernetes deployment

---

# Автор

Артур Старовойт  
Java Backend Developer