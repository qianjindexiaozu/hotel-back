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
}
