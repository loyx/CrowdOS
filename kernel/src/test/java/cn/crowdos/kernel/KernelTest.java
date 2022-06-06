package cn.crowdos.kernel;

import cn.crowdos.kernel.CrowdKernel;
import cn.crowdos.kernel.Kernel;
import cn.crowdos.kernel.UninitializedKernelException;
import cn.crowdos.kernel.common.TimeParticipant;
import cn.crowdos.kernel.constraint.InvalidConstraintException;
import cn.crowdos.kernel.constraint.SimpleTimeConstraint;
import cn.crowdos.kernel.resource.SimpleTask;
import cn.crowdos.kernel.resource.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.text.SimpleDateFormat;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class KernelTest {

    CrowdKernel kernel;

    @BeforeEach
    void setUp() {
        kernel = Kernel.getKernel();
        kernel.initial();
        TimeParticipant p1 = new TimeParticipant("2022.6.1");
        TimeParticipant p2 = new TimeParticipant("2022.6.2");
        TimeParticipant p3 = new TimeParticipant("2022.6.3");
        TimeParticipant p4 = new TimeParticipant("2022.6.4");
        TimeParticipant p5 = new TimeParticipant("2022.6.5");
        TimeParticipant p6 = new TimeParticipant("2022.6.6");
        kernel.registerParticipant(p1);
        kernel.registerParticipant(p2);
        kernel.registerParticipant(p3);
        kernel.registerParticipant(p4);
        kernel.registerParticipant(p5);
        kernel.registerParticipant(p6);
        try {
            SimpleTimeConstraint timeConst = new SimpleTimeConstraint("2022.6.1", "2022.6.5");
            SimpleTask t1 = new SimpleTask(Collections.singletonList(timeConst), Task.TaskDistributionType.RECOMMENDATION);
            timeConst = new SimpleTimeConstraint("2022.6.2", "2022.6.4");
            SimpleTask t2 = new SimpleTask(Collections.singletonList(timeConst), Task.TaskDistributionType.ASSIGNMENT);
            kernel.submitTask(t1);
            kernel.submitTask(t2);
        } catch (InvalidConstraintException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void tearDown() {
        Kernel.shutdown();
    }
    @Test
    void getTasks() {
        System.out.println(kernel.getTasks());
    }

    @Test
    void getParticipants() {
        System.out.println(kernel.getParticipants());
    }

    @Test
    void getTaskAssignmentScheme() {
        for (Task task : kernel.getTasks()) {
            System.out.println(kernel.getTaskAssignmentScheme(task));
        }
    }

    @Test
    void getTaskRecommendationScheme() {
        for (Task task : kernel.getTasks()) {
            System.out.println(kernel.getTaskRecommendationScheme(task));
        }
    }

    @Test
    void getTaskParticipantSelectionResult() {
        for (Task task : kernel.getTasks()) {
            System.out.println(kernel.getTaskParticipantSelectionResult(task));
        }
    }
}