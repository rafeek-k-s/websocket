package com.example.socketdemo.websocket;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class StudentFeedback {
	
	public Long id;
	
	public Long userId;
	
public Long emotion;
	
	public Long lessonPlanLogId;
	
	public Long teacherId;
	
	

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public boolean isPreClass;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmotion() {
		return emotion;
	}

	public void setEmotion(Long emotion) {
		this.emotion = emotion;
	}

	public Long getLessonPlanLogId() {
		return lessonPlanLogId;
	}

	public void setLessonPlanLogId(Long lessonPlanLogId) {
		this.lessonPlanLogId = lessonPlanLogId;
	}
	public boolean isPreClass() {
		return isPreClass;
	}

	public void setPreClass(boolean isPreClass) {
		this.isPreClass = isPreClass;
	}
	
}
