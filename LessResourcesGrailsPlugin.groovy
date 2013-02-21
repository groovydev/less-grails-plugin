import org.grails.plugin.resource.CSSPreprocessorResourceMapper
import org.grails.plugin.resource.CSSRewriterResourceMapper

class LessResourcesGrailsPlugin {
    def version = "1.3.3.1"
    def grailsVersion = "2.0 > *"

    def author = "Karol Balejko"
    def authorEmail = "kb@groovydev.com"
    def organization = [ name: "GroovyDev", url: "http://groovydev.com/" ]
    def title = "Plugin LESS files resource mapper"
    def description = '''Provides LESS files resource mapper. Compile .less files into .css files. Less compiler based on less.js (LESS - Leaner CSS v1.3.0 http://lesscss.org)'''

    def documentation = "https://github.com/groovydev/less-grails-plugin/blob/master/README.md"
    def license = "APACHE"
    def issueManagement = [ system: "github", url: "https://github.com/groovydev/less-grails-plugin/issues" ]
    def scm = [ url: "https://github.com/groovydev/less-grails-plugin" ]

    def doWithSpring = {
        CSSPreprocessorResourceMapper.defaultIncludes.add('**/*.less')
        CSSRewriterResourceMapper.defaultIncludes.add('**/*.less')
    }
}
