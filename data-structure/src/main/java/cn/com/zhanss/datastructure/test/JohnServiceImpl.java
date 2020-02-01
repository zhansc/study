package cn.com.zhanss.datastructure.test;

import org.springframework.stereotype.Service;

/**
 * @author zhanshuchan
 * @date 2019/12/8
 */
@Service
public class JohnServiceImpl implements PersonService {
    @Override
    public Person get(int id) {
        Person person = Person.builder()
                .id(1)
                .age(21)
                .gender("male")
                .name("john")
                .build();
        System.out.println("john===>"+ person);
        return person;
    }
}
