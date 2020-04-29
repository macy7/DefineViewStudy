package com.mcy.define;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: MeiZi
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/4/29 9:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/29 9:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Entity
public class MeiZi {
    @Id(autoincrement = true)
    private Long _id;
    private String source;
    @NotNull
    private String url;
    @Generated(hash = 1007481640)
    public MeiZi(Long _id, String source, @NotNull String url) {
        this._id = _id;
        this.source = source;
        this.url = url;
    }
    @Generated(hash = 142896928)
    public MeiZi() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public String getSource() {
        return this.source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MeiZi{" +
                "_id=" + _id +
                ", source='" + source + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
