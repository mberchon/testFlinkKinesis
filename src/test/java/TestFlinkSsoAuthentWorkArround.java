import com.amazonaws.services.securitytoken.model.GetSessionTokenResult;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.junit.Test;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.kinesis.connectors.flink.FlinkKinesisConsumer;
import software.amazon.kinesis.connectors.flink.config.AWSConfigConstants;
import software.amazon.kinesis.connectors.flink.config.ConsumerConfigConstants;

import java.util.Properties;

public class TestFlinkSsoAuthentWorkArround {

    @Test
    public void test() throws Exception {

        StreamExecutionEnvironment see = StreamExecutionEnvironment.getExecutionEnvironment();
        see.setParallelism(1);

        var provider = DefaultCredentialsProvider.create();

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
