package com.appsdeveloperblog.ws.products;

import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import com.appsdeveloperblog.ws.core.ProductCreatedEvent;

@SpringBootTest
public class IdempotentProducerIntegrationTest {

  @Autowired
  KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

  @MockBean
  KafkaAdmin kafkaAdmin;

  @Test
  void testProducerConfig_whenIdempotenceEnabled_assertsIdempotentProperties() {

    // Arrange
    ProducerFactory<String, ProductCreatedEvent> producerFactory =
        kafkaTemplate.getProducerFactory();

    // Act
    Map<String, Object> config = producerFactory.getConfigurationProperties();

    // Assert
    // Assertions.assertTrue((Boolean) config.get(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG));
    Object enableIdempotence = config.get(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG);
    Assertions.assertNotNull(enableIdempotence, "ENABLE_IDEMPOTENCE_CONFIG should not be null");
    Assertions.assertTrue(Boolean.parseBoolean(enableIdempotence.toString()));

    // Assertions.assertTrue("all".equalsIgnoreCase((String) config.get(ProducerConfig.ACKS_CONFIG)));
    Object acksConfig = config.get(ProducerConfig.ACKS_CONFIG);
    Assertions.assertNotNull(acksConfig, "ACKS_CONFIG should not be null");
    Assertions.assertTrue("all".equalsIgnoreCase(acksConfig.toString()));

    if (config.containsKey(ProducerConfig.RETRIES_CONFIG)) {
      // Assertions.assertTrue(Integer.parseInt(config.get(ProducerConfig.RETRIES_CONFIG).toString()) > 0);
      Object retriesConfig = config.get(ProducerConfig.RETRIES_CONFIG);
      Assertions.assertNotNull(retriesConfig, "RETRIES_CONFIG should not be null");
      Assertions.assertTrue(Integer.parseInt(retriesConfig.toString()) > 0);
    }


  }

}
