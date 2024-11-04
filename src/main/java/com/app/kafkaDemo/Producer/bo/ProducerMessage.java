package com.app.kafkaDemo.Producer.bo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProducerMessage implements Serializable {

    private Integer id;
    private String msg;
}
