package com.cloud.utils.qal;

import java.util.Map;

import com.google.gson.JsonElement;

public interface ListenerService {
	
	Boolean processDetails(JsonElement reqMethod, JsonElement reqArgs, JsonElement reqMsgId, Map<String, Object> mStatus);
	

}
