# springboot-demo

一个面向学习的 **Spring Boot 学生信息管理系统**，用于前端开发者系统练习 Java 后端开发流程：  
从接口设计、数据库连接、业务分层到异常处理与基础工程化。

---

## 1. 项目简介

这是一个以“学生信息管理”为核心场景的后端练手项目。  
目标不是做复杂业务，而是通过真实 CRUD 场景掌握 Spring Boot 项目的完整开发链路。

你可以把它理解为：

- 前端视角：消费学生管理接口，完成页面与接口联调
- 后端视角：练习 Controller / Service / Mapper 分层与 DTO-VO 模型转换
- 工程视角：掌握 Maven、配置文件、异常统一返回与 SQL 脚本协作

---

## 2. 当前技术栈（基于仓库代码）

- **语言**：Java（100%）
- **框架**：Spring Boot
- **数据访问**：MyBatis Mapper（`StudentMapper`）
- **构建工具**：Maven（`mvnw` / `mvnw.cmd`）
- **配置文件**：`application.properties`
- **异常处理**：`@RestControllerAdvice` + `BusinessException`
- **统一返回**：`Result<T>`

---

## 3. 项目结构（按真实代码）

```text
src/main/java/com/example/springbootdemo
├─ SpringbootDemoApplication.java      # Spring Boot 启动入口
├─ controller/
│  └─ StudentController.java           # 学生 CRUD REST 接口
├─ service/
│  └─ StudentService.java              # 学生业务逻辑
├─ mapper/
│  └─ StudentMapper.java               # 数据访问层接口（MyBatis）
├─ entity/
│  └─ Student.java                     # 实体模型
├─ dto/
│  ├─ StudentCreateDTO.java            # 创建请求模型
│  └─ StudentUpdateDTO.java            # 更新请求模型
├─ vo/
│  └─ StudentVO.java                   # 返回给前端的视图模型
├─ common/
│  └─ Result.java                      # 统一响应结构
└─ exception/
   ├─ BusinessException.java           # 业务异常
   └─ GlobalExceptionHandler.java      # 全局异常处理
```

此外：

- `pom.xml`：依赖管理
- `application.properties`：应用配置
- `reset_pass.sql`：数据库辅助脚本

---

## 4. 系统架构与请求流转（面试可讲）

### 4.1 分层架构

- **Controller 层**：接收 HTTP 请求，解析参数，返回 `Result<T>`
- **Service 层**：封装业务规则（查询、创建、更新、删除）
- **Mapper 层**：定义数据访问接口（如 `findAll()` / `findById()`）
- **模型层**：
  - `DTO`：接收前端请求
  - `Entity`：内部数据对象
  - `VO`：返回前端展示对象

### 4.2 一次请求如何流转

1. 前端请求 `/api/students` 或 `/api/students/{id}`
2. `StudentController` 调用 `StudentService`
3. `StudentService` 调 `StudentMapper` 获取/修改数据
4. Service 将 `Student` 转为 `StudentVO`
5. Controller 用 `Result.success(data)` 返回
6. 若抛出 `BusinessException`，由 `GlobalExceptionHandler` 统一返回错误

---

## 5. 真实接口文档（与当前 Controller 对齐）

> 当前项目已确认的接口来自 `StudentController`，基础路径为：`/api/students`

### 5.1 查询全部学生

- **URL**: `/api/students`
- **Method**: `GET`
- **Request**: 无
- **Response 示例**:

```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "id": 1,
      "studentNo": "S2026001",
      "name": "张三",
      "age": 20,
      "gender": "M"
    }
  ]
}
```

---

### 5.2 查询学生详情

- **URL**: `/api/students/{id}`
- **Method**: `GET`
- **Path 参数**:
  - `id` (Long, 必填)
- **Response 示例**:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "id": 1,
    "studentNo": "S2026001",
    "name": "张三",
    "age": 20,
    "gender": "M"
  }
}
```

---

### 5.3 创建学生

- **URL**: `/api/students`
- **Method**: `POST`
- **Request Body（对应 StudentCreateDTO）**:

```json
{
  "studentNo": "S2026002",
  "name": "李四",
  "age": 19,
  "gender": "F"
}
```

- **Response 示例**:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "id": 2,
    "studentNo": "S2026002",
    "name": "李四",
    "age": 19,
    "gender": "F"
  }
}
```

---

### 5.4 修改学生

- **URL**: `/api/students/{id}`
- **Method**: `PUT`
- **Path 参数**:
  - `id` (Long, 必填)
- **Request Body（对应 StudentUpdateDTO）**:

```json
{
  "studentNo": "S2026002",
  "name": "李四-更新",
  "age": 20,
  "gender": "F"
}
```

- **Response 示例**:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "id": 2,
    "studentNo": "S2026002",
    "name": "李四-更新",
    "age": 20,
    "gender": "F"
  }
}
```

---

### 5.5 删除学生

- **URL**: `/api/students/{id}`
- **Method**: `DELETE`
- **Path 参数**:
  - `id` (Long, 必填)
- **Response 示例**:

```json
{
  "code": 200,
  "message": "success",
  "data": null
}
```

---

### 5.6 错误响应（基于当前异常处理）

- `BusinessException`：返回 `Result.error(code, message)`（例如学生不存在）
- 未知异常：返回

```json
{
  "code": 500,
  "message": "系统异常",
  "data": null
}
```

---

## 6. 本项目已覆盖的关键知识点

- Spring Boot 启动与 REST 接口编写
- DTO / Entity / VO 分离与对象转换
- 统一响应结构设计（`Result<T>`）
- 业务异常设计（`BusinessException`）
- 全局异常处理（`GlobalExceptionHandler`）
- 基于 Mapper 接口的数据访问组织方式

---

## 7. 如何运行（从 0 到可用）

### 7.1 环境准备

- JDK 17+（建议）
- Maven（或直接使用仓库内 Maven Wrapper）

### 7.2 启动命令

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

或：

```bash
./mvnw clean package
java -jar target/*.jar
```

### 7.3 快速验证

启动后请求：

```bash
curl http://localhost:8080/api/students
```

---

## 8. 常见问题排查（学习期高频）

### 8.1 端口占用

现象：应用启动失败，提示端口被占用。  
处理：修改 `application.properties` 里的 `server.port`，或结束占用进程。

### 8.2 JDK 版本不匹配

现象：编译报错（语法/字节码版本）。  
处理：确认 `java -version` 与 `pom.xml` 配置一致。

### 8.3 数据层空结果/空指针

现象：查询返回空，或更新/删除报错。  
处理：先检查 Mapper 实现与数据初始化，再核对 `id` 是否存在。

---

## 9. 项目亮点（简历/面试表达）

- 以学生管理场景完整打通了从请求到数据层再到响应的后端闭环。
- 使用 DTO/VO 分离和统一返回结构，具备基础可维护性与可读性。
- 引入业务异常与全局异常处理，提升接口稳定性和前后端联调体验。
- 作为前端转后端项目，体现了工程化和架构思维的迁移能力。

---

## 10. 学习里程碑（继续迭代）

### 7 天目标

- [ ] 完成所有接口本地联调
- [ ] 给每个接口补充 Postman/Apifox 用例
- [ ] 完成一次 Service 层重构（减少重复查找逻辑）

### 30 天目标

- [ ] 增加参数校验注解（`@NotBlank` / `@NotNull`）
- [ ] 接入 Swagger/OpenAPI 文档
- [ ] 增加登录鉴权（JWT）
- [ ] 增加单元测试与集成测试
- [ ] Docker 化部署

---

## 11. 开发规范（建议）

### 11.1 分支命名

- `feature/*`：新功能
- `fix/*`：Bug 修复
- `docs/*`：文档变更
- `refactor/*`：重构

### 11.2 Commit 规范

- `feat: ...`
- `fix: ...`
- `docs: ...`
- `refactor: ...`
- `test: ...`

示例：

- `feat: add student detail endpoint`
- `fix: handle student not found with business exception`
- `docs: align README API docs with controller`

### 11.3 学习记录规范

建议新增目录：`weekly-log/`

- `week-01.md`
- `week-02.md`
- `week-03.md`

每周记录：

1. 本周新增功能
2. 本周问题与排查过程
3. 下周计划

---

## 12. 后续规划（Roadmap）

- [ ] 把当前内存式 `nextId` 逻辑替换为数据库自增 ID
- [ ] 补齐 Mapper 对应的新增/修改/删除 SQL
- [ ] 增加分页、条件筛选与排序
- [ ] 增加课程管理、成绩管理模块
- [ ] 增加角色权限（管理员/教师/学生）
- [ ] 引入日志追踪与操作审计

---

## 13. 你可以这样使用这个项目

1. 当“教程项目”：逐层阅读 Controller → Service → Mapper 代码  
2. 当“实验项目”：每周新增一个真实业务字段并做全链路改造  
3. 当“作品项目”：完善文档 + 测试 + 部署后用于简历展示
