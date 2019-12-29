package backend;

import java.io.OutputStream;
import java.io.PrintStream;

import org.apache.log4j.Logger;

public class Log4jPrintStream extends PrintStream {
    private Logger log = Logger.getLogger("SystemOut");
    private static PrintStream instance = new Log4jPrintStream(System.out);

    private Log4jPrintStream(OutputStream out) {
        super(out);
    }

    public static void redirectSystemOut() {
        System.setOut(instance);
    }

    @Override
    public void println(boolean x) {
        log.debug(Boolean.valueOf(x));
    }

    @Override
    public void println(char x) {
        log.debug(Character.valueOf(x));
    }

    @Override
    public void println(char[] x) {
        log.debug(x == null ? null : new String(x));
    }

    @Override
    public void println(double x) {
        log.debug(Double.valueOf(x));
    }

    @Override
    public void println(float x) {
        log.debug(Float.valueOf(x));
    }

    @Override
    public void println(int x) {
        log.debug(Integer.valueOf(x));
    }

    @Override
    public void println(long x) {
        log.debug(x);
    }

    @Override
    public void println(Object x) {
        log.debug(x);
    }

    @Override
    public void println(String x) {
        log.debug(x);
    }

}
