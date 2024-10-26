package com.hotel.back.entity;

import com.hotel.back.constant.enums.Rating;

import java.util.Date;

public class FeedBack {
    private Integer feedbackId;
    private Integer userId;
    private Integer roomId;
    private Rating rating;
    private String text;
    private Date submittedDate;

    public FeedBack() {
    }

    public Integer getFeedbackId() {
        return this.feedbackId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public Integer getRoomId() {
        return this.roomId;
    }

    public Rating getRating() {
        return this.rating;
    }

    public String getText() {
        return this.text;
    }

    public Date getSubmittedDate() {
        return this.submittedDate;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSubmittedDate(Date submittedDate) {
        this.submittedDate = submittedDate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof FeedBack)) return false;
        final FeedBack other = (FeedBack) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$feedbackId = this.getFeedbackId();
        final Object other$feedbackId = other.getFeedbackId();
        if (this$feedbackId == null ? other$feedbackId != null : !this$feedbackId.equals(other$feedbackId))
            return false;
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId)) return false;
        final Object this$roomId = this.getRoomId();
        final Object other$roomId = other.getRoomId();
        if (this$roomId == null ? other$roomId != null : !this$roomId.equals(other$roomId)) return false;
        final Object this$rating = this.getRating();
        final Object other$rating = other.getRating();
        if (this$rating == null ? other$rating != null : !this$rating.equals(other$rating)) return false;
        final Object this$text = this.getText();
        final Object other$text = other.getText();
        if (this$text == null ? other$text != null : !this$text.equals(other$text)) return false;
        final Object this$submittedDate = this.getSubmittedDate();
        final Object other$submittedDate = other.getSubmittedDate();
        if (this$submittedDate == null ? other$submittedDate != null : !this$submittedDate.equals(other$submittedDate))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof FeedBack;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $feedbackId = this.getFeedbackId();
        result = result * PRIME + ($feedbackId == null ? 43 : $feedbackId.hashCode());
        final Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        final Object $roomId = this.getRoomId();
        result = result * PRIME + ($roomId == null ? 43 : $roomId.hashCode());
        final Object $rating = this.getRating();
        result = result * PRIME + ($rating == null ? 43 : $rating.hashCode());
        final Object $text = this.getText();
        result = result * PRIME + ($text == null ? 43 : $text.hashCode());
        final Object $submittedDate = this.getSubmittedDate();
        result = result * PRIME + ($submittedDate == null ? 43 : $submittedDate.hashCode());
        return result;
    }

    public String toString() {
        return "FeedBack(feedbackId=" + this.getFeedbackId() + ", userId=" + this.getUserId() + ", roomId=" + this.getRoomId() + ", rating=" + this.getRating() + ", text=" + this.getText() + ", submittedDate=" + this.getSubmittedDate() + ")";
    }
}
