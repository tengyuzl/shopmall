package cn.com.liandisys.shopmall.common;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import cn.com.liandisys.shopmall.expection.BusinessException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public String globalBusinessException(HttpServletResponse response, BusinessException ex) {

    System.out.println("BusinessException：访问/error" + "  错误代码：" + response.getStatus());
    return "";
  }

}
