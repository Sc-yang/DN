import org.junit.Test;

public class AvroSerialDemoTest {

    @Test
    public void create(){
        User u1 = new User();
        u1.setName("Amy");
        u1.setAge(13);
        u1.setGender(true);

        System.out.println(u1);
    }
}
