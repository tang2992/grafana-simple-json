package top.tangkf.grafana.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class TargetsItem{
	private String refId;
	private String type;
	private String target;
	private String datasource;
	private JSONObject data;
}
