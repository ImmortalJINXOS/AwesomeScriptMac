package de.jinxos.AwesomeScript;

public class MemoryWriter {

	public static int VM_READ = 1;
	public static int VM_WRITE = 2;
	public static int VM_EXECUTE = 4;
	
	public static native int CopyMemory(int task, int sourceptr, int size, int destinationptr);
	public static native int GetTask(int pid);
	public static native int ProtectMemory(int task, int address, int size, int protection);
}