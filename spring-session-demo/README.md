# spring session 核心原理和组件
![ss](https://github.com/fuos/springboot3.x-demo/assets/34535312/df742b4f-470a-43f4-bbc6-701a16c8f4ae)

在左图中，每个 Spring 应用程序都将其会话存储在一个只有他们自己可以访问它们的地方，通常是在服务器的内存中，但这在分布式环境中可能是一个问题。想象一下，Spring App #2 收到带有会话 #3 的请求，应用程序将无法读取会话数据，因为它存储在 Spring App #1 的内存中。为了解决这个问题，我们需要实现某种共享会话存储，如右图所示。

Spring Session 的原理主要围绕提供一个外部的Session管理机制，允许开发者替代传统的基于Servlet容器的Session管理。它通过提供一套API和实现，支持在分布式环境下共享Session信息，解决了传统Session管理在分布式部署时遇到的问题，如Session不一致、扩展性差等。其核心原理和组件包括：

### 1. **Session抽象**

Spring Session 提供了 `Session` 接口，这是一个高级的抽象，用于封装Session信息。这个接口允许Spring Session通过不同的后端存储（如Redis、Hazelcast、JDBC等）来实现Session的存储和检索，而对应用程序来说是透明的。

### 2. **Session存储**

Spring Session 支持多种类型的后端存储方案，这些方案可以持久化Session数据。这意味着即使应用服务器重启，Session信息也不会丢失。常见的存储方案包括：

- **Redis**：利用Redis的高性能和高可用性，作为Session数据的存储方案。
- **Hazelcast**：使用Hazelcast的分布式数据结构来共享Session，适用于需要分布式计算的场景。
- **JDBC**：通过关系数据库来持久化Session数据，适用于传统的企业应用。
- **MongoDB**：使用文档数据库MongoDB来存储Session数据，适用于文档导向的数据模型。

### 3. **Session替换**

Spring Session 自动集成并替换了Servlet容器的默认Session机制。它通过一个过滤器（Filter）拦截所有的请求，然后使用其自己的 `SessionRepository` 来创建和管理Session。这个过程对于应用程序是透明的，开发者可以像使用传统的HttpSession一样使用它。

### 4. **Session ID解析和管理**

Spring Session 使用特定的策略来生成和解析Session ID。默认情况下，Session ID通常通过Cookie传递给客户端，但Spring Session也支持其他方式（如HTTP头）来传递Session ID，这对于RESTful API和单页面应用（SPA）非常有用。

### 5. **跨域支持**

对于前后端分离的应用，Spring Session 提供了跨域资源共享（CORS）的支持，使得前端应用能够安全地访问后端服务的Session数据。

### 实现原理简述：

当请求到达应用时，Spring Session 的过滤器会首先检查请求中是否包含有效的Session ID。如果包含，则使用该ID从后端存储中加载Session数据；如果不包含或Session不存在，则创建一个新的Session。在请求处理完成后，对Session的任何更改都会被同步到后端存储中，确保Session状态的一致性。

通过这种方式，Spring Session 实现了跨多个应用实例的Session共享，提高了应用的可伸缩性和可靠性，同时也简化了分布式环境下的Session管理。
