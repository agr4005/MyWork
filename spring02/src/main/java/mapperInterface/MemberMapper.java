package mapperInterface;

import java.util.List;

import com.ncs.spring02.domain.MemberDTO;

import pageTest.Criteria;
import pageTest.SearchCriteria;

public interface MemberMapper {

	// ** Member Check List
	public List<MemberDTO> mCheckList(SearchCriteria cri);
	public int mCheckRowsCount(SearchCriteria cri);
		
	//** Member Search Paging
	public List<MemberDTO> mSearchList(SearchCriteria cri);
	public int mSearchRowsCount(SearchCriteria cri);

	//** Member_Paging
	public List<MemberDTO> mPageList(Criteria cri);
	public int totalRowsCount(Criteria cri);
	
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
