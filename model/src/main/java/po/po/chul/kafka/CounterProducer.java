package po.po.chul.kafka;

import org.apache.kafka.clients.producer.Producer;
import kafka.producer.ProducerConfig;

import java.util.Properties;

public class CounterProducer {

    private Properties kafkaPropos = new Properties();
    private Producer<String, String> producer;
    private ProducerConfig config;
    private String topic;

    public static void main(String[] args) {
        String brokerList ="";
        String topic ="";
        String sync ="";
        int delay = 0;
        int count = 0;

        CounterProducer counter = new CounterProducer();

        counter.configure(brokerList, sync);
    }

    private void configure(String brokerList, String sync) {
        kafkaPropos.put("metadata.broker.list", brokerList);
        kafkaPropos.put("serializer.class", "kafka.serializer.StringEncoder");
        kafkaPropos.put("request.required.asks", "1");
        kafkaPropos.put("producer.type", sync);

        config = new ProducerConfig(kafkaPropos);
    }
}
