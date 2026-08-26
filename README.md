# springboot-demo

一个面向学习的 **Spring Boot 学生信息管理系统**，用于前端开发者系统练习 Java 后端开发流程：  
从接口设计、数据库连接、业务分层到基础权限与项目工程化。

---

## 1. 项目简介

这是一个以“学生信息管理”为核心场景的后端练手项目。  
目标不是做复杂业务，而是通过真实 CRUD 场景掌握 Spring Boot 项目的完整开发链路。

你可以把它理解为：

- 前端视角：提供学生增删改查、登录/密码相关接口
- 后端视角：练习 Controller / Service / Mapper 分层、参数校验、异常处理、数据库操作
- 工程视角：学习 Maven、配置文件、SQL 脚本、基础部署与后续扩展思路

---

## 2. 当前技术栈

- **语言**：Java（100%）
- **框架**：Spring Boot
- **构建工具**：Maven（含 `mvnw` / `mvnw.cmd`）
- **配置文件**：`application.properties`
- **数据库相关**：SQL 脚本（如 `reset_pass.sql`）

---

## 3. 目录结构（学习向说明）

```text
springboot-demo/
├─ .mvn/                   # Maven Wrapper 配置
├─ src/                    # 业务源码（核心学习区）
├─ pom.xml                 # Maven 依赖与构建配置
├─ application.properties  # Spring Boot 配置（端口、数据源等）
├─ reset_pass.sql          # 数据库脚本（密码重置场景）
├─ mvnw / mvnw.cmd         # 跨平台 Maven 启动脚本
└─ .gitignore              # Git 忽略规则
```

---

## 4. 这个项目适合练的核心知识点

### 4.1 Java 基础（后端必备）

- 面向对象：类、封装、继承、多态
- 集合与泛型：List / Map、泛型接口与返回值
- 异常机制：try-catch、自定义异常、全局异常处理思想
- 时间与工具类：LocalDateTime、常用字符串处理

### 4.2 Spring Boot 基础

- 启动类与自动装配机制（知道“为什么能跑”）
- Controller：REST 风格接口设计（GET/POST/PUT/DELETE）
- 参数接收与校验：`@RequestParam` / `@PathVariable` / `@RequestBody`
- 配置管理：`application.properties` 多环境意识（dev/test/prod）

### 4.3 分层架构思维（重点）

- Controller：处理请求与响应
- Service：封装业务逻辑
- DAO/Mapper：数据库交互
- Entity/DTO/VO：数据模型分离（逐步规范）

### 4.4 数据库与 SQL

- 表结构设计（学生、用户等）
- 基础 CRUD SQL
- 条件查询、分页查询思路
- 数据初始化与脚本管理（如 `reset_pass.sql`）

### 4.5 工程化能力

- Maven 依赖管理与生命周期
- Git 提交规范（功能分支、原子提交）
- 接口文档意识（建议接入 Swagger/OpenAPI）
- 日志与错误排查基本流程

---

## 5. 学习计划（4 周路线）

> 节奏建议：每天 1~2 小时，周末做总结与重构。

### 第 1 周：能跑 + 看懂结构

目标：

- 本地成功启动项目
- 看懂 `pom.xml`、配置文件、启动入口
- 跑通 1~2 个最基础接口

任务：

- 配置 JDK、Maven 环境
- 阅读并标注主要包结构（controller/service/mapper/entity）
- 画一张“请求从前端到数据库再返回”的流程图

### 第 2 周：CRUD + 分层实践

目标：

- 独立完成学生信息的增删改查
- 理清分层职责，避免 Controller 写业务细节

任务：

- 新增一个业务字段（如年级/班级）并全链路改造
- 给查询接口加分页参数
- 统一返回结构（code/message/data）

### 第 3 周：质量提升（异常、校验、日志）

目标：

- 让接口“可用”变“好用”
- 出错时有统一、可读的错误响应

任务：

- 增加参数校验（不能为空、长度限制等）
- 增加全局异常处理（`@ControllerAdvice`）
- 为关键操作加日志（新增/删除/登录等）

### 第 4 周：进阶与项目包装

目标：

- 完成简历可展示的“学习型后端项目”
- 形成可复用模板

任务：

- 增加简单鉴权（JWT 或 Session 二选一）
- 增加接口文档（Swagger）
- 补充 README：架构图、接口示例、踩坑记录

---

## 6. 前端转 Java 的学习路线图（建议长期执行）

### 阶段 A：后端入门（你当前阶段）

- Java 语法 + Spring Boot CRUD + MySQL 基础
- 关键词：**能开发接口，能连库，能排错**

### 阶段 B：后端进阶

- Spring MVC 深入、MyBatis/JPA、事务、缓存
- 安全与权限（Spring Security / JWT）
- 关键词：**接口稳定、结构清晰、具备可维护性**

### 阶段 C：后端工程化

- Redis、消息队列、Docker、Linux 部署
- 单元测试、性能优化、监控告警
- 关键词：**可上线、可扩展、可协作**

---

## 7. API 接口文档（模板示例）

> 以下为学习阶段的文档模板，可按你的实际 controller 路径调整。

### 7.1 登录

- **URL**: `/api/auth/login`
- **Method**: `POST`
- **Request Body**:

```json
{
  "username": "admin",
  "password": "123456"
}
```

- **Response 示例**:

```json
{
  "code": 0,
  "message": "success",
  "data": {
    "token": "jwt-token-demo",
    "expireAt": "2026-08-26T23:59:59"
  }
}
```

---

### 7.2 查询学生列表

- **URL**: `/api/students`
- **Method**: `GET`
- **Query 参数**:
  - `page` (int, 可选, 默认 1)
  - `size` (int, 可选, 默认 10)
  - `name` (string, 可选)

- **Response 示例**:

```json
{
  "code": 0,
  "message": "success",
  "data": {
    "total": 2,
    "records": [
      {"id": 1, "name": "张三", "age": 20, "gender": "M"},
      {"id": 2, "name": "李四", "age": 21, "gender": "F"}
    ]
  }
}
```

---

### 7.3 新增学生

- **URL**: `/api/students`
- **Method**: `POST`
- **Request Body**:

```json
{
  "name": "王五",
  "age": 19,
  "gender": "M",
  "grade": "大一"
}
```

- **Response 示例**:

```json
{
  "code": 0,
  "message": "created",
  "data": {"id": 3}
}
```

---

### 7.4 更新学生

- **URL**: `/api/students/{id}`
- **Method**: `PUT`
- **Path 参数**:
  - `id` (long, 必填)
- **Request Body**:

```json
{
  "name": "王五",
  "age": 20,
  "grade": "大二"
}
```

---

### 7.5 删除学生

- **URL**: `/api/students/{id}`
- **Method**: `DELETE`
- **Path 参数**:
  - `id` (long, 必填)

---

### 7.6 通用错误码建议

- `0`：成功
- `4001`：参数错误
- `4004`：资源不存在
- `5000`：服务器内部错误
- `4010`：未授权/登录失效

---

## 8. 项目亮点（简历/面试可讲）

- 从前端视角切入后端开发，完整实践了 API 到数据库的闭环。
- 强化分层设计：Controller、Service、DAO/Mapper 职责清晰，便于维护。
- 具备工程化意识：Maven、配置管理、SQL 脚本、README 文档化。
- 可扩展性良好：可继续平滑扩展课程管理、成绩管理、用户权限模块。

---

## 9. 学习里程碑（可打卡）

### 7 天目标

- [ ] 本地环境完全跑通
- [ ] 跑通学生模块 CRUD
- [ ] 明确分层职责并完成一次小重构

### 30 天目标

- [ ] 增加 JWT 或 Session 登录鉴权
- [ ] 接入 Swagger/OpenAPI
- [ ] 增加全局异常处理 + 参数校验
- [ ] 补充基础单元测试
- [ ] 完成 Docker 本地部署

---

## 10. 开发规范（建议）

### 10.1 分支命名

- `feature/*`：新功能
- `fix/*`：Bug 修复
- `docs/*`：文档变更
- `refactor/*`：重构

### 10.2 Commit 规范

- `feat: ...`
- `fix: ...`
- `docs: ...`
- `refactor: ...`
- `test: ...`

示例：

- `feat: add student pagination api`
- `fix: handle null pointer in login service`
- `docs: update README learning roadmap`

### 10.3 学习记录规范

建议新增目录：`weekly-log/`

- `week-01.md`
- `week-02.md`
- `week-03.md`

每周记录：

1. 本周新增功能
2. 本周遇到问题与排查过程
3. 下周计划

---

## 11. 本项目后续规划（Roadmap）

- [ ] 补充真实接口清单（与 controller 代码保持一致）
- [ ] 增加统一响应体与错误码枚举
- [ ] 增加参数校验与全局异常处理
- [ ] 接入 Swagger/OpenAPI 文档
- [ ] 增加登录鉴权（JWT）
- [ ] 增加分页/模糊搜索/批量导入导出
- [ ] 增加单元测试与集成测试
- [ ] Docker 化部署（本地一键启动）

---

## 12. 你可以这样使用这个项目

1. 先当“教程项目”：逐模块阅读 + 注释理解  
2. 再当“实验项目”：每周做 1 次功能扩展  
3. 最后当“作品项目”：完善文档后放进简历/面试项目集
