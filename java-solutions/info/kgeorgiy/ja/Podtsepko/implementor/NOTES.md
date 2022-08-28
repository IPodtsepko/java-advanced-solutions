commit b0acbaab0bc214ae89bc6017eb9c2259a6202cc1
Author: Igor Podcepko <i.podtsepko2002@gmail.com>
Date:   Thu Mar 24 15:30:40 2022 +0300

    Added HW-5 solution
==================================================
Compiling 2 Java sources
Tests: running
WARNING: A command line option has enabled the Security Manager
WARNING: The Security Manager is deprecated and will be removed in a future release
Running class info.kgeorgiy.java.advanced.implementor.InterfaceJarImplementorTest for info.kgeorgiy.ja.Podtsepko.implementor.Implementor
=== Running test01_constructor
=== Running test09_encoding
C:\Users\kWX1085047\java-advanced-private\test\__local\build\test09_encoding\out14458100028646812219\info\kgeorgiy\java\advanced\implementor\full\lang\??????InterfaceImpl.java:3: error: illegal character: '\u00b8'
class ПриветInterfaceImpl implements info.kgeorgiy.java.advanced.implementor.full.lang.ПриветInterface {
           ^
C:\Users\kWX1085047\java-advanced-private\test\__local\build\test09_encoding\out14458100028646812219\info\kgeorgiy\java\advanced\implementor\full\lang\??????InterfaceImpl.java:3: error: illegal character: '\u00b2'
class ПриветInterfaceImpl implements info.kgeorgiy.java.advanced.implementor.full.lang.ПриветInterface {
             ^
C:\Users\kWX1085047\java-advanced-private\test\__local\build\test09_encoding\out14458100028646812219\info\kgeorgiy\java\advanced\implementor\full\lang\??????InterfaceImpl.java:3: error: illegal character: '\u201a'
class ПриветInterfaceImpl implements info.kgeorgiy.java.advanced.implementor.full.lang.ПриветInterface {
                 ^
C:\Users\kWX1085047\java-advanced-private\test\__local\build\test09_encoding\out14458100028646812219\info\kgeorgiy\java\advanced\implementor\full\lang\??????InterfaceImpl.java:3: error: illegal character: '\u00b8'
class ПриветInterfaceImpl implements info.kgeorgiy.java.advanced.implementor.full.lang.ПриветInterface {
                                                                                                  ^
C:\Users\kWX1085047\java-advanced-private\test\__local\build\test09_encoding\out14458100028646812219\info\kgeorgiy\java\advanced\implementor\full\lang\??????InterfaceImpl.java:3: error: illegal character: '\u00b2'
class ПриветInterfaceImpl implements info.kgeorgiy.java.advanced.implementor.full.lang.ПриветInterface {
                                                                                                    ^
C:\Users\kWX1085047\java-advanced-private\test\__local\build\test09_encoding\out14458100028646812219\info\kgeorgiy\java\advanced\implementor\full\lang\??????InterfaceImpl.java:3: error: illegal character: '\u201a'
class ПриветInterfaceImpl implements info.kgeorgiy.java.advanced.implementor.full.lang.ПриветInterface {
                                                                                                        ^
6 errors
=== Running test03_standardInterfaces
C:\Users\kWX1085047\java-advanced-private\test\__local\build\test03_standardInterfaces\out4268402103271882376\info\kgeorgiy\java\advanced\implementor\basic\interfaces\standard\AccessibleImpl.java:3: error: cannot find symbol
class AccessibleImpl implements info.kgeorgiy.java.advanced.implementor.basic.interfaces.standard.Accessible {
                                                                                                 ^
  symbol:   class Accessible
  location: package info.kgeorgiy.java.advanced.implementor.basic.interfaces.standard
1 error
=== Running test07_duplicateClasses
C:\Users\kWX1085047\java-advanced-private\test\__local\build\test07_duplicateClasses\out4093460759751717670\info\kgeorgiy\java\advanced\implementor\full\interfaces\ProxiesImpl.java:3: error: cannot find symbol
class ProxiesImpl implements info.kgeorgiy.java.advanced.implementor.full.interfaces.Proxies {
                                                                                    ^
  symbol:   class Proxies
  location: package info.kgeorgiy.java.advanced.implementor.full.interfaces
1 error
=== Running test04_extendedInterfaces
C:\Users\kWX1085047\java-advanced-private\test\__local\build\test04_extendedInterfaces\out7105041687582475831\info\kgeorgiy\java\advanced\implementor\basic\interfaces\standard\DescriptorImpl.java:3: error: cannot find symbol
class DescriptorImpl implements info.kgeorgiy.java.advanced.implementor.basic.interfaces.standard.Descriptor {
                                                                                                 ^
  symbol:   class Descriptor
  location: package info.kgeorgiy.java.advanced.implementor.basic.interfaces.standard
1 error
=== Running test08_nestedInterfaces
C:\Users\kWX1085047\java-advanced-private\test\__local\build\test08_nestedInterfaces\out14845296997853017236\info\kgeorgiy\java\advanced\implementor\full\interfaces\PublicInterfaceImpl.java:3: error: package info.kgeorgiy.java.advanced.implementor.full.interfaces.Interfaces does not exist
class PublicInterfaceImpl implements info.kgeorgiy.java.advanced.implementor.full.interfaces.Interfaces.PublicInterface {
                                                                                                       ^
1 error
=== Running test06_java8Interfaces
C:\Users\kWX1085047\java-advanced-private\test\__local\build\test06_java8Interfaces\out17834407387777420320\info\kgeorgiy\java\advanced\implementor\basic\interfaces\InterfaceWithStaticMethodImpl.java:3: error: cannot find symbol
class InterfaceWithStaticMethodImpl implements info.kgeorgiy.java.advanced.implementor.basic.interfaces.InterfaceWithStaticMethod {
                                                                                                       ^
  symbol:   class InterfaceWithStaticMethod
  location: package info.kgeorgiy.java.advanced.implementor.basic.interfaces
1 error
=== Running test05_standardNonInterfaces
=== Running test02_methodlessInterfaces
C:\Users\kWX1085047\java-advanced-private\test\__local\build\test02_methodlessInterfaces\out6028521844785431348\info\kgeorgiy\java\advanced\implementor\basic\interfaces\standard\RandomAccessImpl.java:3: error: cannot find symbol
class RandomAccessImpl implements info.kgeorgiy.java.advanced.implementor.basic.interfaces.standard.RandomAccess {
                                                                                                   ^
  symbol:   class RandomAccess
  location: package info.kgeorgiy.java.advanced.implementor.basic.interfaces.standard
1 error
Test test09_encoding failed: Error implementing interface info.kgeorgiy.java.advanced.implementor.full.lang.??????Interface
java.lang.AssertionError: Error implementing interface info.kgeorgiy.java.advanced.implementor.full.lang.??????Interface
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.implement(BaseImplementorTest.java:98)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.test(BaseImplementorTest.java:154)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.test(BaseImplementorTest.java:176)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.InterfaceJarImplementorTest.test09_encoding(InterfaceJarImplementorTest.java:31)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at junit@4.11/org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:47)
	at junit@4.11/org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at junit@4.11/org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:44)
	at junit@4.11/org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at junit@4.11/org.junit.rules.TestWatcher$1.evaluate(TestWatcher.java:55)
	at junit@4.11/org.junit.rules.RunRules.evaluate(RunRules.java:20)
	at junit@4.11/org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:271)
	at junit@4.11/org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:70)
	at junit@4.11/org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:50)
	at junit@4.11/org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
	at junit@4.11/org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
	at junit@4.11/org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
	at junit@4.11/org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
	at junit@4.11/org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
	at junit@4.11/org.junit.runners.ParentRunner.run(ParentRunner.java:309)
	at junit@4.11/org.junit.runners.Suite.runChild(Suite.java:127)
	at junit@4.11/org.junit.runners.Suite.runChild(Suite.java:26)
	at junit@4.11/org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
	at junit@4.11/org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
	at junit@4.11/org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
	at junit@4.11/org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
	at junit@4.11/org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
	at junit@4.11/org.junit.runners.ParentRunner.run(ParentRunner.java:309)
	at junit@4.11/org.junit.runner.JUnitCore.run(JUnitCore.java:160)
	at junit@4.11/org.junit.runner.JUnitCore.run(JUnitCore.java:138)
	at junit@4.11/org.junit.runner.JUnitCore.run(JUnitCore.java:117)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.test(BaseTester.java:55)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.lambda$add$0(BaseTester.java:95)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.test(BaseTester.java:48)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.run(BaseTester.java:39)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.Tester.main(Tester.java:21)
Caused by: info.kgeorgiy.java.advanced.implementor.ImplerException: Compilation error
	at info.kgeorgiy.ja.Podtsepko.implementor.Implementor.compile(Implementor.java:237)
	at info.kgeorgiy.ja.Podtsepko.implementor.Implementor.implementJar(Implementor.java:213)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.InterfaceJarImplementorTest.implementJar(InterfaceJarImplementorTest.java:42)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.InterfaceJarImplementorTest.implement(InterfaceJarImplementorTest.java:37)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.implement(BaseImplementorTest.java:91)
	... 38 more
Test test03_standardInterfaces failed: Error implementing interface info.kgeorgiy.java.advanced.implementor.basic.interfaces.standard.Accessible
java.lang.AssertionError: Error implementing interface info.kgeorgiy.java.advanced.implementor.basic.interfaces.standard.Accessible
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.implement(BaseImplementorTest.java:98)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.test(BaseImplementorTest.java:154)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.test(BaseImplementorTest.java:176)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.InterfaceImplementorTest.test03_standardInterfaces(InterfaceImplementorTest.java:38)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at junit@4.11/org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:47)
	at junit@4.11/org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at junit@4.11/org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:44)
	at junit@4.11/org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at junit@4.11/org.junit.rules.TestWatcher$1.evaluate(TestWatcher.java:55)
	at junit@4.11/org.junit.rules.RunRules.evaluate(RunRules.java:20)
	at junit@4.11/org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:271)
	at junit@4.11/org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:70)
	at junit@4.11/org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:50)
	at junit@4.11/org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
	at junit@4.11/org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
	at junit@4.11/org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
	at junit@4.11/org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
	at junit@4.11/org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
	at junit@4.11/org.junit.runners.ParentRunner.run(ParentRunner.java:309)
	at junit@4.11/org.junit.runners.Suite.runChild(Suite.java:127)
	at junit@4.11/org.junit.runners.Suite.runChild(Suite.java:26)
	at junit@4.11/org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
	at junit@4.11/org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
	at junit@4.11/org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
	at junit@4.11/org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
	at junit@4.11/org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
	at junit@4.11/org.junit.runners.ParentRunner.run(ParentRunner.java:309)
	at junit@4.11/org.junit.runner.JUnitCore.run(JUnitCore.java:160)
	at junit@4.11/org.junit.runner.JUnitCore.run(JUnitCore.java:138)
	at junit@4.11/org.junit.runner.JUnitCore.run(JUnitCore.java:117)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.test(BaseTester.java:55)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.lambda$add$0(BaseTester.java:95)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.test(BaseTester.java:48)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.run(BaseTester.java:39)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.Tester.main(Tester.java:21)
Caused by: info.kgeorgiy.java.advanced.implementor.ImplerException: Compilation error
	at info.kgeorgiy.ja.Podtsepko.implementor.Implementor.compile(Implementor.java:237)
	at info.kgeorgiy.ja.Podtsepko.implementor.Implementor.implementJar(Implementor.java:213)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.InterfaceJarImplementorTest.implementJar(InterfaceJarImplementorTest.java:42)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.InterfaceJarImplementorTest.implement(InterfaceJarImplementorTest.java:37)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.implement(BaseImplementorTest.java:91)
	... 38 more
Test test07_duplicateClasses failed: Error implementing interface info.kgeorgiy.java.advanced.implementor.full.interfaces.Proxies
java.lang.AssertionError: Error implementing interface info.kgeorgiy.java.advanced.implementor.full.interfaces.Proxies
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.implement(BaseImplementorTest.java:98)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.test(BaseImplementorTest.java:154)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.test(BaseImplementorTest.java:176)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.InterfaceImplementorTest.test07_duplicateClasses(InterfaceImplementorTest.java:58)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at junit@4.11/org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:47)
	at junit@4.11/org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at junit@4.11/org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:44)
	at junit@4.11/org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at junit@4.11/org.junit.rules.TestWatcher$1.evaluate(TestWatcher.java:55)
	at junit@4.11/org.junit.rules.RunRules.evaluate(RunRules.java:20)
	at junit@4.11/org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:271)
	at junit@4.11/org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:70)
	at junit@4.11/org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:50)
	at junit@4.11/org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
	at junit@4.11/org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
	at junit@4.11/org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
	at junit@4.11/org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
	at junit@4.11/org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
	at junit@4.11/org.junit.runners.ParentRunner.run(ParentRunner.java:309)
	at junit@4.11/org.junit.runners.Suite.runChild(Suite.java:127)
	at junit@4.11/org.junit.runners.Suite.runChild(Suite.java:26)
	at junit@4.11/org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
	at junit@4.11/org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
	at junit@4.11/org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
	at junit@4.11/org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
	at junit@4.11/org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
	at junit@4.11/org.junit.runners.ParentRunner.run(ParentRunner.java:309)
	at junit@4.11/org.junit.runner.JUnitCore.run(JUnitCore.java:160)
	at junit@4.11/org.junit.runner.JUnitCore.run(JUnitCore.java:138)
	at junit@4.11/org.junit.runner.JUnitCore.run(JUnitCore.java:117)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.test(BaseTester.java:55)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.lambda$add$0(BaseTester.java:95)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.test(BaseTester.java:48)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.run(BaseTester.java:39)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.Tester.main(Tester.java:21)
Caused by: info.kgeorgiy.java.advanced.implementor.ImplerException: Compilation error
	at info.kgeorgiy.ja.Podtsepko.implementor.Implementor.compile(Implementor.java:237)
	at info.kgeorgiy.ja.Podtsepko.implementor.Implementor.implementJar(Implementor.java:213)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.InterfaceJarImplementorTest.implementJar(InterfaceJarImplementorTest.java:42)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.InterfaceJarImplementorTest.implement(InterfaceJarImplementorTest.java:37)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.implement(BaseImplementorTest.java:91)
	... 38 more
Test test04_extendedInterfaces failed: Error implementing interface info.kgeorgiy.java.advanced.implementor.basic.interfaces.standard.Descriptor
java.lang.AssertionError: Error implementing interface info.kgeorgiy.java.advanced.implementor.basic.interfaces.standard.Descriptor
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.implement(BaseImplementorTest.java:98)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.test(BaseImplementorTest.java:154)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.test(BaseImplementorTest.java:176)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.InterfaceImplementorTest.test04_extendedInterfaces(InterfaceImplementorTest.java:43)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at junit@4.11/org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:47)
	at junit@4.11/org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at junit@4.11/org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:44)
	at junit@4.11/org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at junit@4.11/org.junit.rules.TestWatcher$1.evaluate(TestWatcher.java:55)
	at junit@4.11/org.junit.rules.RunRules.evaluate(RunRules.java:20)
	at junit@4.11/org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:271)
	at junit@4.11/org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:70)
	at junit@4.11/org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:50)
	at junit@4.11/org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
	at junit@4.11/org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
	at junit@4.11/org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
	at junit@4.11/org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
	at junit@4.11/org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
	at junit@4.11/org.junit.runners.ParentRunner.run(ParentRunner.java:309)
	at junit@4.11/org.junit.runners.Suite.runChild(Suite.java:127)
	at junit@4.11/org.junit.runners.Suite.runChild(Suite.java:26)
	at junit@4.11/org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
	at junit@4.11/org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
	at junit@4.11/org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
	at junit@4.11/org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
	at junit@4.11/org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
	at junit@4.11/org.junit.runners.ParentRunner.run(ParentRunner.java:309)
	at junit@4.11/org.junit.runner.JUnitCore.run(JUnitCore.java:160)
	at junit@4.11/org.junit.runner.JUnitCore.run(JUnitCore.java:138)
	at junit@4.11/org.junit.runner.JUnitCore.run(JUnitCore.java:117)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.test(BaseTester.java:55)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.lambda$add$0(BaseTester.java:95)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.test(BaseTester.java:48)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.run(BaseTester.java:39)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.Tester.main(Tester.java:21)
Caused by: info.kgeorgiy.java.advanced.implementor.ImplerException: Compilation error
	at info.kgeorgiy.ja.Podtsepko.implementor.Implementor.compile(Implementor.java:237)
	at info.kgeorgiy.ja.Podtsepko.implementor.Implementor.implementJar(Implementor.java:213)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.InterfaceJarImplementorTest.implementJar(InterfaceJarImplementorTest.java:42)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.InterfaceJarImplementorTest.implement(InterfaceJarImplementorTest.java:37)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.implement(BaseImplementorTest.java:91)
	... 38 more
Test test08_nestedInterfaces failed: Error implementing interface info.kgeorgiy.java.advanced.implementor.full.interfaces.Interfaces$PublicInterface
java.lang.AssertionError: Error implementing interface info.kgeorgiy.java.advanced.implementor.full.interfaces.Interfaces$PublicInterface
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.implement(BaseImplementorTest.java:98)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.test(BaseImplementorTest.java:154)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.test(BaseImplementorTest.java:176)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.InterfaceImplementorTest.test08_nestedInterfaces(InterfaceImplementorTest.java:63)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at junit@4.11/org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:47)
	at junit@4.11/org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at junit@4.11/org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:44)
	at junit@4.11/org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at junit@4.11/org.junit.rules.TestWatcher$1.evaluate(TestWatcher.java:55)
	at junit@4.11/org.junit.rules.RunRules.evaluate(RunRules.java:20)
	at junit@4.11/org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:271)
	at junit@4.11/org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:70)
	at junit@4.11/org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:50)
	at junit@4.11/org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
	at junit@4.11/org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
	at junit@4.11/org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
	at junit@4.11/org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
	at junit@4.11/org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
	at junit@4.11/org.junit.runners.ParentRunner.run(ParentRunner.java:309)
	at junit@4.11/org.junit.runners.Suite.runChild(Suite.java:127)
	at junit@4.11/org.junit.runners.Suite.runChild(Suite.java:26)
	at junit@4.11/org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
	at junit@4.11/org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
	at junit@4.11/org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
	at junit@4.11/org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
	at junit@4.11/org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
	at junit@4.11/org.junit.runners.ParentRunner.run(ParentRunner.java:309)
	at junit@4.11/org.junit.runner.JUnitCore.run(JUnitCore.java:160)
	at junit@4.11/org.junit.runner.JUnitCore.run(JUnitCore.java:138)
	at junit@4.11/org.junit.runner.JUnitCore.run(JUnitCore.java:117)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.test(BaseTester.java:55)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.lambda$add$0(BaseTester.java:95)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.test(BaseTester.java:48)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.run(BaseTester.java:39)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.Tester.main(Tester.java:21)
Caused by: info.kgeorgiy.java.advanced.implementor.ImplerException: Compilation error
	at info.kgeorgiy.ja.Podtsepko.implementor.Implementor.compile(Implementor.java:237)
	at info.kgeorgiy.ja.Podtsepko.implementor.Implementor.implementJar(Implementor.java:213)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.InterfaceJarImplementorTest.implementJar(InterfaceJarImplementorTest.java:42)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.InterfaceJarImplementorTest.implement(InterfaceJarImplementorTest.java:37)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.implement(BaseImplementorTest.java:91)
	... 38 more
Test test06_java8Interfaces failed: Error implementing interface info.kgeorgiy.java.advanced.implementor.basic.interfaces.InterfaceWithStaticMethod
java.lang.AssertionError: Error implementing interface info.kgeorgiy.java.advanced.implementor.basic.interfaces.InterfaceWithStaticMethod
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.implement(BaseImplementorTest.java:98)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.test(BaseImplementorTest.java:154)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.test(BaseImplementorTest.java:176)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.InterfaceImplementorTest.test06_java8Interfaces(InterfaceImplementorTest.java:53)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at junit@4.11/org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:47)
	at junit@4.11/org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at junit@4.11/org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:44)
	at junit@4.11/org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at junit@4.11/org.junit.rules.TestWatcher$1.evaluate(TestWatcher.java:55)
	at junit@4.11/org.junit.rules.RunRules.evaluate(RunRules.java:20)
	at junit@4.11/org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:271)
	at junit@4.11/org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:70)
	at junit@4.11/org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:50)
	at junit@4.11/org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
	at junit@4.11/org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
	at junit@4.11/org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
	at junit@4.11/org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
	at junit@4.11/org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
	at junit@4.11/org.junit.runners.ParentRunner.run(ParentRunner.java:309)
	at junit@4.11/org.junit.runners.Suite.runChild(Suite.java:127)
	at junit@4.11/org.junit.runners.Suite.runChild(Suite.java:26)
	at junit@4.11/org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
	at junit@4.11/org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
	at junit@4.11/org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
	at junit@4.11/org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
	at junit@4.11/org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
	at junit@4.11/org.junit.runners.ParentRunner.run(ParentRunner.java:309)
	at junit@4.11/org.junit.runner.JUnitCore.run(JUnitCore.java:160)
	at junit@4.11/org.junit.runner.JUnitCore.run(JUnitCore.java:138)
	at junit@4.11/org.junit.runner.JUnitCore.run(JUnitCore.java:117)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.test(BaseTester.java:55)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.lambda$add$0(BaseTester.java:95)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.test(BaseTester.java:48)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.run(BaseTester.java:39)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.Tester.main(Tester.java:21)
Caused by: info.kgeorgiy.java.advanced.implementor.ImplerException: Compilation error
	at info.kgeorgiy.ja.Podtsepko.implementor.Implementor.compile(Implementor.java:237)
	at info.kgeorgiy.ja.Podtsepko.implementor.Implementor.implementJar(Implementor.java:213)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.InterfaceJarImplementorTest.implementJar(InterfaceJarImplementorTest.java:42)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.InterfaceJarImplementorTest.implement(InterfaceJarImplementorTest.java:37)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.implement(BaseImplementorTest.java:91)
	... 38 more
Test test02_methodlessInterfaces failed: Error implementing interface info.kgeorgiy.java.advanced.implementor.basic.interfaces.standard.RandomAccess
java.lang.AssertionError: Error implementing interface info.kgeorgiy.java.advanced.implementor.basic.interfaces.standard.RandomAccess
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.implement(BaseImplementorTest.java:98)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.test(BaseImplementorTest.java:154)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.test(BaseImplementorTest.java:176)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.InterfaceImplementorTest.test02_methodlessInterfaces(InterfaceImplementorTest.java:33)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at junit@4.11/org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:47)
	at junit@4.11/org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at junit@4.11/org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:44)
	at junit@4.11/org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at junit@4.11/org.junit.rules.TestWatcher$1.evaluate(TestWatcher.java:55)
	at junit@4.11/org.junit.rules.RunRules.evaluate(RunRules.java:20)
	at junit@4.11/org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:271)
	at junit@4.11/org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:70)
	at junit@4.11/org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:50)
	at junit@4.11/org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
	at junit@4.11/org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
	at junit@4.11/org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
	at junit@4.11/org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
	at junit@4.11/org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
	at junit@4.11/org.junit.runners.ParentRunner.run(ParentRunner.java:309)
	at junit@4.11/org.junit.runners.Suite.runChild(Suite.java:127)
	at junit@4.11/org.junit.runners.Suite.runChild(Suite.java:26)
	at junit@4.11/org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
	at junit@4.11/org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
	at junit@4.11/org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
	at junit@4.11/org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
	at junit@4.11/org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
	at junit@4.11/org.junit.runners.ParentRunner.run(ParentRunner.java:309)
	at junit@4.11/org.junit.runner.JUnitCore.run(JUnitCore.java:160)
	at junit@4.11/org.junit.runner.JUnitCore.run(JUnitCore.java:138)
	at junit@4.11/org.junit.runner.JUnitCore.run(JUnitCore.java:117)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.test(BaseTester.java:55)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.lambda$add$0(BaseTester.java:95)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.test(BaseTester.java:48)
	at info.kgeorgiy.java.advanced.base/info.kgeorgiy.java.advanced.base.BaseTester.run(BaseTester.java:39)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.Tester.main(Tester.java:21)
Caused by: info.kgeorgiy.java.advanced.implementor.ImplerException: Compilation error
	at info.kgeorgiy.ja.Podtsepko.implementor.Implementor.compile(Implementor.java:237)
	at info.kgeorgiy.ja.Podtsepko.implementor.Implementor.implementJar(Implementor.java:213)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.InterfaceJarImplementorTest.implementJar(InterfaceJarImplementorTest.java:42)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.InterfaceJarImplementorTest.implement(InterfaceJarImplementorTest.java:37)
	at info.kgeorgiy.java.advanced.implementor/info.kgeorgiy.java.advanced.implementor.BaseImplementorTest.implement(BaseImplementorTest.java:91)
	... 38 more
ERROR: Tests: failed
