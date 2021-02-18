package com.wecash.sheJiMoShi.chain.test1;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: tong.cheng
 * Date: 2019-01-23
 * Time: 21:55
 */
public class ChoreServiceContainer {
    Map<ServiceType,RequestHandler> serviceMap = Maps.newHashMap();

    RequestHandler chain1;
    RequestHandler chain2;


    public ChoreServiceContainer() {
        buildChain();
    }

    private void buildChain() {
        chain1 = new OrcCommander(new OrcOfficer(new OrcSoldier(null,true,"WZX"),true),false);
        chain2 = new OrcCommander(new OrcOfficer(null,true),false);
        serviceMap.put(ServiceType.ONE,chain1);
        serviceMap.put(ServiceType.TWO,chain2);
    }

    public void makeRequest(Request req) {
        chain1.handleRequest(req);
    }
}
