package com.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.opensymphony.xwork2.ActionContext;
import com.pojo.Blog;
import com.pojo.User;
import com.service.BlogService;
import com.tools.Constants;
import com.tools.Html;
import com.tools.Timer;

public class BlogServiceImpl extends HibernateDaoSupport implements
		BlogService {
	@Override
	public Blog addBlog(Blog blog) {
		// TODO Auto-generated method stub
		dealOrtherAttribute(blog);
		blog.setReadedCount(0);
		this.getHibernateTemplate().save(blog);
		//ȡblog �Ա�õ�id
		String hql = "from Blog order by blogId DESC limit 1";
		return (Blog) this.getHibernateTemplate().find(hql).get(0);
	}

	@Override
	public List<Blog> findBlogs(String condition) {
		// TODO Auto-generated method stub
		String hql = "from Blog " + condition;
		List<Blog> blogs = (List<Blog>)this.getHibernateTemplate().find(hql);
		
		return blogs;
	}
	
	@Override
	public Blog findBlogByBlogId(int blogId) {
		// TODO Auto-generated method stub
		Blog blog = (Blog)this.getHibernateTemplate().get(Blog.class, blogId);
		return blog;
	}
	
	@Override
	public Blog readBlog(int blogId) {
		// TODO Auto-generated method stub
		Blog blog = findBlogByBlogId(blogId);
		int readedCount = blog.getReadedCount() == null ? 0 : blog.getReadedCount();
		blog.setReadedCount(readedCount + 1);
		this.getHibernateTemplate().update(blog);
		return blog;
	}
	
	@Override
	public void deleteBlogByBlogId(int blogId) {
		// TODO Auto-generated method stub
		Blog blog = findBlogByBlogId(blogId);
		this.getHibernateTemplate().delete(blog);
	}
	
	@Override
	public Blog updateBlog(Blog blog) {
		// TODO Auto-generated method stub
		dealOrtherAttribute(blog);
		
		this.getHibernateTemplate().update(blog);
		return blog;
	}
	
	
	public void dealOrtherAttribute(Blog blog) {
		// ��������
		String content = blog.getContent();

		// ժҪ����
		String abstract_ = blog.getAbstract_();
		if (abstract_ == "" || abstract_.trim().length() == 0) {
			// ȡ����ǰ200����ΪժҪ ��������ͼƬ���� ժҪ���...
			abstract_ = Html.removeTag(content); // ȥ��html��ǩ
			int endPos = abstract_.length() > 200 ? 200 : abstract_.length();
			abstract_ = abstract_.substring(0, endPos) + "...";
			blog.setAbstract_(abstract_);
		}
		
		//��ͼ ȡ���µ�һ��ͼƬ ���û�� ȡĬ��ͼƬ
		String imgPath = Html.getImagePath(content);
		if (imgPath == null) {
			imgPath = "images/blog_default.jpg";
		}
		blog.setImgPath(imgPath);
		
		//ʱ��
		blog.setDate(Timer.getDate());
		
		//userId
		//��һ��ȡsession��user �ķ���
		Map session = (Map)ActionContext.getContext().get("session");
		User user = (User)session.get(Constants.USER);
		//�ڶ���ȡsession��user �ķ���
		//HttpSession session=ServletActionContext.getRequest().getSession();
		//User user = (User)session.getAttribute(Constants.USER);
		if (user != null) {
			blog.setUserId(user.getUserId());
		}
	}

}
