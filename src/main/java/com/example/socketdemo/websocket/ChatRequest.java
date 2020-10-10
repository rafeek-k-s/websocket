package com.example.socketdemo.websocket;


public class ChatRequest {
    private String message;
    private String lessonPlanLogId;
   private Long classId;
   private Long userId;
   private Long teacherId;
 

   
   
   
   
   
   public Long getTeacherId() {
	return teacherId;
}

public void setTeacherId(Long teacherId) {
	this.teacherId = teacherId;
}

public ChatRequest(){
	   
	   
   }
   
  public ChatRequest(String message,String lessonPlanLogId,Long classId,Long teacherId){

	  this.message =message;
	  this.lessonPlanLogId = lessonPlanLogId;
	  this.classId = classId;
	  this.teacherId = teacherId;
	   
   } 
 public String getMessage() {
	return message;
}




public void setMessage(String message) {
	this.message = message;
}




public String getLessonPlanLogId() {
	return lessonPlanLogId;
}




public void setLessonPlanLogId(String lessonPlanLogId) {
	this.lessonPlanLogId = lessonPlanLogId;
}




public Long getClassId() {
	return classId;
}




public void setClassId(Long classId) {
	this.classId = classId;
}




public Long getUserId() {
	return userId;
}




public void setUserId(Long userId) {
	this.userId = userId;
}




    
 

    }
