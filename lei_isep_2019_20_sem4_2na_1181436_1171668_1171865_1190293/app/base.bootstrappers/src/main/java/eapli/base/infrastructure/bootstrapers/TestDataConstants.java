package eapli.base.infrastructure.bootstrapers;

import java.util.Calendar;

import eapli.framework.util.Calendars;

public final class TestDataConstants {

	public static final String USER_TEST1 = "user1";

	@SuppressWarnings("squid:S2068")
	public static final String PASSWORD1 = "Password1";

	@SuppressWarnings("squid:S2885")
	public static final Calendar DATE_TO_BOOK = Calendars.of(2017, 12, 01);

	private TestDataConstants() {
		// ensure utility
	}
}
