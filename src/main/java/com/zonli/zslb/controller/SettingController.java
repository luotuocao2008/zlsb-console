package com.zonli.zslb.controller;

import com.zonli.zslb.common.model.Settings;
import com.zonli.zslb.util.HttpRequestUtils;
import com.zonli.zslb.util.JsonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/setting")
public class SettingController {

	@RequestMapping(value="/")
	public ModelAndView index(Integer p){
//        Page page = new Page();
//        if(p != null) page.setCurrentPage(p);
//        page.setUrl("/?p=");
//
//        List<Setting> settings = settingsService.selectAllOpen();

        String url="http://localhost:8500/v1/catalog/nodes?dc=dc1";
        String strResult= HttpRequestUtils.httpGet(url);
        JSONArray jsonArray = JSONArray.fromObject(strResult);
        List list =JsonUtil.jsonToList(jsonArray);
        Map<String, Object> map = new HashMap<>();
        map.put("lists", list);
        return new ModelAndView("setting/index", map);
	}


    @RequestMapping(value="/add")
    public ModelAndView add(){
        return new ModelAndView("setting/add");
    }

    /**
     * 保存设置
     *
     * @param settings
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Map<String, Object> create(@ModelAttribute @Valid Settings settings) {
        Map<String, Object> resultMap = new HashMap<>();
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("Node",settings.getNode());
        jsonParam.put("Address",settings.getAddress());
        JSONObject serviceObject = new JSONObject();
        serviceObject.put("Service",settings.getServiceName());
        serviceObject.put("Address",settings.getServiceAddress());
        serviceObject.put("Port",settings.getServicePort());
        jsonParam.put("Service",serviceObject);

        String url="http://192.168.1.116:8500/v1/catalog/register";
        String strResult= HttpRequestUtils.httpPut(url,jsonParam);
        if(strResult.equals("true")) {
            resultMap.put("success", true);
            return resultMap;
        }
        resultMap.put("success", false);
        resultMap.put("message", "失败");
        return resultMap;
    }

    /**
     *
     * @param node
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Map<String, Object> delete(@RequestParam String node){
        Map<String, Object> resultMap = new HashMap<>();
        if(!StringUtils.isEmpty(node)){
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("Node",node);
            String url="http://192.168.1.116:8500/v1/catalog/deregister";
            String strResult= HttpRequestUtils.httpPut(url,jsonParam);
            if(strResult.equals("true")) {
                resultMap.put("success", true);
                return resultMap;
            }

        }
        resultMap.put("success", false);
        resultMap.put("message", "失败");
        return resultMap;
    }

}
