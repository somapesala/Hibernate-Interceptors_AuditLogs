package com.mkyong.interceptor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Session;
import org.hibernate.type.Type;

import com.mkyong.util.AuditLogUtil;

public class CricketAuditLogInterceptor extends EmptyInterceptor {

	Session session;
	private Set inserts = new HashSet();
	private Set updates = new HashSet();
	private Set deletes = new HashSet();

	public void setSession(Session session) {
		this.session = session;
	}

	/*@Override
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		super.onDelete(entity, id, state, propertyNames, types);
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {

		return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
	}*/

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {

		System.out.println("onSave");

		System.out.println("onSave methos entity is :" + entity);

		if (entity instanceof IAuditLog) {
			inserts.add(entity);
		}
		return false;
	}

	// called after committed into database
	public void postFlush(Iterator iterator) {
		System.out.println("postFlush");

		try {

			for (Iterator it = inserts.iterator(); it.hasNext();) {
				IAuditLog entity = (IAuditLog) it.next();
				System.out.println("postFlush - insert");

				AuditLogUtil.LogIt("Saved", entity, session.connection());
			}
		} finally {
			inserts.clear();
			updates.clear();
			deletes.clear();
		}
	}

}
