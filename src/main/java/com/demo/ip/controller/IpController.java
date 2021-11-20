package com.demo.ip.controller;

import com.maxmind.geoip2.model.CityResponse;
import com.demo.ip.util.IpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 站点IP信息
 */
@RestController
@RequestMapping("ip")
public class IpController {
    @GetMapping
    public Map detail(HttpServletRequest request) {
        String ip = IpUtil.getIPAddress(request);
        CityResponse ipCountryAndCity = IpUtil.getIpCountryAndCity(ip);
        HashMap<String, Object> result = new HashMap<>();
        boolean success = false;
        result.put("ip", ip);
        if (ipCountryAndCity != null) {
            success = true;
            result.put("country", ipCountryAndCity.getCountry());
            result.put("country", ipCountryAndCity.getCity());
        }
        result.put("success", success);
        return result;

    }


}
