package org.mall;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.fastjson.JSONObject;
import com.ql.util.express.ExpressRunner;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Unit test for simple App.
 */
public class JayTest{

    /**
     * ￥限时购￥
     * 1：获取活动详情
     *  第一版：管理后台触发，从mysql缓存进redis
     *  第二版：增加了ehcache，通过rabbitmq的fanout模式广播一个消息，活动服务消费，进行数据缓存；与redis组成二级缓存
     *
     * 2：扣减库存《-做了好几版-》
     *  第一版：直接操作数据库，通过update 。。。 set... where...，修改成功标示操作成功
     *      但是qps上不去
     *  第二版：直接使用redis incty操作，返回的结果大于0表示扣减成功
     *       库存已经不足的情况下，还会继续扣除，形成负库存，后边支付成功补偿的话，也补不回来
     *  第三版：使用redis分布式锁
     *  第四版：使用lua脚本
     *
     * 3：创建订单
     *  第一版：在创建订单的请求中，直接走创建订单流程
     *  第二版：在创建订单的请求中，先创建一个简版订单信息存入redis，
     *          发送创建订单详情信息到延迟队列rabbitmq中，同时绑定一个死信队列，
     *          30分钟后消费消息，检查订单的支付状态，未支付成功的调用更改库存接口(跟扣库存一样，只不过这里是+1)对库存进行补偿
     *
     *
     *
     * 在这个过程中发现了几个隐患
     *  1：项目中原先使用redis分布式锁的代码存在问题，只使用了setnx操作，没有考虑锁续命问题
     *      改进代码，增加了锁续命操作、拿锁失败增加了一个自旋机制、新增了一个拿锁方式(可重入)
     *  2：项目中使用的rabbitmq很多敏感操作没有确认机制，
     *      改进代码，增加发送方确认机制、增加手动确认机制、敏感消息持久化。
     *      比如发送了订单的创建的消息，消费的时候需要手动确认，防止发生异常消息丢失问题
     *
     *
     *
     *
     * 主要负责用户服务、授权服务、活动服务的开发
     * 引入OAuth2服务，解决业务中台授权认证问题
     * 从架构层面引入Ehcache、Redis多级缓存系统，极大的提升系统的查询效率，解决热点缓存问题，在该系统上线后性能提升了60%，服务节点同时也相应减少
     * 对原有服务架构进行升级，引入分布式事务，解决了数据一致性问题
     * 对多个服务中间件RabbitMQ、Redis等的使用进行优化，解决数据安全隐患
     * 对服务限流设计以减少服务异常爆出
     * 引入nacos、hystrix、admin等，简化微服务组件、对服务限流设计以减少服务异常爆出、开启对节点的健康监控信息观察，优化ribbon的使用开启负载均衡机制等
     * 引入LogStash做数据清洗到ES提供全文搜索服务
     * CodeReview进行业务代码优化，使用并发编程、编程式事务、db批处理、aop优化业务代码、检查风险code
     * 参与对新业务可能需要的新技术选型、架构变动设计、复杂业务设计，线上疑难杂症诊断等
     *
     *
     *
     *
     *
     *
     * 主要负责用户服务、授权服务、活动服务的开发
     * 引入OAuth2服务，解决业务中台授权认证问题
     * 从架构层面引入Ehcache、Redis多级缓存系统，极大的提升系统的查询效率，解决热点缓存问题，在该系统上线后性能提升了60%，服务节点同时也相应减少
     * 对原有服务架构进行升级，引入分布式事务，解决了数据一致性问题
     * 对多个服务中间件RabbitMQ、Redis等的使用进行优化，解决数据安全隐患
     * 引入LogStash做数据清洗到ES提供全文搜索服务
     * 引入Nacos、Hystrix、Admin等，简化微服务组件、对节点健康监控，优化Ribbon的使用开启负载均衡机制等
     * 做限流设计以减少服务异常爆出
     * CodeReview对业务代码优化，使用并发编程、编程式事务、db批处理、aop优化业务代码、检查风险code等
     * 参与对新业务可能需要的新技术选型、架构变动设计、复杂业务设计，线上疑难杂症诊断等
     *
     *
     *
     **/

    /**
     * ￥拆盲盒￥
     * 从活动服务发起，记录用户参与活动信息；调用积分服务修改积分；商品服务兑换商品
     *
     **/


    @Test
    public void test() throws Exception {
        String publickey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJPdDpIWvJammjs1kDPz8v3xZMt6wXx1d3dSgPY/RTk5Q1zdpKUbGaH1Bu76BFCm1YHjN85YCnxCVjtlMkyvk50CAwEAAQ==";
        String password = "Gzu8NoPxyoBYSDuRCTjCrfd6H4UaB0ilHMhDmh2fF7YhIPoolRqqCGPCp+ei35lb913Tcg1CXNYTplxYzEL29w==";
        String pwd = ConfigTools.decrypt(publickey, password);

        System.out.println(pwd);
    }

    @Test
    public void test0() throws Exception {
        String format = "yyyy-MM-dd HH:mm:ss";
        Date date = new SimpleDateFormat(format).parse("2021-05-20 00:00:00");

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String format1 = sdf.format(date);

        System.out.println(format1.replace(" ","T"));
    }

    @Test
    public void test1() throws Exception {
        //定义表达式，相当于 1+(22+22)+(2+2)
        String exp = " 1 addT 22 addT 2";
        ExpressRunner runner = new ExpressRunner();
        //定义操作符addT，其实现为AddTwiceOperator
        runner.addOperator("addT", new AddTwiceOperator());
        //执行表达式，并将结果赋给r
        int r = (Integer)runner.execute(exp,null,null,false,false);
        System.out.println(r);
        Assert.assertTrue("操作符执行错误",r==49);
    }

    @Test
    public void test3() throws Exception {
        Map<String, String> map = new HashMap<>(2);

        List<Integer> list = new ArrayList<>(1);
        list.add(1);
        list.add(2);
        list.add(3);

        list.add(1,1);

        System.out.println();
    }


    @Test
    public void test2() throws Exception {

        Semaphore semaphore = new Semaphore(0);
        semaphore.release();
        semaphore.getQueueLength();
        semaphore.acquire();
        semaphore.tryAcquire();


        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        lock.lock();
        condition.await();
        condition.signalAll();
        lock.unlock();


        new ArrayBlockingQueue<>(1, true);
        new LinkedBlockingQueue<>();
        new SynchronousQueue<>();

        new ConcurrentHashMap<>();

        new CopyOnWriteArrayList<>();
        new CopyOnWriteArraySet<>();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.countDown();
        countDownLatch.await();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1);
        cyclicBarrier.await();
        cyclicBarrier.reset();


        new ConcurrentSkipListMap<>();
        new LinkedHashMap<>();



        Collections.swap(new ArrayList<>(), 1, 1);
        Collections.binarySearch(new ArrayList<>(), 1);
        Arrays.binarySearch(new int[1], 1);

        Collections.unmodifiableList(new ArrayList<>());
        Collections.synchronizedList(new ArrayList<>());

        new Thread();
        new ThreadLocal<>();
        new InheritableThreadLocal();

        new HashSet<>();
        new HashMap<>();
        new Hashtable<>();

        new LongAdder();

        new ArrayList<>();
        new Vector<>();
        new Stack<>();

        new StringBuffer();

        Class<?> aClass = Class.forName("");
    }

    public static void main(String[] args) throws IOException {
        System.out.println(Math.pow(0,-1));
        byte[] b = new byte[]{(byte)0x0,(byte)0x1,(byte)0x2};
//        LinkedHashMap<String, String> lruList = new LinkedHashMap<>(5, 0.75F, true);
        LinkedHashMap<String, String> lruList = new LRULinkedHashMap<>(5);
        lruList.put("1","11");
        lruList.put("2","22");
        lruList.put("3","33");
        lruList.put("4","44");
        lruList.put("5","55");

        System.out.println(lruList.toString());
        lruList.put("2","22");
        System.out.println(lruList.toString());
        lruList.put("6","66");
        System.out.println(lruList.toString());
        lruList.put("5","55");
        System.out.println(lruList.toString());
    }


    static class LRULinkedHashMap<K,V> extends LinkedHashMap<K,V>{
        //定义缓存的容量
        private int capacity;
        private static final long serialVersionUID = 1L;
        //带参数的构造器
        LRULinkedHashMap(int capacity){
            //调用LinkedHashMap的构造器，传入以下参数
            super(16,0.75f,true);
            //传入指定的缓存最大容量
            this.capacity=capacity;
        }
        //实现LRU的关键方法，如果map里面的元素个数大于了缓存最大容量，则删除链表的顶端元素
        @Override
        public boolean removeEldestEntry(Map.Entry<K, V> eldest){
            System.out.println(eldest.getKey() + "=" + eldest.getValue());
            return size()>capacity;
        }
    }
}
