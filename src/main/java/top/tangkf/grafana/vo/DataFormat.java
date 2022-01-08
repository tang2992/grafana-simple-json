package top.tangkf.grafana.vo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class DataFormat {
    private ArrayList<ColUnit> columns;
    private ArrayList<ArrayList<String>> rows;
    private String type;

}

