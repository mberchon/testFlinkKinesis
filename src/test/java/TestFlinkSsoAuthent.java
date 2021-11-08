import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.junit.Test;
import software.amazon.kinesis.connectors.flink.FlinkKinesisConsumer;
import software.amazon.kinesis.connectors.flink.config.AWSConfigConstants;
import software.amazon.kinesis.connectors.flink.config.ConsumerConfigConstants;

import java.util.Properties;

public class TestFlinkSsoAuthent {

    @Test
    public void test() throws Exception {

        StreamExecutionEnvironment see = StreamExecutionEnvironment.getExecutionEnvironment();
        see.setParallelism(1);

        Properties kinesisConsumerConfig = new Properties();
        kinesisConsumerConfig.setProperty(ConsumerConfigConstants.AWS_REGION, "us-east-1");
        kinesisConsumerConfig.setProperty(AWSConfigConstants.AWS_CREDENTIALS_PROVIDER, AWSConfigConstants.CredentialProvider.AUTO.toString());


        DataStream<String> kinesis = see.addSource(new FlinkKinesisConsumer<>(
                "flink-test",
                new SimpleStringSchema(),
                kinesisConsumerConfig));

        kinesis.print();

        see.execute();

    }

}
