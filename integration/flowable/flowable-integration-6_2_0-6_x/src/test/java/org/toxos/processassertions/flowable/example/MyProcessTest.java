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
package org.toxos.processassertions.flowable.example;

import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.test.Deployment;
import org.flowable.engine.test.FlowableRule;
import org.flowable.task.api.Task;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.toxos.processassertions.api.ProcessAssert;
import org.toxos.processassertions.flowable.ProcessAssertFlowableConfiguration;
import org.toxos.processassertions.flowable.integration.configuration.FlowableTestConfiguration;

/**
 * Example test for a simple process checked using process assertions.
 * 
 * @author Tiese Barrell
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FlowableTestConfiguration.class)
public class MyProcessTest {

    @Autowired
    @Rule
    public FlowableRule flowableRule;

    @Before
    public void before() {
        ProcessAssertFlowableConfiguration.from(flowableRule).register();
    }

    @Test
    @Deployment(resources = "example/MyProcess.bpmn")
    public void testMyProcess() throws Exception {
        final ProcessInstance processInstance = flowableRule.getRuntimeService().startProcessInstanceByKey("myProcess");

        // assert the process is still running
        ProcessAssert.assertProcessActive(processInstance.getProcessInstanceId());

        // assert the process is waiting for a UserTask to be completed
        ProcessAssert.assertTaskUncompleted(processInstance.getProcessInstanceId(), "usertask1");

        // complete the task
        final Task userTask1 = flowableRule.getTaskService().createTaskQuery().processInstanceId(processInstance.getProcessInstanceId())
                .taskDefinitionKey("usertask1").singleResult();
        flowableRule.getTaskService().complete(userTask1.getId());

        // assert the process is now ended
        ProcessAssert.assertProcessEnded(processInstance.getProcessInstanceId());

    }
}
