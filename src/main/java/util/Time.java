package util;

public class Time
{
    public static long timeStarted = System.nanoTime();

    // 1E-9 is to convert from nanoseconds to seconds
    public static float getTime()
    {
        return (float)((System.nanoTime() - timeStarted) * 1E-9);
    }
}
