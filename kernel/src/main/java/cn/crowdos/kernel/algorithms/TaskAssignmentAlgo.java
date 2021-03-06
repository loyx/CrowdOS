package cn.crowdos.kernel.algorithms;

import cn.crowdos.kernel.resource.Participant;
import cn.crowdos.kernel.resource.Task;

import java.util.List;

@FunctionalInterface
public interface TaskAssignmentAlgo {
    /**
     * Given a task, return a list of participants that are assigned to the task.
     *
     * @param task The task for which you want to get the assignment scheme.
     * @return A list of participants.
     */
    List<Participant> getAssignmentScheme(Task task);
}
