### **API Resiliency Example**

Our APIs change - not because they are poorly designed, this is just the nature of development. It is important to remember that getting your API “right” is only a temporary state. This is because we cannot predict the ways in which our APIs will need to evolve over time based on ever changing requirements. This is a good problem to have! It means we are building new things, making improvements, growing!

This example highlights some of the ways we can keep our APIs resilient to necessary change!

Examples include:
- **keeping your changes backwards compatible**
    - If you need to introduce a breaking change, do so by putting the change in a new version of your API. This way, existing consumers are shielded from your change.
    
    
- **separating your data classes**
    - Because our APIs are often simply serving JSON objects, which may correspond to service-layer objects and persistence-layer objects, it may be tempting to use a single class for all of these. However, these data models change at different times for different reasons. To be able to rapidly iterate on your API request and response bodies, these classes can be separate from other data classes in your system. You can separate how you supply resources to callers from how you model those resources internally. 
    - In other words, just because you need to change your internal representation, does not necessarily mean you need to change what you are sending to your clients & vice versa.


- **the Robustness Principle: be conservative in what you send but liberal in what you accept**
    - Producers should be conservative in what they send, making themselves more flexible in what they can change without having to release a new major version.
    - Consumers should only read what they need. Be as tolerant as possible when reading data from a service. Only take the elements you need & ignore anything you don’t. This will make your code more resilient to any changes made on the producer side.