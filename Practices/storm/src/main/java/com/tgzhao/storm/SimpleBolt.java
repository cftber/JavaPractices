package com.tgzhao.storm;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.util.Map;

/**
 * 接收喷发节点(Spout)发送的数据进行简单的处理后，发射出去。
 *
 * @author Administrator
 *
 */
public class SimpleBolt extends BaseRichBolt {
//    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
//        try {
//            String msg = tuple.getString(0);
//            if (msg != null){
//                //System.out.println("msg="+msg);
//                basicOutputCollector.emit(new Values(msg + "msg is processed!"));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        System.out.println("====Bolt==declarer========");
    }

    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        System.out.println("======Bolt=====prepare========");
    }

    public void execute(Tuple tuple) {
        String desc = "out :"+ tuple.getString(0);
        System.out.println("====Bolt========desc:"+desc);
    }
}
