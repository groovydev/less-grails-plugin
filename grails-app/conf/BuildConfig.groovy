grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()
    }
    plugins {
        runtime ":hibernate:$grailsVersion"
        build ":tomcat:$grailsVersion"

        runtime ":resources:1.2.RC2"
        build(":release:2.2.0", ":rest-client-builder:1.0.3") {
            export = false
        }

        test(':spock:0.7') {
            exclude "spock-grails-support"
        }
    }
    dependencies {
        runtime 'org.mozilla:rhino:1.7R4'

        test "org.spockframework:spock-grails-support:0.7-groovy-2.0"
    }
}
