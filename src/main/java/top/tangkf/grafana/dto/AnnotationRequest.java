package top.tangkf.grafana.dto;

import lombok.Data;

@Data
public class AnnotationRequest{
	private Annotation annotation;
	private Range range;
	private RangeRaw rangeRaw;
}
