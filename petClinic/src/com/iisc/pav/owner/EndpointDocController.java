package com.iisc.pav.owner;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.samples.petclinic.system.CrashController;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.iisc.pav.others.ModelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@Controller
public class EndpointDocController {
    List<String> handlerMethods = new ArrayList<>();


//	@RequestMapping(value= CrashController.PATH, method=RequestMethod.GET)
//	@ResponseBody
	public String show(ModelMap model) {
		model.put("handlerMethods", handlerMethods);
		System.out.println(handlerMethods);
		return (handlerMethods.toString());
	}
}
