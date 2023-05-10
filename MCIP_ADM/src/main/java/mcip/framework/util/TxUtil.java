package mcip.framework.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
public class TxUtil {
	
	private DataSourceTransactionManager txManager;
	private TransactionStatus status;
	
	public void setTxManager(DataSourceTransactionManager txManager){
		this.txManager = txManager;
	}
	
	@Autowired 
	public TxUtil(DataSourceTransactionManager txManager)
	{
		this.setTxManager(txManager);
	}
	
	public void startTransaction(){
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		synchronized (txManager) {
			this.status = this.txManager.getTransaction(def);
		}
	}

	public void commitTransaction(){		
		synchronized (txManager) {
			this.txManager.commit(this.status);
		}
	}

        public void rollBackTransaction(){
		synchronized (txManager) {
			this.txManager.rollback(this.status);
		}
	} 
}
