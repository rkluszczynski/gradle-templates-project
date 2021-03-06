import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

import static ch.qos.logback.classic.Level.INFO

appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d %5p | %t | %-55logger{55} | %m %n"
    }
}

logger("pl.info.rkluszczynski.examples", INFO)
logger("org.springframework", INFO)

root(INFO, ["CONSOLE"])
