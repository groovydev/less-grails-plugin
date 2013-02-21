package com.groovydev

import grails.plugin.spock.IntegrationSpec
import spock.lang.Unroll

class LessCompilerServiceSpec extends IntegrationSpec {

    def grailsApplication
    def lessCompilerService

    @Unroll
    def "compile #srcLessFile into CSS"() {
        when:
        def tmpTarget = File.createTempFile("LessCompilerServiceSpec", ".css")
        tmpTarget.deleteOnExit()
        def srcFile = grailsApplication.parentContext.getResource(srcLessFile)?.file
        def modelFile = grailsApplication.parentContext.getResource(modelCssFile)?.file
        def importPath = srcFile?.parentFile?.absolutePath
        lessCompilerService.compile(srcFile, tmpTarget, [importPath])

        then:
        tmpTarget.text.equals(modelFile.text)

        where:
        srcLessFile                           | modelCssFile
        "css-only.less"                       | "css-only.css"
        "bootstrap-2.2.2/less/bootstrap.less" | "bootstrap-2.2.2/css/bootstrap.css"
        "bootstrap-2.3.0/less/bootstrap.less" | "bootstrap-2.3.0/css/bootstrap.css"
    }
}
