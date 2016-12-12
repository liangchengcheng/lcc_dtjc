package com.lcc.taxi.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lcc.taxi.bean.Log;
import com.lcc.taxi.bean.TJcZfjg;
import com.lcc.taxi.dao.ZfjgDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.aspectj.apache.bcel.generic.ARRAYLENGTH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ZfjgService {

    @Autowired
    public ZfjgDao zfjgDao;

    public String zfjgAdd(TJcZfjg zfjg, Log log, String zgz) throws Exception {
        String jgid = zfjgDao.zfjgAdd(zfjg, log);
        int i = 0;
        if (jgid != null && zfjg.getJgfid().equals("0")) {
            //更新默认用户id为跟节点id
            i = zfjgDao.updateJgid(jgid, zgz);
            if (i == 0) {
                jgid = null;
            }
        }

        return jgid;
    }

    public void zfjgUpd(TJcZfjg zfjg, Log log) throws Exception {
        zfjgDao.zfjgUpd(zfjg, log);
    }

    public boolean zfjgDel(TJcZfjg zfjg, Log log) throws Exception {
        boolean result = true;
        List list = zfjgDao.checkJG(zfjg.getId());
        if (list != null && list.size() != 0) {
            result = false;
        }
        if (result) {
            zfjgDao.zfjgDel(zfjg, log);
        }
        //同步注销执法人员
        zfjgDao.jcryDel(zfjg.getId());
        return result;
    }

    public List zfjgMCCheck(String jgmc, String id) throws Exception {
        List list = zfjgDao.zfjgMCCheck(jgmc, id);
        return list;
    }

    public List<Map<String, Object>> zfjgCom(String jgid) throws Exception {
        return zfjgDao.zfjgCom(jgid);
    }

    public List<Map<String, Object>> zfjgCom0() throws Exception {
        return zfjgDao.zfjgCom0();
    }

    public List<Map<String, Object>> getAllResource(String jgid) throws Exception {
        return zfjgDao.findAll(jgid);
    }

    public JSONArray getResourceTreeToJson(String jgid) throws Exception {
        return this.createTreeJson(getAllResource(jgid), jgid);
    }

    /**
     * 创建一颗树，以json字符串形式返回
     *
     * @param list 原始数据列表
     * @return 树
     */
    private JSONArray createTreeJson(List<Map<String, Object>> list, String jgid) throws Exception {
        JSONArray rootArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> resource = list.get(i);
            if (resource.get("ID").equals(jgid)) {
                JSONObject rootObj = createBranch(list, resource, jgid);
                rootArray.add(rootObj);
            }
        }

        return rootArray;
    }

    /**
     * 递归创建分支节点Json对象
     *
     * @param list        创建树的原始数据
     * @param currentNode 当前节点
     * @return 当前节点与该节点的子节点json对象
     */
    private JSONObject createBranch(List<Map<String, Object>> list, Map<String, Object> currentNode, String jgid) throws Exception {
        /*
         * 将javabean对象解析成为JSON对象
	     */
        JSONObject currentObj = JSONObject.fromObject(currentNode);
        JSONArray childArray = new JSONArray();
	    /*
	     * 循环遍历原始数据列表，判断列表中某对象的父id值是否等于当前节点的id值，
	     * 如果相等，进入递归创建新节点的子节点，直至无子节点时，返回节点，并将该
	     * 节点放入当前节点的子节点列表中
	     */
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> newNode = list.get(i);
            if (!newNode.get("ID").equals(jgid) && newNode.get("JGFID").toString().compareTo((currentNode.get("ID") != null ? currentNode.get("ID").toString() : "")) == 0) {
                JSONObject childObj = createBranch(list, newNode, jgid);
                childArray.add(childObj);
            }
        }

	    /*
	     * 判断当前子节点数组是否为空，不为空将子节点数组加入children字段中
	     */
        if (!childArray.isEmpty()) {
            currentObj.put("children", childArray);
        }

        return currentObj;
    }


    public JSONArray getResourceTreeToJsonC(String jgid) throws Exception {
        return this.createTreeJsonC(getAllResourceC(jgid), jgid);
    }

    /**
     * 创建一颗树，以json字符串形式返回
     *
     * @param list 原始数据列表
     * @return 树
     */
    private JSONArray createTreeJsonC(List<Map<String, Object>> list, String jgid) throws Exception {
        JSONArray rootArray = new JSONArray();
        Map<String, Object> m = null;
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> resource = list.get(i);
            m = new HashMap<>();
            m.put("id", resource.get("ID"));
            m.put("text", resource.get("TEXT"));
            if (resource.get("ID").equals(jgid)) {
                JSONObject rootObj = createBranchC(list, m, jgid);
                rootArray.add(rootObj);
            }
        }

        return rootArray;
    }

    /**
     * 递归创建分支节点Json对象
     *
     * @param list        创建树的原始数据
     * @param currentNode 当前节点
     * @return 当前节点与该节点的子节点json对象
     */
    private JSONObject createBranchC(List<Map<String, Object>> list, Map<String, Object> currentNode, String jgid) throws Exception {
        //将javabean对象解析成为JSON对象
        JSONObject currentObj = JSONObject.fromObject(currentNode);
        JSONArray childArray = new JSONArray();
		/*
		 * 循环遍历原始数据列表，判断列表中某对象的父id值是否等于当前节点的id值，
		 * 如果相等，进入递归创建新节点的子节点，直至无子节点时，返回节点，并将该
		 * 节点放入当前节点的子节点列表中
		 */
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> newNode = list.get(i);
            if (!newNode.get("ID").equals(jgid) && newNode.get("JGFID").toString().compareTo((currentNode.get("id") != null ? currentNode.get("id").toString() : "")) == 0) {
                Map<String, Object> m = new HashMap();
                m.put("id", newNode.get("ID"));
                m.put("text", newNode.get("TEXT"));
                JSONObject childObj = createBranchC(list, m, jgid);
                childArray.add(childObj);
            }
        }

		//判断当前子节点数组是否为空，不为空将子节点数组加入children字段中
        if (!childArray.isEmpty()) {
            currentObj.put("children", childArray);
        }

        return currentObj;
    }

    public List<Map<String, Object>> getAllResourceC(String jgid) throws Exception {
        return zfjgDao.findAllC(jgid);
    }
}
