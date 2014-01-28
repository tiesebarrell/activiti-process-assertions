/*******************************************************************************
 * Copyright 2014 Tiese Barrell
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.toxos.activiti.assertion.suite.locale;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.toxos.activiti.assertion.suite.AssertionExampleTests;
import org.toxos.activiti.assertion.suite.AssertionTests;
import org.toxos.activiti.assertion.suite.LocaleUtilityTests;

/**
 * Test suite for all test cases that are specific for locales.
 * 
 * @author Tiese Barrell
 */
@RunWith(Suite.class)
@SuiteClasses({ AssertionTests.class, LocaleUtilityTests.class, AssertionExampleTests.class })
public class AllLocaleSpecificTests {

}
