# SpringMVC Demo CQRS App With Axon Framework using Gradle

This is a demo Spring MVC using Axon Framework with Gradle. The demo is a typical Basket domain where products are added to a basket, can be removed from the basket and an invariant of the basket states that a basket can only allow 3 products (crazy, ey?!).

A projection interprets the events dispatched by the domain into a CustomersBasket ReadModel in ElasticSearch.

The domain was written with TDD in mind and is complete with functional testing using MockMVC.




