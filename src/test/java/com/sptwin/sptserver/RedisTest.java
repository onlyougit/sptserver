package com.sptwin.sptserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Pipeline;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedis(){
        Redis redis = new Redis();
        redis.setId(1);
        redis.setName("Redis");
        redis.setPrice(new BigDecimal("55"));
        Object object = redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.boundValueOps("redis_1").set(redis);
                return redisOperations.boundValueOps("redis_1").get();
            }
        });
        Redis redis1 = (Redis)object;
        //---------------------------
        System.out.println();
    }
    @Test
    public void testString(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //set key1
        valueOperations.set("key1","val1");
        //get key1
        String value = (String) valueOperations.get("key1");
        //strlen key1
        valueOperations.size("key1");
        //删除key1
        redisTemplate.delete("key1");
    }
    @Test
    public void testHash(){
        Map map = new HashMap();
        map.put("key1","val1");
        map.put("key2","val2");
        //hmset hash key1 val1 key2 val2
        redisTemplate.opsForHash().putAll("hash",map);
        //hset hash key3 val3
        redisTemplate.opsForHash().put("hash","key3","val3");
        //hgetall hash
        Map<String,String> result = redisTemplate.opsForHash().entries("hash");
        for(Map.Entry<String,String> entry : result.entrySet()){
            System.out.println("--------------------------"+entry.getKey()+":"+entry.getValue());
        }
        //hkeys hash
        Set<String> keys = redisTemplate.opsForHash().keys("hash");
        for (String set : keys){
            System.out.println("==========="+set);
        }
        //hvals hash
        List<String> values = redisTemplate.opsForHash().values("hash");
        values.forEach(w->{
            System.out.println("************"+w);
        });
        //hdel hash key1 key2
        redisTemplate.opsForHash().delete("hash","key1","key2");
        //删除hash
        redisTemplate.delete("hash");
    }

    @Test
    public void testList(){
        //可重复，有序
        //链表大部分命令都是进程不安全的，所以提供了阻塞命令，会对对应的链表加锁，来保证并发数据安全和一致性
        ListOperations listOperations = redisTemplate.opsForList();
        //lpush list val1
        listOperations.leftPush("list","val1");
        List<String> list = new ArrayList<>();
        list.add("val2");
        list.add("val3");
        //lpush list val2 val3
        listOperations.leftPushAll("list",list);
        //llen list
        long size = listOperations.size("list");
        //lpop list
        String value = (String) listOperations.leftPop("list");
        //lrange list 0 -1
        List<String> values = listOperations.range("list",0,-1);
        //lrem list 3 val2
        listOperations.remove("list",3,"val2");
        //删除
        redisTemplate.delete("list");
        //阻塞命令
        //blpop list 1
        listOperations.leftPop("list",1, TimeUnit.SECONDS);
    }

    @Test
    public void testSet(){
        //哈希表结构，不可重复，无序的，元素是String结构
        SetOperations setOperations = redisTemplate.opsForSet();
        //sadd set1 val1 val2 val3
        long count = redisTemplate.boundSetOps("set1").add("val1","val2","val3");
        System.out.println("count="+count);//3
        setOperations.add("set2", "val2","val4","val6");
        //scard set1
        Long size = setOperations.size("set1");
        System.out.println("size="+size);//3
        //smembers set1
        Set<String> values = setOperations.members("set1");
        for (String result : values){
            System.out.println("result1="+result);
            /*result1=val1
            result1=val3
            result1=val2*/
        }
        //srem set1 val2
        Long removeCount = setOperations.remove("set1","val2");
        System.out.println("removeCount="+removeCount);//1
        //差集：sdiff set1 set2
        Set<String> diffSet = setOperations.difference("set1","set2");
        for (String result : diffSet){
            System.out.println("result2="+result);
            /*result2=val1
            result2=val3*/
        }
        //交集：sinter set1 set2
        Set<String> interSet = setOperations.intersect("set1","set2");
        for (String result : interSet){
            System.out.println("result3="+result);
            //结果无交集
        }
        //并集：sunion set1 set2
        Set<String> unionSet = setOperations.union("set1","set2");
        for (String result : unionSet){
            System.out.println("result4="+result);
            /*result4=val4
            result4=val1
            result4=val6
            result4=val3
            result4=val2*/
        }
        //求交集，并保存到diff_set中
        Long diffCount = setOperations.differenceAndStore("set1","set2","diff_set");
        System.out.println("diffCount="+diffCount);//2
    }

    @Test
    public void testSortSet(){
        //与Set比较，就多了一个分数和有序
        Set<ZSetOperations.TypedTuple> set1 = new HashSet<ZSetOperations.TypedTuple>();
        Set<ZSetOperations.TypedTuple> set2 = new HashSet<ZSetOperations.TypedTuple>();
        for (int i = 0; i < 5; i++) {
            Double score1 = Double.valueOf(i);
            String val1 = "x"+i;
            ZSetOperations.TypedTuple typedTuple1 = new DefaultTypedTuple(val1,score1);
            set1.add(typedTuple1);
            ZSetOperations.TypedTuple typedTuple2 = new DefaultTypedTuple(val1,score1);
            set2.add(typedTuple2);
        }
        redisTemplate.opsForZSet().add("zset1",set1);
        redisTemplate.opsForZSet().add("zset2",set2);
        Long size = redisTemplate.opsForZSet().zCard("zset1");
    }

    @Test
    public void testHyperLogLog(){
        //基数的作用是评估大约需要多少个存储单元去存储数据
        redisTemplate.opsForHyperLogLog().add("hyper1","a","b","c","d");
        redisTemplate.opsForHyperLogLog().add("hyper1","a");
        redisTemplate.opsForHyperLogLog().add("hyper1","z");
        Long size = redisTemplate.opsForHyperLogLog().size("hyper1");
        System.out.println("size="+size);//5

    }

    /**
     * 流水线：由于网络延迟，多个命令需要等待，造成系统瓶颈，为了提高性能，所以使用流水线
     * 注意：需要考虑List内存大小，严重时会导致内存不足，引发JVM溢出
     */
    @Test
    public void testPipeline(){
        Long start  = System.currentTimeMillis();
        List list = redisTemplate.executePipelined(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                for (int i = 0; i < 100000; i++) {
                    int j = i + 1;
                    redisOperations.boundValueOps("pipeline_key_"+j).set("pipe_value_"+j);
                    redisOperations.boundValueOps("pipeline_key_"+j).get();//没有这行list就没值
                }
                return null;
            }
        });
        Long end  = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
