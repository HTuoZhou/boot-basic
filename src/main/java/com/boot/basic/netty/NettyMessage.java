package com.boot.basic.netty;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author HTuoZhou
 */
@Data
@Accessors(chain = true)
public class NettyMessage implements Serializable {

    /**
     * 指令
     */
    private String clientInstruction;

    /**
     * 唯一id
     */
    private String clientUniqueId;

}
