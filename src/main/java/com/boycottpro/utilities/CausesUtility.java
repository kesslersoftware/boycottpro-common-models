package com.boycottpro.utilities;

import com.boycottpro.models.Causes;
import com.boycottpro.models.CausesSubset;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public class CausesUtility {

    public static Causes mapToCauses(Map<String, AttributeValue> item) {
        Causes cause = new Causes();
        cause.setCause_id(item.get("cause_id").s());
        cause.setCategory(item.get("category").s());
        cause.setCause_desc(item.get("cause_desc").s());
        cause.setFollower_count(Integer.parseInt(item.getOrDefault("follower_count", AttributeValue.fromN("0")).n()));
        return cause;
    }

    public static CausesSubset mapToSubset(Map<String, AttributeValue> item) {
        CausesSubset cause = new CausesSubset();
        cause.setCause_id(item.get("cause_id").s());
        cause.setCause_desc(item.get("cause_desc").s());
        cause.setFollower_count(Integer.parseInt(item.getOrDefault("follower_count", AttributeValue.fromN("0")).n()));
        return cause;
    }
}
