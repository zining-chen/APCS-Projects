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

There is also a way to do this with an environment variable, but that is fairly complex.
