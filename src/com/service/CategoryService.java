package com.service;

import java.util.List;

public interface CategoryService {
	/*
	 * @param articleId ����id�����ͣ��ռǣ���ʣ�
	 * @param articleTypes ���·���
	 * @param articleAttribute �������ԣ����ͣ��ռǣ���ʣ�������� ���Ӧ�ı�
	 */
	void addArticleTypes(int articleId, String articleTypes, String articleAttribute);
	List findCategorys(String articleAttribute);
	void deleteCategoryByArticleType(String articleType, String articleAttribute);
	void updateCategory(Object category, String articleAttribute);
	Object findCategoryByCategoryId(int categoryId, String articleAttribute);
	
	List findArticlesByArticleType(int categoryId, String articleAttribute);
}
