package com.jk.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("education-server")
public interface EducationServiceFeign extends EducationServiceApi{

}
