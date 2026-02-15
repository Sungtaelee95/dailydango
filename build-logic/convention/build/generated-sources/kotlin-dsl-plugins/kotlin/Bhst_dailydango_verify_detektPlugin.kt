/**
 * Precompiled [bhst.dailydango.verify.detekt.gradle.kts][Bhst_dailydango_verify_detekt_gradle] script plugin.
 *
 * @see Bhst_dailydango_verify_detekt_gradle
 */
public
class Bhst_dailydango_verify_detektPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("Bhst_dailydango_verify_detekt_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
