package com.rubypaper;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.domain.BoardSequence;

public class JPAClientSequence {

	public static void main(String[] args) {

		// EntityManager 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("chapter-04");
		EntityManager em = emf.createEntityManager();

		// Transaction 생성
		EntityTransaction tx = em.getTransaction();

		try {
			// Transaction 시작
			tx.begin();

			// 게시글 생성
			BoardSequence boardSequence = new BoardSequence();
			boardSequence.setTitle("JPA 제목");
			boardSequence.setWriter("작성자");
			boardSequence.setContent("JPA 내용");
			boardSequence.setCreateDate(new Date());
			boardSequence.setCnt(0L);

			// 수정할 게시글 조회
//			Board boardSequence = em.find(boardSequence.class, 1L);
//			boardSequence.setTitle("검색한 게시글의 제목 수정");
			
			em.persist(boardSequence);
			
			// Transaction commit
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Transaction rollback
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}

	}

}
