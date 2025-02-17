plugins {
    base
}

tasks.named("check").configure {
    dependsOn(gradle.includedBuild("build-logic").task(":check"))
}
