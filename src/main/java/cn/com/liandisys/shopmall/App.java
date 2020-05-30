package cn.com.liandisys.shopmall;

import java.io.File;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import cn.com.liandisys.shopmall.common.Conts;

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
      SpringApplication.run(App.class, args);
      //创建订单上传路径
      File fileDirectory = new File(Conts.FILE_UPLOAD_DIC);
      if (!fileDirectory.exists()) {
        fileDirectory.mkdir();
      }
    }
}
