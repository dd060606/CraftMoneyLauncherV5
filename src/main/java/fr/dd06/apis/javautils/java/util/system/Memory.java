package fr.dd06.apis.javautils.java.util.system;

import java.lang.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;

public class Memory {

	public static long getInstalledMemory() {
		long installedMemory = ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean())
				.getTotalPhysicalMemorySize();
		return installedMemory;
	}
	public static long getInstalledMemoryInGb() {
		long installedMemoryInGb = ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean())
				.getTotalPhysicalMemorySize() / 1000000000L;
		return installedMemoryInGb;
	}
	public static long getInstalledMemoryInMb() {
		long installedMemoryInMb = ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean())
				.getTotalPhysicalMemorySize() / 1000000L;
		return installedMemoryInMb;
	}
}
