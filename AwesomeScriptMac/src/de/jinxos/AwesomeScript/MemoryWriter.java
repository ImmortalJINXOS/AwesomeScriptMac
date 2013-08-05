package de.jinxos.AwesomeScript;

public class MemoryWriter {

	public static native int CopyMemory(int task, int sourceptr, int size, int destinationptr);
	public static native int GetTask(int pid);
}