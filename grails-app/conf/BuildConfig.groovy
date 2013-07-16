grails.project.work.dir = 'target'

grails.project.dependency.resolution = {

    inherits 'global'
    log 'warn'

    repositories {
        grailsCentral()
        mavenLocal()
        mavenCentral()
    }

    dependencies {
        runtime 'org.mozilla:rhino:1.7R4'

        test("org.spockframework:spock-grails-support:0.7-groovy-2.0") {
            export = false
        }
    }

    plugins {
        runtime ":hibernate:$grailsVersion", {
            export = false
        }

        runtime ":resources:1.2"
        build(":release:2.2.0", ":rest-client-builder:1.0.3") {
            export = false
        }

        test(':spock:0.7') {
            exclude "spock-grails-support"
            export = false
        }
    }
}
