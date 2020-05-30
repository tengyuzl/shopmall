package cn.com.liandisys.shopmall.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import cn.com.liandisys.shopmall.response.form.GoodsTypeResult;
import cn.com.liandisys.shopmall.service.GoodsService;

@Controller
public class IndexController {
  
  @Autowired
  private GoodsService goodService;
  
  @GetMapping({"/index", "/", "/index.html"})
  public String index(HttpServletRequest request){
    List<GoodsTypeResult>  goodsTypeList = goodService.indexGoodsType();
    //分类数据
    request.setAttribute("goodsTypeList", goodsTypeList);
    //轮播图
    //request.setAttribute("carousels", carousels);
    return "shopmall";
  }
  
}
