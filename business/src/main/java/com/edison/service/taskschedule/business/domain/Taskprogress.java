package com.edison.service.taskschedule.business.domain;

import com.edison.saas.common.jpa.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;



/**
 * 任务进展
 * @author icode
 */
@Entity()
@Table(name = "task_taskprogress")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class Taskprogress extends BaseEntity<Long>{

	public static final String PROPERTY_PROGRESS_VOICE = "progressVoice";
	public static final String PROPERTY_TASK_ID = "taskId";
	public static final String PROPERTY_TASK_STATUS = "taskStatus";
	public static final String PROPERTY_TASK_PROGRESS = "taskProgress";
	public static final String PROPERTY_PROGRESS_BAR = "progressBar";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 语音地址
    * 
    */
    @Column(name = "progress_voice", nullable = true, updatable = true)
	@Size(max = 255, message = "语音地址超长，最多255个字符")
	private String progressVoice;

    /**
    * 任务主键id
    * 
    */
    @Column(name = "task_id", nullable = true, updatable = true)
	private Long taskId;

    /**
    * 任务状态
    * 
    */
    @Column(name = "task_status", nullable = true, updatable = true)
	private Long taskStatus;

    /**
    * 任务进展
    * 
    */
    @Column(name = "task_progress", nullable = true, updatable = true)
	@Size(max = 255, message = "任务进展超长，最多255个字符")
	private String taskProgress;

	/**
	 * 任务进度条
	 *
	 */
	@Column(name = "progress_bar", nullable = true, updatable = true)
	@Size(max = 255, message = "任务进度条超长，最多255个字符")
	private String progressBar;
	public String getProgressVoice(){
		return progressVoice;
	}
	public void setProgressVoice(String progressVoice) {
		this.progressVoice = progressVoice;
	}

	public Long getTaskId(){
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getTaskStatus(){
		return taskStatus;
	}
	public void setTaskStatus(Long taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getTaskProgress(){
		return taskProgress;
	}
	public void setTaskProgress(String taskProgress) {
		this.taskProgress = taskProgress;
	}

	public String getProgressBar(){
		return progressBar;
	}
	public void setProgressBar(String progressBar) {
		this.progressBar = progressBar;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}

