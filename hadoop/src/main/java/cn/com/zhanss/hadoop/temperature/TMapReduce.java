package cn.com.zhanss.hadoop.temperature;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

/**
 * @Description TODO
 * @ClassName TMapReduce
 * @Author zhanshuchan
 * @Date 2023/11/9
 **/
public class TMapReduce {

    public static void main(String[] args) {
        // 加载默认配置文件
        Configuration conf = new Configuration(true);
        try {
            Job job = Job.getInstance(conf);
            // 指定要执行job任务对应的jar类型
            job.setJarByClass(TMapReduce.class);
            job.setJobName("TMapReduce");

            // map对应key类型
            job.setMapOutputKeyClass(TKey.class);
            // mapTask输出类型
            job.setMapOutputValueClass(WritableComparator.class);
            job.setMapperClass(TMapper.class);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
