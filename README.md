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
- `room_id` 居住房间号 (INT, FOREIGN KEY REFERENCES `rooms(room_id)`)
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


以下是各角色功能

### 1. 管理员(`admin`)
- 查看RevPAR
- 增减调整员工(`staff`)信息
- 增减房屋
- 房价设置
- 账目信息查看统计

### 2. 员工(`staff`)
- 处理预定信息
- 处理结账信息

### 3. 顾客(`customer`)
- 在线预定房间
- 到店支付订单
- 订单评价

RevPAR（Revenue per Available Room， 每可用房收入）是衡量酒店业绩的一个关键指标，它计算的是酒店每个可用房间在一定时间内的收入。计算公式如下：

$$
\text{RevPAR} = \frac{\text{总房间收入}}{\text{可用房间数}}
$$
或者，也可以通过以下两个指标的乘积来计算：

$$
\text{RevPAR} = \text{平均房价（ADR）} \times \text{入住率（Occupancy Rate）}
$$
其中：
- **总房间收入**：酒店所有客房在一定时间内的总收入。
- **可用房间数**：指酒店在该时间段内所有可出租的房间数量。
- **平均房价（ADR, Average Daily Rate）**：酒店每间已出租房间的平均收入。
- **入住率（Occupancy Rate）**：已出租房间数占可出租房间数的比例，计算公式为：

$$
\text{入住率} = \frac{\text{已出租房间数}}{\text{可用房间数}}
$$

RevPAR 是一个反映酒店整体盈利能力的综合性指标，常用于比较不同酒店或不同时间段的业绩。
