package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.domain.GuestbookDTO;
import com.example.demo.domain.PageRequestDTO;
import com.example.demo.domain.PageResultDTO;
import com.example.demo.entity.Guestbook;
import com.example.demo.repository.GuestbookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Service
@Log4j2
@RequiredArgsConstructor
//	=> 각 필드에 대해 1개의 매개변수가 있는 생성자를 생성함.
//	=> 초기화 되지 않은 모든 final 필드에만 적용됨.
public class GuestbookServiceImpl implements GuestbookService{
	
	private final GuestbookRepository repository;
	//	=> JPA Sql문 처리를 위해 정의
	//		생성자 주입 ( @RequiredArgsConstructor 를 통해 주입받는중)
	
	@Override
	public PageResultDTO<GuestbookDTO, Guestbook> pageList(PageRequestDTO requestDTO) {
		//	=> 조건완성
		Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
		
		//	=> repository 실행 
		Page<Guestbook> result = repository.findAll(pageable);
		
		//	=> Function<EN,DTO> 정의
		Function<Guestbook,GuestbookDTO> fn = (entity -> entityToDto(entity));
		//	=> Service에 정의한 entityToDto 메서드를 이용해서 entity를 Dto로
		//		람다식을 인자로 직접 사용해도 됨(이로써, Java도 함수형 언어라 할 수 있게 되었다고함.)
		//return new PageResultDTO<>(result, entity -> entityToDto(entity));
		
		return new PageResultDTO<GuestbookDTO, Guestbook>(result,fn);
	}
	
	@Override
	public List<Guestbook> selectList() {
		return repository.findAll();
	}

	@Override
	public Guestbook selectOne(Long gno) {
		
		// ** Optional<T>
	       // => Java8부터 Optional<T>클래스를 사용해 NullPointerException(이하 NPE)를 방지할수 있도록 지원.
	       //     즉, Optional<T>는 null이 올수 있는 값을 감싸는 Wrapper클래스로
		   //	  참조하더라도 NPE가 발생하지 않도록 도와줌.
	       //     제공되는 메소드로 복잡한 조건문 없이 NPE를 회피할 수 있어록 해줌
	       // => isPresent() : Optional객체에 저장된 값이 null인지 확인 (false면 null)
	       // => get() : Optional객체에 저장된 값 제공
	       // => 참고 https://esoongan.tistory.com/95
		
		Optional<Guestbook> result = repository.findById(gno);
		if(result.isPresent()) return result.get(); 
		else return null;
	}
	
	//	=> 없으면 insert, 있으면 update
	@Override
	public Long register(GuestbookDTO dto) {
		
		log.info("** register, dto => " + dto);
		
		Guestbook entity = dtoToEntity(dto);
		repository.save(entity);	//처리후 entity를 return
		
		return entity.getGno();	//저장된 자동생성된 키값을 return
	}
	
	//	=> delete
	@Override
	public void delete(Long gno) {

		repository.deleteById(gno);
	}

}
