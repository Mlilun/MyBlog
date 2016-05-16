package com.serviceImpl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.pojo.BlogCategory;
import com.service.CategoryService;
import com.tools.Constants;

public class CategoryServiceImpl extends HibernateDaoSupport implements
		CategoryService {

	@Override
	public void addArticleTypes(int articleId, String articleTypes, String articleAttribute) {
		// TODO Auto-generated method stub
		if(articleTypes == null) return;
		
		String[] types = articleTypes.split(",|��");
		for (int i = 0; i < types.length; i++) {
			//ȥ�� �õ�ǰstr�����ıȽ� ��������� ����Ե�ǰ��
			int j;
			for (j = i + 1; j < types.length; j++) {
				if (types[i].equals(types[j])) {
					break;
				}
			}
			if (j != types.length) {
				continue;
			}
			
			String type = types[i];
			System.out.print(type + " ; ");
			//������������ݿ�
			if (articleAttribute .equals(Constants.BLOG)) {
				BlogCategory category = new BlogCategory(articleId, type.trim());
				this.getHibernateTemplate().save(category);
			}else if(articleAttribute .equals(Constants.DIARY)){
				
			}else if(articleAttribute .equals(Constants.ESSAY)){
				
			}
		}
		
	}

}
