## 프로젝트 개요
Spring Boot 4.1.0 + Java 17 기반 스터디 카페 웹 애플리케이션

## 의존성 주입 (DI)
- `@Autowired`를 이용한 필드 주입은 절대 사용하지 않는다.
- 반드시 Lombok의 `@RequiredArgsConstructor`를 이용한 **생성자 주입(Constructor Injection)**을 사용한다.

## JPA 및 Entity 규칙
- Entity 클래스에는 무한 참조 문제가 발생할 수 있으므로 `@Data`나 `@ToString` 어노테이션을 절대 사용하지 않는다.
- Entity의 Setter 메서드 사용은 지양하고, 대신 의미 있는 비즈니스 메서드(예: `changePassword()`)를 만들어 사용한다.

## 커밋 및 주석 (Language)
- 코드 내의 주석, 변수/메서드에 대한 설명은 모두 **한국어(Korean)**로 작성한다.
- 커밋 메시지는 'feat: ~', 'fix: ~'와 같이 Conventional Commits 형식을 따른다.


## Build and Development Commands

### Gradle 기본 명령어 (PowerShell)

```powershell
# 프로젝트 빌드
cd api
./gradlew build

# 단일 테스트 실행
./gradlew test --tests "클래스명"

# 모든 테스트 실행
./gradlew test

# 애플리케이션 실행 (개발 모드)
./gradlew bootRun

# 깨끗한 빌드 (기존 빌드 산출물 제거)
./gradlew clean build

# 의존성 확인
./gradlew dependencies
```

### Gradle 기본 명령어 (Bash)

```bash
cd api
./gradlew build
./gradlew test
./gradlew bootRun
./gradlew test --tests "ClassName"
./gradlew clean build
```

## Project Structure

```
api/
├── src/main/java/com/study24/api/       # 애플리케이션 소스 코드
│   └── ApiApplication.java               # Spring Boot 애플리케이션 진입점
├── src/main/resources/
│   └── application.properties             # Spring Boot 설정 파일
├── src/test/java/com/study24/api/       # 테스트 코드
├── build.gradle                          # Gradle 빌드 설정
└── gradle/                               # Gradle Wrapper
```

## Key Technologies

- **Java 25**: 최신 Java 버전 사용
- **Spring Boot 4.1.0**: 웹 애플리케이션 프레임워크
- **Spring Data JPA**: ORM 및 데이터 접근
- **Spring Web MVC**: REST API 및 웹 컨트롤러
- **JUnit**: 단위 테스트 프레임워크

## Development Notes

1. **Java 버전**: 프로젝트는 Java 25를 사용하도록 설정되어 있습니다. 로컬 환경에서 이 버전이 설치되어 있는지 확인하세요.

2. **애플리케이션 진입점**: `ApiApplication.java`에서 Spring Boot 애플리케이션이 시작됩니다.

3. **테스트 프레임워크**: JUnit Platform을 사용하여 테스트가 실행됩니다.

4. **설정**: `application.properties`에서 Spring Boot 관련 설정을 관리합니다.

## Gradle Wrapper

이 프로젝트는 Gradle Wrapper를 사용하므로, 로컬에 Gradle을 설치할 필요가 없습니다:

- Windows: `gradlew.bat` 사용
- Unix/Linux/Mac: `gradlew` 사용