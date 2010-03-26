package it.yup.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Vector;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;

//#mdebug
//@
//@import javax.microedition.rms.InvalidRecordIDException;
//@
//@import javax.microedition.rms.RecordStoreException;
//@import javax.microedition.rms.RecordStoreNotOpenException;
//@
//#enddebug

public class RMSIndex {

	private final static int CHUNK_MAXSIZE = 50;
	/** chunk_id -> chunk */

	private Vector chunk_index = new Vector();
	private Vector current_chunk = null;
	private Item current_index = null;

	/*
	 * The max length that can be used for a record
	 */
	private int recordMaxLength = 64535;

	/*
	 * 
	 */

	class RecordType {
		static final byte CHUNK_INDEX = 0;
		static final byte CHUNK = 1;
		static final byte DATA_RECORD = 2;
		static final byte SPLITTED_HEAD = 3;
		static final byte SPLITTED_RECORD = 4;
	}

	class Item {
		byte key[];
		int id;

		// this field is multiplexed:
		// - used in the chunk_index to store the number of items present in the pointed chunk.
		// - and to contain the type of the record (DATA_RECORD or SPLITTED_HEAD) in the other case
		short num = 0;
	}

	private class DefaultUTF8Comparator implements Comparator {

		public int compare(byte[] a, byte[] b) {
			try {
				// XXX change in order to support phones without utf-8
				return (new String(a, "utf-8"))
						.compareTo(new String(b, "utf-8"));
			} catch (UnsupportedEncodingException e) {
				return 0;
			}
		}
	}

	public interface Comparator {
		public int compare(byte[] a, byte[] b);
	}

	public static boolean rmExist(String dbName) {
		RecordStore tempRs = null;
		try {
			tempRs = RecordStore.openRecordStore(dbName, false);
			return true;
		} catch (RecordStoreFullException e) {
			return true;
		} catch (RecordStoreNotFoundException e) {
			return false;
		} catch (RecordStoreException e) {
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (tempRs != null) {
				try {
					tempRs.closeRecordStore();
				} catch (Exception e) {
					// #mdebug
//@						e.printStackTrace();
//@						System.out.println("In rmExists" + e.getMessage()
//@							+ e.getClass());
					// #enddebug
				}
			}
		}
	}

	private class KeyEnumeration implements Enumeration {

		int t_i; // top index
		int c_i; // chunk index
		Vector current_chunk = null;

		Item next = null;

		public KeyEnumeration() {
			if (chunk_index.size() > 0) {
				t_i = -1;
				c_i = -1;
				// this forces to load the first item
				current_chunk = new Vector();
				next_item();
			}
		}

		public boolean hasMoreElements() {
			return next != null;
		}

		public Object nextElement() {
			if (next == null) { throw new NoSuchElementException(); }

			byte data[] = next.key;
			next_item();
			return data;
		}

		private void next_item() {
			c_i++;
			next = null;
			while (true) {
				if (c_i < current_chunk.size()) {
					// load from current chunk
					next = (Item) current_chunk.elementAt(c_i);
					break;
				} else {
					// find a new chunk
					t_i++;
					if (t_i < chunk_index.size()) {
						c_i = 0;
						Item item = (Item) chunk_index.elementAt(t_i);
						try {
							current_chunk = loadChunk(item.id);
						} catch (Exception e) {
							throw new NoSuchElementException();
						}
					} else {
						break;
					}
				}
			}

		}
	}

	private Comparator comparator;

	/*
	 * The Record Store
	 */
	private RecordStore rs;

	/*
	 * The name of Record Store
	 */
	private String name;

	private void deleteSplittedRecords(int id) {
		try {
			byte[] oldBytes = rs.getRecord(id);
			DataInputStream is = new DataInputStream(new ByteArrayInputStream(
					oldBytes));
			byte oldType = is.readByte();
			short oldKeyLength = is.readShort();
			byte[] oldKey = new byte[oldKeyLength];
			is.read(oldKey, 0, oldKeyLength);
			short oldChunkSize = is.readShort();
			int ithRid = 0;
			for (int i = 0; i < oldChunkSize; i++) {
				ithRid = is.readInt();
				rs.deleteRecord(ithRid);
			}
		} catch (Exception e) {
			// #mdebug
//@						// XXX clean dirty records! how ??!?
//@						e.printStackTrace();
//@						System.out.println("In deleting splitted records" + e.getMessage()
//@								+ e.getClass());
			// #enddebug
		}
	}

	private void join(Item firstItem, Item secondItem) {
		// #mdebug
//@				System.out.println("join");
		// #enddebug
		try {
			Vector firstChunk, secondChunk;
			if (firstItem == current_index) {
				firstChunk = current_chunk;
				secondChunk = loadChunk(secondItem.id);
			} else {
				firstChunk = loadChunk(firstItem.id);
				secondChunk = current_chunk;
			}
			chunk_index.removeElement(secondItem);
			Enumeration en = secondChunk.elements();
			while (en.hasMoreElements()) {
				firstChunk.addElement(en.nextElement());
			}
			saveChunk(firstItem.id, firstChunk);
			current_chunk = firstChunk;
			current_index = firstItem;
			current_index.num = (short) current_chunk.size();
			// remove the unused chunk
			rs.deleteRecord(secondItem.id);
			saveChunk(1, chunk_index);
		} catch (Exception e) {
			// #mdebug
//@						e.printStackTrace();
//@						System.out.println(e.getMessage() + e.getClass());
			// #enddebug
		}
	}

	/**
	 * Get an item inside a chunk
	 * @param chunk
	 * @param key
	 * @param exact
	 * @return
	 */
	private Item get_item(Vector chunk, byte key[], boolean exact) {

		int last = get_offset(chunk, key, exact);
		if (last != -1) {
			return (Item) chunk.elementAt(last);
		} else {
			return null;
		}
	}

	private void sorted_insert(Vector chunk, Item ii) {
		int pos = get_offset(chunk, ii.key, false);
		if (pos == -1) {
			chunk.addElement(ii);
		} else {
			Item ij = (Item) chunk.elementAt(pos);
			if (comparator.compare(ii.key, ij.key) > 0) {
				chunk.insertElementAt(ii, pos + 1);
			} else {
				chunk.insertElementAt(ii, pos);
			}
		}
	}

	/**
	 * Get the offset of an item within a sector
	 * @param chunk
	 * @param key
	 * @param exact
	 * @return
	 */
	private int get_offset(Vector chunk, byte key[], boolean exact) {
		int min = 0;
		int max = chunk.size();
		int last = -1;
		while (max > min && (last != min + (max - min) / 2)) {
			last = min + (max - min) / 2;
			Item item = (Item) chunk.elementAt(last);
			int res = comparator.compare(item.key, key);
			if (res == 0) {
				return last;
			} else {
				if (res < 0) {
					min = last;
				} else {
					max = last;
				}
			}
		}
		return exact ? -1 : last;
	}

	/**
	 * Load a sector from the record store
	 * @param index
	 * @return
	 * @throws Exception
	 */
	private Vector loadChunk(int index) throws Exception {
		Vector chunk = new Vector();
		try {
			byte buf[] = rs.getRecord(index);
			DataInputStream is = new DataInputStream(new ByteArrayInputStream(
					buf));
			byte type = is.readByte();
			while (is.available() > 0) {
				Item item = new Item();
				short len = is.readShort();
				item.key = new byte[len];
				is.readFully(item.key);
				item.id = is.readInt();
				item.num = is.readShort();
				chunk.addElement(item);
			}
			return chunk;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// #mdebug
//@						e.printStackTrace();
//@						System.out.println("In loading a chuck " + e.getMessage()
//@								+ e.getClass());
			// #enddebug
		}
		return chunk;
	}

	/**
	 * Save a sector to the record store
	 * @param rid
	 * @param v
	 * @return
	 * @throws Exception
	 */
	private int saveChunk(int rid, Vector v) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream os = new DataOutputStream(baos);
		Enumeration en = v.elements();
		while (en.hasMoreElements()) {
			Item ii = (Item) en.nextElement();
			os.writeShort(ii.key.length);
			os.write(ii.key);
			os.writeInt(ii.id);
			os.writeShort(ii.num);
		}
		byte buf[] = baos.toByteArray();
		//		if (rid >= 0) {
		//			rs.setRecord(rid, buf, 0, buf.length);
		//		} else {
		//			rid = rs.addRecord(buf, 0, buf.length);
		//		}
		if (rid == 1) {
			physicalWrite(1, null, buf, 0, buf.length, RecordType.CHUNK_INDEX,
					null);
		} else {
			rid = physicalWrite(rid, null, buf, 0, buf.length,
					RecordType.CHUNK, null);
		}
		return rid;
	}

	private int physicalWrite(int recordId, byte key[], byte data[], int start,
			int len, byte type, Item item) {
		// I write in this order
		// -----------------------------------------------------------------------------
		// | headerBuf | keyLength | keyBuf (or ordinal in split ) |       dataBuf     |
		// -----------------------------------------------------------------------------
		byte headerBuf = type;
		byte[] keyBuf = key;
		short keyLength = (short) (keyBuf != null ? keyBuf.length : -1);
		byte[] dataBuf = null;
		if (type == RecordType.CHUNK
				|| type == RecordType.CHUNK_INDEX
				|| type == RecordType.SPLITTED_HEAD
				|| type == RecordType.SPLITTED_RECORD
				|| (type == RecordType.DATA_RECORD && data.length <= this.recordMaxLength)) {
			dataBuf = data;
		}
		if (type == RecordType.SPLITTED_RECORD) {
			keyLength = -1;
		}
		// first delete the previous entry;
		// this or the previous one could be split 
		if (recordId > 0 && type == RecordType.DATA_RECORD && item != null
				&& item.num == RecordType.SPLITTED_HEAD) {
			// only the "tail" is cut the head is used for this to update 
			deleteSplittedRecords(recordId);
		}
		if (type == RecordType.DATA_RECORD
				&& data.length > this.recordMaxLength) {
			Vector mapChunks = new Vector();
			int offset = recordMaxLength;
			short index = 0;
			while (offset < data.length) {
				int ithRid = physicalWrite(-1, new byte[] {
						(byte) (index >> 8), (byte) (index & 0xff) }, data,
						offset,
						Math.min(recordMaxLength, data.length - offset),
						RecordType.SPLITTED_RECORD, null);
				mapChunks.addElement(new Integer(ithRid));
				offset += recordMaxLength;
			}
			try {
				// serialize mapChunks
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				DataOutputStream os = new DataOutputStream(baos);
				os.writeShort(mapChunks.size());
				Enumeration en = mapChunks.elements();
				while (en.hasMoreElements()) {
					Integer ithRid = (Integer) en.nextElement();
					os.writeInt(ithRid.intValue());
				}
				os.write(data, 0, recordMaxLength);
				dataBuf = baos.toByteArray();
				headerBuf = RecordType.SPLITTED_HEAD;
				len = dataBuf.length;
			} catch (Exception e) {
				// #mdebug
//@								// XXX clean dirty records! how ??!?
//@								e.printStackTrace();
//@								System.out.println("Error in serialize split chunks "
//@										+ e.getMessage() + e.getClass());
				// #enddebug
			}
		}

		// final write
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream os = new DataOutputStream(baos);
			os.write(headerBuf);
			if (keyLength > 0) os.writeShort(keyLength);
			if (keyBuf != null) os.write(keyBuf);
			os.write(dataBuf, start, len);
			byte[] finalBuf = baos.toByteArray();
			if (recordId >= 0) rs.setRecord(recordId, finalBuf, 0,
					finalBuf.length);
			else
				recordId = rs.addRecord(finalBuf, 0, finalBuf.length);
			// save the type of record in item
			if (item != null) item.num = headerBuf;
			return recordId;
		} catch (Exception e) {
			// #mdebug
//@						// XXX clean dirty records! how ??!?
//@						e.printStackTrace();
//@						System.out.println("Error in final write " + e.getMessage()
//@								+ e.getClass());
			// #enddebug
		}

		return -1;
	}

	private byte[] physicalRead(Item ii) {
		try {
			byte[] buf = rs.getRecord(ii.id);
			DataInputStream is = new DataInputStream(new ByteArrayInputStream(
					buf));
			byte type = is.readByte();
			ii.num = type;
			byte[] realData = null;
			short keyLen;
			byte[] key;
			switch (type) {
				case RecordType.DATA_RECORD:
					keyLen = is.readShort();
					key = new byte[keyLen];
					is.read(key);
					realData = new byte[is.available()];
					is.read(realData);
					return realData;

				case RecordType.SPLITTED_HEAD:
					keyLen = is.readShort();
					key = new byte[keyLen];
					is.read(key, 0, keyLen);
					short chunkSize = is.readShort();
					int ithRid = 0;
					int rids[] = new int[chunkSize];
					for (int i = 0; i < chunkSize; i++)
						rids[i] = is.readInt();
					byte headBytes[] = new byte[is.available()];
					is.read(headBytes);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					DataOutputStream os = new DataOutputStream(baos);
					os.write(headBytes);
					for (int i = 0; i < chunkSize; i++) {
						byte[] ithRecord = rs.getRecord(rids[i]);
						byte ithType = ithRecord[0];
						short ordinal = (short) (ithRecord[1] << 8 + ithRecord[2]);
						os.write(ithRecord, 3, ithRecord.length - 3);
					}
					realData = baos.toByteArray();
					return realData;

				default:
					break;
			}
		} catch (Exception e) {
			// #mdebug
//@						e.printStackTrace();
//@						System.out.println(e.getMessage() + e.getClass());
			// #enddebug
		}
		return null;
	}

	public int[] getSizes() {
		try {
			return new int[] { this.rs.getSize(), this.rs.getSizeAvailable(),
					this.rs.getNumRecords(), this.rs.getRecordSize(1) };
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// #mdebug
//@						e.printStackTrace();
//@						System.out.println(e.getMessage() + e.getClass());
			// #enddebug
		}
		return new int[] { 0, 0 };
	}

	/**
	 * Store an item
	 * @param key
	 * @param data
	 */
	public synchronized void store(byte key[], byte data[]) {
		try {
			Item ci = get_item(chunk_index, key, false);
			if (ci == null) {
				// first record setup all
				// create a new chunk and add the record
				current_chunk = new Vector();
				Item ii = new Item();
				//ii.id = rs.addRecord(data, 0, data.length);
				ii.id = physicalWrite(-1, key, data, 0, data.length,
						RecordType.DATA_RECORD, ii);
				ii.key = key;
				current_chunk.addElement(ii);

				// Now save the index of the just created chunk
				current_index = new Item();
				current_index.key = key;
				// save the chunk and get its record
				current_index.id = saveChunk(-1, current_chunk);
				current_index.num = (short) current_chunk.size();
				chunk_index.addElement(current_index);
				// save the index
				saveChunk(1, chunk_index);
			} else {
				if (ci != current_index) {
					current_chunk = loadChunk(ci.id);
					current_index = ci;
					current_index.num = (short) current_chunk.size();
				}
				Item ii = get_item(current_chunk, key, true);
				if (ii != null) {
					// item found, simple update
					//rs.setRecord(ii.id, data, 0, data.length);
					// if the file was split and now not (or on the contrary)
					// I need to save the current_chunk!!
					short oldNum = ii.num;
					physicalWrite(ii.id, key, data, 0, data.length,
							RecordType.DATA_RECORD, ii);
					if (ii.num != oldNum) {
						saveChunk(ci.id, current_chunk);
					}
				} else {
					// add the new record
					ii = new Item();
					//ii.id = rs.addRecord(data, 0, data.length);
					ii.id = physicalWrite(-1, key, data, 0, data.length,
							RecordType.DATA_RECORD, ii);
					ii.key = key;

					// insert the record into the chunk 
					sorted_insert(current_chunk, ii);

					if (current_chunk.size() <= CHUNK_MAXSIZE) {
						// just save
						saveChunk(ci.id, current_chunk);
						ci.num = (short) current_chunk.size();
						if (comparator.compare(ii.key, ci.key) < 0) {
							ci.key = ii.key;
							saveChunk(1, chunk_index);
						}
					} else {
						// split the chunk before saving
						// #mdebug
//@												System.out.println("split");
						// #enddebug
						Vector new_chunk = new Vector();
						int end = current_chunk.size();
						for (int i = current_chunk.size() / 2; i < end; i++) {
							new_chunk.addElement(current_chunk.elementAt(i));
						}

						for (int i = current_chunk.size() / 2; i < end; i++) {
							current_chunk
									.removeElementAt(current_chunk.size() - 1);
						}
						saveChunk(ci.id, current_chunk);
						ci.num = (short) current_chunk.size();
						Item new_index = new Item();
						new_index.id = saveChunk(-1, new_chunk);
						new_index.key = ((Item) new_chunk.elementAt(0)).key;
						new_index.num = (short) new_chunk.size();
						sorted_insert(chunk_index, new_index);
						saveChunk(1, chunk_index);
						// #mdebug
//@												for (int i = 0; i < chunk_index.size(); i++) {
//@													System.out.println("~"
//@															+ i
//@															+ "~:"
//@															+ new String(((Item) chunk_index
//@																	.elementAt(i)).key));
//@												}
						// #enddebug
					}
				}
			}
		} catch (Exception e) {
			// #mdebug
//@						e.printStackTrace();
//@						System.out.println(e.getMessage() + e.getClass());
			// #enddebug
		}
	}

	public synchronized byte[] load(byte key[]) {
		try {
			Item ci = get_item(chunk_index, key, false);
			if (ci != current_index) {
				current_chunk = loadChunk(ci.id);
				current_index = ci;
				current_index.num = (short) current_chunk.size();
			}
			if (current_chunk==null)
				return null;
			Item ii = get_item(current_chunk, key, true);
			if (ii != null) {
				return physicalRead(ii);
			} else {
				return null;
			}
		} catch (Exception e) {
			// #mdebug
//@						e.printStackTrace();
//@						System.out.println(e.getMessage() + e.getClass());
			// #enddebug
		}
		return null;
	}

	public synchronized void delete(byte[] key) {

		try {
			Item ci = get_item(chunk_index, key, false);
			if (ci != current_index) {
				current_chunk = loadChunk(ci.id);
				current_index = ci;
				current_index.num = (short) current_chunk.size();
			}
			int pos = get_offset(current_chunk, key, true);
			if (pos >= 0) {
				Item ii = (Item) current_chunk.elementAt(pos);
				if (ii.num == RecordType.SPLITTED_HEAD) {
					deleteSplittedRecords(ii.id);
				}
				rs.deleteRecord(ii.id);
				current_chunk.removeElementAt(pos);
				if (current_chunk.size() == 0) {
					// remove the chunk
					rs.deleteRecord(ci.id);
					chunk_index.removeElement(ci);
					saveChunk(1, chunk_index);
					current_index = null;
				} else {
					if (pos == 0) {
						ci.key = ((Item) current_chunk.elementAt(0)).key;
						saveChunk(1, chunk_index);
					}
					saveChunk(ci.id, current_chunk);
					ci.num = (short) current_chunk.size();
				}

				// check join
				if (current_index != null) {
					if (current_index != chunk_index.elementAt(0)) {
						Item prev_index = (Item) chunk_index
								.elementAt(chunk_index.indexOf(current_index) - 1);
						if (current_index.num + prev_index.num <= RMSIndex.CHUNK_MAXSIZE) {
							join(prev_index, current_index);
						}
					} else if (current_index != chunk_index
							.elementAt(chunk_index.size() - 1)) {
						Item next_index = (Item) chunk_index
								.elementAt(chunk_index.indexOf(current_index) + 1);
						if (current_index.num + next_index.num <= RMSIndex.CHUNK_MAXSIZE) {
							join(current_index, next_index);
						}
					}
				}
			}
		} catch (Exception e) {
			// #mdebug
//@						e.printStackTrace();
//@						System.out.println(e.getMessage() + e.getClass());
			// #enddebug
		}
	}

	public boolean close() {
		try {
			this.rs.closeRecordStore();
		} catch (Exception e) {
			// #mdebug
//@						e.printStackTrace();
//@						System.out.println(e.getMessage() + e.getClass());
			// #enddebug
			return false;
		}
		return true;
	}

	public Enumeration keys() {
		return new KeyEnumeration();
	}

	public RMSIndex(String name) {
		this.name = name;
		comparator = new DefaultUTF8Comparator();
		//init(name);
	}

	public RMSIndex(String name, int recordMaxLength) {
		this(name);
		this.recordMaxLength = recordMaxLength;
	}

	public boolean open() {
		try {
			rs = RecordStore.openRecordStore(name, true);
			if (rs.getNumRecords() == 0) {
				// first time we open the store, reserve an area for the chunk index
				//rs.addRecord(new byte[] {}, 0, 0);
				physicalWrite(-1, null, new byte[] {}, 0, 0,
						RecordType.CHUNK_INDEX, null);
			} else {
				// load the chunk index
				chunk_index = loadChunk(1);
			}
		} catch (Exception e) {
			// #mdebug
//@						e.printStackTrace();
//@						System.out.println(e.getMessage() + e.getClass());
			// #enddebug
			return false;
		}
		return true;
	}
}
