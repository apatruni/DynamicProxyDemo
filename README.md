## DynamicProxyDemoCreating Clients using the Dynamic Proxy API in Java

If you're familiar with the Proxy Pattern, you appreciate it's helpfulness in allowing you to perform actions through a Proxy Object. Say you want to check how many times a method was invoked. Or you might want to perform some basic checks before you allow a method on an object to be invoked. 

The Java Dynamic Proxy Class API allows you to do this and much more. Let's take a simple example as follows. Say you have an interface `StreetAddress` which provides a method `addressToCity()`. The method takes a street address as input and returns the city it belongs to. For the sake of simplicity, let's assume street names are unique to a city.

```java
interface StreetAddress { 
     String addressToCity(String address);
}
```

Let's say you want to preprocess the input by ensuring that it contains no special characters which might invalidate the input (such as *,!,etc). Let's also assume you want to log method invocation counts. A way to do this without a Proxy API might be as follows:

```java
class StreetAddressProxyImpl implements StreetAddress {
     Pattern p = Pattern.compile("[a-zA-Z0-9\\s]+");
     Logger logger = Logger.getLogger("StreetAddressProxyImpl");
     private static int count = 0;


     String addressToCityActualImpl(String address) {
          //actual implementation here
     }


     public String addressToCity(String address) {
          count++;
          logger.log(Level.INFO, "Called " + count + "times");
          Matcher m = p.matcher(address);
          if ( m.matches() ) {
               return addressToCityActualImpl(address);
          }
     }
}
```

Then you might use it as so: 
```java
class MainClass {
     private static void main(String[] args) {
          StreetAddress streetAddress = new StreetAddressImplProxy();
           streetAddress.addressToCity(/*some inp*/);
     }
}
```

Not very clean, is it?

Here's where you can use the Java Dynamic Proxy Class API instead. First, let's see the main components involved:
- `Proxy Class` - the class taking care of the proxying
- `Proxy Object` - instance of the Proxy Class
- `InvocationHandler` - a class you write to override `invoke()` in `java.lang.reflect.InvocationHandler`

Construct the Proxy Object using the following method [java.lang.reflect.InvocationHandler](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Proxy.html#newProxyInstance(java.lang.ClassLoader,%20java.lang.Class[],%20java.lang.reflect.InvocationHandler)): 

So let's use this instead for our previous example:
```java
class StreetAddressImpl implements StreetAddress {
    @Override
    public String addressToCity(String address) {
        // TODO Auto-generated method stub
        //logic here and return
    }
}

class OurInvocationHandler implements InvocationHandler {
     private StreetAddressImpl _streetAddressImpl = new StreetAddressImpl();
     Pattern p = Pattern.compile("[a-zA-Z0-9\\s]+");
     
     Logger logger = Logger.getLogger("InvocationHandler");
     private static int count = 0;
     
     @Override
     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
          // TODO Auto-generated method stub
     
          if (method.getName().equals("addressToCity")) {
               count ++;
               logger.log(Level.INFO, "Called " + count + "times");
               Matcher m = p.matcher((String)args[0]);
               if ( m.matches() ) {
                    return _streetAddressImpl.addressToCity((String)args[0]);
               }
           }
           return null;
      }
}
```
Another advantage of this is quickly observed. You can add more interfaces to the second parameter in `newProxyInstance()` and perform cross cutting concerns such as these on multiple interfaces [implementations]. For example, if you had another interface

```java
interface StateAddress {
     String cityToState(String city);
}
```

You can add an instance of `StreetAddressImpl` to `OurInvocationHandler`, and another `if` check for a call of `cityToState()`, and perform whatever cross cutting concerns you'd like there. The only other change needed is to add `StreetAddress.class` to the `Class[]` in `newProxyInstance()`.

This was a simple example to get us introduced to the idea of using the Dynamic Proxy API. What's an example of how my enterprise application can benefit from this, you might ask?

Check out this [PENDING] link to see an example of how to create Restful Clients in a scalable way, permitting easy additions of cross cutting concern code for your Rest Services.
