package com.mcy.define;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: Student
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/4/28 22:31
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/28 22:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Student {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void setAge(int age) {
        this.age = age;
    }

}
