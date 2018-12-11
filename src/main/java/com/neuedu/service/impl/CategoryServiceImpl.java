package com.neuedu.service.impl;

import com.google.common.collect.Sets;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.CategoryMapper;
import com.neuedu.pojo.Category;
import com.neuedu.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements ICategoryService {


    @Autowired
    CategoryMapper categoryMapper;


    @Override
    public ServerResponse get_category(Integer categoryId) {
        //step1:非空校验
        if (categoryId==null){
            //返回的是参数不能为空
            return ServerResponse.createServerResponseByError(ResponseCode.PARAM_EMPTY.getStatus(),ResponseCode.PARAM_EMPTY.getMsg());
        }
        //step2:根据categoryid查询类别
      Category category =  categoryMapper.selectByPrimaryKey(categoryId);
      //如果category等于空 说明查询的类别不存在
       if (category==null){
           return ServerResponse.createServerResponseByError("查询的类别不存在");
       }

        //step3:查询子类别
       List<Category> categoryList = categoryMapper.findChildCategory(categoryId);

        //step4:返回结果

        return ServerResponse.createServerResponseBySuccess(null,categoryList);
    }

    //增加节点的实现类
    @Override
    public ServerResponse add_category(Integer perentId, String categoryName) {
        //step1:非空校验
        if (categoryName==null || categoryName.equals("")){
            return ServerResponse.createServerResponseByError("类别不能为空");
        }

        //step2：添加节点
        Category category = new Category();
        category.setName(categoryName);
        category.setPerentId(perentId);
        //设置默认状态是1
        category.setStatus(1);
       int result = categoryMapper.insert(category);

        //step3:返回结果
        if (result>0){
            //添加成功
            return ServerResponse.createServerResponseBySuccess();
        }
        //否则返回添加失败
        return ServerResponse.createServerResponseByError("添加失败");
    }


    //修改节点接口实现类
    @Override
    public ServerResponse set_category_name(Integer categoryId, String categoryName) {
        //step1:非空校验
            if (categoryId==null || categoryId.equals("")){
                return  ServerResponse.createServerResponseByError("类别id不能为空");
            }
            if (categoryName==null || categoryName.equals("")){
                return ServerResponse.createServerResponseByError("类别名称不能为空");
            }
        //step2:根据categoryId查询
       Category category =  categoryMapper.selectByPrimaryKey(categoryId);
            if (category==null){
                return  ServerResponse.createServerResponseByError("要修改的类别不存在");
            }
        //step3：修改节点
            category.setName(categoryName);
           int result =  categoryMapper.updateByPrimaryKey(category);

        //step4:返回结果
        if (result>0){
            return ServerResponse.createServerResponseBySuccess("修改成功");
        }


        return ServerResponse.createServerResponseByError("修改失败");
    }

    @Override
    public ServerResponse get_deep_category(Integer categoryId) {


        //step1:参数非空校验
            if (categoryId==null){
                return ServerResponse.createServerResponseByError("类别id不能为空");
            }

        //step2:查询
        Set<Category> categorySet = Sets.newHashSet();
            categorySet =findAllChildCategory(categorySet,categoryId);

        Set<Integer> integerSet = Sets.newHashSet();
        Iterator<Category> categoryIterator = categorySet.iterator();
        while (categoryIterator.hasNext()){
            Category category = categoryIterator.next();
            integerSet.add(category.getId());
        }

        return ServerResponse.createServerResponseBySuccess(null,integerSet);
    }

//给递归写个方法
    private Set<Category> findAllChildCategory(Set<Category> categorySet,Integer categoryId){

       Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category!=null){
            categorySet.add(category);
        }

        //查找categoryId下的子节点（平级）
     List<Category> categoryList =  categoryMapper.findChildCategory(categoryId);
        if (categoryList!=null && categoryList.size()>0){
            for (Category category1:categoryList){
                findAllChildCategory(categorySet,category1.getId());
            }
        }
        //递归结束条件
        return categorySet;
    }




}
