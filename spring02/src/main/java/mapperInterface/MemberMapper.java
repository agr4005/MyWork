package mapperInterface;

import java.util.List;

import com.ncs.spring02.domain.MemberDTO;

public interface MemberMapper {

		// ** selectList
		List<MemberDTO> selectList();

		// ** selectOne
		MemberDTO selectOne(String id);

		// ** insert
		int insert(MemberDTO dto);

		// ** update
		int update(MemberDTO dto);

		// ** delete
		int delete(String id);
		
		// ** selectJoList
		List<MemberDTO> selectJoList(int jno);

		// ** pwUpdate
		int pwUpdate(MemberDTO dto);
	
}
