
# 开发规范指南

为保证代码质量、可维护性、安全性与可扩展性，请在开发过程中严格遵循以下规范。

## 一、项目基本信息

- **用户工作目录**：`/Users/zhanshuchan/IdeaProjects/study`
- **操作系统版本**：Mac OS X
- **当前时间**：2025-11-14 15:00:36
- **代码作者**：zhanshuchan

## 二、目录结构

```bash
study
├── activemq-data
│   └── localhost
│       └── KahaDB
├── architecture
├── com
│   └── sun
│       └── proxy
├── common
│   └── src
│       └── main
│           └── java
│               └── cn
│                   └── com
│                       └── zhanss
│                           └── common
│                               └── exception
├── data-structure
│   └── src
│       └── main
│           ├── java
│           │   └── cn
│           │       └── com
│           │           └── zhanss
│           │               └── datastructure
│           │                   ├── array
│           │                   ├── common
│           │                   │   ├── entity
│           │                   │   └── utils
│           │                   ├── doexercise
│           │                   │   ├── binary
│           │                   │   ├── dichotomy
│           │                   │   ├── lru
│           │                   │   ├── number
│           │                   │   └── random
│           │                   ├── graph
│           │                   ├── heap
│           │                   ├── linkedlist
│           │                   │   ├── doubly
│           │                   │   └── single
│           │                   ├── map
│           │                   ├── queue
│           │                   ├── sparsearray
│           │                   ├── stack
│           │                   ├── str
│           │                   └── tree
│           │                       ├── bstAVL
│           │                       ├── doexercise
│           │                       └── rbt
│           └── resources
├── design-mode
│   └── src
│       └── main
│           └── java
│               └── cn
│                   └── com
│                       └── zhanss
│                           └── creationalpatterns
├── elasticsearch
│   └── src
│       └── main
│           ├── java
│           │   └── cn
│           │       └── com
│           │           └── zhanss
│           │               └── elasticsearch
│           │                   ├── config
│           │                   ├── controller
│           │                   ├── dto
│           │                   ├── service
│           │                   │   └── impl
│           │                   └── vo
│           └── resources
├── hadoop
│   └── src
│       └── main
│           └── java
│               └── cn
│                   └── com
│                       └── zhanss
│                           └── hadoop
│                               └── temperature
├── javaee
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── cn
│       │   │       └── com
│       │   │           └── zhanss
│       │   │               └── javaee
│       │   │                   ├── juc
│       │   │                   ├── jvm
│       │   │                   └── test
│       │   └── resources
│       │       └── META-INF
│       └── test
│           └── java
│               └── cn
│                   └── com
│                       └── zhanss
│                           └── javaee
├── kafka
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── cn
│       │   │       └── com
│       │   │           └── zhanss
│       │   │               └── kafka
│       │   │                   ├── consumer
│       │   │                   └── producer
│       │   └── resources
│       └── test
│           └── java
│               └── cn
│                   └── com
│                       └── zhanss
│                           └── kafka
├── nosql
│   ├── src
│   │   └── main
│   │       ├── java
│   │       │   └── cn
│   │       │       └── com
│   │       │           └── zhanssl
│   │       │               └── nosql
│   │       └── resources
│   │           └── templates
│   └── web
│       └── WEB-INF
├── queue
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── cn
│       │   │       └── com
│       │   │           └── zhanss
│       │   │               └── queue
│       │   │                   └── queue
│       │   └── resources
│       └── test
│           └── java
│               └── cn
│                   └── com
│                       └── zhanss
│                           └── queue
│                               └── interview
├── spring
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── cn
│       │   │       └── com
│       │   │           └── zhanss
│       │   │               ├── annotation
│       │   │               │   ├── dao
│       │   │               │   ├── service
│       │   │               │   └── utils
│       │   │               ├── spring
│       │   │               │   ├── aop
│       │   │               │   ├── entity
│       │   │               │   ├── factory
│       │   │               │   └── service
│       │   │               │       └── impl
│       │   │               └── springboot
│       │   │                   ├── conditional
│       │   │                   ├── config
│       │   │                   ├── enable
│       │   │                   └── myimport
│       │   └── resources
│       │       └── my
│       └── test
├── springboot-mq-consumer
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── cn
│       │   │       └── com
│       │   │           └── zhanss
│       │   │               └── springbootmqconsumer
│       │   │                   └── activemq
│       │   └── resources
│       └── test
│           └── java
│               └── cn
│                   └── com
│                       └── zhanss
│                           └── springbootmqconsumer
├── springboot-mq-produce
│   └── src
│       ├── main
│       │   ├── java
│       │   │   ├── cn
│       │   │   │   └── com
│       │   │   │       └── zhanss
│       │   │   │           └── springbootmqproduce
│       │   │   │               └── activemq
│       │   │   │                   ├── config
│       │   │   │                   └── producer
│       │   │   ├── java
│       │   │   │   └── lang
│       │   │   └── test
│       │   └── resources
│       └── test
│           └── java
│               └── cn
│                   └── com
│                       └── zhanss
│                           └── springbootmqproduce
├── thread-io
│   ├── io
│   │   └── src
│   │       └── main
│   │           └── java
│   │               └── cn
│   │                   └── com
│   │                       └── zhanss
│   │                           └── io
│   │                               ├── client
│   │                               └── server
│   │                                   └── step1
│   └── thread
│       └── src
│           ├── main
│           │   ├── java
│           │   │   └── cn
│           │   │       └── com
│           │   │           └── zhanss
│           │   │               ├── juc
│           │   │               ├── jvm
│           │   │               │   └── proxy
│           │   │               └── thread
│           │   └── resources
│           └── test
│               └── java
│                   └── cn
│                       └── com
│                           └── zhanss
│                               └── thread
├── wework
│   ├── src
│   │   └── main
│   │       ├── java
│   │       │   └── cn
│   │       │       └── com
│   │       │           └── zhanss
│   │       │               └── wework
│   │       │                   ├── controller
│   │       │                   ├── entity
│   │       │                   ├── enums
│   │       │                   ├── javaapi
│   │       │                   │   ├── aes
│   │       │                   │   ├── annotation
│   │       │                   │   ├── lambda
│   │       │                   │   ├── manager
│   │       │                   │   ├── parser
│   │       │                   │   │   └── xml
│   │       │                   │   │       └── entity
│   │       │                   │   ├── pool
│   │       │                   │   ├── producerconsumer
│   │       │                   │   └── reflect
│   │       │                   └── service
│   │       │                       └── impl
│   │       └── resources
│   │           └── templates
│   └── web
│       └── WEB-INF
├── zhanss-mvc
│   ├── src
│   │   └── main
│   │       ├── java
│   │       │   └── com
│   │       │       └── zhanss
│   │       │           ├── controller
│   │       │           ├── entity
│   │       │           ├── framework
│   │       │           │   ├── annotation
│   │       │           │   ├── context
│   │       │           │   ├── handler
│   │       │           │   ├── servlet
│   │       │           │   └── xml
│   │       │           └── service
│   │       │               └── impl
│   │       └── resources
│   └── web
│       └── WEB-INF
└── zookeeper
    └── src
        ├── main
        │   ├── java
        │   │   └── cn
        │   │       └── com
        │   │           └── zhanss
        │   │               └── zookeeper
        │   │                   ├── config
        │   │                   ├── controller
        │   │                   └── service
        │   │                       └── impl
        │   └── resources
        └── test
            └── java
                └── cn
                    └── com
                        └── zhanss
                            └── zookeeper
```

## 三、技术栈要求

- **主框架**：
  - Spring Boot 2.7.4
  - Spring Boot 3.1.1
- **语言版本**：Java 1.8 / Java 17
- **构建工具**：Maven
- **SDK版本**：
  - JDK 1.8.0_341
  - JDK 17 (用于 architecture 模块)

## 四、分层架构规范

| 层级        | 职责说明                         | 开发约束与注意事项                                               |
|-------------|----------------------------------|----------------------------------------------------------------|
| **Controller** | 处理 HTTP 请求与响应，定义 API 接口 | 不得直接访问数据库，必须通过 Service 层调用                  |
| **Service**    | 实现业务逻辑、事务管理与数据校验   | 必须通过 Repository 层访问数据库；返回 DTO 而非 Entity（除非必要） |
| **Repository** | 数据库访问与持久化操作             | 继承 `JpaRepository`；使用 `@EntityGraph` 避免 N+1 查询问题     |
| **Entity**     | 映射数据库表结构                   | 不得直接返回给前端（需转换为 DTO）；包名统一为 `entity`         |

### 接口与实现分离

- 所有接口实现类需放在接口所在包下的 `impl` 子包中。

## 五、安全与性能规范

### 输入校验

- 使用 `@Valid` 与 JSR-303 校验注解（如 `@NotBlank`, `@Size` 等）
  - 注意：Spring Boot 3.x 中校验注解位于 `jakarta.validation.constraints.*`

- 禁止手动拼接 SQL 字符串，防止 SQL 注入攻击。

### 事务管理

- `@Transactional` 注解仅用于 **Service 层**方法。
- 避免在循环中频繁提交事务，影响性能。

## 六、代码风格规范

### 命名规范

| 类型       | 命名方式             | 示例                  |
|------------|----------------------|-----------------------|
| 类名       | UpperCamelCase       | `UserServiceImpl`     |
| 方法/变量  | lowerCamelCase       | `saveUser()`          |
| 常量       | UPPER_SNAKE_CASE     | `MAX_LOGIN_ATTEMPTS`  |

### 注释规范

- 所有类、方法、字段需添加 **Javadoc** 注释。
- 使用中文注释（第一语言）

### 类型命名规范（阿里巴巴风格）

| 后缀 | 用途说明                     | 示例         |
|------|------------------------------|--------------|
| DTO  | 数据传输对象                 | `UserDTO`    |
| DO   | 数据库实体对象               | `UserDO`     |
| BO   | 业务逻辑封装对象             | `UserBO`     |
| VO   | 视图展示对象                 | `UserVO`     |
| Query| 查询参数封装对象             | `UserQuery`  |

### 实体类简化工具

- 使用 Lombok 注解替代手动编写 getter/setter/构造方法：
  - `@Data`
  - `@NoArgsConstructor`
  - `@AllArgsConstructor`

## 七、扩展性与日志规范

### 接口优先原则

- 所有业务逻辑通过接口定义（如 `UserService`），具体实现放在 `impl` 包中（如 `UserServiceImpl`）。

### 日志记录

- 使用 `@Slf4j` 注解代替 `System.out.println`

## 八、编码原则总结

| 原则       | 说明                                       |
|------------|--------------------------------------------|
| **SOLID**  | 高内聚、低耦合，增强可维护性与可扩展性     |
| **DRY**    | 避免重复代码，提高复用性                   |
| **KISS**   | 保持代码简洁易懂                           |
| **YAGNI**  | 不实现当前不需要的功能                     |
| **OWASP**  | 防范常见安全漏洞，如 SQL 注入、XSS 等      |

## 九、通用规则总结

### 依赖管理

- 项目采用 Maven 进行依赖管理，所有依赖均在父 pom.xml 中统一管理。
- 使用了大量第三方库，如 Spring、Dubbo、MyBatis、Hutool、Guava 等。
- 特别注意版本兼容性，例如 Spring Boot 2.7.4 和 Java 1.8 的组合。

### 构建工具

- 项目基于 Maven 构建，使用 `spring-boot-maven-plugin` 插件进行打包和部署。
- 部分模块使用了自定义编译插件，如 `maven-compiler-plugin` 来设置 Java 版本。

### 代码规范

- 项目中广泛使用了 Lombok 注解来简化代码，减少样板代码。
- 使用了阿里巴巴 Java 开发手册中的命名规范和类型命名规范。
- 项目中包含多个子模块，每个模块都有明确的功能划分和依赖关系。

### 项目结构

- 项目结构清晰，按照功能模块划分为多个子模块，便于管理和维护。
- 每个模块都有自己的 pom.xml 文件，用于管理该模块的依赖和构建配置。
- 项目的资源文件和源代码都按照标准的 Java 项目结构组织。

### 测试策略

- 项目中大部分模块都包含了单元测试，使用 JUnit 进行测试。
- 部分模块使用了 Spring Boot 提供的测试支持，如 `@SpringBootTest`。
- 对于一些复杂的模块，如 Kafka、ActiveMQ、Zookeeper 等，也提供了相应的集成测试。

### 安全实践

- 在处理用户输入时，严格按照 OWASP 安全建议进行校验。
- 避免在代码中硬编码敏感信息，如密码、密钥等。
- 使用 Spring Security 或其他安全框架来保护应用的安全性。

### 性能优化

- 使用缓存机制提升系统性能，如 Redis。
- 通过合理的索引设计优化数据库查询性能。
- 对于高并发场景，使用线程池和异步处理机制提高响应速度。
