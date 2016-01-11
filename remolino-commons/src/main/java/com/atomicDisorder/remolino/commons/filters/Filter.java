package com.atomicDisorder.remolino.commons.filters;

import java.util.ArrayList;
import java.util.regex.Pattern;

import com.atomicDisorder.remolino.commons.messages.RawMessage;
import com.atomicDisorder.remolino.commons.modules.Module;

/**
 * @author Mariano Blua
 *
 */

public interface Filter {
	public void addObserver(StringFilterObserver observer);
	public String getFilterCanonicalName();
}
