package com.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.opensymphony.xwork2.ActionContext;
import com.pojo.Article;
import com.pojo.User;
import com.service.ArticleService;
import com.tools.Constants;
import com.tools.Html;
import com.tools.Timer;

public class ArticleServiceImpl extends HibernateDaoSupport implements
		ArticleService {
	@Override
	public boolean addArticle(Article article) {
		// TODO Auto-generated method stub
		dealOrtherAttribute(article);
		this.getHibernateTemplate().save(article);
		return true;
	}

	@Override
	public List<Article> findAllArticle(String condition) {
		// TODO Auto-generated method stub
		String hql = "from Article " + condition;
		List<Article> articles = (List<Article>)this.getHibernateTemplate().find(hql);
		
		return articles;
	}
	
	@Override
	public Article findArticleByArticleId(int articleId) {
		// TODO Auto-generated method stub
		Article article = (Article)this.getHibernateTemplate().get(Article.class, articleId);
		return article;
	}
	
	@Override
	public void deleteArticleByArticleId(int articleId) {
		// TODO Auto-generated method stub
		Article article = findArticleByArticleId(articleId);
		this.getHibernateTemplate().delete(article);
	}
	
	@Override
	public void updateArticle(Article article) {
		// TODO Auto-generated method stub
		dealOrtherAttribute(article);
		
		this.getHibernateTemplate().update(article);
	}
	
	
	public void dealOrtherAttribute(Article article) {
		// ��������
		String content = article.getContent();

		// ժҪ����
		String abstract_ = article.getAbstract_();
		if (abstract_ == "" || abstract_.trim().length() == 0) {
			// ȡ����ǰ200����ΪժҪ ��������ͼƬ���� ժҪ���...
			abstract_ = Html.removeTag(content); // ȥ��html��ǩ
			int endPos = abstract_.length() > 200 ? 200 : abstract_.length();
			abstract_ = abstract_.substring(0, endPos) + "...";
			article.setAbstract_(abstract_);
		}
		
		//��ͼ ȡ���µ�һ��ͼƬ ���û�� ȡĬ��ͼƬ
		String imgPath = Html.getImagePath(content);
		if (imgPath == null) {
			imgPath = "images/blog_default.jpg";
		}
		article.setImgPath(imgPath);
		
		//ʱ��
		article.setDate(Timer.getData());
		
		//userId
		//��һ��ȡsession��user �ķ���
		Map session = (Map)ActionContext.getContext().get("session");
		User user = (User)session.get(Constants.USER);
		//�ڶ���ȡsession��user �ķ���
		//HttpSession session=ServletActionContext.getRequest().getSession();
		//User user = (User)session.getAttribute(Constants.USER);
		if (user != null) {
			article.setUserId(user.getUserId());
		}
	}

}
