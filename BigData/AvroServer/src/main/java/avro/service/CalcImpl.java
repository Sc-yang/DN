package avro.service;

import org.apache.avro.AvroRemoteException;

public class CalcImpl implements Calc{
    @Override
    public double add(double i, double j) throws AvroRemoteException {
        System.out.println("收到了前端的信息： " + i + "，" + j);
        return i + j;
    }

    @Override
    public double minus(double i, double j) throws AvroRemoteException {
        System.out.println("收到了前端的信息： " + i + "，" + j);
        return i - j;
    }
}
