package top.tangkf.grafana.dto;

import java.util.List;
import lombok.Data;

@Data
public class QueryRequest{
	private int panelId;
	private int intervalMs;
	private int maxDataPoints;
	private String format;
	private Range range;
	private RangeRaw rangeRaw;
	private String interval;
	private List<TargetsItem> targets;
}
