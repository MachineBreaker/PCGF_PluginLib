/*
 *   Copyright (C) 2016-2018 GeorgH93
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package at.pcgamingfreaks;

import at.pcgamingfreaks.Calendar.TimeSpan;

import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Logger;

public class Utils
{
	/**
	 * Converts a byte array into a hex string.
	 *
	 * @param bytes The byte array to convert to hex.
	 * @return The hex string matching the given byte array. The chars a-f will be lower case!
	 */
	public static String byteArrayToHex(@Nullable byte[] bytes)
	{
		if(bytes == null || bytes.length == 0) return "";
		StringBuilder hexBuilder = new StringBuilder(bytes.length * 2);
		for(byte b: bytes)
		{
			hexBuilder.append(String.format("%02x", b));
		}
		return hexBuilder.toString();
	}

	/**
	 * Shows a warning message if the Java version is still 1.7.
	 *
	 * @param logger The logger to output the warning
	 */
	public static void warnOnJava_1_7(@NotNull Logger logger)
	{
		warnOnJava_1_7(logger, 0);
	}

	/**
	 * Shows a warning message if the Java version is still 1.7.
	 *
	 * @param logger The logger to output the warning
	 * @param pauseTime The time in seconds the function should be blocking (in seconds) if Java is outdated. Values below 1 wont block.
	 */
	public static void warnOnJava_1_7(@NotNull Logger logger, int pauseTime)
	{
		Validate.notNull(logger, "The logger must not be null.");
		if (System.getProperty("java.version").startsWith("1.7"))
		{
			TimeSpan ts = new TimeSpan(1430438401000L, true);
			StringBuilder msgBuilder = new StringBuilder(ConsoleColor.RED.toString());
			msgBuilder.append("You are still using Java 1.7. The support end of Java 1.7 was ");
			msgBuilder.append(ts.getYears());
			msgBuilder.append(" year");
			if(ts.getYears() > 1) msgBuilder.append('s');
			msgBuilder.append(' ');
			if(ts.getMonths() > 0)
			{
				msgBuilder.append("and ");
				msgBuilder.append(ts.getMonths());
				msgBuilder.append(" month");
				if(ts.getMonths() > 1) msgBuilder.append('s');
				msgBuilder.append(' ');
			}
			msgBuilder.append(" ago! You should really update to Java 1.8!");
			msgBuilder.append(ConsoleColor.RESET.toString());
			logger.warning(msgBuilder.toString());
			logger.info(ConsoleColor.YELLOW + "For now this plugin will still work fine with Java 1.7 but no warranty that this won't change in the future." + ConsoleColor.RESET);
			blockThread(pauseTime);
		}
	}

	/**
	 * Blocks the thread for a given time
	 *
	 * @param pauseTime The time in seconds that the thread should be blocked
	 */
	public static void blockThread(int pauseTime)
	{
		if(pauseTime > 0) // If there is a valid time we pause the current thread for that time
		{
			try
			{
				Thread.sleep(pauseTime * 1000L);
			}
			catch(InterruptedException ignored) {}
		}
	}

	/**
	 * Checks if an array contains an certain value
	 *
	 * @param array The array that should be checked
	 * @param data  The data that should be searched for in the array
	 * @param <T>   The class of the array and data
	 * @return True if the data is contained within the array, false if not
	 */
	public static <T> boolean arrayContains(@NotNull T[] array, @Nullable T data)
	{
		Validate.notNull(array);
		for(T element : array)
		{
			if(element.equals(data)) return true;
		}
		return false;
	}
}