package mcip.webapps.dao.msg;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import mcip.webapps.dto.msg.MsgDTO;


@Mapper("msgDAO")
public interface MsgDAO {

	/**
	 * 메시지 데이터 호출
	 * @param string
	 * @return
	 */
	public MsgDTO getMsgData(String string);
	
}
