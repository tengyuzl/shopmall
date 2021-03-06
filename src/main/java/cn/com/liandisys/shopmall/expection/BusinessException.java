package cn.com.liandisys.shopmall.expection;

public class BusinessException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private Integer code;

  public BusinessException(String message) {
    super(message);
  }

  public BusinessException(Integer code, String message) {
    super(message);
    this.code = code;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }


}
