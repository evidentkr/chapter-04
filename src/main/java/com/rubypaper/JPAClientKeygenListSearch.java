package com.rubypaper;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.BoardKeygen;

public class JPAClientKeygenListSearch {

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
			for(int i = 0 ; i <10 ; i++) {
				BoardKeygen boardKeygen = new BoardKeygen();
				boardKeygen.setTitle("JPA 제목");
				boardKeygen.setWriter("작성자");
				boardKeygen.setContent("JPA 내용");
				boardKeygen.setCreateDate(new Date());
				boardKeygen.setCnt(0L);
				em.persist(boardKeygen);
			}
			// 트랜잭션 반영
			tx.commit();

			// 트랜잭션 시작
			tx.begin();
			
			// 글 목록 조회
			String jpql = "select b from BoardKeygen b order by b.seq desc";
			List<BoardKeygen> boardList =  em.createQuery(jpql, BoardKeygen.class).getResultList();
			for (BoardKeygen brd : boardList) {
				System.out.println(brd.toString());
			}
			
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
