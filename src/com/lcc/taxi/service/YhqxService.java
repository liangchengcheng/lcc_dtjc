package com.lcc.taxi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.lcc.taxi.bean.Log;
import com.lcc.taxi.bean.TreeBean;
import com.lcc.taxi.dao.YhqxDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class YhqxService {

    @Autowired
    private YhqxDao yhqxdao;

    /**
     * 查询主节点
     */
    public List<Map<String, Object>> queryroot() throws Exception {
        List<Map<String, Object>> list = yhqxdao.queryroot();
        return list;
    }

    /**
     * 节点插入权限角色中间表
     */
    public int addqxjsService(int role_id, String nodes, String visible, String enable, String sysfalg, Log log) throws Exception {
        yhqxdao.log(log);
        String[] string = nodes.split(",");
        int c = 0;
        for (int i = 0; i < string.length; i++) {
            c += yhqxdao.addqxjsDao(role_id, Integer.parseInt(string[i]), visible, enable, sysfalg);

        }
        return c;
    }

    /**
     * 按父节点查询子节点
     */
    public List<Map<String, Object>> queryqx(int privilegeid) throws Exception {
        List<Map<String, Object>> list = yhqxdao.queryqx(privilegeid);
        return list;
    }

    /**
     * 删除角色
     */
    public int deljsService(int id, Log log) throws Exception {
        int c = yhqxdao.deljsDao(id, log);
        return c;
    }

    /**
     * 删除角色权限对照表
     */
    public int delqxService(int id) throws Exception {
        int c = yhqxdao.delqxDao(id);
        return c;
    }

    /**
     * 添加角色
     */
    public int addjsService(int role_id, String role_name, String role_desc, String sysflag, Log log) throws Exception {
        int c = yhqxdao.addjsDao(role_id, role_name, role_desc, sysflag, log);
        return c;
    }

    /**
     * 按ID查找角色
     */
    public List getjsByid(int role_id) throws Exception {
        List list = yhqxdao.getjsByIdDao(role_id);
        return list;
    }

    /**
     * 按ID查找角色
     */
    public int queryjs(int role_id) throws Exception {
        int r = yhqxdao.queryjs(role_id);
        return r;
    }

    /**
     * 更新角色
     */
    public int updatejs(int role_id, String role_desc, String role_name, Log log) throws Exception {
        int c = yhqxdao.updatejs(role_id, role_desc, role_name, log);
        return c;
    }

    /**
     * 查询权限节点
     */
    public List queryqxjs(int role_id) throws Exception {
        List<Map<String, Object>> list = yhqxdao.queryqxjs(role_id);
        List a = new ArrayList();
        for (Map date : list) {
            try {
                //遍历循环的结果直接去	DAO中查询PRIVILEGE_ID然后放入list
                List<Map<String, Object>> lt = yhqxdao.queryqxByPri(Integer.valueOf(date.get("PRIVILEGE").toString()));
                if (lt != null && lt.size() != 0) {
                    a.add(lt.get(0).get("PRIVILEGE_ID"));
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return a;
    }

    public List<TreeBean> getfatherNode(List<TreeBean> treeDataList) {
        List<TreeBean> newTreeDataList = new ArrayList<TreeBean>();
        for (TreeBean TreeBean : treeDataList) {
            if ("0".equals(TreeBean.getIsleaf())) {
                //获取父节点下的子节点
                TreeBean.setChildren(getChildrenNode(TreeBean.getId(), treeDataList));
                TreeBean.setState("open");
                newTreeDataList.add(TreeBean);
            }
            break;
        }
        return newTreeDataList;
    }

    /**
     *  方法描述: 子节点
     */
    private final static List<TreeBean> getChildrenNode(String pid, List<TreeBean> treeDataList) {
        List<TreeBean> newTreeDataList = new ArrayList<TreeBean>();
        for (TreeBean TreeBean : treeDataList) {
            if (TreeBean.getPid() == null) continue;
            //这是一个子节点
            if (TreeBean.getPid().equals(pid)) {
                //递归获取子节点下的子节点
                TreeBean.setChildren(getChildrenNode(TreeBean.getId(), treeDataList));
                newTreeDataList.add(TreeBean);
            }
        }
        return newTreeDataList;
    }


    public List<TreeBean> getGoodsSpec() throws Exception {
        List<Map<String, Object>> resultList = yhqxdao.queryroot();
        List<TreeBean> treeDataList = new ArrayList<TreeBean>();
         /*为了整理成公用的方法，所以将查询结果进行二次转换。
          * 其中specid为主键ID，varchar类型UUID生成
          * parentid为父ID
          * specname为节点名称
          * */
        for (Map m : resultList) {
            TreeBean treeData = new TreeBean();
            treeData.setId(m.get("PRIVILEGE_ID").toString());
            treeData.setPid(m.get("PRIVILEGE_PARENT").toString());
            treeData.setText(m.get("PRIVILEGE_NAME").toString());
            treeData.setIconCls(m.get("PRIVILEGE").toString());
            treeData.setIsleaf(m.get("LEAF_NOD").toString());
            treeDataList.add(treeData);
        }
        //最后得到结果集,经过FirstJSON转换后就可得所需的json格式
        List<TreeBean> newTreeDataList = getfatherNode(treeDataList);
        return newTreeDataList;
    }
}

