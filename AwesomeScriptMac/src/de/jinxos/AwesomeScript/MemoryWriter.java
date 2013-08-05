package de.jinxos.AwesomeScript;

public class MemoryWriter {

	public native int WriteMemory(int task, int pointer, int size, byte[] replace);
	public native int ReadMemory(int task, int pointer, int size, byte[] target);
	public native int GetTask(int pid);
}