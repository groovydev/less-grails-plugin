package com.groovydev

import org.mozilla.javascript.ContextFactory
import org.mozilla.javascript.Context
import org.mozilla.javascript.tools.shell.Global
import org.mozilla.javascript.ScriptableObject
import org.mozilla.javascript.tools.shell.Main

class LessCompilerService {

    def grailsApplication

    private String loadResource(name) {
        grailsApplication.parentContext.getResource("classpath:${name}").file.text
    }

    def compile(source, target, paths = []) {

        Context cx = Context.enter()
        cx.setOptimizationLevel(-1)
        cx.setLanguageVersion(Context.VERSION_1_5)

        try {
            ScriptableObject scope = cx.initStandardObjects()

            def pathstext = paths.collect{
                def p = it.replaceAll("\\\\", "/")
                if (p.endsWith("/")) {
                    "'${p}'"
                } else {
                    "'${p}/'"
                }
            }.toString()

            def script = new StringBuilder()
            script.append(loadResource('shell.js'))
            script.append(loadResource('env.rhino.js'))
            script.append(loadResource('hooks.js'))
            script.append(loadResource('less-1.3.3.js'))
            script.append(loadResource('compile.js'))

            def sourceFileName = source.absolutePath.replaceAll("\\\\", "/");
            script.append("compile('${sourceFileName}', ${pathstext});" as String)

            if(log.debugEnabled) {
                def f = File.createTempFile("lessCompilerService", ".js")
                f.text = script.toString()
            }

            def result = cx.evaluateString(scope, script.toString(), "<script>", 1, null)
            def css = cx.toString(result)
            target.text = css
        } finally {
            Context.exit()
        }
    }

}
