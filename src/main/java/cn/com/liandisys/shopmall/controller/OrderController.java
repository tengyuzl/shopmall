package cn.com.liandisys.shopmall.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.unionpay.acp.sdk.AcpService;
import com.unionpay.acp.sdk.LogUtil;
import com.unionpay.acp.sdk.SDKConfig;
import cn.com.liandisys.shopmall.common.Conts;
import cn.com.liandisys.shopmall.entity.Goods;
import cn.com.liandisys.shopmall.entity.GoodsType;
import cn.com.liandisys.shopmall.entity.OrderInfo;
import cn.com.liandisys.shopmall.entity.Users;
import cn.com.liandisys.shopmall.enumerat.IsVipEnum;
import cn.com.liandisys.shopmall.enumerat.OrderErrorEnum;
import cn.com.liandisys.shopmall.expection.BusinessException;
import cn.com.liandisys.shopmall.response.OrderResult;
import cn.com.liandisys.shopmall.service.GoodsService;
import cn.com.liandisys.shopmall.service.OrderService;
import cn.com.liandisys.shopmall.unionpay.DemoBase;

/**
 * 订单控制器
 * @author zhuht
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController{
  
  /** 订单文件路径*/
  private final static String FILE_PATH = "order";
  
  /** 错误路径*/
  private final static String ERROR_PATH = "order/error";
  /** 创建订单路径*/
  private final static String CREATE_PATH = "order/create";
  /** 订单支付路径*/
  private final static String DETAIL_PATH = "order/pay";
  /** 我的订单*/
  private final static String MY_ORDER_PATH = "order/list";
  
  @Autowired
  private GoodsService goodService;
  
  @Autowired
  private OrderService orderService;
  
  @PostConstruct
  public void init() {
    //创建order路径
    String newFileDirectoryPath = Conts.FILE_UPLOAD_DIC + File.separator + FILE_PATH ;
    File fileDirectory = new File(newFileDirectoryPath);
    if (!fileDirectory.exists()) {
      fileDirectory.mkdir();
    }
    //从classpath加载acp_sdk.properties文件
    SDKConfig.getConfig().loadPropertiesFromSrc();
  }

  
  @GetMapping("/list")
  public String list(HttpServletRequest request , HttpSession httpSession) {
    //判断用户是否登陆
    if(!super.checkUser(httpSession)) {
      //没有登陆，调转到login页面
      return Conts.PATH_LOGIN;
    }
    
    Users curUsers = super.getCurUser(httpSession);
    List<OrderResult> orderList = orderService.findOrderListByUserId(curUsers.getId());
    request.setAttribute("orderList", orderList);
    return MY_ORDER_PATH;
  }
  
  @GetMapping("/tocreate/{goodsId}")
  public String toCreate(@PathVariable("goodsId") String goodsId, HttpServletRequest request , HttpSession httpSession){
    //判断用户是否登陆
    if(!super.checkUser(httpSession)) {
      //没有登陆，调转到login页面
      return Conts.PATH_LOGIN;
    }
    //根据商品id查询商品
    Goods goods = goodService.findGoodsById(goodsId);
    if(Objects.isNull(goods)) {
      //商品不存在
      request.setAttribute("errorType", OrderErrorEnum.NOTGOODS.getEnumValue());
      return ERROR_PATH;
    }
    //判断商品是否是vip商品
    boolean isVip = goodService.isVipGoods(goods);
    if(isVip) {
      //需要vip用户
      Users user = super.getCurUser(httpSession);
      Integer uType = user.getUtype();
      if(IsVipEnum.VIP.getEnumValue() != uType) {
        //商品不存在
        request.setAttribute("errorType", OrderErrorEnum.VIPGOODS.getEnumValue());
        return ERROR_PATH;
      }
    }
    //获取商品类别id
    String goodsTypeId = goods.getGoodsTypeId();
    GoodsType goodstype = goodService.findGoodsTypeById(goodsTypeId);
    request.setAttribute("goodsId", goodsId);
    request.setAttribute("goods", goods);
    request.setAttribute("goodstype", goodstype);
    return CREATE_PATH;
  }
  
  @PostMapping("/create")
  public String create(@RequestParam("goodsId") String goodsId, 
      @RequestParam("file01") MultipartFile file01,@RequestParam("file25") MultipartFile file25,
      HttpServletRequest request , HttpSession httpSession){
    //判断用户是否登陆
    if(!super.checkUser(httpSession)) {
      //没有登陆，调转到login页面
      return Conts.PATH_LOGIN;
    }
    //生成订单id
    String orderNo = UUID.randomUUID().toString().replaceAll("-","");
    
    try {
      if(Objects.nonNull(file01) && !StringUtils.isEmpty(file01.getOriginalFilename())) {
        uploadOrderFile(orderNo,file01,httpSession);
      }
      if(Objects.nonNull(file25) && !StringUtils.isEmpty(file25.getOriginalFilename())) {
        uploadOrderFile(orderNo,file25,httpSession);
      }
    }catch (BusinessException e) {
      request.setAttribute("errorType", OrderErrorEnum.FILEERROR.getEnumValue());
      return ERROR_PATH;
    }
    
    //上传文件成功
    String file01Name = file01.getOriginalFilename();
    String file25Name = file25.getOriginalFilename();
    
    //创建订单
    Users curUsers = super.getCurUser(httpSession);
    try {
      OrderInfo order = orderService.insertOrder(orderNo, goodsId, file01Name, file25Name, curUsers.getId());
      //订单信息
      request.setAttribute("order", order);
    } catch (Exception e) {
        request.setAttribute("errorType", OrderErrorEnum.CREATEORDERERROR.getEnumValue());
        return ERROR_PATH;
    }
    
    //保持创建订单内容
    Goods goods = goodService.findGoodsById(goodsId);
    String goodsTypeId = goods.getGoodsTypeId();
    GoodsType goodstype = goodService.findGoodsTypeById(goodsTypeId);
    request.setAttribute("goods", goods);
    request.setAttribute("goodstype", goodstype);
    return DETAIL_PATH;
  }
  
  @PostMapping("/pay/{orderNo}")
  public void pay(@PathVariable("orderNo") String orderNo,@RequestParam("commodityName") String commodityName,
      HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("text/html; charset="+ DemoBase.encoding);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    
    //前台页面订单编号
    OrderInfo order = orderService.findOrderById(orderNo);
    
    Map<String, String> requestData = new HashMap<String, String>();
    //设置不变参数
    setPayHeader(requestData);
    
    /***商户接入参数***/
    //商户号码，请改成自己申请的正式商户号或者open上注册得来的777测试商户号
    requestData.put("merId", Conts.MER_ID);        
    //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则
    requestData.put("orderId",orderNo);                    

    //订单发送时间
    //订单发送时间，取系统时间，格式为yyyyMMddHHmmss，必须取当前时间，否则会报txnTime无效
    String txnTime = sdf.format(new Date());
    requestData.put("txnTime", txnTime);  
    //交易币种（境内商户一般是156 人民币）
    //TODO
    requestData.put("currencyCode", "156"); 
    //交易金额，单位分，不要带小数点
    int txnAmt = (int) (order.getTotalPrice()*100);
    requestData.put("txnAmt", String.valueOf(txnAmt)); 
    //请求方保留域，如需使用请启用即可；透传字段（可以实现商户自定义参数的追踪）本交易的后台通知,
    //对本交易的交易状态查询交易、对账文件中均会原样返回，商户可以按需上传，长度为1-1024个字节。
    //出现&={}[]符号时可能导致查询接口应答报文解析失败，建议尽量只传字母数字并使用|分割，
    //或者可以最外层做一次base64编码(base64编码之后出现的等号不会导致解析失败可以不用管)。
    //requestData.put("reqReserved", "透传字段");                             
    
    requestData.put("riskRateInfo", "{commodityName="+commodityName+"}");
    
    //前台通知地址 （需设置为外网能访问 http https均可），支付成功后的页面 点击“返回商户”按钮的时候将异步通知报文post到该地址
    //如果想要实现过几秒中自动跳转回商户页面权限，需联系银联业务申请开通自动返回商户权限
    //异步通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
    requestData.put("frontUrl", DemoBase.frontUrl);
    
    /**
     * 后台通知地址（需设置为【外网】能访问 http https均可），支付成功后银联会自动将异步通知报文post到商户上送的该地址，
     * 失败的交易银联不会发送后台通知
     * 后台通知参数详见open.unionpay.com帮助中心 下载 产品接口规范 网关支付产品接口规范 消费交易 商户通知 
     * 注意:1.需设置为外网能访问，否则收不到通知 2.http
     * https均可 3.收单后台通知后需要10秒内返回http200或302状态码
     * 4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200，
     * 那么银联会间隔一段时间再次发送。 总共发送5次，每次的间隔时间为0,1,2,4分钟。
     * 5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，
     * 否则将会验签失败
     */
    requestData.put("backUrl", DemoBase.backUrl);

    // 订单超时时间。
    // 超过此时间后，除网银交易外，其他交易银联系统会拒绝受理，提示超时。 
    // 跳转银行网银交易如果超时后交易成功，会自动退款，大约5个工作日金额返还到持卡人账户。
    // 此时间建议取支付时的北京时间加15分钟。
    // 超过超时时间调查询接口应答origRespCode不是A6或者00的就可以判断为失败。
    requestData.put("payTimeout", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date().getTime() + 15 * 60 * 1000));
    
    //////////////////////////////////////////////////
    //
    //       报文中特殊用法请查看 PCwap网关跳转支付特殊用法.txt
    //
    //////////////////////////////////////////////////
    
    /**请求参数设置完毕，以下对请求参数进行签名并生成html表单，将表单写入浏览器跳转打开银联页面**/
    //报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
    Map<String, String> submitFromData = AcpService.sign(requestData,DemoBase.encoding);  
    
    ////获取请求银联的前台地址：对应属性文件acp_sdk.properties文件中的acpsdk.frontTransUrl
    String requestFrontUrl = SDKConfig.getConfig().getFrontRequestUrl();  
    //生成自动跳转的Html表单
    String html = AcpService.createAutoFormHtml(requestFrontUrl, submitFromData,DemoBase.encoding);  
    
    LogUtil.writeLog("打印请求HTML，此为请求报文，为联调排查问题的依据："+html);
    //将生成的html写到浏览器中完成自动跳转打开银联支付页面；这里调用signData之后，
    //将html写到浏览器跳转到银联页面之前均不能对html中的表单项的名称和值进行修改，如果修改会导致验签不通过
    resp.getWriter().write(html);
  }
  
  
  private void uploadOrderFile(String orderNo,MultipartFile file,HttpSession httpSession) {
    String fileName = file.getOriginalFilename();
    Users curUsers = super.getCurUser(httpSession);
    //判断用户是否创建过
    String newFileDirectoryUserPath = Conts.FILE_UPLOAD_DIC + File.separator + FILE_PATH + File.separator 
        + curUsers.getUname();
    File fileDirectoryUser = new File(newFileDirectoryUserPath);
    if (!fileDirectoryUser.exists()) {
      fileDirectoryUser.mkdir();
    }
    //创建订单文件夹
    String newFileDirectoryOrderPath = newFileDirectoryUserPath + File.separator + orderNo;
    File fileDirectoryOrder = new File(newFileDirectoryOrderPath);
    if (!fileDirectoryOrder.exists()) {
      fileDirectoryOrder.mkdir();
    }
    
    String newFileName = newFileDirectoryOrderPath + File.separator + fileName;
    //创建文件
    File destFile = new File(newFileName);
    try {
        file.transferTo(destFile);
    } catch (IOException e) {
        throw new BusinessException(OrderErrorEnum.FILEERROR.getEnumValue());
    }
  }
  
  private void setPayHeader(Map<String, String> requestData) {
    /***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
    requestData.put("version", DemoBase.version);                 //版本号，全渠道默认值
    requestData.put("encoding", DemoBase.encoding);               //字符集编码，可以使用UTF-8,GBK两种方式
    requestData.put("signMethod", SDKConfig.getConfig().getSignMethod()); //签名方法
    requestData.put("txnType", "01");                             //交易类型 ，01：消费
    requestData.put("txnSubType", "01");                          //交易子类型， 01：自助消费
    requestData.put("bizType", "000201");                         //业务类型，B2C网关支付，手机wap支付
    requestData.put("accessType", "0");                           //接入类型，0：直连商户 
    requestData.put("channelType", "07");                         //渠道类型，这个字段区分B2C网关支付和手机wap支付；07：PC,平板  08：手机
  }
}
