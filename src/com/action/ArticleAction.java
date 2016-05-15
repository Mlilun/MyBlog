package com.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.pojo.Article;
import com.service.ArticleService;
import com.tools.Constants;

public class ArticleAction extends ActionSupport{
	Article article;
	List<Article> articles;
	ArticleService articleService;
	int articleId;
	int userId;
	public Article getArticle() {
		return article;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	public ArticleService getArticleService() {
		return articleService;
	}
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	
	//-------------------------------------
	
	public String addArticle(){
		return articleService.addArticle(article)? SUCCESS: ERROR;
	}
	
	//�������ɽ���Զ����
	public String findAllArticle(){
		this.setArticles(articleService.findArticles("order by date DESC"));
		//�Ѳ��ʹ浽session�� �Ա��ҳ
		Map session = (Map)ActionContext.getContext().get("session");
		session.put(Constants.BLOGS, articles);

		return SUCCESS;
	}
	
	//�������Ų���
	public String findHotArticle(){
		this.setArticles(articleService.findArticles("order by readedCount DESC limit 50"));//limit 50  ȡ50����¼
		return SUCCESS;
	}
	
	public String findArticleByArticleId(){
		this.setArticle(articleService.findArticleByArticleId(articleId));
		return SUCCESS;
	}
	
	public String deleteArticleByArticleId(){
		articleService.deleteArticleByArticleId(articleId);
		return SUCCESS;
	}
	
	public String updateArticle(){
		articleService.updateArticle(article);
		return SUCCESS;
	}

}
