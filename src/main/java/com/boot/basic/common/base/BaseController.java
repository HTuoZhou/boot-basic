package com.boot.basic.common.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author HTuoZhou
 */
public class BaseController {

    @Autowired
    protected RestTemplate restTemplate;
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;

    @ModelAttribute
    public void initReqAndRes(RestTemplate restTemplate, HttpServletRequest request, HttpServletResponse response) {
        this.restTemplate = restTemplate;
        this.request = request;
        this.response = response;
    }

}
