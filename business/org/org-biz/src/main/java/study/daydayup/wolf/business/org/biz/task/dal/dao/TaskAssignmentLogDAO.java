package study.daydayup.wolf.business.org.biz.task.dal.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import study.daydayup.wolf.business.org.biz.task.dal.dataobject.TaskAssignmentLogDO;

public interface TaskAssignmentLogDAO {
    int deleteById(Long id);

    int insert(TaskAssignmentLogDO record);

    int insertSelective(TaskAssignmentLogDO record);

    TaskAssignmentLogDO selectById(Long id);

    int updateByIdSelective(TaskAssignmentLogDO record);

    int updateById(TaskAssignmentLogDO record);

    TaskAssignmentLogDO selectByTaskId(@Param("taskId")Long taskId, @Param("orgId")Long orgId);

    List<TaskAssignmentLogDO> selectByTaskIdIn(@Param("taskId")Long taskId, @Param("orgId")Long orgId);


}