package com.ttc.ch2.audit;

import java.util.Date;

public class Metric {

    public String hostAddr;
    
    public Date timestamp;

    public Long threadCount;

    public Long uptime;

    public Double processCpuLoad;
    public Double systemCpuLoad;

    // the amount [MB] of free physical memory in bytes.
    public Double memPhysicalFree;
    //
    public Double memPhysicalTotal;
    // XXX
    public Double memPhysicalUsedPerc;

    // the amount [MB] of virtual memory that is guaranteed to be available to
    // the running process, or -1 if this operation is not supported
    public Double memVirtualCommitted;
    // the percentage of virtual memory that is committed to be available to the
    // running process against whole physical and swap space
    public Double memVirtualCommitedUsedPerc; // XXX

    // the amount [MB] of free swap space
    public Double swapSpaceFree;
    // the total [MB] amount of swap space
    public Double swapSpaceTotal;
    // the percentage of used swap space to total swap space available
    public Double swapUsedPerc;
    
    public Long heapMemory;
}
