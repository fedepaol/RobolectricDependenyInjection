# RobolectricDependenyInjection
Sample project to demonstrate how to inject mock objects with robolectric

One really cool thing about robolectric 3.0 is the fact that you can override the Application object declared in your manifest with a custom one (which can inherit from your application's one).

If you are using dagger (or dagger 2) and you are using the application as the source of dependency injection for your classes, this allow to easily replace your injected objects with mocks. You can even choose which mocks inject in the setup phase of your tests.

More details can be found in [this blogpost](http://fedepaol.github.io/blog/2015/09/05/mocking-with-robolectric-and-dagger-2/)

