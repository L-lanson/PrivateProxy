# PrivateProxy
一、项目背景<br><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最近在工作中遇到一个问题：测试项目时，如何在不修改项目代码的情况下改变方法的逻辑？<br><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;首先想到的解决方案是动态代理。我要代理的是一个类的私有方法，所以不可能用JDK的原生动态代理方法，
因为JDK的原生动态代理是基于接口进行代理的方式，在实现接口的方法时不可以将其访问权限缩小成private。另外一种代理方式为CGLIB，CGLIB是基于继承的代理模式，
从这点看貌似可以代理被代理类的私有方法，但是实际上是CGLIB并支持代理私有方法。因此这个小项目可以满足JDK动态代理和CGLIB代理所不能实现的需求--代理私有方法。
<br>
<br>
<br>
二、项目概况<br><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本项目的核心类是ByteCodeGenerator和ClassGenerator。ByteCodeGenerator改编自JDK动态代理的核心类，用于
生成代理类的字节码，使得代理类通过继承被代理类实现，可以代理被代理类的私有方法并将访问权限设置成public。ClassGenerator通过反射调用ClassLoader的
defineClass方法，将代理类的byte[]数组转换成Class对象，进而可以实例化出代理对象。<br><br>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;通过这个项目，你可以代理被代理类的私有方法，因此可以在不改变源代码的情况下改变业务逻辑。基于这个特性，
你可以在测试项目时不修改项目的源代码，避免模拟错误情况后忘了改回来的情况。<br><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;但是这个项目还有些不足。一是在进行代理前很多安全检测都做得不到位（参照JDK动态代理前的一系列检测）；二是
每个被代理类都必须定义一个无参构造函数，否则程序会出错。<br><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;基于这些不足，建议在测试时使用本项目，而不要在生产环境中使用。

