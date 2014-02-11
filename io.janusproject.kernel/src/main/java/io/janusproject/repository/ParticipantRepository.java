/*
 * $Id$
 * 
 * Janus platform is an open-source multiagent platform.
 * More details on http://www.janusproject.io
 * 
 * Copyright (C) 2014 Sebastian RODRIGUEZ, Nicolas GAUD, Stéphane GALLAND.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.janusproject.repository;

import io.sarl.lang.core.EventListener;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;



/**
 * An abstract repository providing the basic support of storage a collection a participant's address and its related listener 
 * $Author: ngaud$
 *
 * @param <ADDRESS> - the generic type representing the address of a participant in the related space. This type must remains small, less thanM in memory and must be {@link java.io.Serializable}
 */
public abstract class ParticipantRepository<ADDRESS extends Serializable> {

	/**
	 * Map linking the unique address of an entity in the related space to the entity itself
	 * This is local non-distributed map
	 */
	private final Map<ADDRESS, EventListener> listeners;
	
	
	protected ParticipantRepository() {
		this.listeners = new ConcurrentHashMap<ADDRESS, EventListener>();
	}

	public int listenerCount() {
		return this.listeners.size();
	}

	protected boolean isListenerEmpty() {
		return this.listeners.isEmpty();
	}

	protected boolean containsAddress(ADDRESS key) {
		return this.listeners.containsKey(key);
	}

	protected boolean containsListener(EventListener value) {
		return this.listeners.containsValue(value);
	}

	protected EventListener getListener(ADDRESS key) {
		return this.listeners.get(key);
	}

	protected EventListener addListener(ADDRESS key, EventListener value) {
		return this.listeners.put(key, value);
	}

	protected EventListener removeListener(ADDRESS key) {
		return this.listeners.remove(key);
	}

	protected void clearListeners() {
		this.listeners.clear();
	}

	protected Set<ADDRESS> getAdresses() {
		return this.listeners.keySet();
	}

	public Collection<EventListener> getListeners() {
		return this.listeners.values();
	}

	protected Set<Entry<ADDRESS, EventListener>> listenersEntrySet() {
		return this.listeners.entrySet();
	}
	
	
	
	
	
}
