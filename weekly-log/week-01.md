# Week 01 学习记录（Spring Boot 入门周）

> 项目：`springboot-demo`  
> 时间：`2026-__-__ ~ 2026-__-__`  
> 本周目标：跑通项目 + 看懂分层 + 跑通学生模块基础 CRUD

---

## 1. 本周完成内容

### 1.1 环境与启动

- [ ] 安装并确认 JDK 版本（建议 JDK 17+）
- [ ] 使用 Maven Wrapper 启动项目（`./mvnw spring-boot:run`）
- [ ] 本地启动成功并访问接口（如 `/api/students`）

记录：

- JDK 版本：
- 启动命令：
- 启动结果：

### 1.2 代码结构理解

- [ ] 找到启动入口：`SpringbootDemoApplication`
- [ ] 理解 Controller → Service → Mapper 分层职责
- [ ] 理解 DTO / Entity / VO 的作用与区别

记录：

- Controller 的职责：
- Service 的职责：
- Mapper 的职责：
- DTO/VO 分别用于：

### 1.3 接口联调（学生模块）

- [ ] 查询全部学生：`GET /api/students`
- [ ] 查询单个学生：`GET /api/students/{id}`
- [ ] 新增学生：`POST /api/students`
- [ ] 修改学生：`PUT /api/students/{id}`
- [ ] 删除学生：`DELETE /api/students/{id}`

记录：

- 调通的接口：
- 失败的接口：
- 失败原因：
- 修复方式：

---

## 2. 本周核心知识点总结

> 按“概念 → 代码位置 → 我的理解”来写，训练面试表达能力。

### 2.1 统一返回结构 `Result<T>`

- 概念：
- 代码位置：`src/main/java/com/example/springbootdemo/common/Result.java`
- 我的理解：

### 2.2 全局异常处理

- 概念：`@RestControllerAdvice` + `@ExceptionHandler`
- 代码位置：`src/main/java/com/example/springbootdemo/exception/GlobalExceptionHandler.java`
- 我的理解：

### 2.3 业务异常 `BusinessException`

- 概念：
- 代码位置：`src/main/java/com/example/springbootdemo/exception/BusinessException.java`
- 我的理解：

### 2.4 DTO / VO 模型分离

- 概念：
- 代码位置：
  - DTO: `src/main/java/com/example/springbootdemo/dto/`
  - VO: `src/main/java/com/example/springbootdemo/vo/StudentVO.java`
- 我的理解：

---

## 3. 本周问题与排查记录

> 至少记录 2~3 个真实问题，重点写排查思路。

### 问题 1：

- 现象：
- 原因定位：
- 解决方案：
- 学到什么：

### 问题 2：

- 现象：
- 原因定位：
- 解决方案：
- 学到什么：

### 问题 3（可选）：

- 现象：
- 原因定位：
- 解决方案：
- 学到什么：

---

## 4. 本周代码改动清单

- [ ] 改动 1：
- [ ] 改动 2：
- [ ] 改动 3：

关联 commit（可选）：

- 

---

## 5. 本周自评（1~5 分）

- Java 基础理解：`__/5`
- Spring Boot 使用熟练度：`__/5`
- 接口联调能力：`__/5`
- 排错能力：`__/5`
- 工程化意识（结构/规范/文档）：`__/5`

本周最满意的点：

- 

本周最需要改进的点：

- 

---

## 6. 下周计划（Week 02）

### 目标

- [ ] 完成学生模块的一次“可维护性重构”
- [ ] 加入参数校验（如 `@NotNull` / `@NotBlank`）
- [ ] 输出一版更完整的 API 文档

### 拆解任务

1. 
2. 
3. 

### 风险与预案

- 风险：
- 预案：

---

## 7. 面试讲述素材（可直接背）

> 用 1 分钟讲清“这周你做了什么”。

示例话术（可按真实情况修改）：

“这周我完成了 Spring Boot 学生管理项目的基础搭建和接口联调，重点理解了 Controller、Service、Mapper 分层。通过 `Result<T>` 统一了接口返回格式，并通过全局异常处理统一了错误响应。过程中我解决了启动环境和接口调试问题，初步具备了从接口到数据层的完整开发和排错能力。下周我会重点做参数校验和代码重构，提升可维护性。”
