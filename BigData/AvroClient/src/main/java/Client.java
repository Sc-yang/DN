import avro.service.Calc;
import org.apache.avro.ipc.NettyTransceiver;
import org.apache.avro.ipc.specific.SpecificRequestor;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Client {
    public static void main(String[] args) throws IOException {

        // 传递信息
        NettyTransceiver tran = new NettyTransceiver(new InetSocketAddress("localhost", 8070));

        Calc c = SpecificRequestor.getClient(Calc.class, tran);

        double result = c.add(3, 5);

        System.out.println(result);
    }
}
