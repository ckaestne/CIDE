package it.yup.tests;


import it.yup.util.RMSIndex;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class RMSTestMidlet extends MIDlet {

	RMSIndex db;
	RMSIndex db2;
	RMSIndex db3;
	Hashtable data = new Hashtable();
	TestForm testForm = new TestForm("test");

	public RMSTestMidlet() {
		Display.getDisplay(this).setCurrent(testForm);
		db = new RMSIndex("test01", 30000);
		//		db2 = new RMSIndex("test02");
		//		db3 = new RMSIndex("test03");
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub
	}

	protected void startApp() throws MIDletStateChangeException {
		db.open();
		//testDb(db);
		//testLength(db);
		//testFuncs(db);
		//testDeletion(db);
		//testJoin(db);
		//testLong(db);
		//testMultiple(db);
		testNull(db);
		db.close();
	}
	
	private void testNull(RMSIndex db) {
		byte [] tempdata = db.load("gineprando2".getBytes());
		db.load("gineprando".getBytes());
		db.store("gineprando2".getBytes(), "".getBytes());
	}

	private void testMultiple(RMSIndex db) {
		Hashtable longHash = new Hashtable(1000);
		for (int i = 1001; i < 10000; i++) {
			longHash.put(i + "", i + "");
		}

		Vector shortVector = new Vector();
		for (int i = 0; i < 1000; i++) {
			shortVector.addElement(i + "");
			db.store((i + "").getBytes(), (i + "").getBytes());
		}

		Enumeration en = longHash.keys();
		while (en.hasMoreElements()) {
			String ithString = (String) en.nextElement();
			shortVector.addElement(ithString);
			db.store(ithString.getBytes(), ithString.getBytes());

			String firstElem = (String) shortVector.elementAt(0);
			shortVector.removeElementAt(0);
			db.delete(firstElem.getBytes());
		}

		en = shortVector.elements();
		while (en.hasMoreElements()) {
			String ithElem = (String) en.nextElement();
			try {
				byte[] elemRead = db.load(ithElem.getBytes());
				String ithRead = new String(elemRead);
				if (ithElem.compareTo(ithRead) != 0) {
					System.out.println("Error!!!");
				}
			} catch (Exception e) {
				System.out.println("Error!!!");
			}
		}
		int a = 0;
		a++;

	}

	private void testLong(RMSIndex db) {
		Hashtable longHash = new Hashtable(100);
		String myStryng = "my String";
		for (int i = 0; i < 200; i++) {
			longHash.put(i + "", i + myStryng);
			myStryng += myStryng;
			if (myStryng.length() > 500000) myStryng = "my String";
		}

		Vector shortVector = new Vector();
		Enumeration en = longHash.keys();
		while (en.hasMoreElements()) {
			String ithString = (String) en.nextElement();
			shortVector.addElement(ithString);
			db.store(ithString.getBytes(), ((String) longHash.get(ithString))
					.getBytes());
		}

		for (int i = 0; i < 50; i++) {
			String ithElem = (String) shortVector.elementAt(0);
			shortVector.removeElementAt(0);
			longHash.remove(ithElem);
			db.delete(ithElem.getBytes());
		}

		checkCoherency(db, longHash, shortVector);

		en = shortVector.elements();
		while (en.hasMoreElements()) {
			String ithString = (String) en.nextElement();
			String hashString = (String) longHash.get(ithString);
			String cutString = hashString.substring(0, hashString.length() / 2);
			longHash.put(ithString, cutString);
			db.store(ithString.getBytes(), cutString.getBytes());
		}

		checkCoherency(db, longHash, shortVector);

		try {
			this.destroyApp(false);
			this.notifyDestroyed();
		} catch (MIDletStateChangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void checkCoherency(RMSIndex db, Hashtable longHash,
			Vector shortVector) {
		Enumeration en;
		en = longHash.keys();
		while (en.hasMoreElements()) {
			String ithElem = (String) en.nextElement();
			if (ithElem.compareTo("47") == 0) {
				int a = 0;
				a++;
			}
			try {
				byte[] elemRead = db.load(ithElem.getBytes());
				String ithRead = new String(elemRead);
				String hashElem = (String) longHash.get(ithElem);
				if (hashElem.compareTo(ithRead) != 0) {
					System.out.println("Error!!!");
				}
				if (shortVector.contains(ithElem) == false) {
					System.out.println("Error!!!");
				}
			} catch (Exception e) {
				System.out.println("Error!!!");
			}
		}
	}

	private void testJoin(RMSIndex db) {
		Hashtable data = new Hashtable(571);
		int size = 1000;
		for (int i = 0; i < size; i++) {
			data.put(i + "", i + "");
		}
		Enumeration en = data.keys();
		while (en.hasMoreElements()) {
			String ithElem = (String) en.nextElement();
			db.store(ithElem.getBytes(), ithElem.getBytes());
		}

		for (int l = 1; l < 8; l++) {
			for (int i = l * 100; i < (l + 2) * 100; i++) {
				String ithString = i + "";
				data.remove(ithString);
				db.delete(ithString.getBytes());
			}
			for (int i = l * 100 + 50; i < (l + 1) * 100 + 50; i++) {
				String ithElem = i + "";
				data.put(ithElem, ithElem);
				db.store(ithElem.getBytes(), ithElem.getBytes());
			}
		}

		en = data.keys();
		while (en.hasMoreElements()) {
			String ithElem = (String) en.nextElement();
			try {
				byte[] elemRead = db.load(ithElem.getBytes());
				String ithRead = new String(elemRead);
				if (ithElem.compareTo(ithRead) != 0) {
					System.out.println("Error!!!");
				}
			} catch (Exception e) {
				System.out.println("Error!!!");
			}
		}
		int a = 0;
		a++;
	}

	private void testDeletion(RMSIndex db4) {
		Hashtable data = new Hashtable(571);
		int size = 1000;
		for (int i = 0; i < size; i++) {
			data.put(i + "", i + "");
		}
		Enumeration en = data.keys();
		while (en.hasMoreElements()) {
			String ithElem = (String) en.nextElement();
			db.store(ithElem.getBytes(), ithElem.getBytes());
		}

		for (int i = 0; i < size; i++) {
			if (i == 999) {
				int a = 0;
				a++;
			}
			String ithString = i + "";
			data.remove(ithString);
			db.delete(ithString.getBytes());
		}

		for (int i = 0; i < size; i++) {
			data.put(i + "", i + "");
		}
		en = data.keys();
		while (en.hasMoreElements()) {
			String ithElem = (String) en.nextElement();
			db.store(ithElem.getBytes(), ithElem.getBytes());
		}

		for (int i = 0; i < size; i++) {
			String ithElem = (String) data.get(i + "");
			byte[] ithByte = db.load(ithElem.getBytes());
		}
		int a = 0;
		a++;
	}

	private void testFuncs(RMSIndex db) {
		Hashtable data = new Hashtable(571);
		int size = 1000;
		int total = 0;
		int totalComputed = 0;
		int num = 0;
		for (int i = 0; i < size; i++) {
			data.put(i + "", i + "");
			total += i;
		}
		Enumeration en = data.keys();
		while (en.hasMoreElements()) {
			String ithElem = (String) en.nextElement();
			db.store(ithElem.getBytes(), ithElem.getBytes());
		}

		for (int i = 0; i < size; i++) {
			String ithElem = (String) data.get(i + "");
			byte[] ithByte = db.load(ithElem.getBytes());
			String ithString = new String(ithByte);
			num = Integer.parseInt(ithString);
			totalComputed += num;
		}

		for (int i = 0; i < size / 2; i++) {
			data.remove(data.elements().nextElement());
		}

		en = data.keys();
		while (en.hasMoreElements()) {
			String ithKey = (String) en.nextElement();
			total += Integer.parseInt(ithKey);
			String ithElem = (String) data.get(ithKey);
			byte[] ithByte = db.load(ithElem.getBytes());
			String ithString = new String(ithByte);
			num = Integer.parseInt(ithString);
			totalComputed += num;
		}

		for (int i = 0; i < size; i++) {
			if (data.containsKey(i + "") == false) {
				data.put(i + "", i + "");
				byte[] bytes = (i + "").getBytes();
				db.store(bytes, bytes);
			}
		}

		en = data.keys();
		while (en.hasMoreElements()) {
			String ithKey = (String) en.nextElement();
			total += Integer.parseInt(ithKey);
			String ithElem = (String) data.get(ithKey);
			byte[] ithByte = db.load(ithElem.getBytes());
			String ithString = new String(ithByte);
			num = Integer.parseInt(ithString);
			totalComputed += num;
		}

		int tempSize = data.size();
		for (int i = 0; i < tempSize; i++) {
			String key = (String) data.remove(data.elements().nextElement());
			total -= Integer.parseInt(key);
			byte[] res = db.load(key.getBytes());
			db.delete(key.getBytes());
			totalComputed -= Integer.parseInt(new String(res));
		}

		for (int i = 0; i < size; i++) {
			data.put(i + "", i + "");
			total += i;
		}
		en = data.keys();
		while (en.hasMoreElements()) {
			String ithElem = (String) en.nextElement();
			db.store(ithElem.getBytes(), ithElem.getBytes());
		}

		for (int i = 0; i < size; i++) {
			String ithElem = (String) data.get(i + "");
			byte[] ithByte = db.load(ithElem.getBytes());
			String ithString = new String(ithByte);
			num = Integer.parseInt(ithString);
			totalComputed += num;
		}

		if (total != totalComputed) {
			int a = 0;
			a++;
		}
	}

	private void testLength(RMSIndex db4) {
		int size = 2048;
		String sName = "a";
		while (size < 50000) {
			size *= 2;
			sName += "a";
			byte[] longBytes = new byte[size];
			for (int i = 0; i < longBytes.length; i++) {
				longBytes[i] = (byte) ((i % ('z' - 'a')) + 'a');
			}
			db.store(sName.getBytes(), longBytes);
			byte[] retBytes = db.load(sName.getBytes());
			if (retBytes == null) return;

			String sizeString = "";
			for (int i = 0; i <= 3; i++)
				sizeString += (db.getSizes()[i] + " - ");
			String oldText = testForm.resItem.getText();
			oldText += sizeString;
			testForm.resItem.setText(oldText);

			db.delete(sName.getBytes());
		}

	}

	private void testDb(RMSIndex db) {
		db.store("gino".getBytes(), "gino".getBytes());
		db.store(
				"stringa-lunga-senza-sensostringa-lunga-senza-sensostringa-lunga-senza-senso"
						.getBytes(),
				"-lunga-senza-sensostringa-lunga-senza-sensostringa-lunga-senza-senso"
						.getBytes());
		db.store("pino".getBytes(), "pino".getBytes());
		db.store("tino".getBytes(), "tino".getBytes());
		db.store("peristalsi".getBytes(), "peristalsi".getBytes());
		db.store("rino".getBytes(), "rino".getBytes());

		for (int i = 0; i < 14; i++) {
			String k = "" + i;
			data.put(k, k);
		}

		Enumeration en = data.keys();
		while (en.hasMoreElements()) {
			String k = (String) en.nextElement();
			System.out.println(k);
			db.store(k.getBytes(), k.getBytes());
		}

		byte[] longBytes = new byte[100000];
		for (int i = 0; i < longBytes.length; i++) {
			longBytes[i] = (byte) ((i % ('z' - 'a')) + 'a');
		}
		db.store("longBytes".getBytes(), longBytes);

		for (int i = 0; i < 5; i++) {
			String k = "" + i;
			db.delete(k.getBytes());
		}

		en = data.keys();
		while (en.hasMoreElements()) {
			String k = (String) en.nextElement();
			System.out.println(k);
			db.store(k.getBytes(), k.getBytes());
		}

		byte[] reads = null;
		reads = db.load("7".getBytes());

		en = db.keys();
		System.out.println("start");
		while (en.hasMoreElements()) {
			String s = new String((byte[]) en.nextElement());
			System.out.println(">" + new String(db.load(s.getBytes())));
		}
		System.out.println("finish");
		//		for(int i=0; i<100; i++){
		//			String k = "" + i;
		//			System.out.println(new String(db.load(k.getBytes())));
		//		}
	}

	class TestForm extends Form {
		StringItem resItem = new StringItem("test", "");

		public TestForm(String title) {
			super(title);
			this.append(resItem);
		}

	}
}
