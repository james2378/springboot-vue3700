package com.project.demo.controller;

import com.project.demo.entity.CatProducts;
import com.project.demo.service.CatProductsService;
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
 *猫猫用品：(CatProducts)表控制层
 *
 */
@RestController
@RequestMapping("/cat_products")
public class CatProductsController extends BaseController<CatProducts,CatProductsService> {

    /**
     *猫猫用品对象
     */
    @Autowired
    public CatProductsController(CatProductsService service) {
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
