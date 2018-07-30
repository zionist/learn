import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

import static ch.qos.logback.classic.Level.*

def logsRoot = 'logs'
def projectName = 'bitzlato-back'
//def logPattern = '%d [%t] %-5p %c{1} - %m%n'
def logPattern = '%t %cyan(%logger{15}) %highlight(%-5level): %d - %m%n'
//def logPattern = '%-5p - %m%n'

appender('CONSOLE', ConsoleAppender) {
    withJansi=true
    encoder(PatternLayoutEncoder) {
        pattern = logPattern
    }
}


appender('FILE', RollingFileAppender) {
    file = "${logsRoot}/${projectName}.log"
    encoder(PatternLayoutEncoder) {
        pattern = logPattern
    }
    rollingPolicy(FixedWindowRollingPolicy) {
        fileNamePattern = "${logsRoot}/${projectName}.%i.log.zip"
        minIndex = 1
        maxIndex = 10
    }
    triggeringPolicy(SizeBasedTriggeringPolicy) {
        maxFileSize = '50MB'
    }
}


logger('org.springframework', INFO)
logger('com.github.bitzlato.back', INFO)
logger('org.springframework.jdbc.datasource.DataSourceTransactionManager', INFO)
logger('org.jooq', DEBUG)
logger('org.springframework.aop.framework.CglibAopProxy', ERROR)
// logger('com.github.isrsal.logging.LoggingFilNjter', DEBUG)

root(INFO, ['CONSOLE'])
root(INFO, ['FILE'])
