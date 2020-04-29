package com.mcy.define;

import android.content.Context;
import android.util.Log;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: MeiziDaoUtils
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/4/29 10:17
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/29 10:17
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MeiziDaoUtils
{
    private static final String TAG = MeiziDaoUtils.class.getSimpleName();
    private DaoManager mManager;
    private MeiZiDao mMeiZiDao;
    private DaoSession mDaoSession;

    public MeiziDaoUtils(Context context){
        mManager = DaoManager.getInstance();
        mManager.init(context);
        mDaoSession = mManager.getDaoSession();
        mMeiZiDao = mDaoSession.getMeiZiDao();
    }

    /**
     * 完成meizi记录的插入，如果表未创建，先创建Meizi表
     * @param meizi
     * @return
     */
    public boolean insertMeiZi(MeiZi meizi){
        boolean flag = false;
        flag = mMeiZiDao.insert(meizi) != -1;
//        flag = mDaoSession.insert(meizi) != -1;
        Log.i(TAG, "insert Meizi :" + flag + "-->" + meizi.toString());
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     * @param meiziList
     * @return
     */
    public boolean insertMultMeiZi(final List<MeiZi> meiziList) {
        boolean flag = false;
        try {
            mMeiZiDao.insertInTx(meiziList);
//            mDaoSession.runInTx(new Runnable() {
//                @Override
//                public void run() {
//                    for (MeiZi meizi : meiziList) {
//                        mDaoSession.insertOrReplace(meizi);
//                    }
//                }
//            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改一条数据
     * @param meizi
     * @return
     */
    public boolean updateMeizi(MeiZi meizi){
        boolean flag = false;
        try {
            mMeiZiDao.update(meizi);
//            mDaoSession.update(meizi);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除单条记录
     * @param meizi
     * @return
     */
    public boolean deleteMeizi(MeiZi meizi){
        boolean flag = false;
        try {
            //按照id删除
            mMeiZiDao.delete(meizi);
//            mDaoSession.delete(meizi);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除所有记录
     * @return
     */
    public boolean deleteAll(){
        boolean flag = false;
        try {
            //按照id删除
            mMeiZiDao.deleteAll();
//            mDaoSession.deleteAll(MeiZi.class);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<MeiZi> queryAllMeizi(){
        return mMeiZiDao.loadAll();
//        return mDaoSession.loadAll(MeiZi.class);
    }

    /**
     * 根据主键id查询记录
     * @param key
     * @return
     */
    public MeiZi queryMeiziById(long key){
        return mMeiZiDao.load(key);
//        return mDaoSession.load(MeiZi.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<MeiZi> queryMeiziByNativeSql(String sql, String[] conditions){
        return mMeiZiDao.queryRaw(sql, conditions);
//        return mDaoSession.queryRaw(MeiZi.class, sql, conditions);
    }

    /**
     * 使用queryBuilder进行查询
     * @return
     */
    public List<MeiZi> queryMeiziByQueryBuilder(long id){
        QueryBuilder<MeiZi> queryBuilder = mMeiZiDao.queryBuilder();
//        QueryBuilder<MeiZi> queryBuilder = mDaoSession.queryBuilder(MeiZi.class);
        return queryBuilder.where(MeiZiDao.Properties._id.eq(id)).list();
    }
}
