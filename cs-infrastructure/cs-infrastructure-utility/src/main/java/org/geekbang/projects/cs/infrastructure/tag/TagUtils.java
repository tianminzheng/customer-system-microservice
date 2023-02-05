package org.geekbang.projects.cs.infrastructure.tag;

import feign.RequestTemplate;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.HttpHeaders;

import java.util.Objects;

public class TagUtils {

    private static final String TAG_NAME = "tag";

    public static String getTag(ServiceInstance instance) {
        return instance.getMetadata().get(TAG_NAME);
    }

    public static String getTag(HttpHeaders headers) {
        String tag = headers.getFirst(TAG_NAME);
        return tag;
    }
    public static void setTag(RequestTemplate requestTemplate, String tag) {
        requestTemplate.header(TAG_NAME, tag);
    }
}
