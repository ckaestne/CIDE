package it.yup.xmpp;

/**
 * This interface is used to notify of an action that the user invoked on
 * the form.
 */
public interface DataFormListener {

	/** available actions */
	/** next button pressed (aka SUBMIT) available for ad hoc commands */
	public static final int CMD_NEXT = 0x01;
	/** prev button pressed, available for ad ho commands */
	public static final int CMD_PREV = 0x02;
	/** submit form */
	public static final int CMD_SUBMIT = 0x04;
	/** cancel form */
	public static final int CMD_CANCEL = 0x08;
	/** keep form for future use */
	public static final int CMD_DELAY = 0x10;
	/** destroy form (don't send anything to other peer) */
	public static final int CMD_DESTROY = 0x20;

	/**
	 * executes an action for the given dataForm. Available actions are one
	 * of the CMD_* constansts.
	 * 
	 * @param cmd
	 *            The command to execute (one of the CMD_* constants)
	 * @return "true" it the listener is going to have a reply (e.g. iq will have a reply
	 * while message not)           
	 */
	public boolean execute(int cmd);
}