以下是包含的功能模块：

1. **用户管理**：客户注册、登录、权限管理。
2. **预订管理**：在线预订、取消预订、查看预订状态。
3. **客房管理**：客房信息维护、状态更新、房价设置。
4. **账单处理**：生成账单、支付处理。
5. **入住管理**：入住登记、退房处理、客户反馈。
6. **库存管理**：管理酒店用品、库存监控。

以下是数据库设计，包括主要的表结构和字段：

### 1. 用户表 (`users`)
- `user_id` 用户编号 (INT, PRIMARY KEY, AUTO_INCREMENT)
- `name` 用户姓名 (VARCHAR, UNIQUE) 
- `id_number` 用户身份证号码 (VARCHAR)
- `gender` 性别 (ENUM('Male','Female'))
- `phone` 手机号 (VARCHAR)
- `password` 用户密码 (VARCHAR)
- `role` 用户身份 (ENUM('Admin', 'Staff', 'Customer'))
- `reg_time` 注册时间 (DATE)

### 2. 客房表 (`rooms`)
- `room_id` 客房编号 (INT, PRIMARY KEY, AUTO_INCREMENT)
- `room_number` 房间号 (VARCHAR, UNIQUE)
- `room_type` 房间类型 (ENUM('Single', 'Double', 'Suite'))
- `status` 房间状态 (ENUM('Available', 'Occupied', 'Maintenance'))
- `price` 价格 (DECIMAL)

### 3. 预订表 (`reservations`)
- `reservation_id` 预定编号 (INT, PRIMARY KEY, AUTO_INCREMENT)
- `user_id` 预定者编号 (INT, FOREIGN KEY REFERENCES `users(user_id)`)
- `room_type` 预定客房类型 (ENUM('Single', 'Double', 'Suite'), FOREIGN KEY REFERENCES `rooms(room_type)`)
- `check_in_date` 入住日期 (DATE)
- `check_out_date` 退房日期 (DATE)
- `status` 预定订单状态 (ENUM('Confirmed', 'Canceled', 'Completed'))

### 4. 账单表 (`bills`)
- `bill_id` 账单编号 (INT, PRIMARY KEY, AUTO_INCREMENT)
- `reservation_id` 预定账单编号 (INT, FOREIGN KEY REFERENCES `reservations(reservation_id)`)
- `amount` 总价 (DECIMAL)
- `payment_status` 支付状态 (ENUM('Paid', 'Unpaid'))
- `issued_date` 支付日期 (DATE)

### 5. 客户反馈表 (`feedback`)
- `feedback_id` 反馈编号 (INT, PRIMARY KEY, AUTO_INCREMENT)
- `user_id` 反馈者编号 (INT, FOREIGN KEY REFERENCES `users(user_id)`)
- `room_id` 反馈者房间编号 (INT, FOREIGN KEY REFERENCES `rooms(room_id)`)
- `rating` 反馈者打分 (INT CHECK (rating BETWEEN 1 AND 5))
- `comments` 反馈信息 (TEXT)
- `submitted_date` 提交时间 (DATE)

### 6. 库存表 (`inventory`)
- `item_id` 货物编号 (INT, PRIMARY KEY, AUTO_INCREMENT)
- `item_name` 货物名称 (VARCHAR)
- `quantity` 货物数量 (INT)
- `unit_price` 货物单价 (DECIMAL)


以下是各角色功能

### 1. 管理员(`admin`)
- 查看用户总体信息
- 查看RevPAR
- 增减调整员工(`staff`)信息
- 重置密码
- 增减房屋
- 房价设置
- 账目信息查看统计
- 消耗品信息

### 2. 员工(`staff`)
- 处理预定信息
- 处理结账信息
- 处理物资信息

### 3. 顾客(`customer`)
- 在线预定房间
- 到店支付订单
- 订单评价
