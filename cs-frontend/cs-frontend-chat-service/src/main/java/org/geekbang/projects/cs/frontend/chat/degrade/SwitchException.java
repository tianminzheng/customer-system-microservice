package org.geekbang.projects.cs.frontend.chat.degrade;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class SwitchException extends BlockException {

    public SwitchException(String ruleLimitApp, String message) {

        super(ruleLimitApp, message);
    }
}
