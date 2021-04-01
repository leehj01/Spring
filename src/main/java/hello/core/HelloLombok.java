package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter  // 롬복있으면 이렇게 하면 코드 간결
@ToString
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("ASGE");

        String name = helloLombok.getName();
        System.out.println("name = " + name); // name = ASGE
        System.out.println("helloLombok = " + helloLombok); // helloLombok = HelloLombok(name=ASGE, age=0)
    }
}
