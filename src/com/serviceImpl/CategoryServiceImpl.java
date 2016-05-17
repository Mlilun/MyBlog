package com.serviceImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.pojo.Blog;
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
	
	@Override
	public void deleteCategoryByArticleType(String articleType, String articleAttribute) {
		// TODO Auto-generated method stub
		if (articleAttribute .equals(Constants.BLOG)) {
			// ���ݿ������п��ܴ����ظ����� �Ȳ����Щһ���ķ��� Ȼ��ɾ��
			String hql = "from BlogCategory where blogType = '" + articleType+"'";
			List<BlogCategory> blogCategorys = this.getHibernateTemplate().find(hql);
			for (BlogCategory blogCategory : blogCategorys) {
				this.getHibernateTemplate().delete(blogCategory);
			}
		}else if(articleAttribute .equals(Constants.DIARY)){
			
		}else if(articleAttribute .equals(Constants.ESSAY)){
			
		}
	}
	
	@Override
	public List findCategorys(String articleAttribute) {
		// TODO Auto-generated method stub
		String hql = null;
		if (articleAttribute .equals(Constants.BLOG)) {
			hql = "from BlogCategory group by blogType";
		}else if(articleAttribute .equals(Constants.DIARY)){
			
		}else if(articleAttribute .equals(Constants.ESSAY)){
			
		}
		
		return this.getHibernateTemplate().find(hql);
	}
	
	@Override
	public void updateCategory(Object category, String articleAttribute) {
		// TODO Auto-generated method stub
		if (articleAttribute .equals(Constants.BLOG)) {
			BlogCategory blogCategory = (BlogCategory)category;
			int categoryId = blogCategory.getCategoryId();
			String blogType = blogCategory.getBlogType();
			// ���ݿ������п��ܴ����ظ����� �Ȳ����Щһ���ķ��� Ȼ�����
			//1. �ҳ�ԭ���ķ�����
			String hql = "from BlogCategory where categoryId = '" + categoryId+"'";
			BlogCategory blogCategoryOlder =(BlogCategory) this.getHibernateTemplate().get(BlogCategory.class, categoryId);
			String blogTypeOlder = blogCategoryOlder.getBlogType();
			//2. ����ԭ���ķ������ҳ�����ͬ�����ֵķ���
			hql = "from BlogCategory where blogType = '" + blogTypeOlder+"'";
			List<BlogCategory> blogCategorys = this.getHibernateTemplate().find(hql);
			//3. ����
			for (BlogCategory blogCategory2 : blogCategorys) {
				blogCategory2.setBlogType(blogType);
				this.getHibernateTemplate().update(blogCategory2);
			}
			
		}else if(articleAttribute .equals(Constants.DIARY)){
			
		}else if(articleAttribute .equals(Constants.ESSAY)){
			
		}
	}

	
	@Override
	public Object findCategoryByCategoryId(int categoryId, String articleAttribute) {
		// TODO Auto-generated method stub
		Object object = null;
		if (articleAttribute .equals(Constants.BLOG)) {
			object = this.getHibernateTemplate().get(BlogCategory.class, categoryId);
		}else if(articleAttribute .equals(Constants.DIARY)){
			
		}else if(articleAttribute .equals(Constants.ESSAY)){
			
		}
		return object;
	}
	
	@Override
	public List findArticlesByArticleType(String articleType, String articleAttribute) {
		// TODO Auto-generated method stub
		String hql = null;
		if (articleAttribute .equals(Constants.BLOG)) {
//			SELECT * FROM MyBlog.Blog where blog_id in (select blog_id from MyBlog.BlogCategory where blog_type = "fdd");
			hql = "from Blog where blogId in (select blogId from BlogCategory where blogType = '"+ articleType +"')";
			return this.getHibernateTemplate().find(hql);
		}else if(articleAttribute .equals(Constants.DIARY)){
			
		}else if(articleAttribute .equals(Constants.ESSAY)){
			
		}
		
		return null;
	}
}
