package com.hotel.back.entity;

import com.hotel.back.constant.enums.Role;

import java.util.Date;

//使用lombok方法在编译阶段自动生成getter等方法
public class User {
    private Integer userId;
    private String name;
    private String idNumber;
    private String phone;
    private String password;
    private Role role;
    private Date regTime;
    private String userPic;

    public User() {
    }

    public Integer getUserId() {
        return this.userId;
    }

    public String getName() {
        return this.name;
    }

    public String getIdNumber() {
        return this.idNumber;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getPassword() {
        return this.password;
    }

    public Role getRole() {
        return this.role;
    }

    public Date getRegTime() {
        return this.regTime;
    }

    public String getUserPic() {
        return this.userPic;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$idNumber = this.getIdNumber();
        final Object other$idNumber = other.getIdNumber();
        if (this$idNumber == null ? other$idNumber != null : !this$idNumber.equals(other$idNumber)) return false;
        final Object this$phone = this.getPhone();
        final Object other$phone = other.getPhone();
        if (this$phone == null ? other$phone != null : !this$phone.equals(other$phone)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$role = this.getRole();
        final Object other$role = other.getRole();
        if (this$role == null ? other$role != null : !this$role.equals(other$role)) return false;
        final Object this$regTime = this.getRegTime();
        final Object other$regTime = other.getRegTime();
        if (this$regTime == null ? other$regTime != null : !this$regTime.equals(other$regTime)) return false;
        final Object this$userPic = this.getUserPic();
        final Object other$userPic = other.getUserPic();
        if (this$userPic == null ? other$userPic != null : !this$userPic.equals(other$userPic)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $idNumber = this.getIdNumber();
        result = result * PRIME + ($idNumber == null ? 43 : $idNumber.hashCode());
        final Object $phone = this.getPhone();
        result = result * PRIME + ($phone == null ? 43 : $phone.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $role = this.getRole();
        result = result * PRIME + ($role == null ? 43 : $role.hashCode());
        final Object $regTime = this.getRegTime();
        result = result * PRIME + ($regTime == null ? 43 : $regTime.hashCode());
        final Object $userPic = this.getUserPic();
        result = result * PRIME + ($userPic == null ? 43 : $userPic.hashCode());
        return result;
    }

    public String toString() {
        return "User(userId=" + this.getUserId() + ", name=" + this.getName() + ", idNumber=" + this.getIdNumber() + ", phone=" + this.getPhone() + ", password=" + this.getPassword() + ", role=" + this.getRole() + ", regTime=" + this.getRegTime() + ", userPic=" + this.getUserPic() + ")";
    }
}

