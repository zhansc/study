package cn.com.zhanss.hadoop.temperature;

import lombok.Data;
import lombok.SneakyThrows;
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
public class TKey implements WritableComparable<DataInput> {
    private Integer year;

    private Integer month;

    private Integer day;

    private Integer temperature;

    @SneakyThrows
    @Override
    public int compareTo(DataInput that) {
        if (that == null) {
            return 0;
        }
        if (this.year != that.readInt()) {
           return 1;
        }
        if (this.month != that.readInt()) {
            return 1;
        }
        if (this.day != that.readInt()) {
            return 1;
        }
        return 0;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.year);
        dataOutput.writeInt(this.month);
        dataOutput.writeInt(this.day);
        dataOutput.writeInt(this.temperature);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.year = dataInput.readInt();
        this.month = dataInput.readInt();
        this.day = dataInput.readInt();
        this.temperature = dataInput.readInt();
    }
}
