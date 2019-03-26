package com.edison.service.taskschedule.business.report.week;


import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class StartWeek {


    public  void startWeek() {
        Date date = new Date();
        // 使用Calendar类进行时间的计算
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        // 获得当前日期是一个星期的第几天
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会计算到下一周去。
        // dayWeek值（1、2、3...）对应周日，周一，周二...
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayWeek == 1) {
            dayWeek = 7;
        } else {
            dayWeek -= 1;
        }
        System.out.println("当前时间是本周的第几天:" + dayWeek); // 输出要当前时间是本周的第几天
        // 计算本周开始的时间
        cal.add(Calendar.DAY_OF_MONTH, 1 - dayWeek);
        Date startOfWeek = cal.getTime();

        cal.add(Calendar.DAY_OF_MONTH, 7);
        Date startOfNextWeek = cal.getTime();
        System.out.println("本周开始时间（周一）：" + startOfWeek + "==下一周开始时间：" + startOfNextWeek);
    }



    //获取这周的开始日期
    public Date getStartOfWeek(){

        Date date = new Date();
        // 使用Calendar类进行时间的计算
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        // 获得当前日期是一个星期的第几天
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会计算到下一周去。
        // dayWeek值（1、2、3...）对应周日，周一，周二...
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayWeek == 1) {
            dayWeek = 7;
        } else {
            dayWeek -= 1;
        }
        cal.add(Calendar.DAY_OF_MONTH, 1 - dayWeek);
        Date startOfWeek = cal.getTime();
       return formatDate(startOfWeek);

    }



    //获取下周的开始日期
    public Date getStartOfNextWeek(){
        Calendar calendar = getCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date startOfNextWeek = calendar.getTime();
        return formatDate(startOfNextWeek);

    }
    //获取上周结束时间
    public Date getEndOfLastWeek(){
        Date date = new Date();
        // 使用Calendar类进行时间的计算
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        // 获得当前日期是一个星期的第几天
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会计算到下一周去。
        // dayWeek值（1、2、3...）对应周日，周一，周二...
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayWeek == 1) {
            dayWeek = 7;
        } else {
            dayWeek -= 1;
        }
        cal.add(Calendar.DAY_OF_MONTH, 1 - dayWeek-1);
        Date endOfLastWeek = cal.getTime();
        return formatDate(endOfLastWeek);
    }
    //获取上周开始时间
    public Date getStartOfLastWeek(){
        Date date = new Date();
        // 使用Calendar类进行时间的计算
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        // 获得当前日期是一个星期的第几天
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会计算到下一周去。
        // dayWeek值（1、2、3...）对应周日，周一，周二...
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayWeek == 1) {
            dayWeek = 7;
        } else {
            dayWeek -= 1;
        }
        cal.add(Calendar.DAY_OF_MONTH, 1 - dayWeek-7);
        Date endOfLastWeek = cal.getTime();
        return formatDate(endOfLastWeek);
    }


    //获取本周结束日期
    public Date getEndOfWeek(){
        Calendar calendar = getCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, 6);
        Date endOfNextWeek = calendar.getTime();
        return formatDate(endOfNextWeek);
    }

        //获取下周结束日期
        public Date getEndOfNextWeek(){
            Calendar calendar = getCalendar();
         calendar.add(Calendar.DAY_OF_MONTH,13);
         Date endOfNextWeek=calendar.getTime();
         return formatDate(endOfNextWeek);
        }

        //获取月份
    public int getMonth(Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
       int i= calendar.get(calendar.MONTH)+ 1;
       return i;
    }
    //获取年份
    public int getYear(Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        int i= calendar.get(calendar.YEAR);
        return i;
    }
    //获取当前月的第一天
    public Date getStartOfMonth(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parse=null;
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String format1 = format.format(c.getTime());
        try {
            parse = format.parse(format1);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }
    //获取当前月的最后一天
    public Date getEndOfMonth(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parse=null;
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        try {
            parse = format.parse(last);
            System.out.println(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

        //获取时间操作类
        public Calendar getCalendar(){
            Date date = new Date();
            // 使用Calendar类进行时间的计算
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
           return cal;

        }



        public Date formatDate(Date date){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(date);
            Date parse = null;
            try {
                parse = sdf.parse(format);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Timestamp timestamp1=new Timestamp(parse.getTime());
            return timestamp1;

        }
         /* //当前时间是本周第几天
    public int dayWeek(){
        Date date = new Date();
        // 使用Calendar类进行时间的计算
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        // 获得当前日期是一个星期的第几天
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会计算到下一周去。
        // dayWeek值（1、2、3...）对应周日，周一，周二...
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayWeek == 1) {
            dayWeek = 7;
        } else {
            dayWeek -= 1;
        }
        return dayWeek;
    }*/

}
