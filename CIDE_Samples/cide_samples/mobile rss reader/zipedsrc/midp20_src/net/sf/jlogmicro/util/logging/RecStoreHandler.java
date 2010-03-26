/*
 * RecStoreHandler.java
 *
 * Copyright (C) 2007 Irving Bunton
 * http://code.google.com/p/jlogmicro/source
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */
// Expand to define CLDC define
//#define DCLDCV10
// Expand to define logging define
//#define DLOGGING
//#ifdef DLOGGING
package net.sf.jlogmicro.util.logging;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Date;
import java.util.Vector;
import java.util.Hashtable;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.rms.*;

import net.sf.jlogmicro.util.logging.Handler;
import net.sf.jlogmicro.util.logging.LogRecord;

public class RecStoreHandler extends BufferedHandler {

	public static final String REC_STORE_NAME = "jlog_rec_store_name";
	private String recStoreName = REC_STORE_NAME;
	private RecordStore recStore;
	private Vector vrecStore = new Vector();

	public RecStoreHandler() {
	}

	public void publish(LogRecord record) {
		synchronized (this) {
			try {
				if (recStore == null) {
					openRecStore();
					if (recStore == null) {
						return;
					}
				}
				String msg = formatter.format(record);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				DataOutputStream dos = new DataOutputStream(bos);
				dos.writeLong(record.getMillis());
				dos.writeUTF(msg);
				if ((maxEntries != 0) && (vrecStore.size() >= maxEntries)) {
					recStore.setRecord(((Integer)vrecStore.elementAt(maxEntries -
									1)).intValue(),
										 bos.toByteArray(), 0, bos.size());
				} else {
					vrecStore.addElement(new Integer(
								recStore.addRecord(bos.toByteArray(), 0,
									bos.size())));
				}
			} catch (Throwable e) {
				System.out.println("publish " + e.getClass().getName() + " " 
						+ e.getMessage());
			}
		}
	}

	private synchronized void openRecStore() {

		try {
			recStore = RecordStore.openRecordStore(recStoreName, true);

			RecordEnumeration erec = recStore.enumerateRecords(null, null,
					false);
			if ((erec == null) || !erec.hasNextElement()) {
				return;
			}
			int ic = 0;
			for (;((maxEntries == 0) || (ic < maxEntries)) &&
					erec.hasNextElement(); ic++) {
				int id = erec.nextRecordId();
				vrecStore.addElement(new Integer(id));
			}
			while (erec.hasNextElement()) {
				int id = erec.nextRecordId();
				recStore.deleteRecord(id);
			}

        } catch (Throwable e) {
            System.out.println("openRecStore " + e.getClass().getName() + " " 
					+ e.getMessage());
		}
	}

    public void setRecStoreName(String recStoreName) {
        this.recStoreName = recStoreName;
    }

    public String getRecStoreName() {
        return (recStoreName);
    }

    public void setRecStore(RecordStore recStore) {
        this.recStore = recStore;
    }

    public RecordStore getRecStore() {
        return (recStore);
    }

}
//#endif
