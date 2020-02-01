package cn.com.zhanss.datastructure.test;

import org.springframework.stereotype.Service;

/**
 * @author zhanshuchan
 * @date 2019/12/8
 */
@Service
public class SmithServiceImpl implements PersonService {
    @Override
    public Person get(int id) {
        Person person = Person.builder()
                .id(2)
                .age(23)
                .gender("female")
                .name("Smith")
                .build();
        System.out.println("smith===>"+ person);
        return person;
    }
}
