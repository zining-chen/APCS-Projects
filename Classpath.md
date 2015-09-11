#How to use Classpath

###Terminal Argument

In order to specify classes that are used outside the folder 
of the program that is being run, we use classpath.
In order to use classpath, we have to do different things for
our running and compiling of files.

Compiling Code:
```$javac -cp <path> <Filename.java>```

Running Code:
```$java -cp .:<path> <Filename.java>```


###Environment Variable

The environment variable is easier to manage at runtime, but
I never got it to work. Here are the instructions to do use
the Environment Variable:

```$CLASSPATH=<path>
set CLASSPATH```

The following should work, but never did for me.


Hopefully this tutorial on classpath helps you organize your projects
into simple folders that are more organized rather than cluttered.
