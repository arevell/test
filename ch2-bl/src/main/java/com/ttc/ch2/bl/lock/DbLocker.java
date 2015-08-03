package com.ttc.ch2.bl.lock;

import java.sql.Connection;
import java.sql.Statement;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class DbLocker {
	
	public enum LockSql{		
		SCHEDULER_LOCK("select * from qrtz_locks where lock_name='SCHEDULER_OPERATION' for update"),		
		OUTGOING_ARCHIVE_LOCK("select * from ch2_locks where lock_name='OUTGOING_ARCHIVE' for update"),
		TI_TD_LOCK("select * from ch2_locks where lock_name='TIandTDLock' for update"),
		UPLOAD_LOCK("select * from ch2_locks where lock_name='UPLOAD_OPERATION' for update");

		
		
		private String sql;
		private LockSql(String sql) {
			this.sql = sql;
		}
		public String getSql() {
			return sql;
		}
	}
	
	@Inject
	@Qualifier("dataSource")
	private DataSource ds;
	
	public void executeOperation(Executor executor,LockSql lockSql) throws ExecutorException{
		Connection conn=null;
		boolean autoCommitStatus=false;
		try{
			conn=ds.getConnection();
			autoCommitStatus=conn.getAutoCommit();
			conn.setAutoCommit(false);
			
			Statement stmt=conn.createStatement();
			stmt.execute(lockSql.getSql());
			executor.invoke();
		}
		catch (ExecutorException e) {
			throw e;
		} catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
		finally {			
			if(conn!=null){
				try{
					conn.rollback();
					conn.setAutoCommit(autoCommitStatus);
					conn.close();
				} catch (Exception e) {
					throw new UnsupportedOperationException(e);
				}			
			}
		}		
	}
	
	public void executeOperationWithWaitThread(Executor executor,LockSql lockSql,long waitTime) throws ExecutorException{
		Connection conn=null;
		boolean autoCommitStatus=false;
		try{
			conn=ds.getConnection();
			autoCommitStatus=conn.getAutoCommit();
			conn.setAutoCommit(false);
			
			Statement stmt=conn.createStatement();
			stmt.execute(lockSql.getSql());
			executor.invoke();
			Thread.sleep(waitTime);
		}
		catch (ExecutorException e) {
			throw e;
		} catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
		finally
		{			
			if(conn!=null){
				try{
					conn.rollback();
					conn.setAutoCommit(autoCommitStatus);
					conn.close();
				} catch (Exception e) {
					throw new UnsupportedOperationException(e);
				}			
			}
		}		
	}

}
