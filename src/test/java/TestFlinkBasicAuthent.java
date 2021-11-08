
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import org.junit.Test;
import software.amazon.kinesis.connectors.flink.FlinkKinesisConsumer;
import software.amazon.kinesis.connectors.flink.config.ConsumerConfigConstants;

import java.util.Properties;

public class TestFlinkBasicAuthent {

    @Test
    public void test() throws Exception {

        StreamExecutionEnvironment see = StreamExecutionEnvironment.getExecutionEnvironment();
        see.setParallelism(1);

        Properties kinesisConsumerConfig = new Properties();
        kinesisConsumerConfig.setProperty(ConsumerConfigConstants.AWS_REGION, "us-east-1");
        kinesisConsumerConfig.setProperty(ConsumerConfigConstants.AWS_ACCESS_KEY_ID, "AKIAVPMDGWJM66K3XUWA");
        kinesisConsumerConfig.setProperty(ConsumerConfigConstants.AWS_SECRET_ACCESS_KEY, "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");

        DataStream<String> kinesis = see.addSource(new FlinkKinesisConsumer<>(
                "flink-test",
                new SimpleStringSchema(),
                kinesisConsumerConfig));

        kinesis.print();

        see.execute();

    }
}
