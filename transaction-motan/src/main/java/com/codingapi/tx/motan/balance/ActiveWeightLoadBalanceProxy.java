package com.codingapi.tx.motan.balance;

import com.weibo.api.motan.cluster.loadbalance.ActiveWeightLoadBalance;
import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.core.extension.Activation;
import com.weibo.api.motan.core.extension.SpiMeta;
import com.weibo.api.motan.rpc.Referer;
import com.weibo.api.motan.rpc.Request;

/**
 * <p>"低并发优化" 负载均衡代理</p>
 *
 * @author 张峰 zfvip_it@163.com
 * 2017/12/1 10:42
 */
@SpiMeta(name = "activeWeightLcn")
@Activation(key = {MotanConstants.NODE_TYPE_SERVICE, MotanConstants.NODE_TYPE_REFERER})
public class ActiveWeightLoadBalanceProxy extends ActiveWeightLoadBalance {

    private LCNBalanceProxy lcnBalanceProxy = new LCNBalanceProxy();

    @Override
    protected Referer doSelect(Request request) {
        return lcnBalanceProxy.proxy(super.doSelect(request));
    }
}
