package cn.com.liandisys.shopmall.response.form;

import java.io.Serializable;
import java.util.List;
import cn.com.liandisys.shopmall.entity.Goods;


public class GoodsTypeResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    
    private String name;
    
    private int level;
    
    private String parentId;
    
    private List<GoodsTypeResult> goodsTypeList;
    
    private List<Goods> goodsList;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    
    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getLevel() {
      return level;
    }

    public void setLevel(int level) {
      this.level = level;
    }

    public String getParentId() {
      return parentId;
    }

    public void setParentId(String parentId) {
      this.parentId = parentId;
    }

    public List<GoodsTypeResult> getGoodsTypeList() {
      return goodsTypeList;
    }

    public void setGoodsTypeList(List<GoodsTypeResult> goodsTypeList) {
      this.goodsTypeList = goodsTypeList;
    }

    public List<Goods> getGoodsList() {
      return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
      this.goodsList = goodsList;
    }

}
