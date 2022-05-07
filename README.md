# Quarkus GraphQL Void Return Reproducer

A reproducer for GraphQL exception when returning Void from a mutation.

Used in discussion [https://github.com/quarkusio/quarkus/discussions/25381](https://github.com/quarkusio/quarkus/discussions/25381) and in issue [https://github.com/smallrye/smallrye-graphql/issues/1383](https://github.com/smallrye/smallrye-graphql/issues/1383).

## Steps to reproduce

1. Just run the application and the exception will be thrown.

```
$ mvn quarkus:dev
```

2. The problematic mutation is `createHero` in file [FilmResource.java](src/main/java/org/acme/microprofile/graphql/FilmResource.java).

## Exception stacktrace

```
ERROR [io.qua.run.boo.StartupActionImpl] (Quarkus Main Thread) Error running Quarkus: java.lang.reflect.InvocationTargetException
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:566)
        at io.quarkus.runner.bootstrap.StartupActionImpl$1.run(StartupActionImpl.java:103)
        at java.base/java.lang.Thread.run(Thread.java:829)
Caused by: java.lang.ExceptionInInitializerError
        at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
        at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
        at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:490)
        at java.base/java.lang.Class.newInstance(Class.java:584)
        at io.quarkus.runtime.Quarkus.run(Quarkus.java:66)
        at io.quarkus.runtime.Quarkus.run(Quarkus.java:41)
        at io.quarkus.runtime.Quarkus.run(Quarkus.java:120)
        at io.quarkus.runner.GeneratedMain.main(Unknown Source)
        ... 6 more
Caused by: java.lang.RuntimeException: Failed to start quarkus
        at io.quarkus.runner.ApplicationImpl.<clinit>(Unknown Source)
        ... 15 more
Caused by: graphql.AssertException: type Void not found in schema
        at graphql.Assert.assertNotNull(Assert.java:17)
        at graphql.schema.GraphQLTypeResolvingVisitor.handleTypeReference(GraphQLTypeResolvingVisitor.java:49)
        at graphql.schema.GraphQLTypeResolvingVisitor.visitGraphQLTypeReference(GraphQLTypeResolvingVisitor.java:44)
        at graphql.schema.GraphQLTypeReference.accept(GraphQLTypeReference.java:62)
        at graphql.schema.SchemaTraverser$TraverserDelegateVisitor.enter(SchemaTraverser.java:108)
        at graphql.util.Traverser.traverse(Traverser.java:144)
        at graphql.schema.SchemaTraverser.doTraverse(SchemaTraverser.java:95)
        at graphql.schema.SchemaTraverser.depthFirst(SchemaTraverser.java:85)
        at graphql.schema.SchemaTraverser.depthFirst(SchemaTraverser.java:78)
        at graphql.schema.impl.SchemaUtil.replaceTypeReferences(SchemaUtil.java:97)
        at graphql.schema.GraphQLSchema$Builder.buildImpl(GraphQLSchema.java:928)
        at graphql.schema.GraphQLSchema$Builder.build(GraphQLSchema.java:894)
        at io.smallrye.graphql.bootstrap.Bootstrap.generateGraphQLSchema(Bootstrap.java:186)
        at io.smallrye.graphql.bootstrap.Bootstrap.bootstrap(Bootstrap.java:113)
        at io.smallrye.graphql.bootstrap.Bootstrap.bootstrap(Bootstrap.java:107)
        at io.smallrye.graphql.cdi.producer.GraphQLProducer.initialize(GraphQLProducer.java:50)
        at io.smallrye.graphql.cdi.producer.GraphQLProducer_Subclass.initialize$$superforward1(Unknown Source)
        at io.smallrye.graphql.cdi.producer.GraphQLProducer_Subclass$$function$$3.apply(Unknown Source)
        at io.quarkus.arc.impl.AroundInvokeInvocationContext.proceed(AroundInvokeInvocationContext.java:54)
        at io.quarkus.arc.runtime.devconsole.InvocationInterceptor.proceed(InvocationInterceptor.java:62)
        at io.quarkus.arc.runtime.devconsole.InvocationInterceptor.monitor(InvocationInterceptor.java:51)
        at io.quarkus.arc.runtime.devconsole.InvocationInterceptor_Bean.intercept(Unknown Source)
        at io.quarkus.arc.impl.InterceptorInvocation.invoke(InterceptorInvocation.java:41)
        at io.quarkus.arc.impl.AroundInvokeInvocationContext.perform(AroundInvokeInvocationContext.java:41)
        at io.quarkus.arc.impl.InvocationContexts.performAroundInvoke(InvocationContexts.java:32)
        at io.smallrye.graphql.cdi.producer.GraphQLProducer_Subclass.initialize(Unknown Source)
        at io.smallrye.graphql.cdi.producer.GraphQLProducer.initialize(GraphQLProducer.java:40)
        at io.smallrye.graphql.cdi.producer.GraphQLProducer_Subclass.initialize$$superforward1(Unknown Source)
        at io.smallrye.graphql.cdi.producer.GraphQLProducer_Subclass$$function$$7.apply(Unknown Source)
        at io.quarkus.arc.impl.AroundInvokeInvocationContext.proceed(AroundInvokeInvocationContext.java:54)
        at io.quarkus.arc.runtime.devconsole.InvocationInterceptor.proceed(InvocationInterceptor.java:62)
        at io.quarkus.arc.runtime.devconsole.InvocationInterceptor.monitor(InvocationInterceptor.java:51)
        at io.quarkus.arc.runtime.devconsole.InvocationInterceptor_Bean.intercept(Unknown Source)
        at io.quarkus.arc.impl.InterceptorInvocation.invoke(InterceptorInvocation.java:41)
        at io.quarkus.arc.impl.AroundInvokeInvocationContext.perform(AroundInvokeInvocationContext.java:41)
        at io.quarkus.arc.impl.InvocationContexts.performAroundInvoke(InvocationContexts.java:32)
        at io.smallrye.graphql.cdi.producer.GraphQLProducer_Subclass.initialize(Unknown Source)
        at io.smallrye.graphql.cdi.producer.GraphQLProducer.initialize(GraphQLProducer.java:30)
        at io.smallrye.graphql.cdi.producer.GraphQLProducer_Subclass.initialize$$superforward1(Unknown Source)
        at io.smallrye.graphql.cdi.producer.GraphQLProducer_Subclass$$function$$8.apply(Unknown Source)
        at io.quarkus.arc.impl.AroundInvokeInvocationContext.proceed(AroundInvokeInvocationContext.java:54)
        at io.quarkus.arc.runtime.devconsole.InvocationInterceptor.proceed(InvocationInterceptor.java:62)
        at io.quarkus.arc.runtime.devconsole.InvocationInterceptor.monitor(InvocationInterceptor.java:51)
        at io.quarkus.arc.runtime.devconsole.InvocationInterceptor_Bean.intercept(Unknown Source)
        at io.quarkus.arc.impl.InterceptorInvocation.invoke(InterceptorInvocation.java:41)
        at io.quarkus.arc.impl.AroundInvokeInvocationContext.perform(AroundInvokeInvocationContext.java:41)
        at io.quarkus.arc.impl.InvocationContexts.performAroundInvoke(InvocationContexts.java:32)
        at io.smallrye.graphql.cdi.producer.GraphQLProducer_Subclass.initialize(Unknown Source)
        at io.smallrye.graphql.cdi.producer.GraphQLProducer.initialize(GraphQLProducer.java:25)
        at io.smallrye.graphql.cdi.producer.GraphQLProducer_Subclass.initialize$$superforward1(Unknown Source)
        at io.smallrye.graphql.cdi.producer.GraphQLProducer_Subclass$$function$$5.apply(Unknown Source)
        at io.quarkus.arc.impl.AroundInvokeInvocationContext.proceed(AroundInvokeInvocationContext.java:54)
        at io.quarkus.arc.runtime.devconsole.InvocationInterceptor.proceed(InvocationInterceptor.java:62)
        at io.quarkus.arc.runtime.devconsole.InvocationInterceptor.monitor(InvocationInterceptor.java:51)
        at io.quarkus.arc.runtime.devconsole.InvocationInterceptor_Bean.intercept(Unknown Source)
        at io.quarkus.arc.impl.InterceptorInvocation.invoke(InterceptorInvocation.java:41)
        at io.quarkus.arc.impl.AroundInvokeInvocationContext.perform(AroundInvokeInvocationContext.java:41)
        at io.quarkus.arc.impl.InvocationContexts.performAroundInvoke(InvocationContexts.java:32)
        at io.smallrye.graphql.cdi.producer.GraphQLProducer_Subclass.initialize(Unknown Source)
        at io.smallrye.graphql.cdi.producer.GraphQLProducer_ClientProxy.initialize(Unknown Source)
        at io.quarkus.smallrye.graphql.runtime.SmallRyeGraphQLRecorder.createExecutionService(SmallRyeGraphQLRecorder.java:31)
        at io.quarkus.deployment.steps.SmallRyeGraphQLProcessor$buildExecutionService259019385.deploy_1(Unknown Source)
        at io.quarkus.deployment.steps.SmallRyeGraphQLProcessor$buildExecutionService259019385.deploy(Unknown Source)
        ... 16 more
```
