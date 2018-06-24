package com.unesco.core.controllerWeb;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({NewsControllerWebTest.class,
        AccountControllerWebTest.class,
        JournalControllerWebTest.class,
        ScheduleControllerWebTest.class,
        DictionaryControllerWebTest.class} )
public class ControllerWebTest {

}
