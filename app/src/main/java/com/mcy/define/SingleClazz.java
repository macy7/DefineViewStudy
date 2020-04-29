package com.mcy.define;

import java.io.Serializable;
import java.net.Socket;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: SingleClazz
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/4/26 9:23
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/26 9:23
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SingleClazz{
    private SingleClazz(){}
    private static class SingleInner{
        private static final SingleClazz singleClazz= new SingleClazz();
    }

    public static SingleClazz getInstance(){
        return SingleInner.singleClazz;
    }

}
