package com.adamsoft.jpa.crudtest;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.adamsoft.jpa.entity.Memo;
import com.adamsoft.jpa.repository.MemoRepository;


import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class RepositoryTest {
	
	@Autowired
	MemoRepository memoRepository;  //MemoRepository 주입
	
	@Test   //주입 확인 테스트... 
	public void testDependency() {
		log.info("주입 여주:"+memoRepository.getClass().getName());
	}

	//삽입 확인
	@Test
	public void testInsert() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Memo memo = Memo.builder().memoText("Sample..."+i).build();
			memoRepository.save(memo);
		});
	}
	
	//데이터 조회 테스트
	@Test
	public void testSelect() {
		//데이터베이스에 존재하는 mno
		Long mno = 100L;
		Optional<Memo> result = memoRepository.findById(mno);
		log.info("================================================");
		if(result.isPresent()) {
			Memo memo = result.get();
			log.info(memo);
		}
	}

	//데이터 수정
	@Test
	public void testUpdate() {
		Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();
		log.info(memoRepository.save(memo));
	}
	
	//데이터 삭제
	@Test
	public void testDelete() {
		Long mno = 100L;
		memoRepository.deleteById(mno);
	}
}
