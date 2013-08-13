package de.jinxos.AwesomeScript;

public class MemoryWriter {

	public static int VM_READ = 1;
	public static int VM_WRITE = 2;
	public static int VM_EXECUTE = 4;
	
	public static native int GetBaseAddress(int task);
	public static native int ReadMemory(int task, int sourceptr, int size, int destinationptr);
	public static native int WriteMemory(int task, int sourceptr, int size, int destinationptr);
	public static native int OpenTask(int pid);
	public static native int CloseTask(int task);
	public static native int ProtectMemory(int task, int address, int size, int protection);
	public static native String GetLastError();
}