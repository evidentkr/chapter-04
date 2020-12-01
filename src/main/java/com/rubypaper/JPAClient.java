package com.rubypaper;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.domain.Board;

public class JPAClient {

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
//			Board board = new Board();
//			board.setTitle("JPA 제목");
//			board.setWriter("작성자");
//			board.setContent("JPA 내용");
//			board.setCreateDate(new Date());
//			board.setCnt(0L);

			// 수정할 게시글 조회
			Board board = em.find(Board.class, 1L);
			board.setTitle("검색한 게시글의 제목 수정");
			
			em.persist(board);
			
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
