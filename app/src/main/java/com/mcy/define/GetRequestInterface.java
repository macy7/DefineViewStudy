package com.mcy.define;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: GetRequestInterface
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/4/30 18:10
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/30 18:10
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface GetRequestInterface {
    @GET("s?wd=%E5%9B%BE%E7%89%87%20%E5%A4%B4%E5%83%8F&rsv_spt=1&rsv_iqid=0x92e62c7600042cc6&issp=1&f=3&rsv_bp=1&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_dl=ts_0&rsv_sug3=8&rsv_sug1=6&rsv_sug7=101&rsv_sug2=1&rsv_btype=i&prefixsug=tupian%2520&rsp=0&inputT=2310&rsv_sug4=3647")
    Observable<String> getCall();
}
