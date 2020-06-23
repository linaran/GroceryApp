# GroceryApp
Classic Android shopping list app. It's good for testing latest Android SDK features, etc.

# What's done so far?
- Single activity with many fragments that are managed by the Navigation component.
- Fancy MVVM architecture where the fragment communicates with its ViewModel through LiveData.
- Even fancier ViewModel to UseCase to Repository communication done with coroutines and coroutines flow.
- At the end of all that fanciness is a surprisingly simple to implement Room database that only persists the grocery list at the moment.
- All of it is being injected by the very fancy, Android Hilt (Dagger injection as it was meant to be).
- Basically a single user can add items to a single grocery list which is being persisted to Room database.

# Todo
- SavedStateHandle is already present in ViewModels to handle UI persistence through activity destruction. Now I need to actually use it. (I know how, I was just rushing to test new Android stuff)
- Local User must be able to create and edit multiple grocery lists. A grocery list must have multiple grocery items. A grocery list must have multiple other users.
- Local data should be uploaded to the cloud (Firebase in mind).
- Multiple users should be able to edit the same grocery list and when the list changes everyone else should get a notification of what happened (Firebase in mind).
