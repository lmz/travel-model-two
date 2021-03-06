/*
 * JPPF.
 * Copyright (C) 2005-2010 JPPF Team.
 * http://www.jppf.org
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
 */

package sample.cascading;

import java.util.List;

import org.jppf.client.*;
import org.jppf.node.policy.Equal;
import org.jppf.server.protocol.JPPFTask;

/**
 * This task submits a JPPF job.
 * @author Laurent Cohen
 */
public class Task1 extends JPPFTask
{

	/**
	 * Run this task.
	 * @see java.lang.Runnable#run()
	 */
	public void run()
	{
		JPPFClient client = null;
		try
		{
			System.out.println("Hello, this is Task1, about to submit Task2");
			client = new JPPFClient();
			JPPFJob job = new JPPFJob();
			job.addTask(new Task2());
			job.getJobSLA().setExecutionPolicy(new Equal("id", 2));
			List<JPPFTask> results = client.submit(job);
			System.out.println("Result of Task2: [" + results.get(0).getResult() + "]");
			setResult("Task1 executed successfully");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			setException(e);
		}
		finally
		{
			if (client != null) client.close();
		}
	}
}
