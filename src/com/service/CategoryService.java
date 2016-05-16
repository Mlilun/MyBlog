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
	void deleteCategoryById(int category, String articleArrtibute);
	void updateCategory(String articleTypes, String articleArrtibute);
}
