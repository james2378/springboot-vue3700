package com.project.demo.controller;

import com.project.demo.entity.MembershipCenter;
import com.project.demo.service.MembershipCenterService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *会员中心：(MembershipCenter)表控制层
 *
 */
@RestController
@RequestMapping("/membership_center")
public class MembershipCenterController extends BaseController<MembershipCenter,MembershipCenterService> {

    /**
     *会员中心对象
     */
    @Autowired
    public MembershipCenterController(MembershipCenterService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
