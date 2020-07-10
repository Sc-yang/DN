import avro.service.Calc;
import avro.service.CalcImpl;
import org.apache.avro.ipc.NettyServer;
import org.apache.avro.ipc.specific.SpecificResponder;

import java.net.InetSocketAddress;

public class Server {
    public static void main(String[] args) {

        //获取服务器
        NettyServer server = new NettyServer(
                new SpecificResponder(Calc.class, new CalcImpl()),
                new InetSocketAddress(8070));
    }
}
