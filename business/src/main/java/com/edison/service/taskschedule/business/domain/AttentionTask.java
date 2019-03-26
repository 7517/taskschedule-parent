package com.edison.service.taskschedule.business.domain;

import com.edison.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 关注的任务
 * @author icode
 */
@Entity()
@Table(name = "task_attention_task")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class AttentionTask extends BaseEntity<Long>{

	public static final String PROPERTY_UID = "uid";
	public static final String PROPERTY_TASK_ID = "taskId";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 用户id
    * 
    */
    @Column(name = "uid", nullable = true, updatable = true)
	private Long uid;

    /**
    * 任务id
    * 
    */
    @Column(name = "task_id", nullable = true, updatable = true)
	private Long taskId;

	public Long getUid(){
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getTaskId(){
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
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

