package org.geekbang.projects.cs.frontend.chat.degrade;

import com.alibaba.csp.sentinel.context.Context;
import com.alibaba.csp.sentinel.slotchain.ResourceWrapper;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

public class SwitchRuleChecker {

    public static void checkSwitch(ResourceWrapper resource, Context context) throws SwitchException {

        Set<SwitchRule> switchRuleSet = initSwitchRule();
        // 遍历规则
        for (SwitchRule rule : switchRuleSet) {
            // 判断开关状态，开关未打开则跳过
            if (!rule.getStatus().equalsIgnoreCase(SwitchRule.SWITCH_KEY_OPEN)) {
                continue;
            }
            if (rule.getResources() == null) {
                continue;
            }
            // 实现 include 语意
            if (!CollectionUtils.isEmpty(rule.getResources().getInclude())) {
                if (rule.getResources().getInclude().contains(resource.getName())) {
                    throw new SwitchException(resource.getName(), "switch");
                }
            }
            // 实现 exclude 语意
            if (!CollectionUtils.isEmpty(rule.getResources().getExclude())) {
                if (!rule.getResources().getExclude().contains(resource.getName())) {
                    throw new SwitchException(resource.getName(), "switch");
                }
            }
        }
    }

    private static Set<SwitchRule> initSwitchRule() {
        Set<SwitchRule> rules = new HashSet<>();

        SwitchRule rule = new SwitchRule();
        rule.setStatus(SwitchRule.SWITCH_KEY_OPEN);
        SwitchRule.Resources resources = new SwitchRule.Resources();
        Set<String> include = new HashSet<>();
        include.add("/chats/switch");
        resources.setInclude(include);
        rule.setResources(resources);

        rules.add(rule);
        return rules;
    }
}