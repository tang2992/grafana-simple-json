package top.tangkf.grafana.dto;

import lombok.Data;

@Data
public class Annotation{
	private String datasource;
	private boolean enable;
	private String query;
	private String name;
	private String iconColor;
}
