package com.mcy.define;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: ActivityScoped
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/5/1 16:45
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/1 16:45
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface ActivityScoped {
}
