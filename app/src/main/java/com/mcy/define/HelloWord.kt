package com.mcy.define

/**

 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: HelloWord
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/5/5 21:52
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/5 21:52
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 *
 */
fun helloWorld(args: Array<String>) {
    println("hello world")
}

fun max(a: Int, b: Int): Int {
    return if (a > b) a else b;
}

fun maxA(a: Int, b: Int) = if (a > b) a else b