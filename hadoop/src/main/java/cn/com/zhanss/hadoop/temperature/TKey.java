package cn.com.zhanss.hadoop.temperature;

import lombok.Data;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @Description MapReduce中的key类型
 * @ClassName TKey
 * @Author zhanshuchan
 * @Date 2023/11/9
 **/
@Data
public class TKey implements WritableComparable {
    private Integer year;

    private Integer month;

    private Integer day;

    private Integer temperature;

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {

    }
}
