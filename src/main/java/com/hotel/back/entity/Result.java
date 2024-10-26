package com.hotel.back.entity;

// 统一响应结果
// 自动构建无参构造方法
// 自动构建全参构造方法
public class Result<T> {
    private Integer code;   // 响应码 0-成功 1-失败
    private String message; // 响应信息
    private T data;         // 响应数据

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }

    // 快速返回操作成功响应数据（带响应数据）
    public static <E> Result<E> success(E data) {
        return new Result<>(0, "操作成功", data);
    }

    // 快速返回操作成功响应数据（不带响应数据）
    public static Result success() {
        return new Result(0, "操作成功", null);
    }

    // 返回操作失败
    public static Result error(String message) {
        return new Result(1, message, null);
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Result)) return false;
        final Result<?> other = (Result<?>) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$code = this.getCode();
        final Object other$code = other.getCode();
        if (this$code == null ? other$code != null : !this$code.equals(other$code)) return false;
        final Object this$message = this.getMessage();
        final Object other$message = other.getMessage();
        if (this$message == null ? other$message != null : !this$message.equals(other$message)) return false;
        final Object this$data = this.getData();
        final Object other$data = other.getData();
        if (this$data == null ? other$data != null : !this$data.equals(other$data)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Result;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $code = this.getCode();
        result = result * PRIME + ($code == null ? 43 : $code.hashCode());
        final Object $message = this.getMessage();
        result = result * PRIME + ($message == null ? 43 : $message.hashCode());
        final Object $data = this.getData();
        result = result * PRIME + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    public String toString() {
        return "Result(code=" + this.getCode() + ", message=" + this.getMessage() + ", data=" + this.getData() + ")";
    }
}
