package com.atomicDisorder.remolino.commons.filters;

import java.util.ArrayList;
import java.util.regex.Pattern;

import com.atomicDisorder.remolino.commons.modules.Module;

public interface StringFilter extends Filter {
	boolean apply(String rawStringMessage);
}
