## 后端
### 启动项目前的准备工作
1. 确保在本机上启动了mysql服务
2. 创建数据库"ase_lab"
```sql
CREATE DATABASE IF NOT EXISTS ase_lab;
```
3. 在`application.properties`中正确配置数据库的用户名和密码
```yaml
spring.datasource.username={yourusername}
spring.datasource.password={yourpassword}
```