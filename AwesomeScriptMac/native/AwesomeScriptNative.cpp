// AwesomeScriptNative.cpp : Defines the exported functions for the DLL application.
//

#include "stdafx.h"
#include "de_jinxos_AwesomeScript_MemoryWriter.h"
#include "de_jinxos_AwesomeScript_ProcessChecker.h"
#include "de_jinxos_AwesomeScript_KeyPress.h"
#include <TlHelp32.h>
#include <Psapi.h>
#include <Dbghelp.h>
#include <stdlib.h>

#pragma comment (lib, "Dbghelp.lib")

JNIEXPORT jint JNICALL Java_de_jinxos_AwesomeScript_MemoryWriter_ReadMemory
  (JNIEnv *, jclass, jint hProc, jint src, jint size, jint dst)
{
	SIZE_T read;
	return ReadProcessMemory((HANDLE)hProc, (LPVOID)src, (LPVOID)dst, size, &read);
}

JNIEXPORT jint JNICALL Java_de_jinxos_AwesomeScript_MemoryWriter_WriteMemory
  (JNIEnv *, jclass, jint hProc, jint src, jint size, jint dst)
{
	SIZE_T written;
	return WriteProcessMemory((HANDLE)hProc, (LPVOID)src, (LPVOID)dst, size, &written);
}

JNIEXPORT jint JNICALL Java_de_jinxos_AwesomeScript_MemoryWriter_OpenTask
  (JNIEnv *, jclass, jint pid)
{
	return (jint)OpenProcess(PROCESS_ALL_ACCESS, false, pid);
}

JNIEXPORT jint JNICALL Java_de_jinxos_AwesomeScript_MemoryWriter_CloseTask
	(JNIEnv *, jclass, jint handle)
{
	return (jint)CloseHandle((HANDLE)handle);
}

JNIEXPORT jint JNICALL Java_de_jinxos_AwesomeScript_MemoryWriter_ProtectMemory
  (JNIEnv *, jclass, jint hProc, jint dst, jint size, jint prot)
{
	DWORD dwOld;
	return VirtualProtectEx((HANDLE)hProc, (LPVOID)dst, size, prot, &dwOld);
}

JNIEXPORT jint JNICALL Java_de_jinxos_AwesomeScript_ProcessChecker_IsRunning
  (JNIEnv *env, jclass, jstring str)
{
	char name[64];
	const char *tmp = env->GetStringUTFChars(str, false);
	sprintf_s(name, "%s.exe", tmp);
	HANDLE hProcSnap;
	PROCESSENTRY32 pe32;
	pe32.dwSize = sizeof(pe32);

	hProcSnap = CreateToolhelp32Snapshot(TH32CS_SNAPPROCESS, 0);
	if (!hProcSnap) return 0;

	if (!Process32First(hProcSnap, &pe32))
	{
		CloseHandle(hProcSnap);
		return 0;
	}

	do
	{
		if (_stricmp(name, pe32.szExeFile) == 0)
		{
			CloseHandle(hProcSnap);
			return pe32.th32ProcessID;
		}
	}
	while (Process32Next(hProcSnap, &pe32));

	CloseHandle(hProcSnap);
	return 0;
}

JNIEXPORT jboolean JNICALL Java_de_jinxos_AwesomeScript_KeyPress_IsPressed
  (JNIEnv *, jclass, jint vkey)
{
	return (jboolean)GetAsyncKeyState(vkey);
}

LPCSTR GetLastErrorStr()
{
  DWORD error = GetLastError();
  if (error)
  {
    LPVOID lpMsgBuf;
    DWORD bufLen = FormatMessage(
        FORMAT_MESSAGE_ALLOCATE_BUFFER | 
        FORMAT_MESSAGE_FROM_SYSTEM |
        FORMAT_MESSAGE_IGNORE_INSERTS,
        NULL,
        error,
        MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT),
        (LPTSTR) &lpMsgBuf,
        0, NULL );
    if (bufLen)
    {
      LPCSTR lpMsgStr = (LPCSTR)lpMsgBuf;

	  return lpMsgStr;
    }
  }
  return "";
}

JNIEXPORT jstring JNICALL Java_de_jinxos_AwesomeScript_MemoryWriter_GetLastError
  (JNIEnv *nev, jclass)
{
	return nev->NewStringUTF(GetLastErrorStr());
}

JNIEXPORT jint JNICALL Java_de_jinxos_AwesomeScript_MemoryWriter_GetBaseAddress
  (JNIEnv *env, jclass cl, jint hproc)
{
	char* name = "Awesomenauts.exe";
	HANDLE hProcSnap;
	MODULEENTRY32 pe32;
	pe32.dwSize = sizeof(pe32);

	hProcSnap = CreateToolhelp32Snapshot(TH32CS_SNAPMODULE, hproc);
	if (!hProcSnap) return 0;

	if (!Module32First(hProcSnap, &pe32))
	{
		CloseHandle(hProcSnap);
		return 0;
	}

	do
	{
		if (_stricmp(name, pe32.szModule) == 0)
		{
			CloseHandle(hProcSnap);
			return (jint)pe32.modBaseAddr;
		}
	}
	while (Module32Next(hProcSnap, &pe32));

	CloseHandle(hProcSnap);
	return 0;
}

JNIEXPORT jint JNICALL Java_de_jinxos_AwesomeScript_MemoryWriter_AllocateBytes
  (JNIEnv *env, jclass, jbyteArray bytearray)
{
	jboolean copy;
	return (jint)env->GetByteArrayElements(bytearray, &copy);
}

JNIEXPORT jint JNICALL Java_de_jinxos_AwesomeScript_MemoryWriter_AllocateMemory
  (JNIEnv *, jclass, jint size)
{
	return (jint)malloc(size);
}

JNIEXPORT jbyteArray JNICALL Java_de_jinxos_AwesomeScript_MemoryWriter_GetBytes
  (JNIEnv *env, jclass, jint ptr, jint size)
{
	jbyteArray jb = env->NewByteArray(size);
	env->SetByteArrayRegion(jb, 0, size, (jbyte*)ptr);
	return jb;
}