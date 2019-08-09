package com.zfh.dubbo.consumer.mq;

/**
 * @auth zhangfanghui
 * @since 2019-08-05
 */

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Slf4j
@RocketMQTransactionListener(txProducerGroup = "Test" )
@Component
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        System.out.println("本地事务和消息发送:" + JSON.toJSONString(message));
        try{
            String bizUniNo = ((Map)message.getHeaders().get("PROPERTIES")).get("UNIQ_KEY").toString();
            int type = (int) ((Map)o).get("bizType");
            jdbcTemplate.update("insert into t_message_transaction(bizUniNo, bizType, status) values(?, ?,?)", bizUniNo,type, "processing");
        }catch (Exception e){
            e.printStackTrace();
        }
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
         System.out.println("回查信息:" + JSON.toJSONString(message));
        //查询t_message_transaction
        String bizUniNo = null;
        try{
            bizUniNo = ((Map)message.getHeaders().get("PROPERTIES")).get("UNIQ_KEY").toString();
            List<Map> res = jdbcTemplate.query("select * from t_message_transaction where bizUniNo = ?",new Object[]{bizUniNo},new MyRowMappe());
            if(CollectionUtils.isEmpty(res)){
                //TODO 判断次数，超过一定次数之后再返回rollback，防止应用断网或者重启
                System.out.println("【rollback】，bizUniNo："+bizUniNo);
                return RocketMQLocalTransactionState.ROLLBACK;
            }
            jdbcTemplate.update("update t_message_transaction set status = ? where bizUniNo = ?",  RocketMQLocalTransactionState.COMMIT.toString(),bizUniNo);
             System.out.println("【commit】，bizUniNo："+bizUniNo);
            return RocketMQLocalTransactionState.COMMIT;
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("【rollback】，bizUniNo："+bizUniNo);
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}