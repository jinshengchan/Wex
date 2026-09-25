pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()

        maven("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/")

        maven {
            url = uri("file:///root/maven-mirror")
        }
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        // 官方 Maven Central 优先
        mavenCentral()

        // GitHub / JitPack 类依赖必须优先从 JitPack 获取
        maven("https://jitpack.io") {
            content {
                includeGroup("com.github.Ujhhgtg")
                includeGroup("com.github.Ujhhgtg.rhino")
                includeGroup("com.github.topjohnwu.libsu")
            }
        }

        // Xposed API
        maven("https://api.xposed.info/") {
            content {
                includeGroup("de.robv.android.xposed")
            }
        }

        // 腾讯镜像只作为后备
        maven("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/") {
            content {
                // MMKV 必须走 Maven Central
                excludeGroup("com.tencent.mmkv")

                // 这些 GitHub 依赖必须走 JitPack
                excludeGroup("com.github.Ujhhgtg")
                excludeGroup("com.github.Ujhhgtg.rhino")
                excludeGroup("com.github.topjohnwu.libsu")
            }
        }

        // 本地镜像最后尝试
        maven {
            url = uri("file:///root/maven-mirror")
        }
    }

    versionCatalogs {
        create("libs")
    }
}

rootProject.name = "wekit"

include(
    ":app",
    ":libs:common:annotation-scanner",
    ":libs:common:stubs",
    ":libs:common:bsh",
    ":libs:common:reflekt"
)
