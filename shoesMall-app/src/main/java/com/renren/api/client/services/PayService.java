package com.renren.api.client.services;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-21
 */
public interface PayService {

    /**
     * 查询某个用户在一个应用中一次消费是否完成。
     * 
     * @param orderId 用户消费校内豆订单号
     * @return
     */
    public boolean isCompleted(long orderId);

    /**
     * 预存入用户在应用中消费产生的订单数据，消费数额等信息，返回保证一个用户某次在一个应用中支付校内豆安全性的Token。
     * 
     * @param orderId 用户消费校内豆订单号，参数必须保证唯一。
     * @param amount 校内豆消费数额, 取值范围为[0,100]。
     * @param desc 用户使用校内豆购买的虚拟物品的名称。
     * @param type 0:WEB支付订单，1:WAP支付订单，默认值为0
     * @return
     */
    public String regOrder(long orderId, int amount, String desc, int type);

    /**
     * 测试用；查询某个用户在一个应用中一次消费是否完成。
     * 
     * @param orderId 用户消费校内豆订单号
     * @return
     */
    public boolean isCompletedTest(long orderId);

    /**
     * 测试用；预存入用户在应用中消费产生的订单数据，消费数额等信息，返回保证一个用户某次在一个应用中支付校内豆安全性的Token。
     * 
     * @param orderId 用户消费校内豆订单号，参数必须保证唯一。
     * @param amount 校内豆消费数额, 取值范围为[0,100]。
     * @param desc 用户使用校内豆购买的虚拟物品的名称。
     * @param type 0:WEB支付订单，1:WAP支付订单，默认值为0
     * @return
     */
    public String regOrderTest(long orderId, int amount, String desc, int type);

}
