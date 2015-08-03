package com.ttc.ch2.audit;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.util.Date;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

public class MetricsManager {
    private static Logger logger = LoggerFactory.getLogger(MetricsManager.class);

    private static final InetAddress addr = getInetAddress();
    private final static long MB = 1024 * 1024;

    private MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
    
    private String appNode;

    private MetricsAppender appender;
    
    @Scheduled(fixedRate=5000)
    public void saveMetrics() {
        Metric m = null;
        try {
            m = getMetric();
            appender.store(m);
        } catch (Throwable ignored) {
            if (isVerbose()) {
                logger.warn("", ignored);
                if(m != null) {
                    logger.warn("{}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}", 
                            m.hostAddr,
                            m.timestamp,
                            m.threadCount,
                            m.uptime,
                            m.processCpuLoad,
                            m.systemCpuLoad,
                            m.memPhysicalFree,
                            m.memPhysicalTotal,
                            m.memPhysicalUsedPerc,
                            m.memVirtualCommitted,
                            m.memVirtualCommitedUsedPerc,
                            m.swapSpaceFree,
                            m.swapSpaceTotal,
                            m.swapUsedPerc,
                            m.heapMemory);
                }

            }
        }
    }
    
    protected boolean isVerbose() {
        return true;
    }

    protected Metric getMetric() {
        Metric m = new Metric();
        if (StringUtils.isNotBlank(appNode)) {
            m.hostAddr = appNode;
        } else {
            m.hostAddr =  addr.getHostName();            
        }
        
        m.timestamp = new Date();

        // ManagementFactory.getThreadMXBean()...
        m.threadCount = getJmxAttributeLong("java.lang:type=Threading", "ThreadCount");

        m.uptime = getJmxAttributeLong("java.lang:type=Runtime", "Uptime");

        // ManagementFactory.getOperatingSystemMXBean()...
        m.processCpuLoad = getJmxAttribute("java.lang:type=OperatingSystem", "ProcessCpuLoad");
        m.systemCpuLoad = getJmxAttribute("java.lang:type=OperatingSystem", "SystemCpuLoad");

        // ManagementFactory.getMemoryManagerMXBeans()...
        m.memPhysicalTotal = getJmxAttribute("java.lang:type=OperatingSystem", "TotalPhysicalMemorySize", MB);
        m.memPhysicalFree = getJmxAttribute("java.lang:type=OperatingSystem", "FreePhysicalMemorySize", MB);
        if (m.memPhysicalTotal != null  &&  m.memPhysicalFree != null && m.memPhysicalTotal != 0.0) {
            m.memPhysicalUsedPerc =  (m.memPhysicalTotal - m.memPhysicalFree) / m.memPhysicalTotal * 100;            
        } else {
            m.memPhysicalUsedPerc = null;
        }

        // VIRT -- Virtual Image (kb) The total amount of virtual memory used by
        // the
        // task. It includes all code, data and shared libraries plus pages that
        // have been swapped out and pages that have been mapped but not used.
        m.memVirtualCommitted = getJmxAttribute("java.lang:type=OperatingSystem", "CommittedVirtualMemorySize", MB);
        if (m.memVirtualCommitted != null && m.memPhysicalTotal != null && m.memPhysicalTotal != 0.0) {
            m.memVirtualCommitedUsedPerc = m.memVirtualCommitted / m.memPhysicalTotal * 100; // XXX
        } else {
            m.memVirtualCommitedUsedPerc = null;
        }
        

        // SWAP -- Swapped size (kb) Memory that is not resident but is present
        // in a
        // task. This is memory that has been swapped out but could include
        // additional non- resident memory. This column is calculated by
        // subtracting
        // physical memory from virtual memory
        m.swapSpaceTotal = getJmxAttribute("java.lang:type=OperatingSystem", "TotalSwapSpaceSize", MB);
        m.swapSpaceFree = getJmxAttribute("java.lang:type=OperatingSystem", "FreeSwapSpaceSize", MB);
        if (m.swapSpaceTotal != null && m.swapSpaceFree != null && m.swapSpaceTotal != 0.0) {
            m.swapUsedPerc = (m.swapSpaceTotal - m.swapSpaceFree) / m.swapSpaceTotal * 100;
        } else {
            m.swapUsedPerc = null;
        }
        
        m.heapMemory = getJmxAttributeLong("java.lang:type=Memory", "HeapMemoryUsage", "used", MB);
        
        checkMetrics(m);
        
        return m;
    }


    private void checkMetrics(Metric m) {
        /*
         * ignored hostAddr nad timestamp
         */
        if (!isValid(m.threadCount)) {
            if (isVerbose()) {
                logger.warn("threadCount {}",  m.threadCount);
            }
            m.threadCount = null;
        }

        if (!isValid(m.uptime)) {
            if (isVerbose()) {
                logger.warn("uptime {}",  m.uptime);
            }
            m.uptime = null;
        }

        if (!isValid(m.processCpuLoad)) {
            if (isVerbose()) {
                logger.warn("processCpuLoad {}",  m.processCpuLoad);
            }
            m.processCpuLoad = null;
        }

        if (!isValid(m.systemCpuLoad)) {
            if (isVerbose()) {
                logger.warn("systemCpuLoad {}",  m.systemCpuLoad);
            }
            m.systemCpuLoad = null;
        }

        if (!isValid(m.memPhysicalFree)) {
            if (isVerbose()) {
                logger.warn("memPhysicalFree {}",  m.memPhysicalFree);
            }
            m.memPhysicalFree = null;
        }

        if (!isValid(m.memPhysicalTotal)) {
            if (isVerbose()) {
                logger.warn("memPhysicalTotal {}",  m.memPhysicalTotal);
            }
            m.memPhysicalTotal = null;
        }

        if (!isValid(m.memPhysicalUsedPerc)) {
            if (isVerbose()) {
                logger.warn("memPhysicalUsedPerc {}",  m.memPhysicalUsedPerc);
            }
            m.memPhysicalUsedPerc = null;
        }

        if (!isValid(m.memVirtualCommitted)) {
            if (isVerbose()) {
                logger.warn("memVirtualCommitted {}",  m.memVirtualCommitted);
            }
            m.memVirtualCommitted =  null;
        }

        if (!isValid(m.memVirtualCommitedUsedPerc)) {
            if (isVerbose()) {
                logger.warn("memVirtualCommitedUsedPerc {}",  m.memVirtualCommitedUsedPerc);
            }
            m.memVirtualCommitedUsedPerc = null;
        }

        if (!isValid(m.swapSpaceFree)) {
            if (isVerbose()) {
                logger.warn("swapSpaceFree {}",  m.swapSpaceFree);
            }
            m.swapSpaceFree = null;
        }

        if (!isValid(m.swapSpaceTotal)) {
            if (isVerbose()) {
                logger.warn("swapSpaceTotal {}",  m.swapSpaceTotal);
            }
            m.swapSpaceTotal = null;
        }

        if (!isValid(m.swapUsedPerc)) {
            if (isVerbose()) {
                logger.warn("swapUsedPerc {}",  m.swapUsedPerc);
            }
            m.swapUsedPerc = null;
        }

        if (!isValid(m.heapMemory)) {
            if (isVerbose()) {
                logger.warn("heapMemory {}",  m.heapMemory);
            }
            m.heapMemory = null;
        }
    }

    private boolean isValid(Double number) {
        if (number == null) {
            return true;
        }
        if (number.isNaN()) {
            return false;
        }
        if (number.isInfinite()) {
            return false;
        }
        return true;
    }

    private boolean isValid(Long number) {
        if (number == null) {
            return true;
        }
        
        return true;
    }

    public Double getJmxAttribute(String objectName, String attributeName) {/**/
        return getJmxAttribute(objectName, attributeName, 1L);
    }
    
    public Double getJmxAttribute(String objectName, String attributeName, long divisor) {/***/
        try {
            ObjectName on = new ObjectName(objectName);
            Number n = (Number) mbs.getAttribute(on, attributeName);

            if (canReplaceToNull(n)) {
                return null;
            }
            
            return n.doubleValue() / divisor;

        } catch (Throwable ignored) {
            if (isVerbose()) {
                logger.warn("", ignored);
            }
        }
        return null;
    }
    
    public Long getJmxAttributeLong(String objectName, String attributeName) {/**/
        try {
            ObjectName on = new ObjectName(objectName);
            Number n = (Number) mbs.getAttribute(on, attributeName);
            
            if (canReplaceToNull(n)) {
                return null;
            }
            
            return n.longValue();
            
        } catch (Throwable ignored) {
            if (isVerbose()) {
                logger.warn("", ignored);
            }
        }
        return null;
    }

    public  Long getJmxAttributeLong(String objectName, String attributeName, String name, long divisor) {/**/
        try {
            ObjectName on = new ObjectName(objectName);
            Object n =  mbs.getAttribute(on, attributeName);
            
            if (n instanceof CompositeData) {
                CompositeData compositeData = (CompositeData) n;
                Object object = compositeData.get(name);
                if (canReplaceToNull(object)) {
                    return null;
                }
                return NumberUtils.toLong(ObjectUtils.toString(object)) / divisor;
            }
            
            
        } catch (Throwable ignored) {
            if (isVerbose()) {
                logger.warn("", ignored);
            }
        }
        return null;
    }

    private boolean canReplaceToNull(Object n) {
        
        if (n instanceof Double && ((Double) n).isNaN()) {
            return true;
        }
        
        if (n instanceof Float && ((Float) n).isNaN() ) {
            return true;
        }
        
        return false;
        
    }
    
    
    
    private static InetAddress getInetAddress() {
        try {
            return InetAddress.getLocalHost();
        } catch (Throwable ignored) {
        }
        return null;
    }

    public String getAppNode() {
        return appNode;
    }

    public void setAppNode(String appNode) {
        this.appNode = appNode;
    }

    public MetricsAppender getAppender() {
        return appender;
    }

    public void setAppender(MetricsAppender appender) {
        this.appender = appender;
    }

}
