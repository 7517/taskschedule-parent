package com.edison.service.taskschedule.business.service;


import com.edison.saas.common.jpa.GenericCrudService;
import com.edison.service.taskschedule.business.dto.TaskCondition;
import com.edison.service.taskschedule.business.dao.TaskDao;
import com.edison.service.taskschedule.business.dao.TaskSpecification;
import com.edison.service.taskschedule.business.domain.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service("taskService")
@Slf4j
@Transactional
public class TaskService  extends GenericCrudService<Task, Long, TaskCondition, TaskDao> {
	@Override
	public Specification<Task> getSpecification(TaskCondition condition) {
		return new TaskSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, Task.PROPERTY_RESP_DEPT_UID);
		return sort;
	}


	public List<Task> findByDate(Date beginAt,String uid) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(beginAt);
		Date parse = null;
		try {
			parse = sdf.parse(format);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Timestamp timestamp1=new Timestamp(parse.getTime());
		Timestamp timestamp2=new Timestamp(parse.getTime()+86400000);
		List<Task> list = dao.findByBeginAt(timestamp1, timestamp2,uid);
		return list;


	}





	//转交协助人
	public Task updateRespUid(Long id,String respUid){
		Task task = dao.findById(id);
		String uid = task.getRespUid();
		String str=uid+","+respUid;
		task.setRespUid(str);
		Task t = dao.save(task);
		return t;

	}




		public Task prosonTask(Long id,String respUid){
			Task task = dao.findById(id);
			String uidStr = task.getRespUid();
			String respDeptUid = task.getRespDeptUid();
			String assDeptUid = task.getAssDeptUid();
			//判断resoUid是否为部门主任
			boolean contains = uidStr.contains(respUid);
			boolean contains1 = respDeptUid.contains(respDeptUid);
			boolean contains2 = assDeptUid.contains(respUid);

			if (contains||contains1||contains2){
				return task;
			}
			return null;
		}

		//设置提醒时间
	public Task updateReminderTime(Long id,Date date){
		Task task = dao.findById(id);
		task.setReminderTime(date);
		Task save = dao.save(task);
		return save;
	}


    //判断是否为创建人,创建人才能删除
    public void deleteById(Long id){

		    dao.deleteById(id);
		    return;
    }

    //将string转换为int类型的数组
    public int[] getIntArray(String str){
		String[] strings = str.split(",");
		int[] i=new int[strings.length];
		for (int i1 = 0; i1 < strings.length; i1++) {
			i[i1]=Integer.parseInt(strings[i1]);
		}
		return i;
	}

   /* //查询我发布的任务
    public List<Task> findByCreateUid(Long uid){
		Calendar calendar=Calendar.getInstance();
		List<Task> list = dao.findByCreateUid(uid);
		long time = new Date().getTime();
		for (Task task : list) {
			long time1 = task.getBeginAt().getTime();
			long t=time1-time;
			if(t<=0){
				task.setTaskVoice("已超时");
			}else{
			calendar.setTimeInMillis(t);
			SimpleDateFormat fmat=new SimpleDateFormat("HH:mm:ss");
			String format = fmat.format(calendar.getTime());
			task.setTaskVoice(format);
			}
		}
		return list;
	}*/

	/*//查询我的任务
	public List<Task> findMyTask(String uid) {
		Calendar calendar = Calendar.getInstance();

		List<Task> myTask = dao.findMyTask(uid);
		long time = new Date().getTime();
		for (Task task : myTask) {
			long time1 = task.getBeginAt().getTime();
			long t = time1 - time;
			task.setCountdown(t);
			*//*if (t<=0){
				task.setCountdown("已超时");
			}else {
				calendar.setTimeInMillis(t);
				SimpleDateFormat fmat = new SimpleDateFormat("HH:mm:ss");
				String format = fmat.format(calendar.getTime());
				task.setCountdown(format);
			}*//*
		}
		return myTask;
	}*/


}