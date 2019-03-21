package com.edison.service.taskschedule.business.task.service;


import com.edison.saas.common.jpa.GenericCrudService;
import com.edison.service.taskschedule.business.task.dao.TaskprogressDao;
import com.edison.service.taskschedule.business.task.dao.TaskprogressSpecification;
import com.edison.service.taskschedule.business.task.domain.Taskprogress;
import com.edison.service.taskschedule.business.task.dto.TaskprogressCondition;
import lombok.extern.slf4j.Slf4j;
import org.apache.xmlbeans.impl.validator.ValidatingXMLStreamReader;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("taskprogressService")
@Slf4j
@Transactional
public class TaskprogressService  extends GenericCrudService<Taskprogress, Long, TaskprogressCondition, TaskprogressDao> {
  
	@Override
	public Specification<Taskprogress> getSpecification(TaskprogressCondition condition) {
		return new TaskprogressSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, Taskprogress.PROPERTY_PROGRESS_VOICE);
		return sort;
	}

	public List<Taskprogress> findByTaskId(Long taskId){
		List<Taskprogress> progressList = dao.findByTaskId(taskId);
		return progressList;

	}


//修改任务状态，添加任务详情
	public Taskprogress updateTaskStatus(Long taskId,Long taskStatus){
		/*List<Taskprogress> list = dao.findByTaskId(taskId);
		List<Taskprogress> list1=new ArrayList<>();
		for (Taskprogress taskprogress : list) {
			taskprogress.setTaskStatus(taskStatus);
			list1.add(taskprogress);
		}
		List<Taskprogress> list2 = dao.save(list1);
		return list2;*/


		Date date=new Date();
		Taskprogress taskprogress=new Taskprogress();
		taskprogress.setTaskStatus(taskStatus);
		taskprogress.setTaskId(taskId);
		taskprogress.setCreateAt(date);
		taskprogress.setCreateUid(taskStatus);
		Taskprogress tp = dao.save(taskprogress);
		return tp;

	}

	public void deleteByTaskId(Long taskId){
	    dao.deleteByTaskId(taskId);
    }

    public Taskprogress findByCreate(Long id){
		Taskprogress tp = dao.findByCreate(id);

		return tp;
	}
	public Taskprogress findByCteateAndStatus(Long taskId,Long taskStatus){
		Taskprogress taskprogress = dao.findByCreateAndStatus(taskId, taskStatus);
		return taskprogress;
	}

}