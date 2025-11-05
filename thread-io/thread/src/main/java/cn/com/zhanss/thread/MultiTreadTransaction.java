package cn.com.zhanss.thread;

import cn.com.zhanss.datastructure.map.MyUnionSet;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * 多线程事务
 * 手动提交事务
 * 线程池 + callable接口
 * 线程池 + CountDownLatch + AtomicBoolean
 *
 * @author zhanshuchan
 * @date 2024/1/24
 **/
public class MultiTreadTransaction {

    @Resource
    SqlContext sqlContext;
    /**
     * 测试多线程事务.
     * @param employeeList
     */
    public void saveThread(List<Employee> employeeList) throws SQLException {
        // 获取数据库连接,获取会话(内部自有事务)
        SqlSession sqlSession = sqlContext.getSqlSession();
        Connection connection = sqlSession.getConnection();
        try {
            // 设置手动提交
            connection.setAutoCommit(false);
//            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            //先做删除操作
//            employeeMapper.delete(null);
            ExecutorService service = Executors.newFixedThreadPool(employeeList.size());
            List<Callable<Integer>> callableList  = new ArrayList<>();
            List<List<Employee>> lists = /*Lists.subList(employeeList, 5)*/null;
            for (int i = 0; i<lists.size(); i ++){
                List<Employee> list  = lists.get(i);
                Callable<Integer> callable = () -> /*employeeMapper.saveBatch(list)*/null;
                callableList.add(callable);
            }
            //执行子线程
            List<Future<Integer>> futures = service.invokeAll(callableList);
            for (Future<Integer> future:futures) {
                if (future.get() <= 0){
                    // 有一个失败就回滚
                    connection.rollback();
                    return;
                }
            }
            connection.commit();
            System.out.println("添加完毕");
        } catch (Exception e) {
            connection.rollback();
            throw new RuntimeException("出现异常");
            // throw new ServiceException(ExceptionCodeEnum.EMPLOYEE_SAVE_OR_UPDATE_ERROR);
        }
    }

    @Component
    public class SqlContext {
//    @Resource
//    private SqlSessionTemplate sqlSessionTemplate;

        public SqlSession getSqlSession(){
            SqlSessionFactory sqlSessionFactory = /*sqlSessionTemplate.getSqlSessionFactory()*/null;
            return sqlSessionFactory.openSession();
        }
    }

    /**
     * 方案二
     * 线程池 + TransactionStatus
     */
    @Service
    public class StudentsTransactionThread {

        @Autowired
        private StudentMapper studentMapper;
        @Autowired
        private StudentService studentService;
        @Autowired
        private PlatformTransactionManager transactionManager;

        List<TransactionStatus> transactionStatuses = Collections.synchronizedList(new ArrayList<TransactionStatus>());

        @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
        public void updateStudentWithThreadsAndTrans() throws InterruptedException {

            //查询总数据
            List<Employee> allStudents = studentMapper.getAll();

            // 线程数量
            final Integer threadCount = 2;

            //每个线程处理的数据量
            final Integer dataPartionLength = (allStudents.size() + threadCount - 1) / threadCount;

            // 创建多线程处理任务
            ExecutorService studentThreadPool = Executors.newFixedThreadPool(threadCount);
            CountDownLatch threadLatchs = new CountDownLatch(threadCount);
            AtomicBoolean isError = new AtomicBoolean(false);
            try {
                for (int i = 0; i < threadCount; i++) {
                    // 每个线程处理的数据
                    List<Employee> threadDatas = allStudents.stream()
                            .skip(i * dataPartionLength).limit(dataPartionLength).collect(Collectors.toList());
                    studentThreadPool.execute(() -> {
                        try {
                            try {
                                studentService.updateStudentsTransaction(transactionManager, transactionStatuses, threadDatas);
                            } catch (Throwable e) {
                                e.printStackTrace();
                                isError.set(true);
                            }finally {
                                threadLatchs.countDown();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            isError.set(true);
                        }
                    });
                }

                // 倒计时锁设置超时时间 30s
                boolean await = threadLatchs.await(30, TimeUnit.SECONDS);
                // 判断是否超时
                if (!await) {
                    isError.set(true);
                }
            } catch (Throwable e) {
                e.printStackTrace();
                isError.set(true);
            }

            if (!transactionStatuses.isEmpty()) {
                if (isError.get()) {
                    transactionStatuses.forEach(s -> transactionManager.rollback(s));
                } else {
                    transactionStatuses.forEach(s -> transactionManager.commit(s));
                }
            }

            System.out.println("主线程完成");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void updateStudentsTransaction(PlatformTransactionManager transactionManager, List<TransactionStatus> transactionStatuses, List<MyUnionSet.Student> students) {
        // 使用这种方式将事务状态都放在同一个事务里面
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
        TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
        transactionStatuses.add(status);

        students.forEach(s -> {
            // 更新教师信息
            // String teacher = s.getTeacher();
            String newTeacher = "TNO_" + new Random().nextInt(100);
            s.setTeacher(newTeacher);
            studentMapper.update(s);
        });
        System.out.println("子线程：" + Thread.currentThread().getName());
    }

}

