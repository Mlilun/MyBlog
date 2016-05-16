package com.serviceImpl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.service.CategoryService;
import com.pojo.Category;

public class CategoryServiceImpl extends HibernateDaoSupport implements
		CategoryService {

	@Override
	public void addArticleTypes(int articleId, String articleTypes, String articleAttribute) {
		// TODO Auto-generated method stub
		if(articleTypes == null) return;
		
		String[] types = articleTypes.split(",|��");
		for(String type : types){
			//������������ݿ�
			Category category = new Category(articleId, type.trim(), articleAttribute);
			this.getHibernateTemplate().save(category);
		}
	}

}
