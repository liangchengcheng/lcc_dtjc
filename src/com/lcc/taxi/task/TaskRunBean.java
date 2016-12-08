package com.lcc.taxi.task;

import com.lcc.taxi.bean.CarInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * Created by lcc on 2016/12/7.
 */
public class TaskRunBean {
    private Dzwl taskinfo;
    private DzwlLog taskruninfo;
    private TaskAreaInfo taskareainfo;

    /**
     * 保存有哪些操作员在监控这个任务
     */
    private ConcurrentHashMap<Integer, UserTaskRunBean> userRunMap = new ConcurrentHashMap<Integer, UserTaskRunBean>();


    /**
     * 保存有多少车辆在电子围栏中
     */
    private ConcurrentHashMap<String, CarInfo> carInWlMap = new ConcurrentHashMap<String, CarInfo>();


    public TaskRunBean() {}

    public Dzwl getTaskinfo() {
        return taskinfo;
    }

    public void setDzwl(Dzwl taskinfo) {
        this.taskinfo = taskinfo;
    }

    public TaskAreaInfo getTaskareainfo() {
        return taskareainfo;
    }

    public void setTaskareainfo(TaskAreaInfo taskareainfo) {
        this.taskareainfo = taskareainfo;
    }


    /**
     * 增加一个任务执行监控操作员
     * @param userid
     */
    public void addUser(UserTaskRunBean userTaskRunBean) {
        userRunMap.remove(userTaskRunBean);
        userRunMap.put(userTaskRunBean.getUserinfo().getId(), userTaskRunBean);
    }



    /**
     * 删除一个任务执行监控操作员
     */
    public synchronized Integer removeUser(Integer userid) {
        userRunMap.remove(userid);
        return userRunMap.size();
    }


    /**
     * 获取此任务下所有正在监控的操作员
     */
    public synchronized List<Users> getAllUser() {
        Iterator<Map.Entry<Integer, UserTaskRunBean>> it = userRunMap.entrySet().iterator();
        List<Users> list = new ArrayList<Users>();
        while (it.hasNext()) {
            Map.Entry<Integer, UserTaskRunBean> entry = it.next();
            list.add(entry.getValue().getUserinfo());
        }
        return list;
    }


    /**
     * 将进入电子围栏的车加到map中
     * @param carInfo
     */
    public void addCar(CarInfo carInfo) {
        carInWlMap.put(carInfo.getCph(), carInfo);
    }


    /**
     * 将驶出电子围栏的车移除
     */
    public void removeCar(CarInfo carInfo) {
        carInWlMap.remove(carInfo.getCph());
    }


    /**
     * 获取所有在围栏中的车
     */
    public synchronized List<CarInfo> getAllInCar() {
        Iterator<Map.Entry<String, CarInfo>> it = carInWlMap.entrySet().iterator();
        List<CarInfo> carList = new ArrayList<CarInfo>();
        while (it.hasNext()) {
            Map.Entry<String, CarInfo> entry = it.next();
            carList.add(entry.getValue());
        }
        return carList;
    }

    public DzwlLog getTaskruninfo() {
        return taskruninfo;
    }

    public void setTaskruninfo(DzwlLog taskruninfo) {
        this.taskruninfo = taskruninfo;
    }

}
