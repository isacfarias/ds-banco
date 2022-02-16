package com.farias.banco.dspessoa.constants;

/**
 * https://docs.spring.io/spring-cloud-stream-binder-rabbit/docs/3.0.10.RELEASE/reference/html/spring-cloud-stream-binder-rabbit.html
 */
public interface HeadersConstants {

    //basic headers
    String AUTHORIZATION_UPPER_CASE = "Authorization";
    String AUTHORIZATION_LOWER_CASE = "authorization";
    String CONTENT_TYPE = "ContentType";
    String HEADER_MICRO_SERVICE_NAME = "microservice-name";
    String MICRO_SERVICE_NAME = "ds-pessoa";
    String HEADER_X_ORIGINAL_EXCHANGE = "x-original-exchange";


    //tratativas de erros
    String HEADER_X_RETRIES = "x-retry-count";
    Integer MAX_RETRY = 3;
    String HEADER_EXCEPTION_MESSAGE = "x-exception-message";
    String X_RETRIES_TO = "x-retry-TO";
    Long DELAY_TIME_RETRY = 60000L; //60 seg

    //origem da consumidor original
    String AMQP_ORIGINAL_CONSUMER_TAG = "original_consumerTag";
    String AMQP_CONSUMER_TAG = "amqp_consumerTag";

    //origem da fila original
    String AMQP_ORIGINAL_QUEUE_CONSUMER = "original_consumer_queue";
    String AMQP_QUEUE_CONSUMER = "original_consumerQueue";

}
