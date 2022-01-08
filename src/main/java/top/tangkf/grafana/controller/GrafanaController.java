package top.tangkf.grafana.controller;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import top.tangkf.grafana.dto.*;
import top.tangkf.grafana.vo.ColUnit;
import top.tangkf.grafana.vo.DataFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 官方实现
 * https://github.com/bergquist/fake-simple-json-datasource/blob/master/index.js
 */
@RestController
@Slf4j
public class GrafanaController {

    /**
     * should return 200 ok. Used for "Test connection" on the datasource config page.
     * @return
     */
    @GetMapping(value = "/")
    public String testConnect() {
        log.info("检查服务成功");
        return "success";
    }

    /**
     * should return annotations.
     * @param request
     * @return
     */
    @PostMapping(value = "/annotations")
    public String annotations(@RequestBody JSONObject request) {
        log.info("调用annotations接口:{}", JSON.toJSONString(request));
        return "[{annotation:annotation,time:time,title:title,tags:tags,text:text}]";
    }

    /**
     * used by the find metric options on the query tab in panels.
     * @param request
     * @return
     */
    @PostMapping(value = "/search")
    public String search(@RequestBody SearchRequest request) {
        log.info("调用search接口:{}",request);
        return "[\"upper_25\",\"upper_50\",\"upper_75\",\"upper_90\",\"upper_95\"]";
    }


    /**
     * should return metrics based on input.
     * @param request
     * @return
     */
    @PostMapping(value = "/query")
    public String query(@RequestBody QueryRequest request) {
        log.info("调用query接口:{}", JSON.toJSONString(request));

        List<TargetsItem> targets = request.getTargets();

        // 第一个对象
        TargetsItem firstTarget = targets.get(0);

        // 第一个对象的第一层字段
        String refId = firstTarget.getRefId();
        String target = firstTarget.getTarget(); // upper_50
        String type = firstTarget.getType(); // timeseries
        String datasource = firstTarget.getDatasource(); // JSON
        JSONObject additionalData = firstTarget.getData();

        // 第一个对象的第一层字段additionalData详情
//        String my_type = additionalData.getString("my_type");
//        String pt_date = additionalData.getString("pt_date");
//        String orgnization_id = additionalData.getString("orgnization_id");
//
//        log.info("target:{},orgnization_id:{},pt_date:{},my_type:{}",target,orgnization_id,pt_date,my_type);


        ArrayList<DataFormat> data = new ArrayList<DataFormat>();

        ArrayList<ColUnit> columns = new ArrayList<>();
        columns.add(new ColUnit("登记门店数(个)"));
        columns.add(new ColUnit("活跃门店数(个)"));
        columns.add(new ColUnit("新增门店数(个)"));

        ArrayList<ArrayList<String>> rows = new ArrayList<>();

        // 第一行
        ArrayList<String> row = new ArrayList<String>();
        rows.add(row);
//        if("20200922".equals(pt_date)){
//            row.add("100");
//            row.add("200");
//            row.add("300");
//        }else{
            row.add("200");
            row.add("400");
            row.add("600");
//        }

        DataFormat dataFormat =  new DataFormat();
        dataFormat.setType("table");
        dataFormat.setRows(rows);
        dataFormat.setColumns(columns);

        data.add(dataFormat);
        log.info(JSON.toJSONString(data));

        return JSON.toJSONString(data);

    }

    /**
     * /tag-keys should return tag keys for ad hoc filters.
     * @param request
     * @return
     */
    @PostMapping(value = "/tag-keys")
    public List<TagKey> tagKeys(@RequestBody JSONObject request) {
        log.info("调用search接口:{}",request);
        return CollUtil.newArrayList(TagKey.builder()
                .text("div-boot").type("string")
                .build());
    }

    /**
     * /tag-values should return tag values for ad hoc filters.
     * @param request
     * @return
     */
    @PostMapping(value = "/tag-values")
    public List<TagValue> tagValues(@RequestBody TagValueRequest request) {
        log.info("调用search接口:{}",request);
        return CollUtil.newArrayList(TagValue.builder()
                .text("div-boot").build());
    }
}
